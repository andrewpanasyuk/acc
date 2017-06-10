package ua.com.foxminded.accountingsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Invoice implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
}
