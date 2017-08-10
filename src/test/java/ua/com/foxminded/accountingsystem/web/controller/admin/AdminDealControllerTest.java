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
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.service.ClientService;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.InvoiceService;
import ua.com.foxminded.accountingsystem.service.DealService;
import ua.com.foxminded.accountingsystem.service.SalaryItemService;
import ua.com.foxminded.accountingsystem.service.ConsultancyService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AdminDealControllerTest {
    @Mock
    private DealService dealService;
    @Mock
    private ClientService clientService;
    @Mock
    private ConsultancyService consultancyService;
    @Mock
    private ContractService contractService;
    @Mock
    private SalaryItemService salaryItemService;
    @Mock
    private InvoiceService invoiceService;


    private AdminDealController controller;

    private MockMvc mockMvc;
    private Model model;

    private static Deal deal_1;
    private static Deal deal_2;
    private static List<Deal> deals;

    @BeforeClass
    public static void initClass(){
        deal_1 = new Deal();
        deal_1.setId(1L);
        deal_2 = new Deal();
        deal_2.setId(2L);
        deals = new ArrayList<>();
        deals.add(deal_1);
        deals.add(deal_2);
    }

    @Before
    public void init() {
        model = new ExtendedModelMap();
        controller = new AdminDealController(dealService, clientService, consultancyService, contractService, salaryItemService, invoiceService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(dealService.findAll()).thenReturn(Arrays.asList(deal_1, deal_2));
        when(dealService.findOne(1L)).thenReturn(deal_1);
    }

    @Test
    public void dealsAddedToModel() {
        assertThat(controller.getAllDeals(model), equalTo("admin/deals"));
        assertThat(model.asMap(), hasEntry("deals", deals));
    }

    @Test
    public void dealsTitleAddedToModel() {
        assertThat(controller.getAllDeals(model), equalTo("admin/deals"));
        assertThat(model.asMap(), hasEntry("title", "Deals"));
    }

    @Test
    public void oneDealAddedToModel() {
        assertThat(controller.getDealById(deal_1.getId(), model), equalTo("admin/deal"));
        assertThat(model.asMap(), hasEntry("deal", deal_1));
    }

    @Test
    public void userTitleAddedToModel() {
        assertThat(controller.getDealById(deal_1.getId(), model), equalTo("admin/deal"));
        assertThat(model.asMap(), hasEntry("title", "Deal: " + deal_1.getId()));
    }

}
