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


### Web UI Testing
- Multi-browser support (**Chrome** & **Firefox**) using DriverPool.
- Layered architecture: TC -> BO -> PO.
- Uses PageFactory and WebElement wrappers.
- Screenshots automatically captured on test failure.
- At least 3 end-to-end UI test scenarios.

### API Testing
- Tests follow TC -> BO pattern.
- API responses deserialized into POJOs for easy assertions.
- At least 3 API test scenarios with object wrappers.
- JSON responses are attached in Allure reports.

### Performance Testing
- JMeter integration with at least 3 ThreadGroups simulating different loads.
All performance tests are packed in a single JMeter .jmx file.
Supports multiple load scenarios via ThreadGroups.
All performance tests are in src/performance/PerformanceTest.jmx.
To run:
```jmeter -n -t src/performance/PerformanceTest.jmx -l results.jtl```
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
  - Performance testing
