package ua.com.foxminded.accountingsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "employee_field")
public class EmployeeField implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_field_sequence")
    @SequenceGenerator(name = "employee_field_sequence", sequenceName = "employee_field_seq", initialValue = 50, allocationSize = 10)
    private long id;
    @Column(name = "field_name", nullable = false)
    private String name;


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

}
