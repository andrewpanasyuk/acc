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
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.service.ContractService;
import ua.com.foxminded.accountingsystem.service.InvoiceService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AdminInvoiceControllerTest {

    @Mock
    private ContractService contractService;

    @Mock
    private InvoiceService invoiceService;

    private AdminInvoiceController controller;

    private MockMvc mockMvc;

    private static Invoice invoice1;
    private static Invoice invoice2;
    private static List<Invoice> invoices;

    @BeforeClass
    public static void initClass(){
        invoice1 = new Invoice();
        invoice2 = new Invoice();
        invoices = Arrays.asList(invoice1, invoice2);
    }

    @Before
    public void init(){
        controller = new AdminInvoiceController(invoiceService, contractService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(contractService.prepareIssueInvoices()).thenReturn(invoices);
    }

    @Test
    public void invoicesForPaymentAddedToController(){
        Model model = new ExtendedModelMap();
        assertThat(controller.prepareIssueInvoices(model), equalTo("admin/issueInvoices"));
        assertThat(model.asMap(), hasEntry("invoices", invoices));
    }
}
