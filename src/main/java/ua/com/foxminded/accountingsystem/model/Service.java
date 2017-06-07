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

        if (!id.equals(service.id)) return false;
        if (!serviceName.equals(service.serviceName)) return false;
        if (!description.equals(service.description)) return false;
        if (!prices.equals(service.prices)) return false;
        return employeeRate.equals(service.employeeRate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + serviceName.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + prices.hashCode();
        result = 31 * result + employeeRate.hashCode();
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
