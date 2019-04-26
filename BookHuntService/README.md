
# BookHunt App

## Overview
This is a springboot application which exposes the Restful API's to support book-hunt-ui project. It supports fetatures such has Spring caching, Pagination, many-to-many mapping etc.

## Installations

Before running the app on server verify below
1. Make sure JAVA_HOME is set to correct jdk directory eg: JAVA_HOME:C:\Program Files (x86)\Java\jdk1.8.0_111
2. Make sure PATH env variable has mvn eg: PATH: ../C:\Program Files\apache-maven-3.6.0-bin\apache-maven-3.6.0\bin

##To run the application (You will need to run it from the project folder which contains the pom.xml file)
1. mvn package -DskipTests
2. java -jar target/books-0.0.1-SNAPSHOT.jar

##To run test cases
 1. mvn clean test

 ##To access H2 repository
 1. Go to http://localhost:8080/h2-console/
 2. JDBC Url = jdbc:h2:mem:bookHunt
 3. Hit Connect
 4. Book, Book_Search, Search_Key and all  batch tables can be accesed here
  
## App Functionality
   
  ### SPRING REST CONTROLLER
    * Spring Controller exposes single rest api eg: http://localhost:8080/books/search?page=0&q=flower
    * It takes two request parameter inputs 1.page 2.book title 
    * It returns the books corresponding to the book title as per Google Api
    * The above service will be consumed by book-hunt-ui front-end project, hence we need to have this back-end project up and running before starting front-end
    
### SPRING SERVICE
    * This layer is responsible to fetch books from actual [Google Books Api](https://developers.google.com/books/) and store it in  in -memory H2 database.
    * In real time application we could be fetching 1000's of books for a single key that the user would enter, which means we have to paginate this data for better user experience.
    * As this is suppose to be a POC we mimic this pagination by fetching 40 books from google api when user searchs with a   perticular book title and saving these 40 books to the DB and return only first 10 books on the 1st request which are sorted    alpahabatically. 
    * The remaining 30 books can be queried by the user by supplying approriate page number.
    * This layer is also responsible to preriodically truncating these table.
    * This layer is also resposible to create Spring cache for each search key+page combination and also periodically clear it to  maintain consistancy.
    
### SPRING REPOSITORY
    * This layer interacts with the H2 database to create, update, paginate, truncate table data with respect to entity objects.
    * There is a many-to-many relationship between books and search_key
