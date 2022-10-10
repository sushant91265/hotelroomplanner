### Note: Please read the README.md for the problem statement and for the format of the input json file.

### Pre-requisites
- Java 17 / Java 8
- Junit 4
- Maven 3+
- Mac or Linux OS with Java configured


### How to run the application
- Run from 'src/main/java/com/thoughtworks/App.java' => `javac App.java` and then `java App <input_file_name.json>`
- OR
- Run from root directory => `mvn clean install` and then `java -jar target/optimizer-1.0.jar <input_file_name.json>`


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



### Algorithm
- The algorithm is as follows:
    - Sort the reservations based on the end time.
    - Iterate over the reservations and rooms to see which room can be allocated to the reservation.
    - The criteria to find the available room is to first check if the roomType is same or not.
    - If the roomType is same then check if the existing available booking is overlapping with the current reservation or not.
    - If both of these conditions match then allocate the room to the reservation.
    - Otherwise move to the next room.

- The complexity for this algorithm is O(n^m) where n is the number of reservations and m is the number of rooms, which can be n^2 in the worst case.



### Test cases
- The test cases are present in the `src/test/java` directory.
- The test cases are divided into 3 classes
    - `AppTest` - contains the test cases for the main class App.java
    - `DefaultRoomAllocatorTest` - contains the test cases for the service class DefaultRoomAllocator.java
- The input test files are present in the `src/test/resources` directory, the test function names are same as input test files names.
