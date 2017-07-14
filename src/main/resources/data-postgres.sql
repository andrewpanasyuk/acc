INSERT INTO fm_users (username, password, enabled, email, created_by, created_date) VALUES
    ('admin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE, 'admin@mail.com', 'system', '2017-07-11'),
    ('user', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'user@mail.com', 'system', '2017-07-11' ),
    ('megaadmin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE, 'megaadmin@mail.com', 'system', '2017-07-11' ),
    ('jack', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'jack@mail.com', 'system', '2017-07-11' ),
    ('chuck', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'chuck@mail.com', 'system', '2017-07-11' ),
    ('martin', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'martin@mail.com', 'system', '2017-07-11' ),
    ('liza', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'liza@mail.com', 'system', '2017-07-11' ),
    ('bob', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'bob@mail.com', 'system', '2017-07-11' ),
    ('ivan', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'ivan@mail.com', 'system', '2017-07-11' ),
    ('josef', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'josef@mail.com', 'system', '2017-07-11' ),
    ('danilo', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'danilo@mail.com', 'system', '2017-07-11' ),
    ('greg', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'greg@mail.com', 'system', '2017-07-11' ),
    ('igor', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'igor@mail.com', 'system', '2017-07-11' ),
    ('lebovskiy', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'lebovskiy@mail.com', 'system', '2017-07-11' ),
    ('artem', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'artem@mail.com', 'system', '2017-07-11' ),
    ('mentor', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'mentor@mail.com', 'system', '2017-07-11' );

INSERT INTO user_role (id, role, created_by, created_date) VALUES
    (1, 'ADMIN', 'system', '2017-07-11'),
    (2, 'USER', 'system', '2017-07-11'),
    (3, 'MENTOR', 'system', '2017-07-11');

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
    ('mentor', 2);

INSERT INTO employee (id, first_name, last_name, max_clients, username, created_by, created_date) VALUES
    (1, 'jack', 'jackson', 10, NULL, 'system', '2017-07-11'),
    (2, 'rob', 'robinson', 10, NULL, 'system', '2017-07-11'),
    (3, 'peter', 'peterson', 10, NULL, 'system', '2017-07-11'),
    (4, 'Evgeniy', 'Smorodskiy', 10, 'mentor', 'system', '2017-07-11');

INSERT INTO employee_field (id, name, created_by, created_date) VALUES
    (1, 'skype', 'system', '2017-07-11'),
    (2, 'email', 'system', '2017-07-11');

INSERT INTO employee_field_value (id, employee_id, employee_field_id, value, created_by, created_date) VALUES
    (1, 1, 1, 'jackSkype', 'system', '2017-07-11'),
    (2, 1, 2, NULL, 'system', '2017-07-11'),
    (3, 2, 1, 'robSkype', 'system', '2017-07-11'),
    (4, 2, 2, 'rob@mail.com', 'system', '2017-07-11'),
    (5, 3, 1, 'peterSkype', 'system', '2017-07-11'),
    (6, 3, 2, 'peter@mail.com', 'system', '2017-07-11');

INSERT INTO money (id, currency, price) VALUES
    (1, 'USD', 1000),
    (2, 'UAH', 2000),
    (3, 'EUR', 3000),
    (4, 'USD', 4000),
    (5, 'UAH', 5000),
    (6, 'EUR', 6000),
    (7, 'UAH', 3500),
    (8, 'UAH', 3500),
    (9, 'UAH', 1000),
    (10, 'UAH', 1000),
    (11, 'UAH', 1500),
    (12, 'UAH', 1500),
    (13, 'UAH', 1500);

INSERT INTO service (id, service_name, description, employee_rate_id, created_by, created_date) VALUES
    (1, 'java', 'java description', 7, 'system', '2017-07-11'),
    (2, 'javascript', 'javascript description', 8, 'system', '2017-07-11');

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

INSERT INTO client (id, last_name, first_name, personal_account_id, created_by, created_date) VALUES
    (1, 'Ivanov', 'Ivan', 1, 'admin', now()),
    (2, 'Petrov', 'Sergey', null, 'admin', now()),
    (3, 'Pupkin', 'Adolf', 3, 'admin', now()),
    (4, 'Selezneva', 'Alisa', 4, 'admin', now()),
    (5, 'Hatabych', 'Old', 5, 'admin', now()),
    (6, 'Sidorov', 'Ivan', 5, 'admin', now()),
    (7, 'Smith', 'Sergey', 4, 'admin', now()),
    (8, 'Pupkin', 'Adolf', null, 'admin', now()),
    (9, 'Waylon', 'Dalton', 2, 'admin', now()),
    (10, 'Ivanov', 'Ivan', 1, 'admin', now()),
    (11, 'Cruz', 'Marcus', 1, 'admin', now()),
    (12, 'Shaffer', 'Joanna', 2, 'admin', now());

INSERT INTO client_field (id, name, created_by, created_date) VALUES
    (1, 'skype', 'system', '2017-07-11'),
    (2, 'email', 'system', '2017-07-11');

INSERT INTO client_field_value (id, client_id, client_field_id, value, created_by, created_date) VALUES
    (1, 1, 1, 'ivanSkype', 'system', '2017-07-11'),
    (2, 1, 2, 'ivan@mail.com', 'system', '2017-07-11'),
    (3, 2, 1, 'petrovSkype', 'system', '2017-07-11'),
    (4, 2, 2, 'petrob@mail.com', 'system', '2017-07-11'),
    (5, 3, 1, 'adolfSkype', 'system', '2017-07-11'),
    (6, 3, 2, 'adolf@mail.com', 'system', '2017-07-11'),
    (7, 4, 1, 'alisaSkype', 'system', '2017-07-11'),
    (8, 4, 2, 'alisa@mail.com', 'system', '2017-07-11'),
    (9, 5, 1, 'oldSkype', 'system', '2017-07-11'),
    (10, 5, 2, NULL, 'system', '2017-07-11'),
    (11, 6, 1, NULL, 'system', '2017-07-11'),
    (12, 6, 2, 'sidorov@mail.com', 'system', '2017-07-11'),
    (13, 7, 1, NULL, 'system', '2017-07-11'),
    (14, 7, 2, NULL, 'system', '2017-07-11'),
    (15, 8, 1, NULL, 'system', '2017-07-11'),
    (16, 8, 2, NULL, 'system', '2017-07-11'),
    (17, 9, 1, NULL, 'system', '2017-07-11'),
    (18, 9, 2, NULL, 'system', '2017-07-11'),
    (19, 10, 1, NULL, 'system', '2017-07-11'),
    (20, 10, 2, NULL, 'system', '2017-07-11'),
    (21, 11, 1, NULL, 'system', '2017-07-11'),
    (22, 11, 2, NULL, 'system', '2017-07-11'),
    (23, 12, 1, NULL, 'system', '2017-07-11'),
    (24, 12, 2, NULL, 'system', '2017-07-11');

INSERT INTO orders (id, service_id, status, client_id, open_date, close_date, created_by, created_date) VALUES
    (1, 1, 'ACTIVE', 1, '2017-01-24', NULL, 'system', '2017-07-11'),
    (2, 1, 'WAITING', 2, '2017-04-04', NULL, 'system', '2017-07-11'),
    (3, 2, 'FROZEN', 3, '2016-12-20', NULL, 'system', '2017-07-11'),
    (4, 1, 'COMPLETED', 4, '2015-11-14', '2016-05-22', 'system', '2017-07-11'),
    (5, 2, 'ACTIVE', 4, '2016-11-15', NULL, 'system', '2017-07-11'),
    (6, 2, 'ACTIVE', 5, '2016-11-24', NULL, 'system', '2017-07-11'),
    (7, 1, 'ACTIVE', 5, '2017-01-01', NULL, 'system', '2017-07-11'),
    (8, 1, 'WAITING', 1, '2017-02-24', NULL, 'system', '2017-07-11'),
    (9, 2, 'FROZEN', 1, '2016-10-01', NULL, 'system', '2017-07-11'),
    (10, 2, 'ACTIVE', 6, '2017-04-01', NULL, 'system', '2017-07-11'),
    (11, 2, 'ACTIVE', 7, '2017-06-24', NULL, 'system', '2017-07-11'),
    (12, 2, 'ACTIVE', 9, '2017-06-28', NULL, 'system', '2017-07-11');

INSERT INTO contract (id, contract_date, order_id, employee_id, payment_type, price_id, employee_rate_id, payment_date, created_by, created_date)
VALUES
    (1, '2017-06-01', 1, 1, 'PREPAY', 5, 2, '2017-02-01', 'system', '2017-07-11'),
    (2, '2017-06-01', 1, 2, 'PREPAY', 5, 2, '2017-03-01', 'system', '2017-07-11'),
    (3, '2017-06-10', 2, 2, 'PREPAY', 5, 2, '2017-01-01', 'system', '2017-07-11'),
    (4, '2017-06-15', 5, 3, 'PREPAY', 5, 2, '2015-12-01', 'system', '2017-07-11'),
    (5, '2017-06-20', 6, 2, 'POSTPAY', 5, 2, '2014-11-21', 'system', '2017-07-11'),
    (6, '2017-06-25', 11, 1, 'TRIAL', 5, 2, '2017-07-25', 'system', '2017-07-11'),
    (7, '2017-06-20', 6, 2, 'POSTPAY', 5, 2, '2014-11-13', 'system', '2017-07-11'),
    (8, '2017-06-25', 11, 1, 'TRIAL', 5, 2, '2017-07-13', 'system', '2017-07-11'),
    (9, '2017-06-30', 12, 3, 'TRIAL', 5, 2, '2014-07-30', 'system', '2017-07-11');

INSERT INTO invoice (id, creation_date, employee_paid, period_from, period_to, contract_id, price_id, created_by, created_date) VALUES
    (1, '2017-06-01', FALSE, '2017-06-01', '2017-06-30', 1, 1, 'system', '2017-07-11'),
    (2, '2017-05-01', FALSE, '2017-06-01', '2017-07-10', 2, 2, 'system', '2017-07-11'),
    (3, '2017-05-01', FALSE, '2017-06-01', '2017-07-01', 3, 3, 'system', '2017-07-11'),
    (4, '2000-01-01', FALSE, '2000-01-01', '2000-01-01', 4, 4, 'system', '2017-07-11'),
    (5, '2000-01-01', TRUE, '2017-07-05', '2017-08-04', 5, 5, 'system', '2017-07-11'),
    (6, '2000-01-01', TRUE, '2017-07-01', '2017-07-10', 6, 6, 'system', '2017-07-11'),
    (7, '2000-01-01', TRUE, '2000-01-01', '2000-01-01', 7, 7, 'system', '2017-07-11'),
    (8, '2017-07-01', TRUE, '2017-07-01', '2017-07-31', 1, 6, 'system', '2017-07-11');

INSERT INTO payment (id, invoice_id, date_paid, created_by, created_date) VALUES
    (1, 7, '2000-01-01', 'system', '2017-07-11'),
    (2, 6, '2000-01-01', 'system', '2017-07-11');

INSERT INTO order_queue (id, queuing_date, priority, order_id, created_by, created_date) VALUES
    (1, '2017-01-01', 'NORMAL', 1, 'system', '2017-07-11');

INSERT INTO salary_item(id, employee_id, invoice_id, employee_payment_id, date_from, date_to, accounted) VALUES
     (1, 1, 8, 9, '2017-07-01', '2017-07-31', FALSE),
     (2, 2, 8, 10, '2017-07-11', '2017-07-31', FALSE),
     (3, 2, 8, 11, '2017-07-01', '2017-07-31', FALSE),
     (4, 2, 8, 12, '2017-07-11', '2017-07-31', FALSE),
     (5, 3, 8, 13, '2017-07-01', '2017-07-25', FALSE);
