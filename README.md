# Restaurant search api

This project is about a RESTful API created to search restaurant.

## Starting the application

To start the application, use the command available in the `Makefile`:

```shell
make start
```

After that the application will be available on `localhost:8080`.

It's also posible to stop or restart the application using the commands `make stop` and `make restart` respectively.

## Using the application

The application has the endpoint `/v1/restaurant` to search for restaurants. 
Filters must be informed via query parameter, as shown in the example below:

```shell
curl --location 'localhost:8080/v1/restaurant?name=table&customerRating=3&distance=4&price=10&cuisine=Mexican'
```

Below we see an example of an endpoint response:

```json
[
  {"name":"Deliciousgenix"},
  {"name":"Deliciouszilla"},
  {"name":"Fodder Table"},
  {"name":"Dished Grill"},
  {"name":"Sizzle Yummy"}
]
```

### About restrictions for query parameters

General: At least one of the following parameters is required.

- name: If entered, it must have at least 3 characters;
- customerRating: If entered, should be numeric from 1 to 5;
- distance: If entered, should be numeric from 1 to 10;
- price: If entered, should be numeric from 10 to 50;
- cuisine: If entered, should contain the name of the cuisine, no matter if it's in lower or upper case.

## Project structure

The api was developed using concepts like Clean Architecture and Domain Driven Design.

Below we see the design of the application structure. We have a core with domain and usecase, where we have all the application's business logic concentrated.
We also have, in the infrastructure layer, a data provider layer and an entrypoint layer. In addition to a configuration layer that permeates the rest of the application and is responsible for SpringBoot Beans creation and dependency injection.

![image](https://github.com/guilhermegs/restaurant-service/assets/27780764/a46cd26f-a231-4692-9aa6-f264a4593384)


For the development of the domain layer, the concept of domain driven design was used, with rich entities, whose behavior is under their responsibility and not that of external services.

To do this, we have a restaurant aggregate, which contains the restaurant entity and its value objects, as well as a repository and a factory.

![image](https://github.com/guilhermegs/restaurant-service/assets/27780764/00d0b082-a936-43b8-847a-2fb049be266c)

