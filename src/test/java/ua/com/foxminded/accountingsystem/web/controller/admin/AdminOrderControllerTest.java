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
import ua.com.foxminded.accountingsystem.model.Order;
import ua.com.foxminded.accountingsystem.service.ClientService;
import ua.com.foxminded.accountingsystem.service.OrderService;
import ua.com.foxminded.accountingsystem.service.ServiceService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AdminOrderControllerTest {
    @Mock
    private OrderService orderService;
    @Mock
    private ClientService clientService;
    @Mock
    private ServiceService serviceService;

    private AdminOrderController controller;

    private MockMvc mockMvc;
    private Model model;

    private static Order order_1;
    private static Order order_2;
    private static List<Order> orders;

    @BeforeClass
    public static void initClass(){
        order_1 = new Order();
        order_1.setId(1L);
        order_2 = new Order();
        order_2.setId(2L);
        orders = new ArrayList<>();
        orders.add(order_1);
        orders.add(order_2);
    }

    @Before
    public void init() {
        model = new ExtendedModelMap();
        controller = new AdminOrderController(orderService, clientService, serviceService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(orderService.findAll()).thenReturn(Arrays.asList(order_1, order_2));
        when(orderService.findOne(1L)).thenReturn(order_1);
    }

    @Test
    public void ordersAddedToModel() {
        assertThat(controller.getAllOrders(model), equalTo("admin/orders"));
        assertThat(model.asMap(), hasEntry("orders", orders));
    }

    @Test
    public void ordersTitleAddedToModel() {
        assertThat(controller.getAllOrders(model), equalTo("admin/orders"));
        assertThat(model.asMap(), hasEntry("title", "Orders"));
    }

    @Test
    public void oneOrderAddedToModel() {
        assertThat(controller.getOrderById(order_1.getId(), model), equalTo("admin/order"));
        assertThat(model.asMap(), hasEntry("order", order_1));
    }

    @Test
    public void userTitleAddedToModel() {
        assertThat(controller.getOrderById(order_1.getId(), model), equalTo("admin/order"));
        assertThat(model.asMap(), hasEntry("title", "Order: " + order_1.getId()));
    }

}
