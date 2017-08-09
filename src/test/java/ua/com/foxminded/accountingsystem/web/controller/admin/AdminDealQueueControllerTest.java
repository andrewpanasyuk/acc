package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ua.com.foxminded.accountingsystem.service.DealQueueService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class AdminDealQueueControllerTest {

    @Mock
    private DealQueueService dealQueueService;

    private MockMvc mockMvc;

    private AdminDealQueueController controller;

    @Before
    public void init() {
        controller = new AdminDealQueueController(dealQueueService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void queueTitleAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getQueues(model), equalTo("admin/queues"));
        assertThat(model.asMap(), hasEntry("title", "Queue"));
    }

}
