package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.foxminded.accountingsystem.service.UserService;
import ua.com.foxminded.accountingsystem.util.GeneratorUtil;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminUserControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private UserService userService;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void addUserWithValidParameters() throws Exception {
        mockMvc.perform(post("/admin/users")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", GeneratorUtil.generateStringWithLength(10))
            .param("password", GeneratorUtil.generateStringWithLength(10))
            .param("email", GeneratorUtil.generateCorrectEmail())
        )
            .andExpect(status().isOk())
            .andExpect(view().name("admin/users"))
            .andExpect(model().hasNoErrors());
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void addUserWithEmptyUsernameShouldGenerateTwoErrors() throws Exception {
        mockMvc.perform(post("/admin/users")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "")
            .param("password", GeneratorUtil.generateStringWithLength(10))
            .param("email", GeneratorUtil.generateCorrectEmail())
        )
            .andExpect(status().isOk())
            .andExpect(view().name("admin/users"))
            .andExpect(model().errorCount(2))
            .andExpect(model().attributeHasErrors("user"))
            .andExpect(model().attributeHasFieldErrors("user"));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void addUserWithShortUsernameShouldGenerateError() throws Exception {
        mockMvc.perform(post("/admin/users")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "A")
            .param("password", GeneratorUtil.generateStringWithLength(10))
            .param("email", GeneratorUtil.generateCorrectEmail())
        )
            .andExpect(status().isOk())
            .andExpect(view().name("admin/users"))
            .andExpect(model().errorCount(1))
            .andExpect(model().attributeHasErrors("user"))
            .andExpect(model().attributeHasFieldErrors("user"));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void addUserWithLongUsernameShouldGenerateError() throws Exception {
        mockMvc.perform(post("/admin/users")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", GeneratorUtil.generateStringWithLength(31))
            .param("password", GeneratorUtil.generateStringWithLength(10))
            .param("email", GeneratorUtil.generateCorrectEmail())
        )
            .andExpect(status().isOk())
            .andExpect(view().name("admin/users"))
            .andExpect(model().errorCount(1))
            .andExpect(model().attributeHasErrors("user"))
            .andExpect(model().attributeHasFieldErrors("user"));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void addUserWithShortPasswordShouldGenerateError() throws Exception {
        mockMvc.perform(post("/admin/users")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", GeneratorUtil.generateStringWithLength(10))
            .param("password", GeneratorUtil.generateStringWithLength(7))
            .param("email", GeneratorUtil.generateCorrectEmail())
        )
            .andExpect(status().isOk())
            .andExpect(view().name("admin/users"))
            .andExpect(model().errorCount(1))
            .andExpect(model().attributeHasErrors("user"))
            .andExpect(model().attributeHasFieldErrors("user"));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void addUserWithEmptyPasswordShouldGenerateError() throws Exception {
        mockMvc.perform(post("/admin/users")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", GeneratorUtil.generateStringWithLength(10))
            .param("password", "")
            .param("email", GeneratorUtil.generateCorrectEmail())
        )
            .andExpect(status().isOk())
            .andExpect(view().name("admin/users"))
            .andExpect(model().errorCount(2))
            .andExpect(model().attributeHasErrors("user"))
            .andExpect(model().attributeHasFieldErrors("user"));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void addUserWithWrongEmailShouldGenerateError() throws Exception {
        mockMvc.perform(post("/admin/users")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", GeneratorUtil.generateStringWithLength(10))
            .param("password", GeneratorUtil.generateStringWithLength(8))
            .param("email", GeneratorUtil.generateIncorrectEmail())
        )
            .andExpect(status().isOk())
            .andExpect(view().name("admin/users"))
            .andExpect(model().errorCount(1))
            .andExpect(model().attributeHasErrors("user"))
            .andExpect(model().attributeHasFieldErrors("user"));
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void addUserWithBlankEmailShouldGenerateError() throws Exception {
        mockMvc.perform(post("/admin/users")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", GeneratorUtil.generateStringWithLength(10))
            .param("password", GeneratorUtil.generateStringWithLength(8))
            .param("email", "")
        )
            .andExpect(status().isOk())
            .andExpect(view().name("admin/users"))
            .andExpect(model().errorCount(1))
            .andExpect(model().attributeHasErrors("user"))
            .andExpect(model().attributeHasFieldErrors("user"));
    }
}
