package ua.com.foxminded.accountingsystem.model;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee_field")
@Audited
public class EmployeeField extends AbstractAuditEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_field_sequence")
    @SequenceGenerator(name = "employee_field_sequence", sequenceName = "employee_field_sequence", allocationSize = 10)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeField)) return false;

        EmployeeField that = (EmployeeField) o;
        if (!(that.getName().equals(((EmployeeField) o).getName()))) {
            return false;
        }

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EmployeeField{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
