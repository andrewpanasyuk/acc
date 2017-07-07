package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.service.EmployeeFieldService;
import ua.com.foxminded.accountingsystem.service.EmployeeService;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AdminEmployeeController.class)
public class AdminEmployeeControllerTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private EmployeeFieldService employeeFieldService;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity())
            .build();

    }

    @Test
    @WithMockUser
    public void getAllemployeesOnWeb() throws Exception {
        Employee employee1 =new Employee();
        employee1.setFirstName("jack");
        employee1.setLastName("jackson");
        employee1.setMaxClients(100);
        Employee employee2 =new Employee();
        employee2.setFirstName("bob");
        employee2.setLastName("bobson");
        employee2.setMaxClients(50);

        when(employeeService.findAll()).thenReturn(Arrays.asList(employee1,
            employee2));
        this.mockMvc.perform(get("/admin/employees").accept(
            MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(content().contentType("text/html;charset=UTF-8"))
            .andExpect(content().string(allOf(
                containsString("jack jackson"), containsString("bob bobson"))));
        verify(employeeService).findAll();


    }


    @Test
    @WithMockUser
    public void saveEmployeeOnWeb() throws Exception {
        this.mockMvc.perform(post("/admin/employees").accept(
            MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().is3xxRedirection());
        verify(employeeService).save(anyObject());

    }


    @Test
    @WithMockUser
    public void updateEmployeeOnWeb() throws Exception {
        this.mockMvc.perform(put("/admin/employees/{id}", 1).accept(
            MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().is3xxRedirection());
        verify(employeeService).save(anyObject());
    }

    @Test
    @WithMockUser
    public void removeEmployeeOnWeb() throws Exception {
        this.mockMvc.perform(delete("/admin/employees/{id}", 1).accept(
            MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().is3xxRedirection());
        verify(employeeService).delete(1);
    }

}

