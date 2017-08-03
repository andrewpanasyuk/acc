package ua.com.foxminded.accountingsystem.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

/**
 * Created by andreb on 03.08.17.
 */
@Entity
@Table(name = "personal_acc_transfer_history")
public class PersonalAccountMoneyTransferHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transfer_history_sequence")
    @SequenceGenerator(name = "transfer_history_sequence", sequenceName = "transfer_history_sequence", initialValue = 50)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private PersonalAccount personalAccount;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timeStamp = LocalDateTime.now();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Money withdrawnMoney = new Money();

    @NotBlank(message = "It is required field")
    @Column(name = "description")
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonalAccount getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Money getWithdrawnMoney() {
        return withdrawnMoney;
    }

    public void setWithdrawnMoney(Money withdrawnMoney) {
        this.withdrawnMoney = withdrawnMoney;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalAccountMoneyTransferHistory that = (PersonalAccountMoneyTransferHistory) o;

        if (id != that.id) return false;
        if (personalAccount != null ? !personalAccount.equals(that.personalAccount) : that.personalAccount != null)
            return false;
        if (timeStamp != null ? !timeStamp.equals(that.timeStamp) : that.timeStamp != null) return false;
        return withdrawnMoney != null ? withdrawnMoney.equals(that.withdrawnMoney) : that.withdrawnMoney == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (personalAccount != null ? personalAccount.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (withdrawnMoney != null ? withdrawnMoney.hashCode() : 0);
        return result;
    }
}
