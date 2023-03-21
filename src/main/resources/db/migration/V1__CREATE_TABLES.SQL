CREATE TABLE Users (
    id BIGINT NOT NULL PRIMARY KEY,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Email varchar(255),
    Login varchar(255) NOT NULL,
    Password BIGINT NOT NULL,
    MobileNumber BIGINT,
    Photo BLOB
);
CREATE TABLE Announcement (
    id BIGINT NOT NULL PRIMARY KEY,
    CategoryId int REFERENCES Category (id) NOT NULL,
    Title varchar(255),
    UserId int REFERENCES Users (id) NOT NULL,
    Description varchar(512),
    LocationId int REFERENCES Location (id) NOT NULL,
    Cost int

);

create TABLE PhotoAnnouncement (
    id BIGINT NOT NULL PRIMARY KEY;
    AnnouncementId int  REFERENCES Announcement (id) NOT NULL,
    src BLOB
);

create TABLE Category (
    id BIGINT NOT NULL PRIMARY KEY;
    Title varchar(255),
    FeaturesId varchar(255) REFERENCES Features (id),
);

create TABLE Location (
    id BIGINT NOT NULL PRIMARY KEY;
    City varchar(255),
    Street varchar(255)
);

create TABLE Features (
    id BIGINT NOT NULL PRIMARY KEY;
   ???
);



