package ua.com.foxminded.accountingsystem.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "payment")
public class Payment extends AbstractAuditEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
    @SequenceGenerator(name = "payment_sequence", sequenceName = "payment_sequence", initialValue = 50)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Column(name = "date_paid")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePaid;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Money sum = new Money();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public LocalDate getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDate datePaid) {
        this.datePaid = datePaid;
    }

    public Money getSum() {
        return sum;
    }

    public void setSum(Money sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != null ? !id.equals(payment.id) : payment.id != null) return false;
        if (invoice != null ? !invoice.equals(payment.invoice) : payment.invoice != null) return false;
        if (datePaid != null ? !datePaid.equals(payment.datePaid) : payment.datePaid != null) return false;
        return sum != null ? sum.equals(payment.sum) : payment.sum == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Payment{" +
            "id=" + id +
            ", invoice=" + invoice +
            ", datePaid=" + datePaid +
            ", sum=" + sum +
            '}';
    }
}
