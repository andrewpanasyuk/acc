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
@Table(name = "employee_field_value")
@Audited
public class EmployeeFieldValue extends AbstractAuditEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_field_value_sequence")
    @SequenceGenerator(name = "employee_field_value_sequence", sequenceName = "employee_field_value_sequnce", initialValue = 50)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_field_id")
    private EmployeeField employeeField;

    @Column(name = "value")
    private String value;

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
