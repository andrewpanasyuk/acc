package ua.com.foxminded.accountingsystem.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "personal_account")
public class PersonalAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_account_sequence")
    @SequenceGenerator(name = "personal_account_sequence", sequenceName = "personal_account_sequence", initialValue = 50)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Money> money;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Money> getMoney() {
        return money;
    }

    public void setMoney(Set<Money> money) {
        this.money = money;
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return 31;
        }
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalAccount personalAccount = (PersonalAccount) o;
        if (id != null && !id.equals(personalAccount.id))  return false;
        return true;
    }
}
