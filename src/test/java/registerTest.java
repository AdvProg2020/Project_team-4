import Control.Controller;
import Model.*;
import Model.Manager;
import javafx.scene.control.Control;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.example.*;
import org.junit.jupiter.api.DynamicTest;

import java.sql.Connection;
import java.util.Random;

public class RegisterTest {

    private String username;
    private String password;

    @Test
    public void testCustomerRegister() {
        username = "testestestestest";
        password = "reza";
        //not exist before
        Assert.assertNull(Account.getAccountWithName(username));
        Controller.getOurController().controllerNewAccount("customer", username, password);
        //createAccount
        Assert.assertNull(Account.getAccountWithName(username + "a"));
        // true username
        Assert.assertEquals(username, Account.getAccountWithName(username).getUserName());
        //true password
        Assert.assertEquals("reza", Account.getAccountWithName(username).getPassWord());

    }

    @Test
    public void testSellerRegister(){
        username = "sellertwo";
        password = "12321";
        //not existBefore
        Assert.assertNull(Account.getAccountWithName(username));
        //create Account
        Assert.assertNull(Account.getAccountWithName(username));
        //Accept By Manager
        Controller.getOurController().controllerNewAccount("seller", username, password);
        Request request = Manager.getRequestByName(username);
        Controller.getOurController().acceptRequest(request);
        Assert.assertNotNull(Account.getAccountWithName(username));
    }
}
