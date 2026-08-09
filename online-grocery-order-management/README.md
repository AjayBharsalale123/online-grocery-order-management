# Online Grocery Order Management

Spring Boot REST API for managing customers, grocery items, and grocery orders.

## Tech Stack

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- MySQL
- Bean Validation
- JUnit 5 / Mockito

## Features

### Customer
- Create customer
- Get all customers
- Get customer by ID
- Update customer
- Delete customer

### Grocery Item
- Create grocery item
- Get all grocery items
- Get grocery item by ID
- Update grocery item
- Delete grocery item

### Order
- Create order
- Get all orders
- Get order by ID
- Update order
- Delete order

An order belongs to one customer and can contain multiple grocery items. A grocery item can belong to multiple orders.

## Database Setup

Install MySQL and make sure the server is running.

The application uses:

- Database: `grocery_db`
- Username: `root`
- Password: `root`

Change these values in `src/main/resources/application.properties` if required.

The database is created automatically by the JDBC URL if it does not already exist.

## Run

```bash
mvn spring-boot:run
```

Run tests:

```bash
mvn test
```

## API Endpoints

### Customers

```text
POST   /api/customers
GET    /api/customers
GET    /api/customers/{id}
PUT    /api/customers/{id}
DELETE /api/customers/{id}
```

Example:

```json
{
  "name": "Ajay",
  "email": "ajay@example.com",
  "address": "Pune",
  "phone": "9876543210"
}
```

### Grocery Items

```text
POST   /api/grocery-items
GET    /api/grocery-items
GET    /api/grocery-items/{id}
PUT    /api/grocery-items/{id}
DELETE /api/grocery-items/{id}
```

Example:

```json
{
  "name": "Rice",
  "category": "Grains",
  "price": 60.00,
  "quantity": 10
}
```

### Orders

```text
POST   /api/orders
GET    /api/orders
GET    /api/orders/{id}
PUT    /api/orders/{id}
DELETE /api/orders/{id}
```

Create/update order:

```json
{
  "customerId": 1,
  "groceryItemIds": [1, 2],
  "orderDate": "2026-08-09"
}
```

The total price is calculated from the selected grocery item prices.

## Error Handling

- `400 Bad Request` for validation errors
- `404 Not Found` when a requested entity does not exist
- `409 Conflict` for database constraint violations
- `204 No Content` for successful deletes
- `201 Created` for successful creates
