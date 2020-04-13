package View;

import org.junit.Assert;
import org.junit.Test;

public class CommandsSourceTest {
    @Test
    public void getEnumByRegexTest(){
        String expected = "CREATE_ACCOUNT";
        String actual = CommandsSource.getEnumNameByRegex("(?i)create\\s+account\\s+(manager|seller|customer)\\s+(\\w)+");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void findEnumTest(){
        String expected = "CREATE_ACCOUNT";
        String actual = CommandsSource.getEnumNameByRegex("(?i)create\\s+account\\s+(manager|seller|customer)\\s+(\\w)+");
        Assert.assertEquals(expected, actual);
    }
}
