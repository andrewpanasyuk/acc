package ua.com.foxminded.accountingsystem.model;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
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

@Entity
@Table(name = "personal_account_money_transfer")
@Audited
public class PersonalAccountMoneyTransfer extends AbstractAuditEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_account_transfer_sequence")
    @SequenceGenerator(name = "personal_account_transfer_sequence", sequenceName = "personal_account_transfer_sequence")
    private Long id;

    @Column(name = "transfer_type")
    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_account_id")
    private PersonalAccount personalAccount;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
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

        PersonalAccountMoneyTransfer that = (PersonalAccountMoneyTransfer) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (transferType != that.transferType) return false;
        if (personalAccount != null ? !personalAccount.equals(that.personalAccount) : that.personalAccount != null)
            return false;
        return money != null ? money.equals(that.money) : that.money == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (transferType != null ? transferType.hashCode() : 0);
        result = 31 * result + (personalAccount != null ? personalAccount.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonalAccountMoneyTransfer{" +
            "id=" + id +
            ", transferType=" + transferType +
            ", personalAccount=" + personalAccount +
            ", money=" + money +
            ", description='" + description + '\'' +
            '}';
    }
}
