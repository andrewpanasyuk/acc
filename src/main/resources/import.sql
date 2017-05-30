INSERT INTO users (username, password, enabled) VALUES
    ('admin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE ),
    ('user', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE );

INSERT INTO user_roles (id, username, role) VALUES
    (nextval('hibernate_sequence'), 'admin', 'ADMIN'),
    (nextval('hibernate_sequence'), 'admin', 'USER'),
    (nextval('hibernate_sequence'), 'user', 'USER');
