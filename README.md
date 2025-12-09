# PetProject_AQA

## Project Structure

AQA

├── pom.xml # Maven project configuration (dependencies, plugins)  
├── allure-results # Allure report results (screenshots, API JSONs, test logs)  
├── CI_CD  
│   ├── docker-compose.yml  
│   ├── Dockerfile  
│   └── readme.md  
├── jenkins_jobs  
│   ├── API/config.xml  
│   └── UI/config.xml  
├── logs  
│   └── AQA.txt # Execution logs  
├── src  
│   ├── main  
│   │   ├── java  
│   │   │   └── at  
│   │   │       ├── ConfigReader.java # Reads configuration properties  
│   │   │       ├── DriverPool.java # Manages WebDriver instances  
|               └── api
│                 ├── DemoWebShopApiBO.java  
│   │   │       ├── dto  
│   │   │       │   ├── request  
│   │   │       │   │ ├── AddToCartRequest.java  
│   │   │       │   │ ├── UserLoginRequest.java  
│   │   │       │   │ └── UserRegisterRequest.java  
│   │   │       │   └── response  
│   │   │       │       ├── AddToCartResponse.java  
│   │   │       │       ├── CartItem.java  
│   │   │       │       ├── UserLoginResponse.java  
│   │   │       │       └── UserRegisterResponse.java  
│   │   │       ├── listener  
│   │   │       │   ├── CustomAllureListener.java  
│   │   │       └── ui  
│   │   │           ├── bo  
│   │   │           │   ├── RegistrationBO.java  
│   │   │           └── po  
│   │   │               ├── LoginPage.java  
│   │   │               ├── RegistrationPage.java  
│   │   │               ├── ShoppingPage.java  
│   │   │               └── UserPage.java  
│   │   └── resources  
│   │       ├── conf.prop  
│   │       ├── log4j2.xml  
│   │       └── user.csv  
│   └── test  
│       └── java  
│           ├── api  
│           │   ├── APITests  # These tests cover **user registration, login, product selection, adding products to cart, and verifying that the products are successfully added.** (one/multiple/premium products)
│           └── ui  
│               ├── AddProductToCartTest.java  # Automates the full user flow: registration, login, adding a product to the shopping cart, and verifying it was added, with Allure reporting.
│               └── UITests.java # These tests cover the full user flow on the demo webshop: **user registration, login, product selection, adding products to cart, and verifying that the products are successfully added.** (one/multiple/premium products)
|           └── resources
│               ├── testng.xml  
├── target  
│   ├── classes # Compiled classes and copied resources  
│   ├── maven-status  
│   └── surefire-reports  

### Test Scenarios

#### UI Tests

**1. Add Product to Cart Flow** (`AddProductToCartTest.java`)

  1. Generate a unique email for registration.
  2. Register.
  3. Verify that registration was successful.
  4. Open the login page and log in with the newly registered email.
  5. Verify.
  6. Navigate to the shopping page.
  7. Add a specific product to the cart.
  8. Verify that the product is added successfully.

**2. Multi-Scenario Product Flow** (`UITests.java`)
  1. Use a DataProvider to supply multiple user names and products.
  2. Generate a unique email for each user.
  3. Register each user and verify registration.
  4. Log in with the registered email and verify login.
  5. Navigate to the shopping page.
  6. Add the specified product/premium prdoct/multi product to the cart.
  7. Verify that the product is successfully added.
  
#### API Tests

**1. Add Products Flow via API** (`APITests.java`)

  1. Use a DataProvider to supply multiple users, product IDs, and quantities.
  2. Generate a unique email for each user.
  3. Send a `POST /register` request with user details.
  4. Validate the registration response.
  5. Send a `POST /login` request using the registered email.
  6. Validate the login response (token, status, user info).
  7. Send a `POST /addproducttocart` request with product ID and quantity.
  8. Validate the cart response (status, product added, quantity).

### Performance Testing
- JMeter integration with 3 ThreadGroups simulating different loads.
All performance tests are in src/performance/Testing_plan_WebShop.jmx.
To run:
```jmeter -n -t src/performance/Testing_plan_WebShop.jmx -l results.jtl```
Output results will be saved to results.jtl. You can open them in JMeter GUI or export as CSV/HTML for analysis.

### Logging & Reporting
- Allure reports with:
  - Screenshots for UI tests.
  - JSON attachments for API responses.
- Logging using Log4j / SLF4J.

### CI/CD Integration
- Jenkins jobs for:
  - Web UI testing
  - API testing
