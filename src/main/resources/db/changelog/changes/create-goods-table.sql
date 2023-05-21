--liquibase formatted sql
--changeset <postgres>:<create-goods-table>
CREATE TABLE IF NOT EXISTS goods (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    order_id BIGINT REFERENCES orders(id)
    );

--rollback DROP TABLE goods;