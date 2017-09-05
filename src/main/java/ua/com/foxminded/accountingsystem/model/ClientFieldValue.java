package ua.com.foxminded.accountingsystem.model;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "client_field_value")
@Audited
public class ClientFieldValue extends AbstractAuditEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_field_value_sequence")
    @SequenceGenerator(name = "client_field_value_sequence", sequenceName = "client_field_value_sequence",
        allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_field_id")
    private ClientField clientField;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientField getClientField() {
        return clientField;
    }

    public void setClientField(ClientField clientField) {
        this.clientField = clientField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientFieldValue that = (ClientFieldValue) o;

        if (!clientField.equals(that.clientField)) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return client.equals(that.client);
    }

    @Override
    public int hashCode() {
        int result = clientField.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + client.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ClientFieldValue{" +
            "clientField=" + clientField +
            ", value='" + value + '\'' +
            ", client=" + client +
            '}';
    }
}
