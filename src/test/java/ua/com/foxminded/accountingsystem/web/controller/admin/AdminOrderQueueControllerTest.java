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
import ua.com.foxminded.accountingsystem.service.OrderQueueService;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AdminOrderQueueControllerTest {

    @Mock
    private OrderQueueService orderQueueService;

    private MockMvc mockMvc;

    private AdminOrderQueueController controller;

    @Before
    public void init() {
        controller = new AdminOrderQueueController(orderQueueService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void queueTitleAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getQueues(model), equalTo("admin/queues"));
        assertThat(model.asMap(), hasEntry("title", "Queue"));
    }

}
