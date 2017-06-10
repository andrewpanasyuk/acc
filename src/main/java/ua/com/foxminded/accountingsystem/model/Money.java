package ua.com.foxminded.accountingsystem.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    private long id;

    @Column(name = "price")
    private int price;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

        Money money = (Money) o;

        if (id != money.id) return false;
        if (price != money.price) return false;
        return currency == money.currency;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + price;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Money{" +
            "id=" + id +
            ", price=" + price +
            ", currency=" + currency +
            '}';
    }
}
