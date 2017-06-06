package ua.com.foxminded.accountingsystem.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_name", nullable = false)
    private String order_name;

    @Column(name = "open_date")
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate openDate;
    //
    @Column(name = "queuing_date")
    private LocalDate queuingDate;
    //
    @Column(name = "close_date")
    private LocalDate closeDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

////    private Service service;
//    @Enumerated(EnumType.STRING)
//    private OrderStatus status;
//
//    public Order(String order_name) {
//        this.order_name = order_name;
//    }
//
//    public Order(){
//
//    }


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

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

//

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

//    public OrderStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(OrderStatus status) {
//        this.status = status;
//    }


    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", order_name='" + order_name + '\'' +
            ", openDate=" + openDate +
            ", queuingDate=" + queuingDate +
            ", closeDate=" + closeDate +
            '}';
    }
}
