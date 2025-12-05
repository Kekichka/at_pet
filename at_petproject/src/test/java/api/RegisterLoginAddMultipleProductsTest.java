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

public class RegisterLoginAddMultipleProductsTest {

    @Test
    public void addMultipleProductsFlow() {
        DemoWebShopApiBO apiBO = new DemoWebShopApiBO();

        String email = "user" + UUID.randomUUID() + "@mail.com";
        UserRegisterRequest registerReq = new UserRegisterRequest();
        registerReq.setGender("F");
        registerReq.setFirstName("Bob");
        registerReq.setLastName("Example");
        registerReq.setEmail(email);
        registerReq.setPassword("abcdef");
        registerReq.setConfirmPassword("abcdef");

        UserRegisterResponse registerRes = apiBO.registerUser(registerReq);
        apiBO.validateRegisterResponse(registerRes);

        UserLoginRequest loginReq = new UserLoginRequest();
        loginReq.setEmail(email);
        loginReq.setPassword("abcdef");

        UserLoginResponse loginRes = apiBO.loginUser(loginReq);
        apiBO.validateLoginResponse(loginRes);

        AddToCartRequest cartReq = new AddToCartRequest();
        cartReq.setProductId(String.valueOf(1));
        cartReq.setQuantity(2);

        AddToCartResponse cartRes = apiBO.addProductToCart(cartReq);
        apiBO.validateAddToCartResponse(cartRes);
    }
}
