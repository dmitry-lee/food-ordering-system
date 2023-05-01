DROP SCHEMA IF EXISTS payment CASCADE;

CREATE SCHEMA payment;

CREATE EXTENSION IF NOT EXISTS "uuid-oosp";

DROP TYPE IF EXISTS payment_status;

CREATE TYPE payment_status AS ENUM ('COMPLETED', 'CANCELLED', 'FAILED');

DROP TABLE IF EXISTS "payment".payments CASCADE;

CREATE TABLE "payment".payments
(
  id UUID NOT NULL,
   customer_id UUID NOT NULL,
   order_id UUID NOT NULL,
   price NUMERIC(10, 2) NOT NULL,
   status VARCHAR(255),
   created_at TIMESTAMP with time zone NOT NULL,
   status payment_status NOT NULL,
   CONSTRAINT pk_payments PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "payment".credit_entry CASCADE;

CREATE TABLE "payment".credit_entry (
  id UUID NOT NULL,
   customer_id UUID NOT NULL,
   total_credit_amount numeric(10, 2) NOT NULL,
   CONSTRAINT pk_credit_entry PRIMARY KEY (id)
);

DROP TYPE IF EXISTS transaction_type;

CREATE TYPE transaction_type AS ENUM ('DEBIT', 'CREDIT');

CREATE TABLE credit_history (
  id UUID NOT NULL,
   customer_id UUID NOT NULL,
   amount numeric(10, 2) NOT NULL,
   transaction_type transaction_type  NOT NULL,
   CONSTRAINT pk_credit_history PRIMARY KEY (id)
);