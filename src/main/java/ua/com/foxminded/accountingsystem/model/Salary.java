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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salary")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salary_sequence")
    @SequenceGenerator(name = "salary_sequence", sequenceName = "salary_sequence", initialValue = 50)
    private Long id;

    @Column(name = "date_salary", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate salaryDate;

    @Column(name = "date_from", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @Column(name = "date_to", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    @JoinColumn(name = "employee_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @OneToMany(mappedBy = "salary", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<SalaryItem> salaryItems;

    @Column(name = "paid")
    private Boolean paid = false;

    @JoinColumn(name = "total_amount_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Money totalAmount;

    public void addSalaryItem(SalaryItem salaryItem){
        if (salaryItems == null){
            salaryItems = new ArrayList<>();
        }
        salaryItems.add(salaryItem);
    }

    public void removeSalaryItem(SalaryItem salaryItem){
        if (salaryItems == null){
            return;
        }
        salaryItems.remove(salaryItem);
    }

    private void calculateTotalAmount(){
        if (totalAmount == null) {
            totalAmount = new Money();
            totalAmount.setCurrency(Currency.UAH);
        }
        totalAmount.setPrice(salaryItems.stream()
            .mapToInt(salaryItem -> salaryItem.getEmployeePayment().getPrice()).sum());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(LocalDate salaryDate) {
        this.salaryDate = salaryDate;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<SalaryItem> getSalaryItems() {
        return salaryItems;
    }

    public void setSalaryItems(List<SalaryItem> salaryItems) {
        this.salaryItems = salaryItems;
        calculateTotalAmount();
    }

    public Boolean isPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }
}
