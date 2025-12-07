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
