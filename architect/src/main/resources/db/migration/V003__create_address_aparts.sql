CREATE TABLE address_apartments
(
    id               SERIAL PRIMARY KEY,
    city             VARCHAR(255) NOT NULL,
    street           VARCHAR(255) NOT NULL,
    house_number     VARCHAR(50)  NOT NULL,
    floor            INTEGER,
    apartment_number VARCHAR(50)
);
