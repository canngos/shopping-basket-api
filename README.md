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

![login-ok](https://github.com/canngos/shopping-basket-api/assets/79870696/fee7595f-f249-45c7-84a9-15dd70b959be)

![login-101](https://github.com/canngos/shopping-basket-api/assets/79870696/57d7e3bb-7027-4a3f-9872-b320b5b2fd8e)

![login-102](https://github.com/canngos/shopping-basket-api/assets/79870696/fa7c21f2-2dcd-4e47-9cde-c0ebaaceb62f)

##### POST /v1/auth/register

- Description: This endpoint allows users to register on the Shopping Basket Service platform.
- Request Body: RegisterRequest (name, lastName, email, password, address)
- Response: MessageResponse (message)
- HTTP Status: 201 (Created) if successful, 400 (Bad Request) if registration data is invalid.

![register-ok](https://github.com/canngos/shopping-basket-api/assets/79870696/eaf0ad00-666e-4eb5-8b89-8279b8d7114e)

![register-103](https://github.com/canngos/shopping-basket-api/assets/79870696/f5e60643-75ce-4aba-baf1-5426d90f9b8b)

### Product API

##### GET /v1/products

- Description: This endpoint is for getting all Products in the db to customers.
- Request Header: Authorization (Bearer Token)
- Response: ProductResponse (List of products)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

![get-products](https://github.com/canngos/shopping-basket-api/assets/79870696/d5c07290-f2b5-4886-ba9b-dadfe64ac6c0)

##### GET /v1/products/{id}

- Description: This endpoint is for getting a detail information about a specific Product.
- Request Header: Authorization (Bearer Token)
- Path Variable: id (The id of the Product)
- Response: Product
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

![get-prod](https://github.com/canngos/shopping-basket-api/assets/79870696/4d1bdaf7-554d-4a12-96a5-a6edf745e3b9)

![get-prod-107](https://github.com/canngos/shopping-basket-api/assets/79870696/a3b30909-3676-4a60-993b-54044fc63475)

##### POST /v1/products

- Description: This endpoint is for creating new Products.
- Request Body: LoginRequest (name, price, description, type, quantity)
- Request Header: Authorization (Bearer Token)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

![create-prod](https://github.com/canngos/shopping-basket-api/assets/79870696/5f9bdbac-c11c-40fb-ac46-af2c1c4f7a3e)

![create-prod-106](https://github.com/canngos/shopping-basket-api/assets/79870696/b9e818fa-ff58-4f31-9d93-1f61246b997b)

##### PUT /v1/products/{id}

- Description: This endpoint is for updating a existing Product.
- Request Header: Authorization (Bearer Token)
- Path Variable: id (The id of the Product)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

![update-prod](https://github.com/canngos/shopping-basket-api/assets/79870696/7debf2ac-9f35-43b9-a605-d596bd21b4b5)

##### DELETE /v1/products/{id}

- Description: This endpoint is for deleting a existing Product.
- Request Header: Authorization (Bearer Token)
- Path Variable: id (The id of the Product)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

![delete-prod](https://github.com/canngos/shopping-basket-api/assets/79870696/7cf838e4-b3f4-475d-b9a5-9035e6a9a5cb)

### Basket API

##### GET /v1/basket

- Description: This endpoint is for getting all the basket items of a customer.
- Request Header: Authorization (Bearer Token)
- Response: BasketResponse (List of basket items, total price)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

![get-basket](https://github.com/canngos/shopping-basket-api/assets/79870696/5c486696-58c5-49ac-9556-1ac549d3beb0)

##### POST /v1/basket

- Description: This endpoint is for adding a Product into the customer's basket.
- Request Body: BasketRequest (productId, quantity)
- Request Header: Authorization (Bearer Token)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

![add-basket](https://github.com/canngos/shopping-basket-api/assets/79870696/3a82582d-ba0c-4f23-90b8-407cb0884ad4)

![add-basket-108](https://github.com/canngos/shopping-basket-api/assets/79870696/ba3bc0d5-1bb4-4e1b-bdf0-9c08da939ea8)

##### PUT /v1/basket/item/{itemId}/quantity/{quantity}

- Description: This endpoint is for updating the quantity of the basket item.
- Request Header: Authorization (Bearer Token)
- Path Variable: itemId (The id of the basket item), quantity (New quantity of the basket item)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

![update-basket-item](https://github.com/canngos/shopping-basket-api/assets/79870696/f644aab3-70fe-4095-ba34-6877dd27cb5d)

##### DELETE /v1/basket/clear

- Description: This endpoint is for removing all the items from the customer's basket.
- Request Header: Authorization (Bearer Token)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

![clear-basket](https://github.com/canngos/shopping-basket-api/assets/79870696/e9cabd4f-4d29-46fb-b402-cb90dd0848cb)

##### DELETE /v1/basket/item/{itemId}

- Description: This endpoint is for removing a specific item from the customer's basket.
- Request Header: Authorization (Bearer Token)
- Path Variable: itemId (The id of the basket item)
- Response: MessageResponse (message)
- HTTP Status: 200 (OK) if successful, 401 (Unauthorized) if the token is invalid.

![delete-basket-item](https://github.com/canngos/shopping-basket-api/assets/79870696/94cebb1c-5dc2-4015-8de9-2dbd74714335)

## Database UML Diagram

![diagram](https://github.com/canngos/shopping-basket-api/assets/79870696/1e95a363-96d7-4206-80bb-219be2c22a2e)

## Check The SwaggerUI

After running the project, open your web browser and go to http://localhost:8080/swagger-ui/basket URL.
You will see the auto created documentation for the whole service. You can test the APIs.

![swagger](https://github.com/canngos/shopping-basket-api/assets/79870696/b0cc67e0-649c-423e-83de-4736931afad5)

## LICENSE
This project is licensed under the MIT License.
