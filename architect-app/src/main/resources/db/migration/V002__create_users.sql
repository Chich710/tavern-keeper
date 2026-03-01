CREATE TABLE users
(
    id         INT8 PRIMARY KEY,
    name       VARCHAR,
    login      VARCHAR,
    password   VARCHAR,
    token      VARCHAR,
    role_id    INT8 REFERENCES role_info (id),
    created_at DATE,
    updated_at DATE,
    deleted_at DATE
);

CREATE SEQUENCE users_sequence START 2 INCREMENT 1;

INSERT INTO users
VALUES (1,
        'админ',
        'adminTest',
        'adminTest1',
        null,
        2,
        '2022-01-01',
        '2022-01-01',
        null);
