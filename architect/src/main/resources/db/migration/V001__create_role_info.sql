CREATE TABLE role_info
(
    id        SERIAL PRIMARY KEY,
    role_code VARCHAR,
    role_name VARCHAR
);

INSERT INTO role_info
VALUES (1, 'guest', 'гость'),
       (2, 'admin', 'админ'),
       (3, 'user', 'пользователь');