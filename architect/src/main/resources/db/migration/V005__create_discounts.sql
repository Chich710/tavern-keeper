CREATE TABLE discounts
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR NOT NULL,
    description      TEXT,
    discount_percent INTEGER      NOT NULL,
    condition_type   VARCHAR  NOT NULL,
    condition_value  VARCHAR,
    start_date       DATE,
    end_date         DATE,
    active           BOOLEAN DEFAULT TRUE,
    created_at       DATE,
    updated_at       DATE,
    deleted_at       DATE
);
