# LibrarySystem
1. Core Language: Java
2. Framework: Spring Boot
3. H2 embedded database used.
4. API is secured with Oauth2

# Steps To Run This Application
 * Download or clone the broject 
 * Traverse to `librarySystem`directory
 *  execute `mvn clean install` (This will generate jar file under directory `librarySystem/target/librarySystem-0.0.1-SNAPSHOT.jar`)
 * After build successful
      * run application using `java -jar librarySystem/target/librarySystem-0.0.1-SNAPSHOT.jar`

##### API Details

0.  * Without Bearer Token validation error:
     <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/WithoutAuthorizationHeader403.png>

 1.* Generating Bearer Token
    * http://localhost:8080/getToken?user=somnath&password=password1
        Simple api is used to generate bearer token and for simplicity just passing user/id ad pass as url param which is ofcoure is not a secure way to do it.
    <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/TokenCreation.png>

2. * onboard an item
       POST URL:  <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/OnboardItem100.png>

        * Header
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJBdWN0aW9uQXBwIiwic3ViIjoic29tbmF0aCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VybmFtZSI6InNvbW5hdGgiLCJpYXQiOjE2Mjc4MzU4NDUsImV4cCI6MTYyNzgzNjQ0NX0.tZnEHS4BZpAZmWGbDP-aq-NTwgKfNcEH5WfBDz7z5Jo-o9JlNsEU7YTFYVZaAe6PX8uhcHg6PLuYzmQQViwZFw`
        
        * Body part
          ```
                       {
            "itemId": 100,
            "itemName": "Head First Java",
            "itemType": "Book",
            "authorName": "Jane Doe",
            "totalNo": 20,
            "availableNo": 20
        }```
        
        
3. * register a customer
       POST URL:  <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/RegisterCustomer1.png>

        * Header
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJBdWN0aW9uQXBwIiwic3ViIjoic29tbmF0aCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VybmFtZSI6InNvbW5hdGgiLCJpYXQiOjE2Mjc4MzU4NDUsImV4cCI6MTYyNzgzNjQ0NX0.tZnEHS4BZpAZmWGbDP-aq-NTwgKfNcEH5WfBDz7z5Jo-o9JlNsEU7YTFYVZaAe6PX8uhcHg6PLuYzmQQViwZFw`
        
        * Body part
          ```
          {
            "customer": {
            "name": "Ramesh",
            "email": "ramesh@gmail.com",
            "phone": "9999999999",
            "accountStatus": "Active"
           }```
          
4. * Issue items to a customer
       POST URL:  <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/IssueCustomer1Items124.png>

        * Header
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJBdWN0aW9uQXBwIiwic3ViIjoic29tbmF0aCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VybmFtZSI6InNvbW5hdGgiLCJpYXQiOjE2Mjc4MzU4NDUsImV4cCI6MTYyNzgzNjQ0NX0.tZnEHS4BZpAZmWGbDP-aq-NTwgKfNcEH5WfBDz7z5Jo-o9JlNsEU7YTFYVZaAe6PX8uhcHg6PLuYzmQQViwZFw`
        
        * Body part
          ```
          [1,2,4]
          ```      

5. * Overdue items for a customer and total charges
       GET URL:  <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/OverdueItemsAndChargesForCustomer3.png>

        * Header
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJBdWN0aW9uQXBwIiwic3ViIjoic29tbmF0aCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VybmFtZSI6InNvbW5hdGgiLCJpYXQiOjE2Mjc4MzU4NDUsImV4cCI6MTYyNzgzNjQ0NX0.tZnEHS4BZpAZmWGbDP-aq-NTwgKfNcEH5WfBDz7z5Jo-o9JlNsEU7YTFYVZaAe6PX8uhcHg6PLuYzmQQViwZFw`
        
6. * Get details of a customer
       GET URL:  <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/Customer1Details.png>

        * Header
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJBdWN0aW9uQXBwIiwic3ViIjoic29tbmF0aCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VybmFtZSI6InNvbW5hdGgiLCJpYXQiOjE2Mjc4MzU4NDUsImV4cCI6MTYyNzgzNjQ0NX0.tZnEHS4BZpAZmWGbDP-aq-NTwgKfNcEH5WfBDz7z5Jo-o9JlNsEU7YTFYVZaAe6PX8uhcHg6PLuYzmQQViwZFw`
            
7. * Get issued items to a customer
       GET URL:  <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/Customer1IssuedItems.png>

        * Header
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJBdWN0aW9uQXBwIiwic3ViIjoic29tbmF0aCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VybmFtZSI6InNvbW5hdGgiLCJpYXQiOjE2Mjc4MzU4NDUsImV4cCI6MTYyNzgzNjQ0NX0.tZnEHS4BZpAZmWGbDP-aq-NTwgKfNcEH5WfBDz7z5Jo-o9JlNsEU7YTFYVZaAe6PX8uhcHg6PLuYzmQQViwZFw`

8. * Get availability of a item
       GET URL:  <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/AvailableCountForItem1.png>

        * Header
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJBdWN0aW9uQXBwIiwic3ViIjoic29tbmF0aCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VybmFtZSI6InNvbW5hdGgiLCJpYXQiOjE2Mjc4MzU4NDUsImV4cCI6MTYyNzgzNjQ0NX0.tZnEHS4BZpAZmWGbDP-aq-NTwgKfNcEH5WfBDz7z5Jo-o9JlNsEU7YTFYVZaAe6PX8uhcHg6PLuYzmQQViwZFw`

8. * Get all customers
       GET URL:  <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/AllCustomers.png>

        * Header
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJBdWN0aW9uQXBwIiwic3ViIjoic29tbmF0aCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VybmFtZSI6InNvbW5hdGgiLCJpYXQiOjE2Mjc4MzU4NDUsImV4cCI6MTYyNzgzNjQ0NX0.tZnEHS4BZpAZmWGbDP-aq-NTwgKfNcEH5WfBDz7z5Jo-o9JlNsEU7YTFYVZaAe6PX8uhcHg6PLuYzmQQViwZFw`

9. * Return an item:
       POST URL:  <img src=https://github.com/somnath-hazra/librarySystem/blob/main/images/ReturnAnItemFromACustomerAccount.png>

        * Header
           * Content-Type : application/json
        * Authorization
           * Type  - Bearer Token
           * Token - `Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJBdWN0aW9uQXBwIiwic3ViIjoic29tbmF0aCIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VybmFtZSI6InNvbW5hdGgiLCJpYXQiOjE2Mjc4MzU4NDUsImV4cCI6MTYyNzgzNjQ0NX0.tZnEHS4BZpAZmWGbDP-aq-NTwgKfNcEH5WfBDz7z5Jo-o9JlNsEU7YTFYVZaAe6PX8uhcHg6PLuYzmQQViwZFw`
 
#####CommandLine run also supported:
<img src="https://github.com/somnath-hazra/librarySystem/blob/main/images/CommandLineRun.png" alt="drawing" width="1000" height="300"/>


##### H2 console access
 
   * url to access h2 console - http://localhost:8080/h2-ui/
   * JDBC URL - `jdbc:h2:mem:testdb`
   * username - somnath
   
   <img src="https://github.com/somnath-hazra/librarySystem/blob/main/images/H2-Database.png">
