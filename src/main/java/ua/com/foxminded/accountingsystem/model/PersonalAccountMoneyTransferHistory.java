package ua.com.foxminded.accountingsystem.model;

import org.hibernate.validator.constraints.NotBlank;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    private Long id;

    @Column(name = "transfer_type")
    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private PersonalAccount personalAccount;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "money_id")
    private Money money = new Money();

    @NotBlank(message = "It is required field")
    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalAccount getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonalAccountMoneyTransferHistory that = (PersonalAccountMoneyTransferHistory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (transferType != that.transferType) return false;
        if (personalAccount != null ? !personalAccount.equals(that.personalAccount) : that.personalAccount != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        return money != null ? money.equals(that.money) : that.money == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (transferType != null ? transferType.hashCode() : 0);
        result = 31 * result + (personalAccount != null ? personalAccount.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        return result;
    }
}
