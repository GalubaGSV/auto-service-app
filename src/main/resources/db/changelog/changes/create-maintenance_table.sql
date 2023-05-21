--liquibase formatted sql
--changeset <postgres>:<create-maintenance-table>
CREATE TABLE IF NOT EXISTS maintenance (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    order_id BIGINT REFERENCES orders(id),
    master_id BIGINT REFERENCES masters(id),
    price NUMERIC(10, 2),
    payment_status VARCHAR(255)
    );

--rollback DROP TABLE maintenance;