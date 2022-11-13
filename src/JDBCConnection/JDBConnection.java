package JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBConnection {
    public static Connection conn=null;

    public static Connection getConnection()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:/Users/ashish/IdeaProjects/APP_Project_V3/src/JDBCConnection/mDb.db";
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception ex) {
        }
        return conn;
    }
}
