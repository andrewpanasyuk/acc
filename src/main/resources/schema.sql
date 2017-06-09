CREATE TABLE employee (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR,
    last_name VARCHAR,
    PRIMARY KEY (id),
);

CREATE TABLE employee_field (
    id INT NOT NULL AUTO_INCREMENT,
    field_name VARCHAR,
    PRIMARY KEY (id),
);

CREATE TABLE employee_field_value (
    id INT NOT NULL AUTO_INCREMENT,
    employee_fk INT,
    employee_field_fk INT,
    field_value VARCHAR,
    PRIMARY KEY (id),
    FOREIGN KEY (employee_fk) REFERENCES employee(id),
    FOREIGN KEY (employee_field_fk) REFERENCES employee_field(id)
);


