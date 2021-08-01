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

 * Generating Bearer Token
    * http://localhost:8080/getToken?user=somnath&password=password1
    *     Simple api is used to generate bearer token and for simplicity just passing user/id ad pass as url param which is ofcoure is not a secure way to do it.
<img src="https://github.com/somnath-hazra/librarySystem/blob/master/images/TokenCreation.png" alt="drawing" width="1000" height="300"/>  
