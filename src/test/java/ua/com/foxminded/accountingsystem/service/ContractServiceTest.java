package ua.com.foxminded.accountingsystem.service;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import ua.com.foxminded.accountingsystem.model.Consultancy;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.Employee;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.service.exception.ActiveContractExistsException;
import ua.com.foxminded.accountingsystem.service.exception.ContractCreatingException;
import ua.com.foxminded.accountingsystem.service.serviceJPA.ContractServiceJPA;
import ua.com.foxminded.accountingsystem.service.serviceJPA.DealServiceJPA;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContractServiceTest {

    private static final int signalPeriod = 3;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    private static Contract trialContract;
    private static Contract paidContract;
    private static Deal deal;
    private static Employee employee;
    private static Contract testExceptionContract;

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

        prepayContract.setPaymentDate(payDate);
        postpayContract.setPaymentDate(payDate);

        prepayInvoice.setContract(prepayContract);
        postpayInvoice.setContract(postpayContract);

        prepayInvoice.setCreationDate(billDate);
        postpayInvoice.setCreationDate(billDate);

        prepayInvoice.setPaymentPeriodFrom(payDate);
        prepayInvoice.setPaymentPeriodTo(payDate.plusMonths(1).minusDays(1));
        postpayInvoice.setPaymentPeriodFrom(payDate.minusMonths(1));
        postpayInvoice.setPaymentPeriodTo(payDate);

        prepayInvoice.setPrice(new Money());
        postpayInvoice.setPrice(new Money());

        prepayInvoice.setEmployeePaid(false);
        postpayInvoice.setEmployeePaid(false);

        createdInvoices = Arrays.asList(prepayInvoice, postpayInvoice);
        contractsForPayment = Arrays.asList(prepayContract, postpayContract);

        deal = new Deal();
        deal.setId(1L);
        deal.setConsultancy(new Consultancy());
        deal.setStatus(DealStatus.ACTIVE);

        employee = new Employee();
        employee.setFirstName("Jack");
        employee.setFirstName("Nikolson");

        trialContract = new Contract();

        Money trialEmployeeRate = new Money();
        trialEmployeeRate.setAmount(1000L);
        trialEmployeeRate.setCurrency(Currency.UAH);

        Money trialPrice = new Money();
        trialPrice.setAmount(2000L);
        trialPrice.setCurrency(Currency.UAH);

        trialContract.setContractDate(LocalDate.now());
        trialContract.setDeal(deal);
        trialContract.setPrice(trialPrice);
        trialContract.setPaymentType(PaymentType.TRIAL);
        trialContract.setEmployee(employee);
        trialContract.setEmployeeRate(trialEmployeeRate);
        trialContract.setPaymentDate(LocalDate.now());

        paidContract = new Contract();

        Money paidEmployeeRate = new Money();
        paidEmployeeRate.setAmount(1000L);
        paidEmployeeRate.setCurrency(Currency.UAH);

        Money paidPrice = new Money();
        paidPrice.setAmount(2000L);
        paidPrice.setCurrency(Currency.UAH);

        paidContract.setContractDate(LocalDate.now());
        paidContract.setDeal(deal);
        paidContract.setPrice(paidPrice);
        paidContract.setPaymentType(PaymentType.TRIAL);
        paidContract.setEmployee(employee);
        paidContract.setEmployeeRate(paidEmployeeRate);
        paidContract.setPaymentDate(LocalDate.now());

        testExceptionContract  = new Contract();
        testExceptionContract.setDeal(deal);
        testExceptionContract.setPaymentType(PaymentType.POSTPAY);
    }

    @Test
    public void invoicesHaveCorrectPaymentPeriod(){
        ReflectionTestUtils.setField(contractService, "signalPeriod", signalPeriod);
        when(contractRepository.findContractsForInvoicesCreation(payDate.getDayOfMonth())).thenReturn(contractsForPayment);

        assertThat(contractService.prepareIssueInvoices(), is(createdInvoices));
    }

    @Test
    public void checkIsPaidContractSimilarToTrialContract(){
        when(contractService.findActiveContractByDeal(deal)).thenReturn(trialContract);

        Contract resultContract = contractService.prepareNewPaidContractFromTrialByDeal(deal);

        assertEquals(resultContract.getContractDate(), paidContract.getContractDate());
        assertEquals(resultContract.getDeal(), paidContract.getDeal());
        assertEquals(resultContract.getPrice(), paidContract.getPrice());
        assertNotEquals(resultContract.getPaymentType(), paidContract.getPaymentType());
        assertEquals(resultContract.getEmployee(), paidContract.getEmployee());
        assertEquals(resultContract.getEmployeeRate(), paidContract.getEmployeeRate());
        assertEquals(resultContract.getPaymentDate(), paidContract.getPaymentDate());
    }

    @Test
    public void checkExpectedExceptionWhenNoActiveTrialContract(){
        testExceptionContract.setPaymentType(PaymentType.POSTPAY);
        when(contractService.findActiveContractByDeal(deal)).thenReturn(testExceptionContract);

        expectedException.expect(ContractCreatingException.class);
        Contract resultContract = contractService.prepareNewPaidContractFromTrialByDeal(deal);
    }

    @Test
    public void checkExpectedExceptionWhenActiveContractAlreadyExists(){
        testExceptionContract.setPaymentType(PaymentType.POSTPAY);
        when(contractService.existsActiveContractByDeal(deal)).thenReturn(true);
        when(contractService.findActiveContractByDeal(deal)).thenReturn(testExceptionContract);

        expectedException.expect(ActiveContractExistsException.class);
        Contract resultContract = contractService.saveByUser(testExceptionContract);
    }
}
