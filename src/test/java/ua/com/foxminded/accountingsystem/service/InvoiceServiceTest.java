package ua.com.foxminded.accountingsystem.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.accountingsystem.model.Contract;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Payment;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.InvoiceRepository;
import ua.com.foxminded.accountingsystem.repository.PaymentRepository;

import java.time.LocalDate;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(profiles = {"test", "test-repository"})
public class InvoiceServiceTest {

    @Autowired
    protected InvoiceRepository invoiceRepository;

    @Autowired
    protected PaymentRepository paymentRepository;

    @Autowired
    protected ContractRepository contractRepository;

    @Test
    public void findAllDebtInvoicesTest() {

        Contract contract_1 = new Contract();
        contract_1.setPaymentType(PaymentType.PREPAY);
        contractRepository.save(contract_1);

        Contract contract_2 = new Contract();
        contract_2.setPaymentType(PaymentType.PREPAY);
        contractRepository.save(contract_2);

        Contract contract_3 = new Contract();
        contract_3.setPaymentType(PaymentType.POSTPAY);
        contractRepository.save(contract_3);

        Contract contract_4 = new Contract();
        contract_4.setPaymentType(PaymentType.POSTPAY);
        contractRepository.save(contract_4);

        Invoice goodPrepay = new Invoice();
        goodPrepay.setContract(contract_1);
        goodPrepay.setCreationDate(LocalDate.now().minusMonths(2));
        goodPrepay.setPrice(new Money());
        goodPrepay.setEmployeePaid(false);
        goodPrepay.setPaymentPeriodFrom(LocalDate.now().minusDays(2));
        goodPrepay.setPaymentPeriodTo(LocalDate.now().plusMonths(1).minusDays(2));
        invoiceRepository.save(goodPrepay);

        Payment payment_1 = new Payment();
        payment_1.setInvoice(goodPrepay);
        paymentRepository.save(payment_1);

        Invoice badPrepay = new Invoice();
        badPrepay.setContract(contract_2);
        badPrepay.setCreationDate(LocalDate.now().minusMonths(2));
        badPrepay.setPrice(new Money());
        badPrepay.setEmployeePaid(false);
        badPrepay.setPaymentPeriodFrom(LocalDate.now().minusDays(2));
        badPrepay.setPaymentPeriodTo(LocalDate.now().plusMonths(1).minusDays(2));
        invoiceRepository.save(badPrepay);

        Invoice goodPostpay = new Invoice();
        goodPostpay.setContract(contract_3);
        goodPostpay.setCreationDate(LocalDate.now().minusMonths(2));
        goodPostpay.setPrice(new Money());
        goodPostpay.setEmployeePaid(false);
        goodPostpay.setPaymentPeriodFrom(LocalDate.now().minusMonths(1).minusDays(2));
        goodPostpay.setPaymentPeriodTo(LocalDate.now().minusDays(2));
        invoiceRepository.save(goodPostpay);

        Payment payment_2 = new Payment();
        payment_2.setInvoice(goodPostpay);
        paymentRepository.save(payment_2);

        Invoice badPostpay = new Invoice();
        badPostpay.setContract(contract_4);
        badPostpay.setCreationDate(LocalDate.now().minusMonths(2));
        badPostpay.setPrice(new Money());
        badPostpay.setEmployeePaid(false);
        badPostpay.setPaymentPeriodFrom(LocalDate.now().minusMonths(1).minusDays(2));
        badPostpay.setPaymentPeriodTo(LocalDate.now().minusDays(2));
        invoiceRepository.save(badPostpay);

        assertEquals(2, invoiceRepository.findDebtInvoices().size());
        assertThat(invoiceRepository.findDebtInvoices(), hasItems(badPostpay, badPrepay));
    }

}
