package edecanat.Core.Structures.DAO;

import edecanat.Core.Structures.LookUPlan;
import edecanat.DBConnection.Connector;
import edecanat.DBConnection.TesterConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author User
 */
public class LookUPlanDAOTest {
    
    public LookUPlanDAOTest() {
    }
    
    private static LookUPlanDAO subj;
    private static Connection con;
    private Statement st;
    
    @BeforeClass
    public static void setUpClass() {
        Connector connector = new TesterConnector();
        con = connector.getDBConnect("");
        subj = new LookUPlanDAO(con);
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            subj.closeConnection();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LookUPlanDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() throws SQLException {
        st = con.createStatement();
    }
    
    @After
    public void tearDown() {
        st = null;
    }

    @Test
    public void testCreate() throws Exception {
        String sql = "select count(*) as cnt from dbo.LOOKUPLAN";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        Integer expected = rs.getInt("cnt") + 1;
        subj.create(new LookUPlan(null, 10, "nameplan", "425", "01.01.2013", 10));
        rs = st.executeQuery(sql);
        rs.next();
        Integer actual = rs.getInt("cnt");
        assertEquals(expected, actual);
    }

    @Test
    public void testReadAll() throws Exception {
        String sql = "select count(*) as cnt from dbo.LOOKUPLAN";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        Integer expected = rs.getInt("cnt");
        Integer actual = subj.readAll().size();
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() throws Exception {
        LookUPlan exp = new LookUPlan(7, 10, "test", "test", "test", 10);
        subj.update(exp);
        String sql = "select * from dbo.LOOKUPLAN where ID=7";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        LookUPlan act = new LookUPlan(rs.getInt("ID"), rs.getInt("IDPLAN"), 
                rs.getString("NAMEPLAN"), rs.getString("GRUPPA"), 
                rs.getString("GOD"), rs.getInt("COLSTUD"));
        assertEquals(exp.getIdPlan(), act.getIdPlan());
        assertEquals(exp.getColStud(), act.getColStud());
        assertEquals(exp.getGod(), act.getGod());
        assertEquals(exp.getGruppa(), act.getGruppa());
        assertEquals(exp.getId(), act.getId());
        assertEquals(exp.getNamePlan(), act.getNamePlan());
    }

    @Test
    public void testDelete() throws Exception {
        String sql = "select count(*) as cnt from dbo.LOOKUPLAN";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        Integer exp = rs.getInt("cnt") - 1;
        subj.delete(new LookUPlan(5, 3, "qqwe", "qwe", "qwe", 10));
        rs = st.executeQuery(sql);
        rs.next();
        Integer act = rs.getInt("cnt");
        assertEquals(exp, act);
    }

    @Test
    public void testGetMinId() throws Exception {
        String sql = "select min(ID) as minid from dbo.LOOKUPLAN";
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        Integer exp = rs.getInt("minid");
        Integer act = subj.getMinId();
        assertEquals(exp, act);
    }
    
    @Test(expected=NullPointerException.class)
    public void testCreateNPE() throws SQLException {
        subj.create(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreateIAE() throws SQLException {
        subj.create(new Object());
    }
    
    @Test(expected=NullPointerException.class)
    public void testUpdateNPE() throws SQLException {
        subj.update(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testUpdateIAE() throws SQLException {
        subj.update(new Object());
    }
    
    @Test(expected=NullPointerException.class)
    public void testDeleteNPE() throws SQLException {
        subj.delete(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testDeleteIAE() throws SQLException {
        subj.delete(new Object());
    }
    
    @Test
    public void testFindById() throws SQLException {
        Integer exp = 13;
        LookUPlan obj = (LookUPlan) subj.findById(13);
        Integer act = obj.getId();
        assertEquals(exp, act);
        obj = (LookUPlan) subj.findById(-10);
        assertNull(obj);
    }
}
