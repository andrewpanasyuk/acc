package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminUserControllerSystemTest {

    @Autowired
    private AdminUserController adminUserController;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Ignore
    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void userLinksAvailableOnUsersPage() throws Exception {
        this.mockMvc.perform(get("/api/users").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html;charset=UTF-8"))
            .andExpect(content().string(allOf(
                containsString("<a href=\"/admin/users/admin\">admin</a>"),
                containsString("<a href=\"/admin/users/user\">user</a>")))
            );
    }

    @Test
    @WithMockUser(authorities = {"ADMIN"})
    public void userLinksAvailableOnUsersPageAjax() throws Exception {
        this.mockMvc.perform(get("/api/users").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(content().string(allOf(
                containsString("\"username\":\"user\""),
                containsString("\"username\":\"admin\"")))
            );
    }


    @Test
    @WithMockUser(authorities = {"USER"})
    public void accessDeniedForUserAuthority() throws Exception {
        this.mockMvc.perform(get("/admin/users").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isForbidden());
    }

    @Test
    public void accessDeniedForAnonymousUser() throws Exception {
        this.mockMvc.perform(get("/admin/users").accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(redirectedUrl("http://localhost/"));
    }
}
