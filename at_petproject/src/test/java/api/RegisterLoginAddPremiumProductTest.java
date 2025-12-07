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

public class RegisterLoginAddPremiumProductTest {

    @Test
    public void addPremiumProductFlow() {
        DemoWebShopApiBO apiBO = new DemoWebShopApiBO();

        String email = "user" + UUID.randomUUID() + "@mail.com";
        UserRegisterRequest registerReq = new UserRegisterRequest();
        registerReq.setGender("M");
        registerReq.setFirstName("Charlie");
        registerReq.setLastName("Demo");
        registerReq.setEmail(email);
        registerReq.setPassword("qwerty");
        registerReq.setConfirmPassword("qwerty");

        UserRegisterResponse registerRes = apiBO.registerUser(registerReq);
        apiBO.validateRegisterResponse(registerRes);

        UserLoginRequest loginReq = new UserLoginRequest();
        loginReq.setEmail(email);
        loginReq.setPassword("qwerty");

        UserLoginResponse loginRes = apiBO.loginUser(loginReq);
        apiBO.validateLoginResponse(loginRes);

        AddToCartRequest cartReq = new AddToCartRequest();
        cartReq.setProductId(String.valueOf(76));
        cartReq.setQuantity(1);

        AddToCartResponse cartRes = apiBO.addProductToCart(cartReq);
        apiBO.validateAddToCartResponse(cartRes);
    }
}
