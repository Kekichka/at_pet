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
│   │   │       ├── db  
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
│   │   │       │   └── CustomListener.java  
│   │   │       ├── task16  
│   │   │       │   ├── TrelloAddItemResModel.java  
│   │   │       │   ├── TrelloCreateChecklistResModel.java  
│   │   │       │   └── TrelloUpdateChecklistNameResModel.java  
│   │   │       ├── task21  
│   │   │       │   └── ScreenHelper.java  
│   │   │       └── ui  
│   │   │           ├── bo  
│   │   │           │   ├── DemoWebShopApiBO.java  
│   │   │           │   ├── RegistrationBO.java  
│   │   │           │   ├── TextAreaBusinessObject.java  
│   │   │           │   ├── TrelloBO.java  
│   │   │           │   └── UserBO.java  
│   │   │           └── po  
│   │   │               ├── LoginPage.java  
│   │   │               ├── RegistrationPage.java  
│   │   │               ├── ShoppingPage.java  
│   │   │               ├── TextAreaPageObject.java  
│   │   │               └── UserPage.java  
│   │   └── resources  
│   │       ├── BUY LAPTOP.jmx  
│   │       ├── REGISTER + LOGIN.jmx  
│   │       ├── Typical Browsing.jmx  
│   │       ├── conf.prop  
│   │       ├── hibernate.cfg.xml  
│   │       ├── input.json  
│   │       ├── input.xml  
│   │       ├── log4j2.xml  
│   │       ├── testng.xml  
│   │       └── user.csv  
│   └── test  
│       └── java  
│           ├── api  
│           │   ├── RegisterLoginAddMultipleProductsTest.java  
│           │   ├── RegisterLoginAddPremiumProductTest.java  
│           │   └── RegisterLoginAddSingleProductTest.java  
│           └── ui  
│               ├── AllureTest.java  
│               └── Task11Test.java  
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
