package ua.com.foxminded.accountingsystem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", initialValue = 50)
    private long id;

    @Column(name = "order_name")
    private String orderName;

    @Column(name = "open_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openDate;

    @Column(name = "queuing_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate queuingDate;

    @Column(name = "close_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate closeDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull(message = "You have to choose 'Service'")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Service service;

    @NotNull(message = "You have to set 'Status'")
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getQueuingDate() {
        return queuingDate;
    }

    public void setQueuingDate(LocalDate queuingDate) {
        this.queuingDate = queuingDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", orderName='" + orderName + '\'' +
            ", openDate=" + openDate +
            ", queuingDate=" + queuingDate +
            ", closeDate=" + closeDate +
            '}';
    }
}
