CREATE TABLE apartments
(
    id              SERIAL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     TEXT,
    price_per_night INTEGER      NOT NULL,
    rooms           INTEGER      NOT NULL,
    address_id      BIGINT       NOT NULL UNIQUE REFERENCES address_apartments (id),
    created_at      DATE,
    updated_at      DATE,
    deleted_at      DATE
);
