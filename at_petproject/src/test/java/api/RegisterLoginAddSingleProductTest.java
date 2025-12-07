package api;

import at.api.DemoWebShopApiBO;
import at.dto.request.UserRegisterRequest;
import at.dto.response.UserRegisterResponse;
import at.dto.request.UserLoginRequest;
import at.dto.response.UserLoginResponse;
import at.dto.request.AddToCartRequest;
import at.dto.response.AddToCartResponse;
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
