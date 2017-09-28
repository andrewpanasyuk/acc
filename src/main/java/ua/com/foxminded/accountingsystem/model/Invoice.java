package ua.com.foxminded.accountingsystem.model;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "invoice")
@Audited
public class Invoice extends AbstractAuditEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_sequence")
    @SequenceGenerator(name = "invoice_sequence", sequenceName = "invoice_sequence")
    private Long id;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    @Column(name = "period_from")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate paymentPeriodFrom;

    @Column(name = "period_to")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate paymentPeriodTo;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "price_id")
    private Money price;

    @OneToOne(mappedBy = "invoice", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Payment payment;

    @Column(name = "employee_paid")
    private Boolean employeePaid = false;

    public Invoice(){

    }

    public Invoice(LocalDate creationDate, Contract contract, LocalDate paymentPeriodFrom, LocalDate paymentPeriodTo, Money price) {
        this.creationDate = creationDate;
        this.contract = contract;
        this.paymentPeriodFrom = paymentPeriodFrom;
        this.paymentPeriodTo = paymentPeriodTo;
        this.price = price;
    }

    public void addPayment(Payment payment) {
        this.payment = payment;
        payment.setInvoice(this);
    }

    public void removePayment() {
        if (payment != null) {
            payment.setInvoice(null);
        }
        payment = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public LocalDate getPaymentPeriodFrom() {
        return paymentPeriodFrom;
    }

    public void setPaymentPeriodFrom(LocalDate paymentPeriodFrom) {
        this.paymentPeriodFrom = paymentPeriodFrom;
    }

    public LocalDate getPaymentPeriodTo() {
        return paymentPeriodTo;
    }

    public void setPaymentPeriodTo(LocalDate paymentPeriodTo) {
        this.paymentPeriodTo = paymentPeriodTo;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Boolean getEmployeePaid() {
        return employeePaid;
    }

    public void setEmployeePaid(Boolean employeePaid) {
        this.employeePaid = employeePaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return (this.creationDate.equals(invoice.creationDate)) &&
            (this.contract.equals(invoice.contract)) &&
            (this.paymentPeriodFrom.equals(invoice.paymentPeriodFrom)) &&
            (this.paymentPeriodTo.equals(invoice.paymentPeriodTo)) &&
            (this.price.equals(invoice.price)) &&
            (this.employeePaid.equals(invoice.employeePaid));
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return 31;
        }
        return id.hashCode();

    }

    @Override
    public String toString() {
        return "Invoice{" +
            "id=" + id +
            ", creationDate=" + creationDate +
            ", contract=" + contract +
            ", paymentPeriodFrom=" + paymentPeriodFrom +
            ", paymentPeriodTo=" + paymentPeriodTo +
            ", price=" + price +
            ", employeePaid=" + employeePaid +
            '}';
    }
}
