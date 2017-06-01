package ua.com.foxminded.accountingsystem.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "order_name", nullable = false)
    private String order_name;
    @Column(name = "open_date")
    @Temporal(TemporalType.DATE)
    private Date openDate;
    @Column(name = "queuing_date")
    @Temporal(TemporalType.DATE)
    private Date queuingDate;
    @Column(name = "close_date")
    @Temporal(TemporalType.DATE)
    private Date closeDate;
    //    private Client client;
//    private Service service;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
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
    public Date getOpenDate() {
        return openDate;
    }
    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
    public Date getQueuingDate() {
        return queuingDate;
    }
    public void setQueuingDate(Date queuingDate) {
        this.queuingDate = queuingDate;
    }
    public Date getCloseDate() {
        return closeDate;
    }
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return id + " - " + order_name;
    }
}
