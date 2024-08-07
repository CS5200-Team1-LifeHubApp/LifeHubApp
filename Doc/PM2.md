## Milestone 2: Relational Model and Data
- **Team Name:** Team 1
- **Group Members:** Alexander Dickey, Jiyu He, Derek Laister, Yueh-Chen Tsai, Yiwen Wang,Fan Zhou, Taiji Zhou Tai.
- **Project Name:** LifeHub App

## UML
![UML](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/UML.jpeg)

## Create table & Insert Data
```MySQL
/** 
Team Name: Team 1
Group Members: Alexander Dickey, Jiyu He, Derek Laister, Yueh-Chen Tsai, Yiwen Wang, Fan Zhou, Taiji Zhou Tai.
Project Name: LifeHub
*/

/**
SCHEMA (TABLES) FOR APPLICATION-------------------------------------------------------------------------------------------------------
*/
# Create the schema if necessary.
CREATE SCHEMA IF NOT EXISTS LifeHubApp;
USE LifeHubApp;

# Drop existing tables
DROP TABLE IF EXISTS CrimeActivity, Pet, FarmersMarket, Park, Restaurant, Review, Recommendation, Bookmark, NeighborhoodbyZip, Users;

# Create tables
CREATE TABLE Users (
    UserId INT AUTO_INCREMENT PRIMARY KEY,
    UserName VARCHAR(255),
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    PasswordHash VARCHAR(255)
);

CREATE TABLE NeighborhoodbyZip (
    NeighborZipId INT PRIMARY KEY,
    City VARCHAR(255)
);

CREATE TABLE Review (
    ReviewId INT AUTO_INCREMENT PRIMARY KEY,
    Created TIMESTAMP,
    Content TEXT,
    Rating DECIMAL(3, 2),
    UserId INT,
    NeighborZipId INT,
    FOREIGN KEY (UserId) REFERENCES Users(UserId),
    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
);

CREATE TABLE Recommendation (
    RecommendationId INT AUTO_INCREMENT PRIMARY KEY,
    UserId INT,
    NeighborZipId INT,
    FOREIGN KEY (UserId) REFERENCES Users(UserId),
    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
);

CREATE TABLE Bookmark (
    BookmarkId INT AUTO_INCREMENT PRIMARY KEY,
    Created TIMESTAMP,
    UserId INT,
    Description TEXT,
    NeighborZipId INT,
    FOREIGN KEY (UserId) REFERENCES Users(UserId),
    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
);

CREATE TABLE CrimeActivity (
    CaseId VARCHAR(255) PRIMARY KEY,
    City VARCHAR(255),
    State VARCHAR(255),
    NeighborZipId INT,
    CrimeName TEXT,
    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
);

CREATE TABLE Pet (
    LicenseId VARCHAR(255) PRIMARY KEY,
    Name VARCHAR(255),
    Species VARCHAR(255),
    PrimaryBreed VARCHAR(255),
    NeighborZipId INT,
    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
);

CREATE TABLE FarmersMarket (
    MarketId INT AUTO_INCREMENT PRIMARY KEY,
    NeighborZipId INT,
    Name VARCHAR(255),
    Dates VARCHAR(255),
    Hours VARCHAR(255),
    Website VARCHAR(255),
    MarketType ENUM('ALLYEAR', 'SEASONAL'),
    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
);

CREATE TABLE Park (
    ParkId INT,
    NeighborZipId INT,
    Name VARCHAR(255),
    FeatureId INT,
    Hours Text,
    FeatureDesc VARCHAR(255),
    PRIMARY KEY (ParkId, FeatureID),
    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
);

CREATE TABLE Restaurant (
    RestaurantId INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255),
    Description TEXT,
    Hours VARCHAR(255),
    Website VARCHAR(255),
    CuisineType ENUM('AFRICAN', 'AMERICAN', 'ASIAN', 'EUROPEAN', 'HISPANIC'),
	NeighborZipId INT,
    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
);


/**
DATA FOR TABLES-------------------------------------------------------------------------------------------------------
*/
#User  - Fan(Drafted by Yiwen, fake data generated by ChatGPT)
INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) 
	VALUES ('william52', 'Diana', 'Lopez', 'ihogan@hotmail.com', 'a9f24d8ae410ef9f4be7a97e70191144');
INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) 
	VALUES ('courtneybeltran', 'Thomas', 'Ball', 'kimberly36@yahoo.com', '26f4c52bbf823dc25ac3ea5fc45336c9');
INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) 
	VALUES ('padillakent', 'Susan', 'Pham', 'jasminerios@hotmail.com', '3cf6a717881d1569f98eef9188ed7a3e');
INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) 
	VALUES ('anthony22', 'Madeline', 'Beasley', 'lerobert@yahoo.com', '9a8ba99ab3f8a7a948c3864170cfcdce');
INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) 
	VALUES ('aaron86', 'Matthew', 'Hatfield', 'cherylmartin@gmail.com', 'b5e786f6536f152bf7ebda456b78aeec');
INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) 
	VALUES ('rushangel', 'David', 'Howell', 'yespinoza@hotmail.com', '063f0edd3d8dad1095f37cfaa9a11895');
INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) 
	VALUES ('samuelreed', 'Alexandra', 'Beck', 'hernandezmark@parker.com', '3e65d13213cf8ffd2d164afd69b46f35');
INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) 
	VALUES ('ychapman', 'Elizabeth', 'Nunez', 'margaret96@hotmail.com', 'b78802f386b50bd6c58214833ab5fddf');
INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) 
	VALUES ('allison17', 'Angela', 'Young', 'david02@morris-snyder.net', 'e9c091112f78090bcf950429de276110');
INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) 
	VALUES ('qfernandez', 'Rebecca', 'Leblanc', 'nicole35@yahoo.com', 'cd4d59fdba154586d6f2e74f2c7f483c');

#NeighborhoodbyZip - Yiwen https://drive.google.com/file/d/1nBoI5PxeSmpUOfEfBxl6yxoS5qhpOQ3N/view?usp=drive_link 
LOAD DATA INFILE '/tmp/King_County_by_Zipcode.csv'
INTO TABLE NeighborhoodbyZip
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(NeighborZipId, @dummy, City);

#Review - (Drafted by Yiwen, fake data generated by ChatGPT)
INSERT INTO Review (Created, Content, Rating, UserId, NeighborZipId) VALUES ('2023-12-18 19:50:51', 'Overrated, there are better options.', 2, 1, 98004);
INSERT INTO Review (Created, Content, Rating, UserId, NeighborZipId) VALUES ('2024-01-09 19:50:51', 'Overrated, there are better options.', 3, 2, 98105);
INSERT INTO Review (Created, Content, Rating, UserId, NeighborZipId) VALUES ('2023-04-22 19:50:51', 'Perfect for a quick visit.', 3, 3, 98004);
INSERT INTO Review (Created, Content, Rating, UserId, NeighborZipId) VALUES ('2023-11-22 19:50:51', 'Loved the place, very welcoming!', 2, 4, 98005);
INSERT INTO Review (Created, Content, Rating, UserId, NeighborZipId) VALUES ('2023-07-01 19:50:51', 'Perfect for a quick visit.', 1, 5, 98105);


#Recommendation - (Drafted by Yiwen, fake data generated by ChatGPT)
INSERT INTO Recommendation (UserId, NeighborZipId) 
	VALUES (1, 98004);
INSERT INTO Recommendation (UserId, NeighborZipId) 
	VALUES (2, 98005);
INSERT INTO Recommendation (UserId, NeighborZipId) 
	VALUES (3, 98105);
INSERT INTO Recommendation (UserId, NeighborZipId) 
	VALUES (4, 98006);
INSERT INTO Recommendation (UserId, NeighborZipId) 
	VALUES (5, 98105);

#Bookmark  - Jiyu - (Drafted by Yiwen, fake data generated by ChatGPT)
INSERT INTO Bookmark (Created, UserId, Description, NeighborZipId) 
	VALUES ('2023-11-15 19:52:42', 1, 'Must visit', 98104);
INSERT INTO Bookmark (Created, UserId, Description, NeighborZipId) 
	VALUES ('2023-08-30 19:52:42', 2, 'Must visit', 98105);
INSERT INTO Bookmark (Created, UserId, Description, NeighborZipId) 
	VALUES ('2023-03-24 19:52:42', 3, 'Loved it, must return', 98004);
INSERT INTO Bookmark (Created, UserId, Description, NeighborZipId) 
	VALUES ('2023-08-20 19:52:42', 4, 'Must visit', 98105);
INSERT INTO Bookmark (Created, UserId, Description, NeighborZipId) 
	VALUES ('2024-02-07 19:52:42', 5, 'Great for families', 98106);




# Crime - (Drafted by Yiwen) https://drive.google.com/file/d/1u3RKTWO_tejXZhUX_MUIZt1v4eBpAzOy/view?usp=drive_link 
LOAD DATA INFILE '/tmp/King_County_Crime_Report.csv'
	INTO TABLE CrimeActivity
	FIELDS TERMINATED BY ',' 
	ENCLOSED BY '"' 
	LINES TERMINATED BY '\n'
	IGNORE 1 ROWS
	(@CaseNumber, @CrimeName, @City, @State, @NeighborZipId)
	SET CaseId = @CaseNumber, 
		CrimeName = @CrimeName,
		City = @City,
        State = @State,
		NeighborZipId = (SELECT NeighborZipId FROM NeighborhoodbyZip WHERE NeighborZipId = @NeighborZipId);

# Pet - Yiwen https://drive.google.com/file/d/16Wdt9CuemW18SWMUJZEMKwxnaaZJpUXz/view?usp=drive_link 
LOAD DATA INFILE '/tmp/Seattle_Pet_Licenses_20240210.csv'
INTO TABLE Pet
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, @LicenseNumber, @Name, @Species, @PrimaryBreed, @dummy, @NeighborZipId)
SET LicenseId = @LicenseNumber, 
    Name = @Name,
    Species = @Species,
    PrimaryBreed = @PrimaryBreed,
    NeighborZipId = (SELECT NeighborZipId FROM NeighborhoodbyZip WHERE NeighborZipId = @NeighborZipId);


# Park - Fan - new data: https://drive.google.com/file/d/1WQUaDAIx9OC-DblzqMAEY_IvIuDJebag/view?usp=sharing
LOAD DATA INFILE '/tmp/Seattle_Parks_and_Recreation_Parks_Features_Cleaned.csv'
	INTO TABLE Park
	FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
	LINES TERMINATED BY '\n'
	IGNORE 1 LINES;

# Farmers Market - Jiyu((Drafted by Yiwen) https://drive.google.com/file/d/1gQ_O1X3x64YhEsf-cV3SUv_4JJFWfApH/view?usp=drive_link 
LOAD DATA INFILE '/tmp/King_County_Farmers_Market.csv'
	INTO TABLE FarmersMarket
	FIELDS TERMINATED BY ',' 
	ENCLOSED BY '"' 
	LINES TERMINATED BY '\n'
	IGNORE 1 ROWS
	(Name, @MarketType, Dates, Hours, @Address, @City, @ZIPCode, Website)
	SET MarketType = 
		CASE 
			WHEN @MarketType = 'Seasonal' THEN 'SEASONAL'
			WHEN @MarketType = 'Open Year-Round' THEN 'ALLYEAR'
			ELSE NULL
			END,
			NeighborZipId = (SELECT NeighborZipId FROM NeighborhoodbyZip WHERE NeighborZipId = @NeighborZipId);

# Restaurant - Alex(Drafted by Yiwen, use a ChatGPT generated fake restaurant list: https://drive.google.com/file/d/1X67MdOYK8EjR7qKvk9WXEiidzz2obRsX/view?usp=drive_link )
LOAD DATA INFILE '/tmp/Seattle_fake_restaurants.csv'
INTO TABLE Restaurant
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@dummy, @Name, @Description, @Hours, @Website, @CuisineType, @NeighborZipId)
SET Name = @Name, 
    Description = @Description, 
    Hours = @Hours, 
    Website = @Website, 
    CuisineType = @CuisineType, 
    NeighborZipId = (SELECT NeighborZipId FROM NeighborhoodbyZip WHERE NeighborZipId = @NeighborZipId);
```

## Row Count
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/Lines.png)
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/zipcode.png)

### Data includes:

| Rows  | From                                           | To               | TotalRows |
|-------|------------------------------------------------|------------------|-----------|
| 94356 | King_County_Crime_Report                       | CrimeActivity    | 138923    |
| 42523 | Seattle_Pet_Licenses_2024010                   | Pet              |           |
| 1307  | Seattle_Parks_and_Recreation_Parks_Features_Cleaned | Park          |           |
| 500   | Seattle_fake_restaurants                       | Restaurant       |           |
| 197   | King_County_by_Zipcode1                        | NeighborhoodbyZip|           |
| 40    | King_County_Farmers_Market                     | FarmersMarket    |           |


**Datasets Download:**
https://drive.google.com/drive/folders/10gOe0pzGZqtsO-ndveepLLHU_fpOao2V?usp=sharing

## Data Source:
- Crime Data by zip code:
https://data.kingcounty.gov/Law-Enforcement-Safety/KCSO-Offense-Reports-2020-to-Present/4kmt-kfqf/about_data
- Pet License data by zip code (to show how pet-friendly this district may be):
https://data.seattle.gov/Community/Seattle-Pet-Licenses/jguv-t9rb/about_data
- Parks by zip code:
https://data.seattle.gov/Parks-and-Recreation/Seattle-Parks-And-Recreation-Park-Addresses/v5tj-kqhc/about_data
https://data.seattle.gov/Parks-and-Recreation/Seattle-Parks-and-Recreation-Parks-Features/2cer-njie/about_data
- Farmers Market:
https://gis-kingcounty.opendata.arcgis.com/datasets/5c6b271048f94fc58089cc3225535315
- Neighborhood by Zipcode:
https://gis-kingcounty.opendata.arcgis.com/datasets/e6c555c6ae7542b2bdec92485892b6e6
- Restaurants: Generated by ChatGPT
- User: Generated by ChatGPT
- Review: Generated by ChatGPT
- Recommendation: Generated by ChatGPT
- Bookmark: Generated by ChatGPT