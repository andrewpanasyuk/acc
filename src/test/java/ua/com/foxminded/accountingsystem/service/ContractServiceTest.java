package ua.com.foxminded.accountingsystem.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.service.serviceJPA.ContractServiceJPA;
import ua.com.foxminded.accountingsystem.service.serviceJPA.DealServiceJPA;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContractServiceTest {

    private static final int signalPeriod = 3;

    @Mock
    private static ContractRepository contractRepository;

    @Mock
    private static DealServiceJPA dealService;

    @InjectMocks
    private static ContractServiceJPA contractService;

    private static Invoice prepayInvoice;
    private static Invoice postpayInvoice;
    private static Contract prepayContract;
    private static Contract postpayContract;
    private static List<Invoice> createdInvoices;
    private static List<Contract> contractsForPayment;
    private static LocalDate billDate;
    private static LocalDate payDate;

    @BeforeClass
    public static void init(){

        billDate = LocalDate.now();
        payDate = billDate.plusDays(signalPeriod);

        contractRepository = mock(ContractRepository.class);
        dealService = mock(DealServiceJPA.class);

        prepayInvoice = new Invoice();
        postpayInvoice = new Invoice();

        prepayContract = new Contract();
        postpayContract = new Contract();

        prepayContract.setId(2L);
        postpayContract.setId(3L);

        prepayContract.setPrice(new Money());
        postpayContract.setPrice(new Money());

        prepayContract.setPaymentType(PaymentType.PREPAY);
        postpayContract.setPaymentType(PaymentType.POSTPAY);

        prepayInvoice.setContract(prepayContract);
        postpayInvoice.setContract(postpayContract);

        prepayInvoice.setCreationDate(billDate);
        postpayInvoice.setCreationDate(billDate);

        prepayInvoice.setPaymentPeriodFrom(payDate);
        prepayInvoice.setPaymentPeriodTo(payDate.plusMonths(1));
        postpayInvoice.setPaymentPeriodFrom(payDate.minusMonths(1));
        postpayInvoice.setPaymentPeriodTo(payDate);

        prepayInvoice.setPrice(new Money());
        postpayInvoice.setPrice(new Money());

        prepayInvoice.setEmployeePaid(false);
        postpayInvoice.setEmployeePaid(false);

        createdInvoices = Arrays.asList(prepayInvoice, postpayInvoice);
        contractsForPayment = Arrays.asList(prepayContract, postpayContract);
    }

    @Test
    public void invoicesHaveCorrectPaymentPeriod(){
        ReflectionTestUtils.setField(contractService, "signalPeriod", signalPeriod);
        when(contractRepository.findContractsForInvoicesCreation(payDate.getDayOfMonth(), billDate)).thenReturn(contractsForPayment);

        assertThat(contractService.prepareIssueInvoices(), is(createdInvoices));
    }
}
