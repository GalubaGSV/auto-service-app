--liquibase formatted sql
--changeset <postgres>:<create-masters-table>
CREATE TABLE IF NOT EXISTS public.masters (
       id SERIAL PRIMARY KEY,
       name VARCHAR(255)
    );


--rollback DROP TABLE masters;