package ua.com.foxminded.accountingsystem.model;


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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_sequence")
    @SequenceGenerator(name="service_sequence", initialValue=50)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Money> monies;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Money employeeRate;

    //TODO Something wrong, change this decision
    public Service(){
        monies = new ArrayList<>();
        employeeRate = new Money();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Money> getMonies() {
        return monies;
    }

    public void setMonies(List<Money> monies) {
        this.monies = monies;
    }

    public Money getEmployeeRate() {
        return employeeRate;
    }

    public void setEmployeeRate(Money employeeRate) {
        this.employeeRate = employeeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (serviceName != null ? !serviceName.equals(service.serviceName) : service.serviceName != null) return false;
        return description != null ? description.equals(service.description) : service.description == null;
    }

    @Override
    public int hashCode() {
        int result = serviceName != null ? serviceName.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
            "serviceName='" + serviceName + '\'' +
            ", description='" + description + '\'' +
            ", monies=" + monies +
            ", employeeRate=" + employeeRate +
            '}';
    }
}
