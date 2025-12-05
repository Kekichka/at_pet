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
