CREATE TABLE VelesUser (
    id BIGINT NOT NULL PRIMARY KEY,
    lastName VARCHAR(255) NOT NULL,
    firstName VARCHAR(255),
    email VARCHAR(255),
    login VARCHAR(255) NOT NULL,
    password BIGINT NOT NULL,
    mobileNumber BIGINT,
    photo BYTEA
);

CREATE TABLE Category (
    id BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(255),
    parentId BIGINT REFERENCES Category (id),
    featuresTable VARCHAR(32)
);

CREATE TABLE Location (
    id BIGINT NOT NULL PRIMARY KEY,
    city VARCHAR(255),
    street VARCHAR(255)
);

CREATE TABLE Announcement (
    id BIGINT NOT NULL PRIMARY KEY,
    categoryId BIGINT REFERENCES Category (id) NOT NULL,
    title VARCHAR(255),
    userId BIGINT REFERENCES VelesUser (id) NOT NULL,
    description VARCHAR(512),
    locationId BIGINT REFERENCES Location (id) NOT NULL,
    cost INT
);

CREATE TABLE PhotoAnnouncement (
    id BIGINT NOT NULL PRIMARY KEY,
    announcementId BIGINT  REFERENCES Announcement (id) NOT NULL,
    src BYTEA
);

CREATE TABLE TvFeature (
    id BIGINT NOT NULL PRIMARY KEY,
    announcementId BIGINT  REFERENCES Announcement (id) NOT NULL,
    screenType VARCHAR(255),
    resolution VARCHAR(255),
    smartTv VARCHAR(255),
    refreshRate VARCHAR(255),
    diagonal VARCHAR(255),
    year INT,
    brand VARCHAR(255),
    model VARCHAR(255)
);

CREATE TABLE ComputerFeature (
    id BIGINT NOT NULL PRIMARY KEY,
    announcementId BIGINT  REFERENCES Announcement (id) NOT NULL,
    screenType VARCHAR(255),
    resolution VARCHAR(255),
    ram VARCHAR(255),
    processor VARCHAR(255),
    diagonal VARCHAR(255),
    graphicsCard VARCHAR(255),
    hardDisk VARCHAR(255),
    hardDiskType VARCHAR(255),
    year INT,
    brand VARCHAR(255),
    model VARCHAR(255)
);

CREATE TABLE AutoFeature (
    id BIGINT NOT NULL PRIMARY KEY,
    announcementId BIGINT  REFERENCES Announcement (id) NOT NULL,
    fuelType VARCHAR(255),
    engineCapacity VARCHAR(255),
    mileage VARCHAR(255),
    autoCategory VARCHAR(255),
    year INT,
    brand VARCHAR(255),
    model VARCHAR(255)
);





