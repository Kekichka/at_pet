package api;

import at.api.DemoWebShopApiBO;
import at.dto.request.UserRegisterRequest;
import at.dto.response.UserRegisterResponse;
import at.dto.request.UserLoginRequest;
import at.dto.response.UserLoginResponse;
import at.dto.request.AddToCartRequest;
import at.dto.response.AddToCartResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

public class APITests {

    @DataProvider(name = "userProductData")
    public Object[][] userProductData() {
        return new Object[][]{
                {"Alice", "Test", "M", "123456", 72, 1},
                {"Charlie", "Demo", "M", "qwerty", 76, 1},
                {"Bob", "Example", "F", "abcdef", 1, 2}
        };
    }

    @Test(dataProvider = "userProductData")
    public void addProductsFlow(String firstName, String lastName, String gender,
                                String password, int productId, int quantity) {

        DemoWebShopApiBO apiBO = new DemoWebShopApiBO();

        String email = "user" + UUID.randomUUID() + "@mail.com";

        UserRegisterRequest registerReq = new UserRegisterRequest();
        registerReq.setFirstName(firstName);
        registerReq.setLastName(lastName);
        registerReq.setGender(gender);
        registerReq.setEmail(email);
        registerReq.setPassword(password);
        registerReq.setConfirmPassword(password);

        UserRegisterResponse registerRes = apiBO.registerUser(registerReq);
        apiBO.validateRegisterResponse(registerRes);

        UserLoginRequest loginReq = new UserLoginRequest();
        loginReq.setEmail(email);
        loginReq.setPassword(password);

        UserLoginResponse loginRes = apiBO.loginUser(loginReq);
        apiBO.validateLoginResponse(loginRes);

        AddToCartRequest cartReq = new AddToCartRequest();
        cartReq.setProductId(String.valueOf(productId));
        cartReq.setQuantity(quantity);

        AddToCartResponse cartRes = apiBO.addProductToCart(cartReq);
        apiBO.validateAddToCartResponse(cartRes);
    }
}
