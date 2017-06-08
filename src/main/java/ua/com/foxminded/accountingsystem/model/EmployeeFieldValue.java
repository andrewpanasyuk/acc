package ua.com.foxminded.accountingsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "employee_field_value")
public class EmployeeFieldValue implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "employee_fk")
    private Employee employee;
    @OneToOne
    @JoinColumn(name = "employee_field_fk")
    private EmployeeField field;
    @Column(name = "field_value")
    private String value;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeField getField() {
        return field;
    }

    public void setField(EmployeeField field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
