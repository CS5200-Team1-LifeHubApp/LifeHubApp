# Milestone 3: Business Insights
- **Team Name:** Team 1
- **Group Members:** Alexander Dickey, Jiyu He, Derek Laister, Yueh-Chen Tsai, Yiwen Wang,Fan Zhou, Taiji Zhou Tai.
- **Project Name:** LifeHub App

- **NOTE:** Our relational model design is sufficient and does NOT require changes to answer any of the
below questions. As such, no updates to the team UML were needed.

## Database Build Verification
![Database Build Verification](https://github.com/YiwenW312/LifeHubApp/blob/main/Img/DataPM3.png)

## Top 5 Advanced Feature Insightful Questions (with result output):
1. Show the names and the hours of all parks with baseball/softball or play fields in neighborhoods with a crime count lower than 100.
![]()
Team 1 Database Management Systems – PM3 CS5200 - Spring ‘24
2. Which farmers markets open on Saturday located within the top 5 highest-rated
neighborhoods?
![]()
Team 1 Database Management Systems – PM3 CS5200 - Spring ‘24
3. Based on the city, how many total pets are licensed in it and contain the name “Betty
Boop”?
![]()
4. Top 10 neighborhoods have the most recommendations from users?
![]()
5. What is the safest neighborhood based on the lowest crime rates?
![]()
## Remaining Top 5 Insightful Questions:
1. What are the most common types of crimes reported in King County by city?
![]()

(NOTE: Image does not contain all results)
2. What are the most common types of cuisine offered by restaurants in Seattle, by ZIPcode?
![]()
(NOTE: Image does not contain all results)
3. What are the most popular pet breeds licensed in Seattle, by ZIP code?
(NOTE: Image does not contain all results)
![]()

4. In which ZIP code area do the most diverse types of restaurants operate in Seattle?
![]()
5. Top 10 neighborhoods have the highest average rating?
![]()

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