package edecanat.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Anton Mytnik <MytnikAA@gmail.com>
 */
public class StringHelperTest {
    
    public StringHelperTest() {
    }

    @Test
    @Ignore
    public void testExtractValueFromString() {
        System.out.println("extractValueFromString");
        String str = "";
        Double expResult = null;
        Double result = StringHelper.extractValueFromString(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testJoin() {
        System.out.println("join");
        String[] strings = {"a", "b", "c"};
        String separator = "-";
        String expResult = "a-b-c";
        String result = StringHelper.join(strings, separator);
        System.out.println(result);
        assertEquals(expResult, result);
    }
}