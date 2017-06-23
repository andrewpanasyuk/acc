package ua.com.foxminded.accountingsystem.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.ServiceService;
import ua.com.foxminded.accountingsystem.web.controller.admin.AdminServiceController;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ServiceControllerTest {

    @Mock
    private ServiceService serviceService;

    private AdminServiceController controller;

    private MockMvc mockMvc;

    @Before
    public void init() {
        controller = new AdminServiceController(serviceService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Service service1 = new Service();
        service1.setName("test1");
        Service service2 = new Service();
        service2.setName("test2");
        when(serviceService.findAll()).thenReturn(Arrays.asList(service1, service2));
        when(serviceService.findOne(1L)).thenReturn(service1);

    }

    @Test
    public void requestForServicesPageIsSuccessfullyProcessed() throws Exception {
        this.mockMvc.perform(get("/admin/services")
            .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("admin/services"));
    }

    @Test
    public void testForListServicesOnPage() throws Exception {
        this.mockMvc.perform(get("/admin/services")
            .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(model().attribute("services", hasSize(2)));
    }

    public void requestForServicePageIsSuccessfullyProcessed() throws Exception {
        this.mockMvc.perform(get("/admin/services/1")
            .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("admin/service"));
    }

    @Test
    public void testForServicePageWithOneService() throws Exception {
        this.mockMvc.perform(get("/admin/services/1")
            .accept(MediaType.parseMediaType("text/html;charset=UTF-8")))
            .andExpect(status().isOk())
            .andExpect(model().attribute("service", notNullValue()
            ));
    }

}
