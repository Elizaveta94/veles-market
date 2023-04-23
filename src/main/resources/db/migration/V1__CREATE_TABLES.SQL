CREATE SEQUENCE veles_user_id_seq;

CREATE TABLE veles_user (
    id BIGINT NOT NULL PRIMARY KEY,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE ,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    mobile_number BIGINT UNIQUE,
    photo BYTEA
);

CREATE SEQUENCE category_id_seq;

CREATE TABLE category (
    id BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(255),
    parent_id BIGINT REFERENCES category (id),
    feature_category VARCHAR(32)
);

CREATE SEQUENCE location_id_seq;

CREATE TABLE location (
    id BIGINT NOT NULL PRIMARY KEY,
    city VARCHAR(255),
    street VARCHAR(255)
);

CREATE SEQUENCE announcement_id_seq;

CREATE TABLE announcement (
    id BIGINT NOT NULL PRIMARY KEY,
    category_id BIGINT REFERENCES category (id) NOT NULL,
    title VARCHAR(255),
    user_id BIGINT REFERENCES veles_user (id) NOT NULL,
    description VARCHAR(512),
    location_id BIGINT REFERENCES location (id) NOT NULL,
    cost INT
);

CREATE SEQUENCE photo_announcement_id_seq;

CREATE TABLE photo_announcement (
    id BIGINT NOT NULL PRIMARY KEY,
    announcement_id BIGINT  REFERENCES announcement (id) NOT NULL,
    src BYTEA
);

CREATE SEQUENCE tv_feature_id_seq;

CREATE TABLE tv_feature (
    id BIGINT NOT NULL PRIMARY KEY,
    announcement_id BIGINT  REFERENCES announcement (id) NOT NULL,
    screen_type VARCHAR(255),
    resolution VARCHAR(255),
    smart_tv VARCHAR(255),
    refresh_rate VARCHAR(255),
    diagonal VARCHAR(255),
    year INT,
    brand VARCHAR(255),
    model VARCHAR(255)
);

CREATE SEQUENCE computer_feature_id_seq;

CREATE TABLE computer_feature (
    id BIGINT NOT NULL PRIMARY KEY,
    announcement_id BIGINT  REFERENCES announcement (id) NOT NULL,
    screen_type VARCHAR(255),
    resolution VARCHAR(255),
    ram VARCHAR(255),
    processor VARCHAR(255),
    diagonal VARCHAR(255),
    computer_type VARCHAR(255),
    graphics_card VARCHAR(255),
    hard_disk VARCHAR(255),
    hard_disk_type VARCHAR(255),
    year INT,
    brand VARCHAR(255),
    model VARCHAR(255)
);

CREATE SEQUENCE auto_feature_id_seq;

CREATE TABLE auto_feature (
    id BIGINT NOT NULL PRIMARY KEY,
    announcement_id BIGINT  REFERENCES announcement (id) NOT NULL,
    fuel_type VARCHAR(255),
    engine_capacity INT,
    mileage INT,
    auto_category VARCHAR(255),
    year INT,
    brand VARCHAR(255),
    model VARCHAR(255)
);





