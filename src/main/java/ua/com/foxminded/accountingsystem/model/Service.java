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
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service")
public class Service implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Price> prices;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Price employeeRate;

    public Service(){
        prices = new ArrayList<>();
        employeeRate = new Price();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public Price getEmployeeRate() {
        return employeeRate;
    }

    public void setEmployeeRate(Price employeeRate) {
        this.employeeRate = employeeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        if (id != null ? !id.equals(service.id) : service.id != null) return false;
        if (serviceName != null ? !serviceName.equals(service.serviceName) : service.serviceName != null) return false;
        if (description != null ? !description.equals(service.description) : service.description != null) return false;
        if (prices != null ? !prices.equals(service.prices) : service.prices != null) return false;
        return employeeRate != null ? employeeRate.equals(service.employeeRate) : service.employeeRate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (prices != null ? prices.hashCode() : 0);
        result = 31 * result + (employeeRate != null ? employeeRate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
            "id=" + id +
            ", serviceName='" + serviceName + '\'' +
            ", description='" + description + '\'' +
            ", prices=" + prices +
            ", employeeRate=" + employeeRate +
            '}';
    }
}
