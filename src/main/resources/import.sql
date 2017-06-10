INSERT INTO users (username, password, enabled) VALUES
    ('admin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE ),
    ('user', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('megaadmin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE ),
    ('jack', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('chuck', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('martin', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('liza', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('samanta', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('bob', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('petro', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('ivan', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('josef', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('semen', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('danilo', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('greg', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('igor', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('lebovskiy', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE ),
    ('artem', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE );

INSERT INTO user_roles (id, username, role) VALUES
    (1, 'admin', 'ADMIN'),
    (2, 'admin', 'USER'),
    (3, 'user', 'USER'),
    (4, 'megaadmin', 'ADMIN'),
    (5, 'megaadmin', 'USER'),
    (6, 'jack', 'USER'),
    (7, 'martin', 'USER'),
    (8, 'chuck', 'USER'),
    (9, 'bob', 'USER'),
    (10, 'igor', 'USER'),
    (11, 'lebovskiy', 'USER'),
    (12, 'artem', 'USER');

INSERT INTO employee(id,first_name,last_name) VALUES
(1,'jack', 'jackson'),
(2,'rob', 'robinson'),
(3,'peter', 'peterson');

INSERT INTO  employee_field(id, field_name) VALUES
(1,'skype'),
(2, 'email');
INSERT INTO  employee_field_value(id,employee_fk,employee_field_fk,field_value) VALUES
(1,1,1, 'jackSkype'),
(2,2,1, 'robSkype'),
(3,3,2,'peter@mail.com');
