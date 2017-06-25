package ua.com.foxminded.accountingsystem.web;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.ServiceService;
import ua.com.foxminded.accountingsystem.web.controller.admin.AdminServiceController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ServiceControllerTest {

    @Mock
    private ServiceService serviceService;

    private AdminServiceController controller;

    private MockMvc mockMvc;

    private static Service service1;
    private static Service service2;
    private static List<Service> services;

    @BeforeClass
    public static void initClass(){
        service1 = new Service();
        service1.setName("test1");
        service1.setDescription("test1 description");

        service2 = new Service();
        service2.setName("test2");
        service2.setDescription("test2 description");

        services = new ArrayList<>();
        services.add(service1);
        services.add(service2);
    }

    @Before
    public void init() {
        controller = new AdminServiceController(serviceService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(serviceService.findAll()).thenReturn(Arrays.asList(service1, service2));
        when(serviceService.findOne(1L)).thenReturn(service1);

    }

    @Test
    public void servicesAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getAllServices(model), equalTo("admin/services"));
        assertThat(model.asMap(), hasEntry("services", services));
    }

    @Test
    public void oneServiceAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getOneService(1, model), equalTo("admin/service"));
        assertThat(model.asMap(), hasEntry("service", service1));
    }

}
