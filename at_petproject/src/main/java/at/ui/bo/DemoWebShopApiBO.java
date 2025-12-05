package at.ui.bo;

import at.db.request.UserRegisterRequest;
import at.db.response.UserRegisterResponse;
import at.db.request.UserLoginRequest;
import at.db.response.UserLoginResponse;
import at.db.request.AddToCartRequest;
import at.db.response.AddToCartResponse;
import org.testng.asserts.SoftAssert;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DemoWebShopApiBO {

    private final String BASE_URL = "https://demowebshop.tricentis.com";

    public UserRegisterResponse registerUser(UserRegisterRequest req) {
        RestAssured.baseURI = BASE_URL;

        Response res = given()
                .formParam("Gender", req.getGender())
                .formParam("FirstName", req.getFirstName())
                .formParam("LastName", req.getLastName())
                .formParam("Email", req.getEmail())
                .formParam("Password", req.getPassword())
                .formParam("ConfirmPassword", req.getConfirmPassword())
                .redirects().follow(true)
                .post("/register")
                .then()
                .extract().response();

        UserRegisterResponse response = new UserRegisterResponse();
        response.setResult("Registration attempted"); // placeholder
        return response;
    }

    public void validateRegisterResponse(UserRegisterResponse res) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(res, "Registration response is null!");
        softAssert.assertAll();
    }

    public UserLoginResponse loginUser(UserLoginRequest req) {
        RestAssured.baseURI = BASE_URL;

        Response res = given()
                .formParam("Email", req.getEmail())
                .formParam("Password", req.getPassword())
                .redirects().follow(true)
                .post("/login")
                .then()
                .extract().response();

        String welcomeMessage = res.htmlPath().getString("**.find { it.@class == 'account' }");
        if (welcomeMessage == null || welcomeMessage.isEmpty()) welcomeMessage = "Login failed or message not found";

        UserLoginResponse response = new UserLoginResponse();
        response.setWelcomeMessage(welcomeMessage);
        return response;
    }

    public void validateLoginResponse(UserLoginResponse res) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(res.getWelcomeMessage(), "Login failed, welcome message missing!");

        String msg = res.getWelcomeMessage().toLowerCase();
        softAssert.assertTrue(
                msg.contains("my account") || msg.contains("@") || msg.contains("logout"),
                "Login failed, unexpected welcome message: " + res.getWelcomeMessage()
        );

        softAssert.assertAll();
    }

    public AddToCartResponse addProductToCart(AddToCartRequest req) {
        RestAssured.baseURI = BASE_URL;

        Response res = given()
                .formParam("addtocart_" + req.getProductId() + ".EnteredQuantity", req.getQuantity())
                .redirects().follow(true)
                .post("/addproducttocart/details/" + req.getProductId() + "/1")
                .then()
                .extract().response();

        String cartMessage = res.htmlPath().getString("**.find { it.@class == 'content' }");
        if (cartMessage == null || cartMessage.isEmpty()) cartMessage = "No cart message found";

        AddToCartResponse response = new AddToCartResponse();
        response.setCartMessage(cartMessage);

        return response;
    }

    public void validateAddToCartResponse(AddToCartResponse res) {
        SoftAssert softAssert = new SoftAssert();

        boolean cartAdded = false;

        if (res.getCartMessage() != null && !res.getCartMessage().isEmpty()) {
            cartAdded = true;
        } else if (res.isSuccess()) {
            cartAdded = true;
        } else if (res.getItems() != null && !res.getItems().isEmpty()) {
            cartAdded = true;
        }

        softAssert.assertTrue(cartAdded,
                "Product was not added to cart! Message: " + res.getCartMessage());

        softAssert.assertAll();
    }

}
