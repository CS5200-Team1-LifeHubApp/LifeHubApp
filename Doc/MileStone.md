# CS5200 DBMS Project Guidelines

## Overview

### Course Syllabus

- Milestone 1: Definition
- Milestone 2: Relational Model and Data
- Milestone 3: Business Insights
- Milestone 4: Hello World
- Milestone 5: Data Warehousing
- Milestone 6: Conclusion
- Final Presentation

We will approach this project like we are building a new product. At the end of the semester, you will have a product that you can pitch to venture capitalists for funding.

Collaborate in a team of five to seven members. Think about a product/service offering revolving around a strong relational model and how it can benefit a set of users. You will deliver a data-driven web application that is built on top of a relational model that you have defined. The project will focus on the design of your relational model, the insights you derive from your data, and how the web application and users interact with the relational model.


## Milestone 1: Definition

**Assigned**: 01/18. **Due**: 02/01.

### Deliverables

- One submission per team. On no more than 1 page, saved as a PDF.
- Team name, team members, project name.
- A one-sentence value proposition using the format:  
  *(Product name) Is A (description of market and/or offering)*  
  That Provides *(key benefits, state around three)*  
  For *(target customers and/or segment)*  
  Who Are Unhappy With *(current barriers, alternatives, and/or competitors)*.
- What features/capabilities do you plan to deliver this semester, and what could you add in the future?
  - Be sure to deliver at least one advanced feature this semester, such as personalized search, recommendations, etc. Leverage your data such as user preferences, history, etc. to deliver a more valuable experience that the user would care about. In other words, provide data-oriented intelligence and go deeper than just presenting and browsing your underlying data.
- Data sources and how you plan to extract the data. Include links to the data sources.
- Each member should be prepared to present the project definition in class on the due date.

## Milestone 2: Relational Model and Data

**Assigned**: 02/01. **Due**: 02/15.

### Deliverables

- One submission per team, as a single PDF.
- UML for your relational model. At least 10 classes must be defined.
- `CREATE TABLE` statements for all tables. Ensure the `CREATE TABLE` statements are in an order that respect referential integrity. Also include `DROP TABLE IF EXISTS` statements at the top so the relational model can be easily recreated. I should be able to copy-paste and run your statements.
- Row counts for each table. You can submit a screenshot of the row counts in MySQL Workbench. At least 100K rows must be loaded (in aggregate).
- Each member should be prepared to demo the relational model implementation in Workbench in class on the due date.

## Milestone 3: Business Insights

**Assigned**: 02/08. **Due**: 02/29.

### Deliverables

- One submission per team, as a single PDF file.
- Identify 10 questions that can be answered with your data. Make sure your questions are insightful and contribute to your value proposition.
- Provide a single `SELECT` statement to answer each question above. Make sure the `SELECT` statements are well formatted for ease of reading. Also include the result output. At least 5 SQL statements should use advanced features, such as functions, joins, aggregation, filtering after aggregation, ordering, etc.
- Does your relational model design suffice, or do you need to make changes to answer the questions? If so, then describe your changes and include an updated UML. Also include your new `CREATE TABLE` statements. I should be able to copy-paste to create your tables and then run your `SELECT` statements.
- Each member should be prepared to demo the SQL statements in class on the due date.

## Milestone 4: Hello World

**Assigned**: 02/29. **Due**: 03/21.

### Deliverables

- One submission per team.
- Use JDBC to build the data access layer for all classes. Use JSP to build the web application.
- At least 4 screenshots that demonstrate the ability to perform the following operations on your database: create, read, update, delete (CRUD). Put the screenshots in a single PDF file. For each screenshot, provide a quick narrative of the workflow.
- Screenshot and description of your advanced feature that you defined in Milestone 1.
- Zip’ed Java code (no .rar files).
- Each member should be prepared to demo CRUD operations in class on the due date. Also demo your advanced feature.

## Milestone 5: Data Warehousing

**Assigned**: 03/28. **Due**: 04/11.

### Deliverables

- One submission per team.
- Combine your existing data with at least 2 external data sources that can provide interesting insights. Describe the external data sources used (including links, what information the data contains, how you plan to use the data). Include this in your final report.
- Define at least 2 ETL workflows that combine and/or transform the external data sources. Provide screenshots of the ETL workflows, and describe the ETL workflows (components used and what they do). Include this in your final report.
- Using Excel and/or Google Sheets, create at least 5 charts from your data warehouse (so the charts should reflect the results of ETLs and should utilize the external data). Include this in your final report for each chart:
  - Your hypothesis for combining the data.
  - The results of combining the data, and if it validates or invalidates your hypothesis.
  - Briefly describe the chart’s significance for your application and the action you could take (if any) given the new information.
- Export the entire report as a single PDF: include the external data source descriptions, ETL descriptions, and chart descriptions/conclusions.
- Zip’ed CloverETL workspace (exclude large data files) and report (no .rar files).
- Each member should be prepared to demo the ETL workflows and present the visualizations in class on the due date.

## Milestone 6: Conclusion

**Assigned**: 04/11. **Due**: 04/18 (before class).

### Deliverables

1. One submission per team:
   - Zip’ed application code (no .rar files).
   - On no more than 2 pages, saved as a PDF:
     - Did you fulfill your value proposition? Explain.
     - List what you initially planned to deliver (as stated in Milestone 1), and compare to what you actually delivered.
     - Your final UML. Include a description of what changed in your UML (including which milestone did you make changes and why).
     - What went well?
     - What were the biggest challenges?
     - What would you do differently?
     - What do you plan to do next?
2. One submission per individual, no more than 1 page saved as PDF:
   - Did your team work well together? Why or why not?
   - Specifically, what did you contribute (implemented part of UML, loaded data source, wrote SELECT statements, wrote JDBC/JSP code, created ETL, etc.)?
   - Optionally, feedback for each of your team members.

## Final Presentation

### Deliverables

- 20 minute presentation:
  - Summarize PM6 in a slide deck. Emphasize the retrospective and lessons learned. For example, no need to spend much time on your UML because we have already seen that in the past.
  - Provide a live demo of your final website. This is your chance to pitch your product!

