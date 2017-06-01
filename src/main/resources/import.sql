INSERT INTO users (username, password, enabled) VALUES
    ('admin', '$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq', TRUE ),
    ('user', '$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq', TRUE );

INSERT INTO user_roles (id, username, role) VALUES
    (1, 'admin', 'ADMIN'),
    (2, 'admin', 'USER'),
    (3, 'user', 'USER');
