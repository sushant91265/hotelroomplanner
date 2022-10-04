Capacity Planning Exercise
--------------------------

Hotel manager needs a system which allows him to plan their room capacity usage. He needs a tool, which allows him to do this capacity planning for a single hotel at a time. Each hotel has several rooms of different types. Towards these rooms are reservations which are always tied to a single room type.

The assignment for this exercise is to create an algorithm which maximises the availability of each room type. In more plain words this means that all the reservations are packed as tightly as possible to as few rooms as possible.

The created application should be a command line application which takes an input file as a parameter and then prints the optimized solution into standard out. Please make sure the provided solution is coded such that it can easily be maintained and extended by other people and that there is proof the solution works. The format of the input file is a JSON file with the following content:

```json
{
  "roomTypes": [1, 2],
  "rooms": [{"id": 1, "roomType": 1},
            {"id": 2, "roomType": 1},
            {"id": 3, "roomType": 1},
            {"id": 4, "roomType": 2}],
  "reservations": [{"reservationId": 1,
                    "roomType": 1,
                    "startDate": "2019-05-10",
                    "endDate": "2019-05-15"},
                   {"reservationId": 2,
                    "roomType": 1,
                    "startDate": "2019-05-12",
                    "endDate": "2019-05-17"},
                   {"reservationId": 3,
                    "roomType": 1,
                    "startDate": "2019-05-15",
                    "endDate": "2019-05-17"}
                   {"reservationId": 4,
                    "roomType": 2,
                    "startDate": "2019-05-8",
                    "endDate": "2019-05-20"}]
}
```

As one can see, reservations are not tied to specific rooms in the input file, it's the algorithms job to place them into those rooms.

After running the algorithm the application should print the result into standard out in the following format (when assuming the above input file):

```
Room ID 1: [1, 3]
Room ID 2: [2]
Room ID 3: []
Room ID 4: [4]
```

Where the numbers in brackets are reservation IDs.

Please note that each reservation starts at 2 PM and ends at noon. This allows reservations to be placed to the same room if their end and start dates are the same (in the example reservations 1 and 3).

Exercised can be implemented in any language which can be created as a command line application. The delivered solution must be compilable (if applicable) with the language's typical compilation tooling. The compilation artifact should be executable directly from the command line. Examples:

```
node optimizer.js reservation-input.json
java -jar optimizer.jar reservation-input.json
./optimizer reservation-input.json
python optimizer.py reservation-input.json
```

Exercise result can be returned as a source code zip/tar file or as a link to a website where it can be downloaded. Please do not put this into a public Github repository though, creating these assignments takes valuable time and it doesn't help anyone if these assignments are publicly available with their solutions. Private Github/Bitbucket/Gitlab repository with appropriate access rights are fine and even preferred choice though.