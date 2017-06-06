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

//    @Column(name = "description")
//    private String description;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Money> money;
//
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Money employeeRate;

//    public Service(){
//        money = new ArrayList<>();
//        employeeRate = new Money();
//    }

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

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public List<Money> getMoney() {
//        return money;
//    }
//    public void setMoney(List<Money> money) {
//        this.money = money;
//    }
//    public Money getEmployeeRate() {
//        return employeeRate;
//    }
//    public void setEmployeeRate(Money employeeRate) {
//        this.employeeRate = employeeRate;
//    }
}
