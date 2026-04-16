CREATE TABLE apartment_bookings
(
    id               SERIAL PRIMARY KEY,
    apartment_id     BIGINT  NOT NULL REFERENCES apartments (id),
    user_id          BIGINT  NOT NULL REFERENCES users (id),
    check_in         DATE    NOT NULL,
    check_out        DATE    NOT NULL,
    price_per_night  INTEGER NOT NULL,
    discount_percent INTEGER DEFAULT 0,
    total_price      INTEGER NOT NULL,
    created_at       DATE,
    updated_at       DATE,
    deleted_at       DATE
);
