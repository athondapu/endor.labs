### Endor Test Notes

### Prerequisites
1. Java
2. MongoDB


#### TechStack:
1. Spring Boot
2. MongoDB
3. Java

#### Notes:
1. Used Java+SprintBoot to start the application.
2. To store the data I used MongoDB as my database.
3. I configured mongo as `localhost` (not added any authentication) and database as `endor`.
4. Added two models as `Person` and `Animal` and both are pointing to the same collection called `item`.
5. The two models have the fields like `id`, `name` and `kind` and proper getters and setters and added index for required fields.
6. Now coming to DAO's. Defined a `GenericDao` interface and it has common methods and added an `AbstractDao`.
7. `AbstractDao` is responsible to maintain the common implementation. Along with this abstract class I define two more Dao classes.
8. Those are `PersonDao` and `AnimalDao`. These classes have methods where which doesn't have common methods.
9. Then added corresponding controllers `PersonController` and `AnimalController`. Where these controller are responsible to serve the HTTP requests.
10. Also, added test cases for both Dao's and Controllers. The test cases includes both unit test and e2e (end-to-end) test cases. Created seperate folders for both.

### Run the application
1. Run the application by using the class called `EndorMain` under the `src/` folder.

### End-points

#### Person
1. Save Person:
   ```
   curl --location --request POST 'http://localhost:8080/person/' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "id": "123",
      "name": "Endor"
      }'
   ```
   
2. Get Person By ID:
   ```
    curl --location --request GET 'http://localhost:8080/person/{ID}'
   ```
   
3. Get Persons:
   ```
    curl --location --request GET 'http://localhost:8080/person/'
   ```

4. Delete Person By ID:
   ```
    curl --location --request DELETE 'http://localhost:8080/person/{ID}'
   ```

#### Animal
1. Save Animal:
   ```
   curl --location --request POST 'http://localhost:8080/animal/' \
      --header 'Content-Type: application/json' \
      --data-raw '{
      "id": "456",
      "name": "Lion"
      }'
   ```

2. Get Animal By ID:
   ```
    curl --location --request GET 'http://localhost:8080/animal/{ID}'
   ```

3. Get Animals:
   ```
    curl --location --request GET 'http://localhost:8080/animal/'
   ```

4. Delete Animal By ID:
   ```
    curl --location --request DELETE 'http://localhost:8080/animal/{ID}'
   ```