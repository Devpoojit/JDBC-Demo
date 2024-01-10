import java.sql.*;

public class Connect {
    private static String dbName = "";

    public static Connection getConnection(String db) throws SQLException {
        dbName = db;
        String url = "jdbc:mysql://localhost:3306/";
        String userName = "root";
        String password = "dev10912";
        return DriverManager.getConnection(url, userName, password);
    }

    public static Connection getConnection() throws SQLException {
        if (dbName.isEmpty()) {
            throw new SQLException("Database name is not set");
        }
        String url = "jdbc:mysql://localhost:3306/" + dbName;
        String userName = "root";
        String password = "dev10912";
        return DriverManager.getConnection(url, userName, password);
    }
}

