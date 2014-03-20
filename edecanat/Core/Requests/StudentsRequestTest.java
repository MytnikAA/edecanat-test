package edecanat.Core.Requests;

import edecanat.utils.sql.Criterion;
import edecanat.utils.sql.Restrictions;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Anton Mytnik <MytnikAA@gmail.com>
 */
public class StudentsRequestTest {
    
    public StudentsRequestTest() {
    }
    
    

    @Test
    @Ignore
    public void testList() {
        System.out.println("list");
        StudentsRequest instance = null;
        List expResult = null;
        List result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testAdd() {
        System.out.println("add");
        Criterion c = null;
        StudentsRequest instance = null;
        StudentsRequest expResult = null;
        StudentsRequest result = instance.add(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testGetCriteries() {
        System.out.println("getCriteries");
        StudentsRequest instance = null;
        List expResult = null;
        List result = instance.getCriteries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetSqlWhereFragment() throws SQLException {
        System.out.println("getSqlWhereFragment");
        StudentsRequest instance = new StudentsRequest("select * from t", null);
        instance.add(Restrictions.like("FIO", "Пупкин"));
        instance.add(Restrictions.gt("SKURS", "8"));
        String expResult = " WHERE  (FIO LIKE 'Пупкин%')  AND  (SKURS > '8') ";
        System.out.println(instance.getSql());
        System.out.println(instance.getSqlWhereFragment());
        System.out.println(expResult);
        String result = instance.getSqlWhereFragment();
        assertEquals(expResult, result);
    }
}