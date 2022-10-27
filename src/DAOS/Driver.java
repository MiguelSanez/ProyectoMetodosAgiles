
package DAOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {
    public Connection link = null;
    public boolean setupRequired = false;

    public Driver() throws SQLException {
        link = DriverManager.getConnection("jdbc:sqlite:app.db");
    }

    public Statement createQuery() throws SQLException {
        Statement s = link.createStatement();
        s.setQueryTimeout(10);
        return s;
    }

    private boolean tableExists(String name) throws SQLException {
        Statement s = link.createStatement();
        ResultSet rs = s.executeQuery("SELECT count(*) as c FROM sqlite_master WHERE type='table' AND name='" + name + "';");
        rs.next();
        return rs.getInt("c") > 0;
    }
    
    public void createTables() throws SQLException {
        Statement statement = link.createStatement();
        statement.setQueryTimeout(30);
        
        statement.executeUpdate("create table if not exists tareas "
                + "(id integer primary key autoincrement not null, nombre string, descripcion string, estado int)");
    }
}
