CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR,
    login      VARCHAR,
    password   VARCHAR,
    token      VARCHAR,
    role_id    INT8 REFERENCES role_info (id),
    created_at DATE,
    updated_at DATE,
    deleted_at DATE
);

INSERT INTO users
VALUES (1,
        'админ',
        'adminLogin',
        'adminPass',
        null,
        2,
        '2022-01-01',
        '2022-01-01',
        null);

SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
