package ua.com.foxminded.accountingsystem.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "max_load")
    private int maxLoadByStudents;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<EmployeeFieldValue> extraFields;

    public long getId() {
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

    public List<EmployeeFieldValue> getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(List<EmployeeFieldValue> extraFields) {
        this.extraFields = extraFields;
    }

    public int getMaxLoadByStudents() {
        return maxLoadByStudents;
    }

    public void setMaxLoadByStudents(int maxLoadByStudents) {
        this.maxLoadByStudents = maxLoadByStudents;
    }


}
