--liquibase formatted sql
--changeset <postgres>:<create-cars-table>
CREATE TABLE IF NOT EXISTS cars (
    id SERIAL PRIMARY KEY,
    manufacturer VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    year INTEGER NOT NULL,
    car_number VARCHAR(255) NOT NULL,
    owner_id INTEGER REFERENCES car_owners(id)
    );

--rollback DROP TABLE cars;