/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import edecanat.DBConnection.Connector;
import edecanat.DBConnection.TesterConnector;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class CommonProcesses {
    private static Connection con;
    private static Statement st;

    public static Connection getCon() {
        return con;
    }

    public static Statement getSt() {
        return st;
    }
    
    public static void beforeClass() {
        Connector connector = new TesterConnector();
        con = connector.getDBConnect("192.168.56.101:2202");
    }

    public static void afterClass() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CommonProcesses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void beforeTest() {
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CommonProcesses.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void afterTest() {
        st = null;
    }
    
}
