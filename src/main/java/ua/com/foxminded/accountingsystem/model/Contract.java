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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_sequence")
    @SequenceGenerator(name = "contract_sequence", sequenceName = "contract_sequence", initialValue = 50)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "contract_date")
    private LocalDate contractDate;

    @NotNull(message = "It's a required field.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull(message = "It's a required field.")
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "price_id")
    private Money price;

    @NotNull(message = "It's a required field.")
    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "open_date")
    private LocalDate openDate;

    @NotNull(message = "It's a required field.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull(message = "It's a required field.")
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "employee_rate_id")
    private Money employeeRate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @OneToMany(mappedBy = "contract", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Invoice> invoices;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "close_date")
    private LocalDate closeDate;

    @Column(name = "close_type")
    @Enumerated(EnumType.STRING)
    private CloseType closeType;

    @Column(name = "closing_description")
    private String closingDescription;


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

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
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

    @Override
    public String toString() {
        return "Contract{" +
            "id=" + id +
            ", contractDate=" + contractDate +
            ", orders=" + order +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        return id != null ? id.equals(contract.id) : contract.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
