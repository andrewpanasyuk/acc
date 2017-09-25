INSERT INTO fm_users (username, password, enabled, email, created_by, created_date) VALUES
    ('admin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE, 'admin@mail.com', 'system', '2017-07-11'),
    ('user', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE, 'user@mail.com', 'system', '2017-07-11' ),
    ('inactiveUser', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', FALSE, 'inactiveuser@mail.com', 'system', '2017-07-11' ),
    ('superMentor', '2a$10$BvU/7BcF0DBrnpxlN9r4cuyABANhhzbPhCXeWLTOPwcAYXA6jbcYK', TRUE, 'superMentor@mail.com', 'system', '2017-07-11' ),
    ('mentor', '2a$10$BvU/7BcF0DBrnpxlN9r4cuyABANhhzbPhCXeWLTOPwcAYXA6jbcYK', TRUE, 'mentor@mail.com', 'system', '2017-07-11' );

INSERT INTO user_role (id, role, created_by, created_date) VALUES
    (1, 'ADMIN', 'system', '2017-07-11'),
    (2, 'USER', 'system', '2017-07-11'),
    (3, 'MENTOR', 'system', '2017-07-11');

INSERT INTO fm_users_user_role (username, role_id) VALUES
    ('admin', 1),
    ('admin', 2),
    ('admin', 3),
    ('user', 2),
    ('inactiveUser', 2),
    ('superMentor', 3),
    ('mentor', 3);

INSERT INTO employee (id, first_name, last_name, max_clients, username, created_by, created_date) VALUES
    (1, 'Charles', 'Xavier', 20, 'superMentor', 'system', '2017-07-11'),
    (2, 'James', 'Howlett', 1, NULL, 'system', '2017-07-11'),
    (3, 'Scott', 'Summers', 3, NULL, 'system', '2017-07-11'),
    (4, 'Ororo', 'Munroe', 10, 'mentor', 'system', '2017-07-11');

INSERT INTO employee_field (id, name, created_by, created_date) VALUES
    (1, 'skype', 'system', '2017-07-11'),
    (2, 'email', 'system', '2017-07-11');

INSERT INTO employee_field_value (id, employee_id, employee_field_id, value, created_by, created_date) VALUES
    (1, 1, 1, 'ProfessorX', 'system', '2017-07-11'),
    (2, 1, 2, NULL, 'system', '2017-07-11'),
    (3, 2, 1, 'Wolverine', 'system', '2017-07-11'),
    (4, 2, 2, 'woolvy@mail.com', 'system', '2017-07-11'),
    (5, 3, 1, 'Cyclops', 'system', '2017-07-11'),
    (6, 3, 2, 'cyclops@mail.com', 'system', '2017-07-11'),
    (7, 4, 1, 'Storm', 'system', '2017-07-11'),
    (8, 4, 2, 'lightning@mail.com', 'system', '2017-07-11');

INSERT INTO money (id, currency, amount) VALUES
    (1, 'USD', 20),
    (2, 'EUR', 9999),
    (3, 'USD', 1500),
    (4, 'EUR', 3000),
    (5, 'UAH', 10000),
    (6, 'USD', 100),
    (7, 'UAH', 5000),
    (8, 'EUR', 6000),
    (9, 'UAH', 1500),
    (10, 'UAH', 3000),
    (11, 'UAH', 1500),
    (12, 'UAH', 3000),
    (13, 'USD', 120),
    (14, 'EUR', 105),
    (15, 'UAH', 6000),
    (16, 'USD', 240),
    (17, 'EUR', 210),
    (18, 'UAH', 3000),
    (19, 'USD', 120),
    (20, 'EUR', 105),
    (21, 'UAH', 6000),
    (22, 'UAH', 3000),
    (23, 'USD', 120),
    (24, 'UAH', 1500),
    (25, 'EUR', 105),
    (26, 'UAH', 1500),
    (27, 'UAH', 1000),
    (28, 'UAH', 500),
    (29, 'UAH', 3000),
    (30, 'UAH', 1500),
    (31, 'UAH', 333),
    (32, 'UAH', 6000),
    (33, 'UAH', 6000),
    (34, 'UAH', 6000),
    (35, 'USD', 120),
    (36, 'USD', 120),
    (37, 'USD', 120),
    (38, 'EUR', 105),
    (39, 'UAH', 1000),
    (40, 'UAH', 1000),
    (41, 'UAH', 3000),
    (42, 'UAH', 3000),
    (43, 'UAH', 3000),
    (44, 'UAH', 1500),
    (45, 'UAH', 1500),
    (46, 'UAH', 500),
    (47, 'UAH', 3000),
    (48, 'UAH', 3000),
    (49, 'UAH', 3000),
    (50, 'UAH', 1500),
    (51, 'UAH', 1500),
    (52, 'UAH', 500),
    (53, 'UAH', 3000),
    (54, 'UAH', 1500),
    (55, 'UAH', 6000),
    (56, 'UAH', 3000);

INSERT INTO consultancy (id, name, description, employee_rate_id, created_by, created_date) VALUES
    (1, 'Mentoring', 'Focuses on the knowledge and needs of clients. Training during free time. No lectures.', 9, 'system', '2017-07-11'),
    (2, 'Personal Mentor', 'Personal couching with Sergey Nemchinskiy', 10, 'system', '2017-07-11'),
    (3, 'Group Development', 'Interact with the team;
Solve conflicts that inevitably arise during teamwork;
Create a project from scratch.', 11, 'system', '2017-07-11');

INSERT INTO consultancy_prices (consultancy_id, prices_id) VALUES
    (1, 12),
    (1, 13),
    (1, 14),
    (2, 15),
    (2, 16),
    (2, 17),
    (3, 18),
    (3, 19),
    (3, 20);

INSERT INTO personal_account (id) VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (6);

INSERT INTO personal_account_money (personal_account_id, money_id) VALUES
    (1, 1),
    (2, 2),
    (2, 3),
    (2, 5),
    (3, 4),
    (4, 6),
    (4, 7),
    (5, 8),
    (6, 31);

INSERT INTO client (id, first_name, last_name, personal_account_id, created_by, created_date) VALUES
    (1, 'John', 'Allerdyce', 1, 'admin', '2017-07-11'),
    (2, 'Raven', 'Darkhölme', 2, 'admin', '2017-07-11'),
    (3, 'Bobby', 'Drake', 3, 'admin', '2017-07-11'),
    (4, 'Piotr', 'Rasputin', 4, 'admin', '2017-07-11'),
    (5, 'Kurt', 'Wagner', 5, 'admin', '2017-07-11'),
    (6, 'Marie', 'Rogue', 6, 'admin', '2017-07-11');

INSERT INTO client_field (id, name, created_by, created_date) VALUES
    (1, 'skype', 'system', '2017-07-11'),
    (2, 'email', 'system', '2017-07-11');

INSERT INTO client_field_value (id, client_id, client_field_id, value, created_by, created_date) VALUES
    (1, 1, 1, 'Pyro', 'system', '2017-07-11'),
    (2, 1, 2, 'fire@mail.com', 'system', '2017-07-11'),
    (3, 2, 1, 'Mystique', 'system', '2017-07-11'),
    (4, 2, 2, 'blue@mail.com', 'system', '2017-07-11'),
    (5, 3, 1, 'Iceman', 'system', '2017-07-11'),
    (6, 3, 2, 'ice@mail.com', 'system', '2017-07-11'),
    (7, 4, 1, 'Colossus', 'system', '2017-07-11'),
    (8, 4, 2, 'iron@mail.com', 'system', '2017-07-11'),
    (9, 5, 1, 'Nightcrawler', 'system', '2017-07-11'),
    (10, 5, 2, NULL, 'system', '2017-07-11'),
    (11, 6, 1, NULL, 'system', '2017-07-11'),
    (12, 6, 2, NULL, 'system', '2017-07-11');

INSERT INTO deal (id, consultancy_id, status, client_id, open_date, close_date, created_by, created_date) VALUES
    (1, 1, 'NEW', 4, now() - interval '1 day', NULL, 'system', '2017-07-11'),
    (2, 1, 'WAITING', 1, now() - interval '3 day', NULL, 'system', '2017-07-11'),
    (3, 3, 'WAITING', 1, now() - interval '3 day', NULL, 'system', '2017-07-11'),
    (4, 3, 'WAITING', 3, now() - interval '4 day', NULL, 'system', '2017-07-11'),
    (5, 2, 'ACTIVE', 2, now() - interval '4 mon + 12 day', NULL, 'system', '2017-07-11'),
    (6, 3, 'ACTIVE', 4, now() - interval '3 mon + 10 day', NULL, 'system', '2017-07-11'),
    (7, 3, 'ACTIVE', 5, now() - interval '35 day', NULL, 'system', '2017-07-11'),
    (8, 1, 'COMPLETED', 5, now() - interval '7 mon', now() - interval '45 day', 'system', '2017-07-11'),
    (9, 2, 'FROZEN', 1, now() - interval '20 day', NULL, 'system', '2017-07-11'),
    (10, 1, 'FROZEN', 6, now() - interval '2 mon + 20 day', NULL, 'system', '2017-07-11'),
    (11, 3, 'ACTIVE', 6, now() - interval '8 day', NULL, 'system', '2017-07-11');

INSERT INTO contract (id, deal_id, contract_date, employee_id, payment_type, price_id, employee_rate_id, payment_date, close_date, close_type, created_by, created_date)
VALUES
    (1, 5, now() - interval '4 mon + 10 day', 1, 'TRIAL', 21, 22, NULL, now() - interval '4 mon + 1 day', 'COMPLETED', 'system', '2017-07-11'),
    (2, 5, now() - interval '4 mon', 1, 'POSTPAY', 21, 22, now() - interval '3 mon + 1 day', NULL, NULL, 'system', '2017-07-11'),
    (3, 6, now() - interval '3 mon + 10 day', 4, 'POSTPAY', 23, 24, now() - interval '2 mon + 11 day', NULL, NULL, 'system', '2017-07-11'),
    (4, 7, now() - interval '1 mon + 4 day', 3, 'PREPAY', 25, 26, now() - interval '4 day', NULL, NULL, 'system', '2017-07-11'),
    (5, 10, now() - interval '2 mon + 20 day', 2, 'TRIAL', 27, 28, NULL, now() - interval '2 mon + 11 day', 'COMPLETED', 'system', '2017-07-11'),
    (6, 10, now() - interval '2 mon + 13 day', 2, 'PREPAY', 27, 28, now() - interval '2 mon + 10 day', now() - interval '1 mon', 'FROZEN', 'system', '2017-07-11'),
    (7, 11, now() - interval '8 day', 1, 'TRIAL', 29, 30, NULL, now() - interval '2 day', 'COMPLETED', 'system', '2017-07-11'),
    (8, 11, now() - interval '1 day', 1, 'PREPAY', 29, 30, now() + interval '2 day', NULL, NULL, 'system', '2017-07-11'),
    (9, 8, now() - interval '7 mon', 3, 'PREPAY', 53, 54, now() - interval '6 mon', now() - interval '45 day', 'COMPLETED', 'system', '2017-07-11'),
    (10, 9, now() - interval '20 day', 2, 'PREPAY', 55, 56, now() + interval '10 day', now() - interval '5 day', 'FROZEN', 'system', '2017-07-11');

INSERT INTO invoice (id, contract_id, price_id, creation_date, employee_paid, period_from, period_to, created_by, created_date) VALUES
    (1, 2, 21, now() - interval '3 mon + 4 day', FALSE, now() - interval '4 mon', now() - interval '3 mon + 1 day', 'system', '2017-07-11'),
    (2, 2, 21, now() - interval '2 mon + 4 day', FALSE, now() - interval '3 mon', now() - interval '2 mon + 1 day', 'system', '2017-07-11'),
    (3, 2, 21, now() - interval '1 mon + 4 day', FALSE, now() - interval '2 mon', now() - interval '1 mon + 1 day', 'system', '2017-07-11'),
    (4, 2, 21, now() - interval '4 day', FALSE, now() - interval '1 mon', now() - interval '1 day', 'system', '2017-07-11'),
    (5, 3, 23, now() - interval '2 mon + 14 day', FALSE, now() - interval '3 mon + 10 day', now() - interval '2 mon + 11 day', 'system', '2017-07-11'),
    (6, 3, 23, now() - interval '1 mon + 14 day', FALSE, now() - interval '2 mon + 10 day', now() - interval '1 mon + 11 day', 'system', '2017-07-11'),
    (7, 3, 23, now() - interval '14 day', FALSE, now() - interval '1 mon + 10 day', now() - interval '11 day', 'system', '2017-07-11'),
    (8, 4, 25, now() - interval '1 mon + 7 day', FALSE, now() - interval '1 mon + 4 day', now() - interval '5 day', 'system', '2017-07-11'),
    (9, 6, 27, now() - interval '2 mon + 13 day', FALSE, now() - interval '2 mon + 10 day', now() - interval '1 mon + 11 day', 'system', '2017-07-11'),
    (10, 6, 27, now() - interval '1 mon + 13 day', FALSE, now() - interval '1 mon + 10 day', now() - interval '11 day', 'system', '2017-07-11'),
    (11, 8, 29, now() - interval '1 day', FALSE, now() + interval '2 day', now() + interval '1 mon + 1 day', 'system', '2017-07-11');

INSERT INTO payment (id, invoice_id, date_paid, sum_id, created_by, created_date) VALUES
    (1, 1, now() - interval '3 mon', 32, 'system', '2017-07-11'),
    (2, 2, now() - interval '2 mon', 33, 'system', '2017-07-11'),
    (3, 3, now() - interval '1 mon', 34, 'system', '2017-07-11'),
    (4, 5, now() - interval '2 mon + 13 day', 35, 'system', '2017-07-11'),
    (5, 6, now() - interval '1 mon + 12 day', 36, 'system', '2017-07-11'),
    (6, 7, now() - interval '11 day', 37, 'system', '2017-07-11'),
    (7, 8, now() - interval '1 mon + 4 day', 38, 'system', '2017-07-11'),
    (8, 9, now() - interval '2 mon + 11 day', 39, 'system', '2017-07-11'),
    (9, 10, now() - interval '1 mon + 10 day', 40, 'system', '2017-07-11');

INSERT INTO deal_queue (id, queuing_date, priority, deal_id, removed, created_by, created_date) VALUES
    (1, now() - interval '3 day', 'NORMAL', 2, FALSE, 'system', '2017-07-11'),
    (2, now() - interval '3 day', 'NORMAL', 3, FALSE, 'system', '2017-07-11'),
    (3, now() - interval '4 day', 'NORMAL', 4, FALSE, 'system', '2017-07-11');

INSERT INTO salary(id, date_salary, date_from, date_to, employee_id, paid, total_amount_id) VALUES
    (1, now() - interval '2 mon' - interval '21 day', now() - interval '3 mon' - interval '21 day', now() - interval '2 mon' - interval '21 day', 1, TRUE, 47),
    (2, now() - interval '1 mon' - interval '21 day', now() - interval '2 mon' - interval '21 day', now() - interval '1 mon' - interval '21 day', 1, TRUE, 48),
    (3, now() - interval '21 day', now() - interval '1 mon' - interval '21 day', now() - interval '21 day', 1, TRUE, 49),
    (4, now() - interval '1 mon' - interval '21 day', now() - interval '2 mon' - interval '21 day', now() - interval '1 mon' - interval '21 day', 4, TRUE, 50),
    (5, now() - interval '21 day', now() - interval '1 mon' - interval '21 day', now() - interval '21 day', 4, TRUE, 51),
    (6, now() - interval '21 day', now() - interval '1 mon' - interval '21 day', now() - interval '21 day', 2, TRUE, 52);

INSERT INTO salary_item(id, salary_id, employee_id, invoice_id, employee_payment_id, date_from, date_to, accounted) VALUES
    (1, null, 1, 1, 22, now() - interval '4 mon', now() - interval '3 mon + 1 day', FALSE),
    (2, null, 1, 2, 22, now() - interval '3 mon', now() - interval '2 mon + 1 day', FALSE),
    (3, null, 1, 3, 22, now() - interval '2 mon', now() - interval '1 mon + 1 day', FALSE),
    (4, null, 1, 4, 22, now() - interval '1 mon', now() - interval '1 day', FALSE),
    (5, null, 4, 5, 24, now() - interval '3 mon + 10 day', now() - interval '2 mon + 11 day', FALSE),
    (6, null, 4, 6, 24, now() - interval '2 mon + 10 day', now() - interval '1 mon + 11 day', FALSE),
    (7, null, 4, 7, 24, now() - interval '1 mon + 10 day', now() - interval '11 day', FALSE),
    (8, null, 3, 8, 26, now() - interval '1 mon + 4 day', now() - interval '5 day', FALSE),
    (9, null, 2, 9, 28, now() - interval '2 mon + 10 day', now() - interval '1 mon + 11 day', FALSE),
    (10, null, 2, 10, 28, now() - interval '1 mon + 10 day', now() - interval '11 day', FALSE),
    (11, 1, 1, 1, 41, now() - interval '4 mon', now() - interval '3 mon + 1 day', TRUE),
    (12, 2, 1, 2, 42, now() - interval '3 mon', now() - interval '2 mon + 1 day', TRUE),
    (13, 3, 1, 3, 43, now() - interval '2 mon', now() - interval '1 mon + 1 day', TRUE),
    (14, 4, 4, 5, 44, now() - interval '3 mon + 10 day', now() - interval '2 mon + 11 day', TRUE),
    (15, 5, 4, 6, 45, now() - interval '2 mon + 10 day', now() - interval '1 mon + 11 day', TRUE),
    (16, 6, 2, 9, 46, now() - interval '2 mon + 10 day', now() - interval '1 mon + 11 day', TRUE);
