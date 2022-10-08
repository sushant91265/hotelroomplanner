### Note: Please read the README.md for the problem statement and for the format of the input json file.

### Pre-requisites
- Java 17
- Maven 3
- Mac or Linux OS


### How to run the application
- `javac App.java` and then `java App <input_file_name.json>`
- OR
- mvn clean install and then `java -jar target/assignment-1.0-SNAPSHOT.jar <input_file_name.json>`
- make sure the input json file is in the same directory as the jar file.


### Assumptions
- Assuming that there will be no garbadge data in the input json file.
- Allocation of rooms happens on the basis of the input data provided in the json file.


### Strucutre of the application
- The application is divided into 3 packages
    - `com.thoughtworks` - contains the main class App.java
    - `com.thoughtworks.model` - contains the model classes
    - `com.thoughtworks.service.impl` - contains the service class
    - `com.thoughtworks.util` - contains the utility class(validation class)
- All the classes and methods have been documented with their purpose.

- We are using template method design pattern to allocate rooms to the reservations.
- Class `RoomAllocatorTemplate` defines the template method and the abstract method which is implemented by the concrete class `DefaultRoomAllocator`.





