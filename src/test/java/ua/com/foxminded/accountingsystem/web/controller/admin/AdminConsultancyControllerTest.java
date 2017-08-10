package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ua.com.foxminded.accountingsystem.model.Consultancy;
import ua.com.foxminded.accountingsystem.service.ConsultancyService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AdminConsultancyControllerTest {

    @Mock
    private ConsultancyService consultancyService;

    private AdminConsultancyController controller;

    private MockMvc mockMvc;

    private static Consultancy consultancy1;
    private static Consultancy consultancy2;
    private static List<Consultancy> consultancies;

    @BeforeClass
    public static void initClass(){
        consultancy1 = new Consultancy();
        consultancy1.setName("test1");
        consultancy1.setDescription("test1 description");

        consultancy2 = new Consultancy();
        consultancy2.setName("test2");
        consultancy2.setDescription("test2 description");

        consultancies = new ArrayList<>();
        consultancies.add(consultancy1);
        consultancies.add(consultancy2);
    }

    @Before
    public void init() {
        controller = new AdminConsultancyController(consultancyService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(consultancyService.findAll()).thenReturn(Arrays.asList(consultancy1, consultancy2));
        when(consultancyService.findOne(1L)).thenReturn(consultancy1);

    }

    @Test
    public void consultanciesAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getAllConsultancies(model), equalTo("admin/consultancies"));
        assertThat(model.asMap(), hasEntry("consultancies", consultancies));
    }

    @Test
    public void oneConsultancyAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getOneConsultancy(1, model), equalTo("admin/consultancy"));
        assertThat(model.asMap(), hasEntry("consultancy", consultancy1));
    }

}
