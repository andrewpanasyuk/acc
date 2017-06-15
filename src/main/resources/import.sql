INSERT INTO users (username, password, enabled, email) VALUES
    ('admin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE, 'admin@mail.com' ),
    ('user', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'user@mail.com' ),
    ('megaadmin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE, 'megaadmin@mail.com' ),
    ('jack', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'jack@mail.com' ),
    ('chuck', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'chuck@mail.com' ),
    ('martin', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'martin@mail.com' ),
    ('liza', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'liza@mail.com' ),
    ('samanta', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'samanta@mail.com' ),
    ('bob', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'bob@mail.com' ),
    ('petro', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'petro@mail.com' ),
    ('ivan', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'ivan@mail.com' ),
    ('josef', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'josef@mail.com' ),
    ('semen', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'semen@mail.com' ),
    ('danilo', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'danilo@mail.com' ),
    ('greg', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'greg@mail.com' ),
    ('igor', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'igor@mail.com' ),
    ('lebovskiy', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'lebovskiy@mail.com' ),
    ('artem', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'artem@mail.com' );


INSERT INTO user_role (id, role) VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO users_user_role (username, role_id) VALUES
    ('admin', 1),
    ('admin', 2),
    ('user', 2),
    ('megaadmin', 1),
    ('megaadmin', 2),
    ('jack', 2),
    ('martin', 2),
    ('chuck', 2),
    ('bob', 2),
    ('igor', 2),
    ('lebovskiy', 2),
    ('artem', 2);

INSERT INTO money (id, currency, price) VALUES
    (1, 'USD', 1000),
    (2, 'UAH', 2000),
    (3, 'EUR', 3000),
    (4, 'USD', 4000),
    (5, 'UAH', 5000),
    (6, 'EUR', 6000),
    (7, 'UAH', 3500),
    (8, 'UAH', 3500);

INSERT INTO service (id, service_name, description, employee_rate_id) VALUES
    (1, 'java', 'java description', 7),
    (2, 'javascript', 'javascript description', 8);

INSERT INTO service_prices (service_id, prices_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 4),
    (2, 5),
    (2, 6);

INSERT INTO client (id, first_name, last_name) VALUES
    (1, 'Ivanov', 'Ivan'),
    (2, 'Petrov', 'Sergey'),
    (3, 'Pupkin', 'Adolf'),
    (4, 'Selezneva', 'Alisa'),
    (5, 'Hatabych', 'Old');

INSERT INTO orders (id, order_name, status, client_id, open_date, queuing_date, close_date) VALUES
    (1, 'Java', 'ACTIVE', 1, '2017-01-24', '2017-01-25', NULL),
    (2, 'Java', 'WAITING', 2, '2017-04-04', '2017-04-05', NULL),
    (3, 'Java', 'FROZEN', 3, '2016-12-20', '2017-01-10', NULL),
    (4, 'Java', 'COMPLETED', 4, '2015-11-14', '2015-12-20', '2016-05-22'),
    (5, 'Java', 'ACTIVE', 4, '2016-11-15', '2016-11-15', NULL),
    (6, 'Java', 'ACTIVE', 5, '2016-11-24', '2016-11-12', NULL),
    (7, 'Java', 'ACTIVE', 5, '2017-01-01', '2017-01-01', NULL),
    (8, 'Java', 'WAITING', 1, '2017-02-24', '2017-05-25', NULL),
    (9, 'Java', 'FROZEN', 1, '2016-10-01', '2016-10-01', NULL);
