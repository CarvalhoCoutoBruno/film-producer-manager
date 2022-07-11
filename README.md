# Read Me First
This project is best read with code editor wrapped at 160 columns.

# Getting Started
Install JDK:
 - Windows: https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-64C27A0D-0562-4A90-A679-3F773FC97A23
 - Mac: https://docs.oracle.com/en/java/javase/11/install/installation-jdk-macos.html#GUID-F575EB4A-70D3-4AB4-A20E-DBE95171AB5F

Install maven:
 - Windows: https://phoenixnap.com/kb/install-maven-windows
 - Mac: 
    - install homebrew: https://brew.sh/
    - install maven: https://formulae.brew.sh/formula/maven

# Running The Project
- Download and open the project folder while in the terminal
- Run the following commands:
  - mvn compile package
  - java -jar target/film-producer-manager-1.0.0-SNAPSHOT.jar

### Reference Documentation
- After the project is running, open your favorite browser and type in:
  - http://localhost:8081/docs/swagger-ui/#
- Open the Get Film Producers Prize Intervals endpoint under Film Producer Controllers
- Click on "Try it out" and click on execute to try out the request. The response details will appear just below
- Feel free to add or delete movies using the movie controller
- Note that most fields cannot be null or empty when saving a movie. If so a detailed error response will appoint what is missing
- To execute on postman locally use the following uri: 
  - http://localhost:8081/v1/film-producers/prize-intervals
- Any curl is available on swagger documentation after clicking on Try it out and Execute button