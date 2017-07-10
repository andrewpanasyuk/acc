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
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.EmployeeService;
import ua.com.foxminded.accountingsystem.service.OrderQueueService;
import ua.com.foxminded.accountingsystem.service.OrderService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AdminContractControllerTest {

    @Mock
    private ContractService contractService;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private OrderService orderService;
    @Mock
    private OrderQueueService orderQueueService;

    private AdminContractController controller;

    private MockMvc mockMvc;

    private static Contract contract1;
    private static Contract contract2;
    private static Invoice invoice1;
    private static Invoice invoice2;

    private static List<Contract> contracts;
    private static List<Invoice> invoices;

    @BeforeClass
    public static void initClass(){
        contract1 = new Contract();
        contract2 = new Contract();

        invoice1 = new Invoice();
        invoice2 = new Invoice();

        contracts = new ArrayList<>();
        contracts.add(contract1);
        contracts.add(contract2);

        invoices = Arrays.asList(invoice1, invoice2);
    }

    @Before
    public void init() {
        controller = new AdminContractController(contractService, employeeService, orderService, orderQueueService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(contractService.findAll()).thenReturn(contracts);
        when(contractService.findOne(1L)).thenReturn(contract1);
        when(contractService.prepareInvoicesForPayment())
            .thenReturn(invoices);
    }

    @Test
    public void contractsAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getAllContracts(model), equalTo("admin/contracts"));
        assertThat(model.asMap(), hasEntry("contracts", contracts));
    }

    @Test
    public void oneContractAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getContract(1L, model), equalTo("admin/contract"));
        assertThat(model.asMap(), hasEntry("contract", contract1));
    }

    @Test
    public void invoicesForPaymentAddedToController(){
        Model model = new ExtendedModelMap();
        assertThat(controller.invoicesForPayment(model), equalTo("admin/invoicesForPay"));
        assertThat(model.asMap(), hasEntry("invoices", invoices));
    }

}
