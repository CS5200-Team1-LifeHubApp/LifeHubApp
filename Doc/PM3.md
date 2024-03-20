# Milestone 3: Business Insights
- **Team Name:** Team 1
- **Group Members:** Alexander Dickey, Jiyu He, Derek Laister, Yueh-Chen Tsai, Yiwen Wang,Fan Zhou, Taiji Zhou Tai.
- **Project Name:** LifeHub App

- **NOTE:** Our relational model design is sufficient and does NOT require changes to answer any of the
below questions. As such, no updates to the team UML were needed.

## Database Build Verification
![Database Build Verification](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/DataPM3.png)

## Top 5 Advanced Feature Insightful Questions (with result output):
**NOTE: Image does not contain all results**
1. Show the names and the hours of all parks with baseball/softball or play fields in neighborhoods with a crime count lower than 100.
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/1-resturanttime.png)
Team 1 Database Management Systems – PM3 CS5200 - Spring ‘24
2. Which farmers markets open on Saturday located within the top 5 highest-rated
neighborhoods?
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/2-farmersmarket.png)
Team 1 Database Management Systems – PM3 CS5200 - Spring ‘24
3. Based on the city, how many total pets are licensed in it and contain the name “Betty Boop”?
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/3-BettyBoo.png)
4. Top 10 neighborhoods have the most recommendations from users?
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/4-toprec.png)
5. What is the safest neighborhood based on the lowest crime rates?
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/5-safest.png)
## Remaining Top 5 Insightful Questions:
1. What are the most common types of crimes reported in King County by city?
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/1-MostCommoncrime.png)


2. What are the most common types of cuisine offered by restaurants in Seattle, by ZIPcode?
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/2-mostcommonrest.png)

3. What are the most popular pet breeds licensed in Seattle, by ZIP code?

![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/3-mostpoppetbreeds.png)

4. In which ZIP code area do the most diverse types of restaurants operate in Seattle?
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/4-Cur_Div.png)
5. Top 10 neighborhoods have the highest average rating?
![](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/5-AverageRating.png)

## Runner-up Insightful Questions (in no particular order):
1. What is the average restaurant rating of each neighborhood?
2. Which cities have the most all-year open farmers markets?
3. How many zipcodes are contained in a city?
4. How many farmers' markets are open year-round in King County, by ZIP code?
5. What are the most common park features in Seattle, by ZIP code?
6. Which ZIP code in King County has the highest number of crimes reported?
7. What is the average number of pets licensed per ZIP code in Seattle?
8. Which city in King County has the highest average number of park features per park?
9. Top 10 neighborhoods have the most parks?
7
Team 1 Database Management Systems – PM3 CS5200 - Spring ‘24
10. Which neighborhoods have the highest average rating?
11. What are the crime trends in each neighborhood over the last year?

## 10 Questions (Queries)
### Top 5 Advanced Feature Insightful Questions:
1. Show the names and the hours of all parks with playgrounds or sports fields in
neighborhoods with a crime count lower than 100.
```MySQL
SELECT p.Name, p.Hours
FROM Park p
JOIN (
SELECT NeighborZipId
FROM CrimeActivity
GROUP BY NeighborZipId
HAVING COUNT(*) < 100
) c
ON p.NeighborZipId = c.NeighborZipId
WHERE p.FeatureDesc LIKE '%ase%' OR p.FeatureDesc LIKE '%lay';
```
2. Which farmers markets open on Saturday located within the top 5 highest-rated
neighborhoods?
```MySQL
SELECT FarmersMarket.Name, FarmersMarket.NeighborZipId,
NeighborhoodbyZip.City
FROM FarmersMarket
INNER JOIN NeighborhoodbyZip
ON FarmersMarket.NeighborZipId = NeighborhoodbyZip.NeighborZipId
INNER JOIN (
SELECT N2.City, AVG(R.Rating) AS CityRatings
FROM Review R
INNER JOIN NeighborhoodbyZip N2
ON R.NeighborZipId = N2.NeighborZipId
GROUP BY N2.City
ORDER BY CityRatings DESC
LIMIT 5
) AS TopFiveCities
ON NeighborhoodbyZip.City = TopFiveCities.City
WHERE FarmersMarket.Dates LIKE '%Sat%';
```
3. Based on the city, how many total pets are licensed in it and contain the name “Betty Boop”?
```MySQL
SELECT zip.City,
COUNT(pname.Name) AS NamedBettyBoop
FROM NeighborhoodbyZip zip
JOIN Pet pname ON zip.NeighborZipId = pname.NeighborZipId
WHERE pname.Name = 'Betty Boop'
GROUP BY zip.City;
```
4. Top 10 neighborhoods that have the most recommendations from users?
```MySQL
SELECT n.NeighborZipId,
COUNT(r.RecommendationId) AS TotalRecommendations
FROM NeighborhoodbyZip n
JOIN Recommendation r
ON n.NeighborZipId = r.NeighborZipId
GROUP BY n.NeighborZipId
ORDER BY TotalRecommendations DESC
LIMIT 10;
```
5. What is the safest neighborhood based on the lowest crime rates?
```MySQL
SELECT n.City AS SafestNeighborhood
FROM NeighborhoodbyZip n
LEFT JOIN CrimeActivity ca
ON n.NeighborZipId = ca.NeighborZipId
GROUP BY SafestNeighborhood
ORDER BY
COUNT(ca.CaseId) ASC
LIMIT 1;
```

### Remaining Top 5 Insightful Queries:
1. What are the most common types of crimes reported in King County by city?
```MySQL
SELECT City, CrimeName,
COUNT(CaseId) AS CrimeCount
FROM CrimeActivity
GROUP BY City, CrimeName
ORDER BY City, CrimeCount DESC;
```
2. What are the most common types of cuisine offered by restaurants in Seattle, by ZIPcode?
```MySQL
SELECT NeighborZipId, CuisineType,
COUNT(RestaurantID) AS RestaurantCount
FROM Restaurant
GROUP BY NeighborZipId, CuisineType
ORDER BY NeighborZipId, RestaurantCount DESC;
```
3. What are the most popular pet breeds licensed in Seattle, by ZIP code?
```MySQL
SELECT
p.NeighborZipId,
p.PrimaryBreed,
COUNT(p.LicenseId) AS LicenseCount
FROM
Pet p
INNER JOIN (
SELECT
NeighborZipId,
PrimaryBreed,
COUNT(LicenseId) AS BreedCount
FROM
Pet
GROUP BY
NeighborZipId, PrimaryBreed
) AS BreedCounts ON p.NeighborZipId = BreedCounts.NeighborZipId AND
p.PrimaryBreed = BreedCounts.PrimaryBreed
INNER JOIN (
SELECT
NeighborZipId,
MAX(BreedCount) AS MaxBreedCount
FROM (
SELECT
NeighborZipId,
PrimaryBreed,
COUNT(LicenseId) AS BreedCount
FROM
Pet
GROUP BY
NeighborZipId, PrimaryBreed
) AS SubBreedCounts
GROUP BY
NeighborZipId
) AS MaxCounts ON BreedCounts.NeighborZipId = MaxCounts.NeighborZipId AND
BreedCounts.BreedCount = MaxCounts.MaxBreedCount
GROUP BY
p.NeighborZipId, p.PrimaryBreed
ORDER BY
p.NeighborZipId, LicenseCount DESC;
```
4. In which ZIP code area do the most diverse types of restaurants operate in Seattle?
```MySQL
SELECT R.NeighborZipId,
COUNT(DISTINCT R.CuisineType) AS CuisineDiversity
FROM Restaurant R
GROUP BY R.NeighborZipId
ORDER BY CuisineDiversity DESC
LIMIT 1;
```
5. Top 10 neighborhoods have the highest average rating?
```MySQL
SELECT n.NeighborZipId, ROUND(AVG(r.Rating), 2) AS AverageRating
FROM NeighborhoodbyZip n
JOIN Review r ON n.NeighborZipId = r.NeighborZipId
GROUP BY n.NeighborZipId
ORDER BY AverageRating DESC
LIMIT 10;
```

### Runner-up Insightful Queries (in no particular order; CODE HAS NOT BEEN TESTED):
1. What is the average restaurant rating of each neighborhood?
```MySQL
SELECT NeighborZipId, AVG(Rating) AS AvgRating
FROM Restaurant
GROUP BY NeighborZipId;
```
2. Which cities have the most all-year open farmers markets?
```MySQL
SELECT
NeighborhoodbyZip.City
COUNT(*) AS MarketCount
FROM FarmersMarket
INNER JOIN NeighborhoodbyZip
ON FarmersMarket.NeighborZipId = NeighborhoodbyZip.NeighborZipId
WHERE FarmersMarket.MarketType = 'ALLYEAR'
GROUP BY NeighborhoodbyZip.City
ORDER BY MarketCount DESC;
```
3. How many zipcodes are contained in a city?
```MySQL
SELECT City,
COUNT(DISTINCT ZIPCode) AS TotalZipCodes
FROM NeighborhoodbyZip
GROUP BY City
ORDER BY TotalZipCodes DESC;
```
4. Which cities in King County have the most ZIP codes?
```MySQL
SELECT City, COUNT(ZipCode) AS ZipCodeCount
FROM ZipCodes
GROUP BY City
ORDER BY ZipCodeCount DESC;
```
5. How many farmers' markets are open year-round in King County, by ZIP code?
```MySQL
SELECT ZipCode, COUNT(MarketID) AS MarketCount
FROM FarmersMarkets
WHERE OpenDates LIKE '%Year-Round%'
GROUP BY ZipCode
ORDER BY MarketCount DESC;
```
6. What are the most common park features in Seattle, by ZIP code?
```MySQL
SELECT ZipCode, Description, COUNT(ParkID) AS FeatureCount
FROM Parks
GROUP BY ZipCode, Description
ORDER BY ZipCode, FeatureCount DESC;
```
7. Which ZIP code in King County has the highest number of crimes reported?
```MySQL
SELECT C.ZipCode, COUNT(C.CaseNumber) AS CrimeCount
FROM Crimes C
JOIN ZipCodes Z ON C.ZipCode = Z.ZipCode
GROUP BY C.ZipCode
ORDER BY CrimeCount DESC
LIMIT 1;
```
8. What is the average number of pets licensed per ZIP code in Seattle?
```MySQL
SELECT AVG(PetCount) AS AvgPetsPerZip
FROM (
SELECT ZipCode, COUNT(LicenseNumber) AS PetCount
FROM PetLicenses
GROUP BY ZipCode
) AS ZipPetCounts;
```
9. Which city in King County has the highest average number of park features per
park?
```MySQL
SELECT Z.City, AVG(FeatureCount) AS AvgFeaturesPerPark
FROM (
SELECT P.ZipCode, P.Name, COUNT(FeatureID) AS FeatureCount
FROM Parks P
GROUP BY P.ZipCode, P.Name
) AS ParkFeatures
JOIN ZipCodes Z ON ParkFeatures.ZipCode = Z.ZipCode
GROUP BY Z.City
ORDER BY AvgFeaturesPerPark DESC
LIMIT 1;
```
10. Top 10 neighborhoods have the most parks?
```MySQL
SELECT Neighborhood, COUNT(*) AS NumberOfParks
FROM Parks
GROUP BY Neighborhood
ORDER BY NumberOfParks DESC
LIMIT 10;
```
11. Which neighborhoods have the highest average rating?
```MySQL
SELECT NeighborhoodbyZip.City AS Neighborhood,
AVG(Review.Rating) AS AverageRating
FROM NeighborhoodbyZip
JOIN Review ON NeighborhoodbyZip.NeighborZipId = Review.NeighborZipId
GROUP BY NeighborhoodbyZip.City
ORDER BY AverageRating DESC;
```
12. What are the crime trends in each neighborhood over the last year?
```MySQL
SELECT NeighborZipId, CrimeName, COUNT(*) AS TotalCases
FROM CrimeActivity
WHERE DATEDIFF (CURDATE (), Created) <= 365
GROUP BY NeighborZipId, CrimeName;
```


## Full code
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


    
/**
DATA QUERIES-------------------------------------------------------------------------------------------------------------
*/   
# Top 5 Advanced Feature Insightful Questions:
-- Show the names and the hours of all parks with playgrounds or sports fields in neighborhoods with a crime count lower than 100.
SELECT p.Name, p.Hours
FROM Park p
JOIN (
	SELECT NeighborZipId
	FROM CrimeActivity
	GROUP BY NeighborZipId
	HAVING COUNT(*) < 100
) c 
	ON p.NeighborZipId = c.NeighborZipId
WHERE p.FeatureDesc LIKE '%ase%' OR p.FeatureDesc LIKE '%lay'; # search for strings like "Baseball/Softball" and "Play Area"

-- Which farmers markets open on Saturday located within the top 5 highest-rated neighborhoods?
SELECT FarmersMarket.Name, FarmersMarket.NeighborZipId, NeighborhoodbyZip.City
FROM FarmersMarket
INNER JOIN NeighborhoodbyZip
    ON FarmersMarket.NeighborZipId = NeighborhoodbyZip.NeighborZipId
INNER JOIN (
    SELECT N2.City, AVG(R.Rating) AS CityRatings
    FROM Review R
    INNER JOIN NeighborhoodbyZip N2
        ON R.NeighborZipId = N2.NeighborZipId
    GROUP BY N2.City
    ORDER BY CityRatings DESC
    LIMIT 5
) AS TopFiveCities 
	ON NeighborhoodbyZip.City = TopFiveCities.City
WHERE FarmersMarket.Dates LIKE '%Sat%';


-- Based on the city, how many total pets are licensed in it and contain the name “Betty Boop”?
SELECT zip.City,
    COUNT(pname.Name) AS NamedBettyBoop
FROM NeighborhoodbyZip zip
JOIN Pet pname ON zip.NeighborZipId = pname.NeighborZipId
WHERE pname.Name = 'Betty Boop'
GROUP BY zip.City;


-- Top 10 neighborhoods have the most recommendations from users?
SELECT n.NeighborZipId, 
       COUNT(r.RecommendationId) AS TotalRecommendations
FROM NeighborhoodbyZip n
JOIN Recommendation r 
	ON n.NeighborZipId = r.NeighborZipId
GROUP BY n.NeighborZipId
ORDER BY TotalRecommendations DESC
LIMIT 10;

-- What is the safest neighborhood based on the lowest crime rates?
SELECT n.City AS SafestNeighborhood
FROM NeighborhoodbyZip n
LEFT JOIN CrimeActivity ca 
	ON n.NeighborZipId = ca.NeighborZipId
GROUP BY SafestNeighborhood
ORDER BY 
    COUNT(ca.CaseId) ASC
LIMIT 1;    
    

# Remaining Top 5 Insightful Queries:
-- What are the most common types of crimes reported in King County by city?
SELECT City, CrimeName, 
    COUNT(CaseId) AS CrimeCount
FROM CrimeActivity
GROUP BY City, CrimeName
ORDER BY City, CrimeCount DESC;


-- What are the most common types of cuisine offered by restaurants in Seattle, by ZIP code?
SELECT NeighborZipId, CuisineType, 
	COUNT(RestaurantID) AS RestaurantCount
FROM Restaurant
GROUP BY NeighborZipId, CuisineType
ORDER BY NeighborZipId, RestaurantCount DESC;


-- What are the most popular pet breeds licensed in Seattle, by ZIP code?
SELECT
  p.NeighborZipId,
  p.PrimaryBreed,
  COUNT(p.LicenseId) AS LicenseCount
FROM
  Pet p
INNER JOIN (
  SELECT
    NeighborZipId,
    PrimaryBreed,
    COUNT(LicenseId) AS BreedCount
  FROM
    Pet
  GROUP BY
    NeighborZipId, PrimaryBreed
) AS BreedCounts ON p.NeighborZipId = BreedCounts.NeighborZipId AND p.PrimaryBreed = BreedCounts.PrimaryBreed
INNER JOIN (
  SELECT
    NeighborZipId,
    MAX(BreedCount) AS MaxBreedCount
  FROM (
    SELECT
      NeighborZipId,
      PrimaryBreed,
      COUNT(LicenseId) AS BreedCount
    FROM
      Pet
    GROUP BY
      NeighborZipId, PrimaryBreed
  ) AS SubBreedCounts
  GROUP BY
    NeighborZipId
) AS MaxCounts ON BreedCounts.NeighborZipId = MaxCounts.NeighborZipId AND BreedCounts.BreedCount = MaxCounts.MaxBreedCount
GROUP BY
  p.NeighborZipId, p.PrimaryBreed
ORDER BY
  p.NeighborZipId, LicenseCount DESC;


-- In which ZIP code area do the most diverse types of restaurants operate in Seattle?
SELECT R.NeighborZipId, 
	COUNT(DISTINCT R.CuisineType) AS CuisineDiversity
FROM Restaurant R
GROUP BY R.NeighborZipId
ORDER BY CuisineDiversity DESC
LIMIT 1;

-- Top 10 neighborhoods have the highest average rating?
SELECT n.NeighborZipId, ROUND(AVG(r.Rating), 2) AS AverageRating
FROM NeighborhoodbyZip n
JOIN Review r ON n.NeighborZipId = r.NeighborZipId
GROUP BY n.NeighborZipId
ORDER BY AverageRating DESC
LIMIT 10;
```