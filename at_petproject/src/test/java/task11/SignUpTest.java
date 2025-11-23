package task11;

import at.DriverPool;
import at.task11.SignUpBO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class SignUpTest {

    @Test
    public void testSignUpSuccess() {
        SignUpBO signUpBO = new SignUpBO();

        String username = UUID.randomUUID().toString().substring(0, 8);
        String password = "test123";

        String alertMsg = signUpBO.registerNewUser(username, password);

        Assert.assertEquals(alertMsg, "Sign up successful.");
    }

    @AfterMethod
    public void cleanUp() {
        DriverPool.quitDriver();
    }
}
