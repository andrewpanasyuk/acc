package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminEmployeeConrollerTest {


    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity())
            .build();
    }

    @Test
    public void getAllemployeesOnWeb() throws Exception {
        this.mockMvc.perform(get("/admin/employees").accept(
            MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html;charset=UTF-8"))
            .andExpect(content().string(allOf(
                containsString("Create Employee"))));

    }

//    @Test
//    public void getEmployeeOnWeb() throws Exception {
//            this.mockMvc.perform(get("/admin/employees/{1}",1).accept(
//            MediaType.parseMediaType("text/html;charset=UTF-8")))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType("text/html;charset=UTF-8"));
//
//    }

    @Test
    public void saveEmployeeOnWeb() throws Exception {
        this.mockMvc.perform(post("/admin/employees").accept(
            MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().is3xxRedirection());

    }


    @Test
    public void updateEmployeeOnWeb() throws Exception {
        this.mockMvc.perform(put("/admin/employees/{id}",1).accept(
            MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().is3xxRedirection());

    }

    @Test
    public void removeEmployeeOnWeb() throws Exception {
        this.mockMvc.perform(delete("/admin/employees/{id}",1).accept(
            MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().is3xxRedirection());
    }


}

