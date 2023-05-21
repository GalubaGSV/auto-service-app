--liquibase formatted sql
--changeset <postgres>:<create-car-owners-table>
CREATE TABLE IF NOT EXISTS public.car_owners (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL
    );

--rollback DROP TABLE car_owners;