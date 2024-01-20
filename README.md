# Shopping Basket Service
The Shopping Basket Service is a comprehensive REST API developed in Java using the Spring framework. This project, culminating from the Techcarieer's Backend From Start To Finish Bootcamp, serves as a robust solution for managing shopping baskets, products, and user authentication within an e-commerce platform. The core functionality of the service revolves around three main components: Authentication, Product Management, and Basket Operations. It employs industry-standard JWT token-based authentication via Spring Security, ensuring secure and seamless user interactions with the platform.

## Getting Started

1. Clone the Shopping Basket Service repository from GitHub.

2. Open the project with your preferred IDE.

3. Configure the necessary database. (default Postgres)

4. Update the application.properties file with the database credentials.

5. Run the application using Maven or your preferred IDE. (Initial port is 8080. You can change it from application.properties)

## Maven Commands
To build, test, and run the Shopping Basket Service, you can use the following Maven commands:

### Clean And Install
To clean the build artifacts and rebuild the project, run:

`./mvnw clean install`

> The built JAR file will be located in the target directory.

### Test
To run the tests for the Shopping Basket Service, use:

`./mvnw test`

> This command will execute all the unit tests in the project. The test results will be displayed in the console, indicating which tests passed and which ones failed.

### Run 
To run the Shopping Basket Service locally, use the following command:

`./mvnw spring-boot:run`

> This will start the service, and you can access the APIs at http://localhost:8080.

## APIs
### Auth API

##### POST /v1/auth/login

- Description: This endpoint allows users to authenticate their account in order to use Product and Basket APIs.
- Request Body: LoginRequest (email, password)
- Response: LoginResponse (JWT Token, Token Expiration Date)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if login credentials are invalid.

##### POST /v1/auth/register

- Description: This endpoint allows users to register on the Shopping Basket Service platform.
- Request Body: RegisterRequest (name, lastName, email, password, address)
- Response: MessageResponse (message)
- HTTP Status: 201 (Created) if successful, 400 (Bad Request) if registration data is invalid.


### Product API

##### GET /v1/products

- Description: This endpoint is for getting all Products in the db to customers.
- Request Header: Authorization (Bearer Token)
- Response: ProductResponse (List of products)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

##### GET /v1/products/{id}

- Description: This endpoint is for getting a detail information about a specific Product.
- Request Header: Authorization (Bearer Token)
- Path Variable: id (The id of the Product)
- Response: Product
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

##### POST /v1/products

- Description: This endpoint is for creating new Products.
- Request Body: LoginRequest (name, price, description, type, quantity)
- Request Header: Authorization (Bearer Token)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

##### PUT /v1/products/{id}

- Description: This endpoint is for updating a existing Product.
- Request Header: Authorization (Bearer Token)
- Path Variable: id (The id of the Product)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

##### DELETE /v1/products/{id}

- Description: This endpoint is for deleting a existing Product.
- Request Header: Authorization (Bearer Token)
- Path Variable: id (The id of the Product)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

### Basket API

##### GET /v1/basket

- Description: This endpoint is for getting all the basket items of a customer.
- Request Header: Authorization (Bearer Token)
- Response: BasketResponse (List of basket items, total price)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

##### POST /v1/basket

- Description: This endpoint is for adding a Product into the customer's basket.
- Request Body: BasketRequest (productId, quantity)
- Request Header: Authorization (Bearer Token)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

##### PUT /v1/basket/item/{itemId}/quantity/{quantity}

- Description: This endpoint is for updating the quantity of the basket item.
- Request Header: Authorization (Bearer Token)
- Path Variable: itemId (The id of the basket item), quantity (New quantity of the basket item)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

##### DELETE /v1/basket/clear

- Description: This endpoint is for removing all the items from the customer's basket.
- Request Header: Authorization (Bearer Token)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

##### DELETE /v1/basket/item/{itemId}

- Description: This endpoint is for removing a specific item from the customer's basket.
- Request Header: Authorization (Bearer Token)
- Path Variable: itemId (The id of the basket item)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

## Database UML Diagram

![App Screenshot](https://via.placeholder.com/468x300?text=App+Screenshot+Here)

## POSTMAN 
![App Screenshot](https://github.com/canngos/shopping-basket-api/assets/79870696/88cc3416-c3c9-437a-809a-d652d1bf8cd8)
 
## LICENSE
This project is licensed under the MIT License.
