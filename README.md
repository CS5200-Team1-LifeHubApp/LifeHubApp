# LifeHubApp

- **Title:** Readme file
- **Date:** Mar 19, 2024
- **Author:** Yiwen Wang
- **Version:** 1.0
- **Current Milestone:** 4
- [MileStone] - milestone documentation

**Revision History**
|Date|Version|Description|Author|
|:----:|:----:|:----:|:----:|
|Mar 19, 2024|1.0|Initial release| Yiwen Wang|

# Introduction
- This is a project for CS5200: Introduction to Database Management Systems, Spring 2024. 
- **Team:** Team 1
- **Team Member:** Alexander Dickey, Jiyu He, Derek Laister, Yueh-Chen Tsai, Yiwen Wang,Fan Zhou, Taiji Zhou Tai
- **Project Name**: LifeHub App
- **LifeHub** is a comprehensive neighborhood discovery platform that provides insights on local community information like local groceries, pet-friendly parks, farmers markets events, walkable greenways, etc., specially designed for prospective residents in the Seattle Area who are unhappy with the transaction-driven approach of mainstream realestate websites and seeking a community-focused and green living experience.
![lifehub logo](https://github.com/CS5200-Team1-LifeHubApp/LifeHubApp/blob/main/Img/LifeHubLogo.png)
- Demo Slides: https://docs.google.com/presentation/d/148SSuBghP_Q9_DyaNx0XTBNgU1Zc-Zg51iadyeUbt84/edit?usp=sharing

# Instructions 
## Running
### JDBC (data access demo):
1. Make sure ConnectionManager.java is updated with your information.
2. MySQL 8: add “&allowPublicKeyRetrieval=true”
3. Make sure MySQL is running.
4. Create tables and insert data. (from the dataset folder) in mySQL Workbench.
5. Run on Server

## Setup
1. Install Java JDK (use JRE JavaSE-17 or lower w/ Tomcat 9): http://www.oracle.com/technetwork/java/javase/downloads/index.html
2. Download Apache Tomcat 9.0 (do not use 10): http://tomcat.apache.org
3. Create new project, configure Eclipse+Tomcat, and run Tomcat (skip the “Web Tools Platform” step): https://www.mulesoft.com/tcat/tomcat-eclipse
4. Download Connector/J and add jar to your buildpath: http://dev.mysql.com/downloads/connector/j/
    - Configure Build Path: Libraries > Classpath > Add External JARS…
    - Browse to path (e.g. /Users/bruce/Documents/mysql-connector-java-8.0.16/mysql-connector-java-8.0.16-bin.jar)
5. Download JSTL IMPL and SPEC jars: (taglibs-standard-impl-1.2.5.jar, taglibs-standard-spec-1.2.5.jar), http://tomcat.apache.org/download-taglibs.cgi
    - Copy the JSTL jars to the directory "BlogApplication/src/main/webapp/WEB-INF/lib".
    - Also copy the Connector/J jar to this path.




[MileStone]:https://github.com/YiwenW312/LifeHubApp/blob/main/Doc/MileStone.md