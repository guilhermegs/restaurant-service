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
