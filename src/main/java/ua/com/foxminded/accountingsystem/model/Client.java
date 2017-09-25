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
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotAudited
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Deal> deals;

    @NotAudited
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientFieldValue> extraFields;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PersonalAccount personalAccount;

    @Column(name = "email")
    private String email;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "skype")
    private String skype;

    public Client(){

    }

    public Client(String firstName, String lastName, List<Deal> deals, List<ClientFieldValue> extraFields,
                  PersonalAccount personalAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.deals = deals;
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

    public void addDeal(Deal deal) {
        deals.add(deal);
        deal.setClient(this);
    }

    public void removeDeal(Deal deal) {
        deals.remove(deal);
        deal.setClient(null);
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
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
            '}';
    }
}
