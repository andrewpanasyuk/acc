INSERT INTO fm_users (username, password, enabled, email) VALUES
    ('admin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE, 'admin@mail.com' ),
    ('user', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'user@mail.com' ),
    ('megaadmin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE, 'megaadmin@mail.com' ),
    ('jack', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'jack@mail.com' ),
    ('chuck', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'chuck@mail.com' ),
    ('martin', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'martin@mail.com' ),
    ('liza', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'liza@mail.com' ),
    ('bob', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'bob@mail.com' ),
    ('ivan', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'ivan@mail.com' ),
    ('josef', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'josef@mail.com' ),
    ('danilo', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'danilo@mail.com' ),
    ('greg', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'greg@mail.com' ),
    ('igor', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'igor@mail.com' ),
    ('lebovskiy', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'lebovskiy@mail.com' ),
    ('artem', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'artem@mail.com' ),
    ('mentor', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'mentor@mail.com' );

INSERT INTO user_role (id, role) VALUES
    (1, 'ADMIN'),
    (2, 'USER'),
    (3, 'MENTOR');

INSERT INTO fm_users_user_role (username, role_id) VALUES
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
    ('artem', 2),
    ('danilo', 2),
    ('greg', 2),
    ('ivan', 2),
    ('josef', 2),
    ('liza', 2),
    ('mentor', 2),
    ('mentor', 3);

INSERT INTO employee (id, first_name, last_name, max_clients, username) VALUES
    (1, 'jack', 'jackson', 10, NULL),
    (2, 'rob', 'robinson', 10, NULL),
    (3, 'peter', 'peterson', 10, NULL),
    (4, 'Evgeniy', 'Smorodskiy', 10, 'mentor');

INSERT INTO employee_field (id, name) VALUES
    (1, 'skype'),
    (2, 'email');

INSERT INTO employee_field_value (id, employee_id, employee_field_id, value) VALUES
    (1, 1, 1, 'jackSkype'),
    (2, 1, 2, NULL),
    (3, 2, 1, 'robSkype'),
    (4, 2, 2, 'rob@mail.com'),
    (5, 3, 1, 'peterSkype'),
    (6, 3, 2, 'peter@mail.com');

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

INSERT INTO personal_account (id) VALUES
    (1),
    (2),
    (3),
    (4),
    (5);

INSERT INTO personal_account_money (personal_account_id, money_id) VALUES
    (1, 1),
    (1, 6),
    (2, 2),
    (3, 3),
    (3, 7),
    (3, 8),
    (4, 4),
    (5, 5);

INSERT INTO client (id, last_name, first_name, personal_account_id) VALUES
    (1, 'Ivanov', 'Ivan',1),
    (2, 'Petrov', 'Sergey', null),
    (3, 'Pupkin', 'Adolf', 3),
    (4, 'Selezneva', 'Alisa', 4),
    (5, 'Hatabych', 'Old', 5),
    (6, 'Sidorov', 'Ivan', 5),
    (7, 'Smith', 'Sergey', 4),
    (8, 'Pupkin', 'Adolf', null),
    (9, 'Waylon', 'Dalton', 2),
    (10, 'Ivanov', 'Ivan', 1),
    (11, 'Cruz', 'Marcus', 1),
    (12, 'Shaffer', 'Joanna', 2);

INSERT INTO client_field (id, name) VALUES
    (1, 'skype'),
    (2, 'email');

INSERT INTO client_field_value (id, client_id, client_field_id, value) VALUES
    (1, 1, 1, 'ivanSkype'),
    (2, 1, 2, 'ivan@mail.com'),
    (3, 2, 1, 'petrovSkype'),
    (4, 2, 2, 'petrob@mail.com'),
    (5, 3, 1, 'adolfSkype'),
    (6, 3, 2, 'adolf@mail.com'),
    (7, 4, 1, 'alisaSkype'),
    (8, 4, 2, 'alisa@mail.com'),
    (9, 5, 1, 'oldSkype'),
    (10, 5, 2, NULL),
    (11, 6, 1, NULL),
    (12, 6, 2, 'sidorov@mail.com'),
    (13, 7, 1, NULL),
    (14, 7, 2, NULL),
    (15, 8, 1, NULL),
    (16, 8, 2, NULL),
    (17, 9, 1, NULL),
    (18, 9, 2, NULL),
    (19, 10, 1, NULL),
    (20, 10, 2, NULL),
    (21, 11, 1, NULL),
    (22, 11, 2, NULL),
    (23, 12, 1, NULL),
    (24, 12, 2, NULL);

INSERT INTO orders (id, service_id, status, client_id, open_date, close_date) VALUES
    (1, 1, 'ACTIVE', 1, '2017-01-24', NULL),
    (2, 1, 'WAITING', 2, '2017-04-04', NULL),
    (3, 2, 'FROZEN', 3, '2016-12-20', NULL),
    (4, 1, 'COMPLETED', 4, '2015-11-14', '2016-05-22'),
    (5, 2, 'ACTIVE', 4, '2016-11-15', NULL),
    (6, 2, 'ACTIVE', 5, '2016-11-24', NULL),
    (7, 1, 'ACTIVE', 5, '2017-01-01', NULL),
    (8, 1, 'WAITING', 1, '2017-02-24', NULL),
    (9, 2, 'FROZEN', 1, '2016-10-01', NULL),
    (10, 2, 'ACTIVE', 6, '2017-04-01', NULL),
    (11, 2, 'ACTIVE', 7, '2017-06-24', NULL),
    (12, 2, 'ACTIVE', 9, '2017-06-28', NULL);

INSERT INTO contract (id, contract_date, order_id, employee_id, payment_type, price_id, employee_rate_id, payment_date)
VALUES
    (1, '2017-06-01', 1, 1, 'PREPAY', 5, 2, '2017-02-01'),
    (2, '2017-06-01', 1, 2, 'PREPAY', 5, 2, '2017-03-01'),
    (3, '2017-06-10', 2, 2, 'PREPAY', 5, 2, '2017-01-01'),
    (4, '2017-06-15', 5, 3, 'PREPAY', 5, 2, '2015-12-01'),
    (5, '2017-06-20', 6, 2, 'POSTPAY', 5, 2, '2014-11-21'),
    (6, '2017-06-25', 11, 1, 'TRIAL', 5, 2, '2017-07-25'),
    (7, '2017-06-30', 12, 3, 'TRIAL', 5, 2, '2014-07-30');

INSERT INTO invoice (id, creation_date, employee_paid, period_from, period_to, contract_id, price_id) VALUES
    (1, '2000-01-01', FALSE, '2000-01-01', '2000-01-01', 1, 5),
    (2, '2000-01-01', FALSE, '2000-01-01', '2000-01-01', 2, 5),
    (3, '2000-01-01', FALSE, '2000-01-01', '2000-01-01', 2, 5),
    (4, '2000-01-01', FALSE, '2000-01-01', '2000-01-01', 2, 5),
    (5, '2000-01-01', TRUE, '2000-01-01', '2000-01-01', 2, 5),
    (6, '2000-01-01', TRUE, '2000-01-01', '2000-01-01', 1, 6),
    (7, '2000-01-01', TRUE, '2000-01-01', '2000-01-01', 1, 7);

INSERT INTO order_queue (id, queuing_date, priority, order_id) VALUES
    (1, '2017-01-01', 'NORMAL', 1);
