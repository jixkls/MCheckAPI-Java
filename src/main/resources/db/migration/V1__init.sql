CREATE TABLE IF NOT EXISTS specialties (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS cities (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    state CHAR(2) NOT NULL,
    constraint UQ_CITY UNIQUE(name, state)
);

CREATE TABLE IF NOT EXISTS doctors (
    id UUID PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    crm VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS doctor_specialties (
    doctor_id UUID NOT NULL,
    specialty_id BIGINT NOT NULL,

    PRIMARY KEY (doctor_id, specialty_id),

    CONSTRAINT fk_doctor_specialties_doctor
        FOREIGN KEY (doctor_id) REFERENCES doctors(id),

    CONSTRAINT fk_doctor_specialties_specialty
        FOREIGN KEY (specialty_id) REFERENCES specialties(id)
);

CREATE TABLE IF NOT EXISTS doctor_cities (
    doctor_id UUID NOT NULL,
    city_id BIGINT NOT NULL,

    PRIMARY KEY (doctor_id, city_id),

    CONSTRAINT fk_doctor_cities_doctor
        FOREIGN KEY (doctor_id) REFERENCES doctors(id),

    CONSTRAINT fk_doctor_cities_city
        FOREIGN KEY (city_id) REFERENCES cities(id)
);

CREATE INDEX IF NOT EXISTS idx_doctors_name ON doctors(name);