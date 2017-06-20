package ua.com.foxminded.accountingsystem.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "personal_account")
public class PersonalAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personal_account_sequence")
    @SequenceGenerator(name = "client_sequence", sequenceName = "personal_account_sequence", initialValue = 50)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Client client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
