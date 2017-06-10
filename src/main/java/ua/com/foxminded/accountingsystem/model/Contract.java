package ua.com.foxminded.accountingsystem.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contracts")
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "contract_date")
    private LocalDate contractDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Money money;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "open_contract")
    private LocalDate openContract;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Money employeeRate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_payment")
    private LocalDate datePayment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Invoice> invoices;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "close_contract")
    private LocalDate closeContract;

    @Column(name = "close_type")
    @Enumerated(EnumType.STRING)
    private CloseType closeType;

    @Column(name = "closing_description")
    private String closingDescription;

    public Contract() {
    }

    public Contract(LocalDate contractDate, Order order, Money money, PaymentType paymentType, LocalDate openContract, Employee employee, Money employeeRate, LocalDate datePayment, List<Invoice> invoices, LocalDate closeContract, CloseType closeType, String closingDescription) {
        this.contractDate = contractDate;
        this.order = order;
        this.money = money;
        this.paymentType = paymentType;
        this.openContract = openContract;
        this.employee = employee;
        this.employeeRate = employeeRate;
        this.datePayment = datePayment;
        this.invoices = invoices;
        this.closeContract = closeContract;
        this.closeType = closeType;
        this.closingDescription = closingDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDate getOpenContract() {
        return openContract;
    }

    public void setOpenContract(LocalDate openContract) {
        this.openContract = openContract;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Money getEmployeeRate() {
        return employeeRate;
    }

    public void setEmployeeRate(Money employeeRate) {
        this.employeeRate = employeeRate;
    }

    public LocalDate getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(LocalDate datePayment) {
        this.datePayment = datePayment;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public LocalDate getCloseContract() {
        return closeContract;
    }

    public void setCloseContract(LocalDate closeContract) {
        this.closeContract = closeContract;
    }

    public CloseType getCloseType() {
        return closeType;
    }

    public void setCloseType(CloseType closeType) {
        this.closeType = closeType;
    }

    public String getClosingDescription() {
        return closingDescription;
    }

    public void setClosingDescription(String closingDescription) {
        this.closingDescription = closingDescription;
    }

}
