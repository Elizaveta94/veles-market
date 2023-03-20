create TABLE Users (
    UserID int NOT NULL PRIMARY KEY,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Email varchar(255),
    Login varchar(255);
    Passwors int NOT NULL,
    MobileNumber int,
    Photo???
);
create TABLE Announcement (
    AnnouncementID int NOT NULL PRIMARY KEY,
    Category_id int NOT NULL,
    FOREIGN KEY (Category_id)  REFERENCES Category_id (Category_idID),
    Title varchar(255),
    User_id int NOT NULL,
    FOREIGN KEY (User_id)  REFERENCES Users (UserID),
    Description varchar(512),
    Location_id int NOT NULL,
    FOREIGN KEY (Location_id)  REFERENCES Location_id (Loction_idID),
    cost int

);

create TABLE PhotoAnnouncement (
    PhotoAnnouncementID int NOT NULL PRIMARY KEY;
    Announcement_id int NOT NULL,
    FOREIGN KEY (Announcement_id)  REFERENCES Announcement (AnnouncementID),
    src??
);

create TABLE Category_id (
    Category_idID int NOT NULL PRIMARY KEY;
    Title varchar(255),
    Features_id varchar(255)
    FOREIGN KEY (Features_id)  REFERENCES Features_id (Features_idID),
);

create TABLE Location_id (
    Loction_idID int NOT NULL PRIMARY KEY;
    City varchar(255),
    Street varchar(255)
);

create TABLE Features_id (
    Features_idID int NOT NULL PRIMARY KEY;
   ???
);



