package ua.com.foxminded.accountingsystem.model;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "order_queue")
public class OrderQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "queue_sequence")
    @SequenceGenerator(name = "queue_sequence", sequenceName = "queue_sequence", initialValue = 50)
    private Long id;

    @Column(name = "queuing_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate queuingDate;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getQueuingDate() {
        return queuingDate;
    }

    public void setQueuingDate(LocalDate queuingDate) {
        this.queuingDate = queuingDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderQueue orderQueue = (OrderQueue) o;
        return id.equals(orderQueue.id);
    }

    @Override
    public int hashCode() {
        if(id==null){
            return 31;
        }
        return id.hashCode();
    }
}
