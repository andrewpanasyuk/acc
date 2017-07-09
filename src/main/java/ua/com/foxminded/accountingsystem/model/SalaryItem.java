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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "salary_item")
public class SalaryItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salary_item_sequence")
    @SequenceGenerator(name = "salary_item_sequence", sequenceName = "salary_item_sequence", initialValue = 50)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    private Invoice invoice;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Money employeePayment;

    @Column(name = "date_from")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Money getEmployeePayment() {
        return employeePayment;
    }

    public void setEmployeePayment(Money employeePayment) {
        this.employeePayment = employeePayment;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
