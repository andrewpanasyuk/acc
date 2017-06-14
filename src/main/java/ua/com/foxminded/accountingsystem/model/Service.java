package ua.com.foxminded.accountingsystem.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_sequence")
    @SequenceGenerator(name = "service_sequence", initialValue = 50)
    private long id;

    @NotNull(message = "It is required field")
    @Size(min = 2, max = 50)
    @Column(name = "service_name")
    private String name;

    @NotBlank(message = "It is required field")
    @Column(name = "description")
    private String description;

    @NotEmpty(message="At least one price is required")
    @Valid
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Money> prices;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Money employeeRate;

    public Service() {
        prices = new ArrayList<>();
        employeeRate = new Money();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Money> getPrices() {
        return prices;
    }

    public void setPrices(List<Money> prices) {
        this.prices = prices;
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

        if (name != null ? !name.equals(service.name) : service.name != null) return false;
        return description != null ? description.equals(service.description) : service.description == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Service{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", prices=" + prices +
            ", employeeRate=" + employeeRate +
            '}';
    }
}
