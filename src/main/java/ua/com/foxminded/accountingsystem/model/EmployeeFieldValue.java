package ua.com.foxminded.accountingsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "employee_field_value")
public class EmployeeFieldValue implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_field_value_sequence")
    @SequenceGenerator(name = "employee_field_value_sequence", sequenceName = "employee_field_value_sequnce", initialValue = 50)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToOne
    @JoinColumn(name = "employee_field_id")
    private EmployeeField employeeField;
    @Column(name = "value")
    private String value;

    public EmployeeFieldValue() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeField getEmployeeField() {
        return employeeField;
    }

    public void setEmployeeField(EmployeeField employeeField) {
        this.employeeField = employeeField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeFieldValue)) return false;

        EmployeeFieldValue that = (EmployeeFieldValue) o;
        return that.getValue().equals(((EmployeeFieldValue) o).getValue()) && id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EmployeeFieldValue{" +
            "id=" + id +
            ", employee=" + employee +
            ", value='" + value + '\'' +
            '}';
    }
}
