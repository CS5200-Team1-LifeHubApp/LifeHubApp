# Milestone 1: Definition
- **Team Name:** Team 1
- **Group Members:** Alexander Dickey, Jiyu He, Derek Laister, Yueh-Chen Tsai, Yiwen Wang,Fan Zhou, Taiji Zhou Tai.
- **Project Name:** LifeHub App
# One-sentence value proposition:
LifeHub Is a comprehensive neighborhood discovery platform that provides insights on local community information like local groceries, pet-friendly parks, farmers markets events, walkable greenways, etc., specially designed for prospective residents in the Seattle Area who are unhappy with the transaction-driven approach of mainstream realestate websites and seeking a community-focused and green living experience.
# Features/capabilities to be delivered and possible future additions:
- A major intended launch feature is to search a neighborhood for features such as
available parks and restaurants.Possible future features could include, but are not limited to, crime activity, restaurant options including type of food offered, pet-friendly areas for walking and playing with common animals, convenience of public transport, bike friendliness, and community park information.
- Our advanced feature will be personalized search based on key criteria (information on pet-friendly neighborhoods, information on local education options (including higher education), and available restaurants and their culinary offering).
- Data sources (not expansive):
1) Seattle Residential Data:
    - Seattle Zoning Details (name, location, zone area, zone location, municipal data link, public description – what we will likely want--; comes with unique “object” ids and another ID – not sure for what)
2) Parks and Walkable City
    - Park Boundaries (name, location, park area data; comes with unique “object” ids for each park) https://geo.wa.gov/datasets/wa-stateparks::parks-park-boundaries-1/about (207 rows)
3) Community Life
    - Farmers Market: https://wafarmersmarkets.org/farmers-market-support/farmersmarket-data/
- Data Extraction Methodology:
    - Each csv dataset will be parsed, extracting key details (like geolocation or zip code data) in which information can be sorted further into other data tables (i.e. parks, restaurants, traffic, etc.) based on query searched information.