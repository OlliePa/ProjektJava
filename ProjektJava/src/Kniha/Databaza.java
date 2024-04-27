package Kniha;

import java.sql.*;
import java.util.*;

public class Databaza {
    static final String DB_URL = "jdbc:sqlite:library.db";
    
    public static Connection getConnection() throws SQLException {
    	try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found.");
            e.printStackTrace();
        }
    	Connection conn = DriverManager.getConnection(DB_URL);
        return conn;
    }

    public static void initializeDatabase(Connection conn) throws SQLException{
             {
            String sql = "CREATE TABLE IF NOT EXISTS knihy (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nazov VARCHAR(255) NOT NULL," +
                    "autory VARCHAR(255) NOT NULL," +
                    "rok INT NOT NULL," +
                    "dostupnost BOOLEAN NOT NULL," +
                    "zaner VARCHAR(255)," +
                    "rocnik INT)";
            try(Statement stmt = conn.createStatement()){
            	stmt.executeUpdate(sql);
            }
            
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
