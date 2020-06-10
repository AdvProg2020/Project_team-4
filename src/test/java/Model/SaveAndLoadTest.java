package Model;

import org.junit.Test;

import java.io.IOException;

public class SaveAndLoadTest {
    private static Customer customer = new Customer("ali", "ali1230?aoksidjfkm']");
    @Test
    private static void saveTest() throws IOException {
        customer.setCredit(50000);
        customer.setEmail("email@email.com");
        customer.setFirstName("ali");
        customer.setLastName("aliiii");
        customer.setPassWord("iuy7tr");
        customer.setPhoneNumber("00000000000000000000");
//        SaveAndLoad.writeJSONAccount(customer);
    }
}
