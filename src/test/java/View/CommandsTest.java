package View;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CommandsTest {
    @Test
    public void getEnumByRegexTest(){
        String expected = "CREATE_ACCOUNT";
        String actual = Commands.getEnumNameByRegex("(?i)create\\s+account\\s+(manager|seller|customer)\\s+(\\w)+");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void findEnumTest(){
        String expected = "CREATE_ACCOUNT";
        String actual = Commands.getEnumNameByRegex("(?i)create\\s+account\\s+(manager|seller|customer)\\s+(\\w)+");
        Assert.assertEquals(expected, actual);
    }
}
