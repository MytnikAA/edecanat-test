/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edecanat.Core.Structures.DAO;

import common.CommonProcesses;
import edecanat.Core.Structures.Sfak;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class SfakDAOTest {
    
    public SfakDAOTest() {
    }
    
    private static SfakDAO subj;
    
    @BeforeClass
    public static void setUpClass() {
        CommonProcesses.beforeClass();
        subj = new SfakDAO(CommonProcesses.getCon());
    }
    
    @AfterClass
    public static void tearDownClass() {
        CommonProcesses.afterClass();
        try {
            subj.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(SfakDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
        CommonProcesses.beforeTest();
    }
    
    @After
    public void tearDown() {
        CommonProcesses.afterTest();
    }

    @Test
    public void testCreate() throws Exception {
        System.out.println("create sfak...");
        String sql = "select count(*) as cnt from dbo.SFAK";
        ResultSet rs = CommonProcesses.getSt().executeQuery(sql);
        rs.next();
        Integer exp = rs.getInt("cnt") + 1;
        subj.create(new Sfak(null, "test", "test"));
        rs = CommonProcesses.getSt().executeQuery(sql);
        rs.next();
        Integer act = rs.getInt("cnt");
        assertEquals(exp, act);
    }

    @Test
    public void testReadAll() throws Exception {
        System.out.println("readAll from sfak...");
        String sql = "select count(*) as cnt from dbo.SFAK";
        ResultSet rs = CommonProcesses.getSt().executeQuery(sql);
        rs.next();
        Integer exp = rs.getInt("cnt");
        ArrayList<Sfak> list = subj.readAll();
        Integer act = list.size();
        assertEquals(exp, act);
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("update sfak... ");
        subj.update(new Sfak(1, "test", "test"));
        String sql = "select * from dbo.SFAK where COD=1";
        ResultSet rs = CommonProcesses.getSt().executeQuery(sql);
        rs.next();
        String exp = "test";
        String act = rs.getString("IM");
        assertEquals(exp, act);
        act = rs.getString("IMS");
        assertEquals(exp, act);
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("delete sfak... ");
        String sql = "select count(*) as cnt from dbo.SFAK";
        ResultSet rs = CommonProcesses.getSt().executeQuery(sql);
        rs.next();
        Integer exp = rs.getInt("cnt") - 1;
        subj.delete(new Sfak(3, "any", "any"));
        rs = CommonProcesses.getSt().executeQuery(sql);
        rs.next();
        Integer act = rs.getInt("cnt");
        assertEquals(exp, act);
        exp = 0;
        sql = "select count(*) as cnt from dbo.SFAK where COD=2";
        rs = CommonProcesses.getSt().executeQuery(sql);
        rs.next();
        act = rs.getInt("cnt");
        assertEquals(exp, act);
    }

    @Test
    public void testGetMinId() throws Exception {
        System.out.println("getMinId");
        String sql = "select min(COD) as minid from dbo.SFAK";
        ResultSet rs = CommonProcesses.getSt().executeQuery(sql);
        rs.next();
        Integer exp = rs.getInt("minid");
        Integer act = subj.getMinId();
        assertEquals(exp, act);
    }
    
    @Test(expected=NullPointerException.class)
    public void testCreateNull() {
        try {
            subj.create(null);
        } catch (SQLException ex) {
            Logger.getLogger(SfakDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreateIllegal() {
        try {   
            subj.create(new Byte("qwe"));
        } catch (SQLException ex) {
            Logger.getLogger(SfakDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected=NullPointerException.class)
    public void testUpdateNull() {
        try {
            subj.update(null);
        } catch (SQLException ex) {
            Logger.getLogger(SfakDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testUpdateIllegal() {
        try {   
            subj.update(new Byte("qwe"));
        } catch (SQLException ex) {
            Logger.getLogger(SfakDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test(expected=NullPointerException.class)
    public void testDeleteNull() {
        try {
            subj.delete(null);
        } catch (SQLException ex) {
            Logger.getLogger(SfakDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testDeleteIllegal() {
        try {   
            subj.delete(new Byte("qwe"));
        } catch (SQLException ex) {
            Logger.getLogger(SfakDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testFindById() throws SQLException {
        Integer exp = 5;
        Sfak sf = (Sfak) subj.findById(5);
        Integer act = sf.getCod();
        assertEquals(exp, act);
        sf = (Sfak) subj.findById(-10);
        assertNull(sf);
    }
}
