package ua.com.foxminded.accountingsystem.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "money")
public class Money {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="money_sequence")
    @SequenceGenerator(name="money_sequence", initialValue=50)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "price")
    private Integer price = 0;
    @Column(name = "currency")
    private Currency currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money1 = (Money) o;

        if (price != money1.price) return false;
        if (id != null ? !id.equals(money1.id) : money1.id != null) return false;
        return currency == money1.currency;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Money{" +
            "price=" + price +
            ", currency=" + currency +
            '}';
    }
}
