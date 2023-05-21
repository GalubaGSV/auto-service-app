--liquibase formatted sql
--changeset <postgres>:<create-orders-table>
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    car_id BIGINT REFERENCES cars(id),
    problem_description TEXT,
    start_date DATE,
    order_status VARCHAR(255),
    final_price NUMERIC(10, 2),
    final_date DATE
    );

--rollback DROP TABLE orders;
