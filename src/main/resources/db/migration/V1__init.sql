CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS doctors (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(120) NOT NULL,
    crm VARCHAR(20) NOT NULL UNIQUE,
    city VARCHAR(80) NOT NULL,
    specialty BIGINT NOT NULL,
    phone VARCHAR(30),
    email VARCHAR(120)
);

CREATE INDEX idx_doctors_name ON doctors(name);