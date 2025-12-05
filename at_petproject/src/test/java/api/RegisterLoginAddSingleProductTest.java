package api;

import at.ui.bo.DemoWebShopApiBO;
import at.db.request.UserRegisterRequest;
import at.db.response.UserRegisterResponse;
import at.db.request.UserLoginRequest;
import at.db.response.UserLoginResponse;
import at.db.request.AddToCartRequest;
import at.db.response.AddToCartResponse;
import org.testng.annotations.Test;

import java.util.UUID;

public class RegisterLoginAddSingleProductTest {

    @Test
    public void addSingleProductFlow() {
        DemoWebShopApiBO apiBO = new DemoWebShopApiBO();

        String email = "user" + UUID.randomUUID() + "@mail.com";
        UserRegisterRequest registerReq = new UserRegisterRequest();
        registerReq.setGender("M");
        registerReq.setFirstName("Alice");
        registerReq.setLastName("Test");
        registerReq.setEmail(email);
        registerReq.setPassword("123456");
        registerReq.setConfirmPassword("123456");

        UserRegisterResponse registerRes = apiBO.registerUser(registerReq);
        apiBO.validateRegisterResponse(registerRes);

        UserLoginRequest loginReq = new UserLoginRequest();
        loginReq.setEmail(email);
        loginReq.setPassword("123456");

        UserLoginResponse loginRes = apiBO.loginUser(loginReq);
        apiBO.validateLoginResponse(loginRes);

        AddToCartRequest cartReq = new AddToCartRequest();
        cartReq.setProductId(String.valueOf(72));
        cartReq.setQuantity(1);

        AddToCartResponse cartRes = apiBO.addProductToCart(cartReq);
        apiBO.validateAddToCartResponse(cartRes);
    }
}
