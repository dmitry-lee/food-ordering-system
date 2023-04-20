DROP SCHEMA IF EXISTS "order" CASCADE;

CREATE SCHEMA "order";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS order_status;
CREATE TYPE order_status AS ENUM ('PENDING', 'PAID', 'APPROVED', 'CANCELLED', 'CANCELLING');

DROP TABLE IF EXISTS "order".orders CASCADE;
CREATE TABLE "order".orders
(
   id UUID NOT NULL,
   customer_id UUID NOT NULL,
   restaurant_id UUID NOT NULL,
   tracking_id UUID NOT NULL,
   price NUMERIC(10,2) NOT NULL,
   order_status order_status NOT NULL,
   failure_messages character varying COLLATE pg_cagalog."default",
   CONSTRAINT orders_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "order".order_items
CREATE TABLE "order".order_items
(
   id BIGINT NOT NULL,
   order_id UUID NOT NULL,
   product_id UUID NOT NULL,
   price NUMERIC(10,2) NOT NULL,
   quantity INTEGER NOT NULL,
   sub_total NUMERIC(10,2) NOT NULL,
   CONSTRAINT order_items_pkey PRIMARY KEY (id, order_id)
);

ALTER TABLE "order".order_items
    ADD CONSTRAINT "FK_ORDER_ID" FOREIGN KEY (order_id)
    REFERENCES "order".orders (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

DROP TABLE IF EXISTS "order".order_address
CREATE TABLE "order".order_address
(
   id UUID NOT NULL,
   order_id UUID UNIQUE NOT NULL,
   street character varying COLLATE pg_cagalog."default" NOT NULL,
   postal_code character varying COLLATE pg_cagalog."default" NOT NULL,
   city character varying COLLATE pg_cagalog."default" NOT NULL,
   CONSTRAINT order_address_pkey PRIMARY KEY (id, order_id)
);

ALTER TABLE "order".order_address
    ADD CONSTRAINT "FK_ORDER_ID" FOREIGN KEY (order_id)
    REFERENCES "order".orders (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;
