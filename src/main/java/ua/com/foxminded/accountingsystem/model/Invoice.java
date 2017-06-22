package ua.com.foxminded.accountingsystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_sequence")
    @SequenceGenerator(name = "invoice_sequence", sequenceName = "invoice_sequence", initialValue = 50)
    private Long id;

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Column(name = "period_from")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentPeriodFrom;

    @Column(name = "period_to")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentPeriodTo;

    @Column(name = "employee_payment")
    private Boolean isEmployeePayment;


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

    public Boolean getIsEmployeePayment() {
        return isEmployeePayment;
    }

    public void setIsEmployeePayment(Boolean isEmployeePayment) {
        this.isEmployeePayment = isEmployeePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return (this.creationDate.equals(((Invoice) o).creationDate)) &&
            (this.paymentPeriodFrom.equals(((Invoice) o).paymentPeriodFrom)) &&
            (this.paymentPeriodTo.equals(((Invoice) o).paymentPeriodTo)) &&
            (this.isEmployeePayment.equals(((Invoice) o).isEmployeePayment));
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
            ", paymentPeriodFrom=" + paymentPeriodFrom +
            ", paymentPeriodTo=" + paymentPeriodTo +
            ", isEmployeePayment=" + isEmployeePayment +
            '}';
    }
}
