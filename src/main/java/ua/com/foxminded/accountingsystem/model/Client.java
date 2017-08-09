package ua.com.foxminded.accountingsystem.model;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "client")
@Audited
public class Client extends AbstractAuditEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence", initialValue = 50)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotAudited
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

    @NotAudited
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientFieldValue> extraFields;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PersonalAccount personalAccount;

    public Client(){

    }

    public Client(String firstName, String lastName, List<Order> orders, List<ClientFieldValue> extraFields,
                  PersonalAccount personalAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.orders = orders;
        this.extraFields = extraFields;
        this.personalAccount = personalAccount;
    }

    public void addClientFieldValue(ClientFieldValue clientFieldValue) {
        extraFields.add(clientFieldValue);
        clientFieldValue.setClient(this);
    }

    public void removeClientFieldValue(ClientFieldValue clientFieldValue) {
        extraFields.remove(clientFieldValue);
        clientFieldValue.setClient(null);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setClient(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setClient(null);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ClientFieldValue> getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(List<ClientFieldValue> extraFields) {
        this.extraFields = extraFields;
    }

    public PersonalAccount getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        if (id != null && !id.equals(client.id)) return false;
        if (!firstName.equals(client.firstName)) return false;
        return lastName.equals(client.lastName);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return 31;
        }
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Client{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", personalAccount=" + personalAccount +
            '}';
    }
}
