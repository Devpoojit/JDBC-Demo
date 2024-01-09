import java.sql.*;

import apple.laf.JRSUIConstants.State;

public class Student {

    /**
     * Create database
     * @param dbName
     * @throws SQLException
     */
    public void createDatabase (String dbName) throws SQLException {
        if (!databaseExists(dbName)) {
            String url = "jdbc:mysql://localhost:3306/";
            String userName = "root";
            String password = "dev10912";

            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement statement = conn.createStatement();
            String query = "create database "+dbName;
            statement.execute(query);
            System.out.println("Database created successfully");
            conn.close();
        } else {
            System.out.println("Database already exists");
        }
    }  

    /**
     * Create table
     * @param tableName2
     * @param dbName
     * @throws SQLException
     */
    public void createTable(String dbName, String tableName) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String userName = "root";
        String password = "dev10912";

        Connection connection = DriverManager.getConnection((url + dbName), userName, password);
        if (doesTableExist(connection, tableName)) {
            System.out.println("Table already exists");
            return;
        }
        Statement statement = connection.createStatement();
        String query = "CREATE TABLE " + tableName + " (id INT PRIMARY KEY, name VARCHAR(20), course VARCHAR(20))";
        statement.execute(query);
        System.out.println("Table created successfully");
        connection.close();
    }

    public void insertData(int i, String string, String string2) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/students";
        String userName = "root";
        String password = "dev10912";
        Connection connection = DriverManager.getConnection(url, userName, password);
        String query = "INSERT INTO students VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, i);
        statement.setString(2, string);
        statement.setString(3, string2);
        statement.executeUpdate();
        System.out.println("Data inserted successfully");
        statement.close();
    }
    

    public void updateData(int i, String string, String string2) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/students";
        String userName = "root";
        String password = "dev10912";
        Connection connection = DriverManager.getConnection(url, userName, password);
        String query = "UPDATE students SET name = ?, course = ? WHERE id = " + i;
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, string);
        statement.setString(2, string2);
        statement.executeUpdate();
        System.out.println("Data updated successfully");
        statement.close();
    }

    public void deleteData(int i) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/students";
        String userName = "root";
        String password = "dev10912";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "DELETE FROM students WHERE id = 1";
        statement.execute(query);
        System.out.println("Data deleted successfully");
        statement.close();
    }

    public void selectData(String tableName) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/students";
        String userName = "root";
        String password = "dev10912";
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM " + tableName;
        ResultSet resultSet = statement.executeQuery(query);        
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
        }
    }




    /** ------------------Helper Methods-------------------------- */


    /**
     * Check if database exists
     * @param dbName
     * @return
     * @throws SQLException
     */
    public boolean databaseExists (String dbName) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String userName = "root";
        String password = "dev10912";

        Connection conn = DriverManager.getConnection(url, userName, password);

        DatabaseMetaData meta = conn.getMetaData();
        ResultSet resultSet = meta.getCatalogs();

        while (resultSet.next()) {
            String databaseName = resultSet.getString(1);
            if (databaseName.equals(dbName)) {
                return true;
            }
        }
        resultSet.close();
        conn.close();
        return false;
    }

    /**
     * Check if table exists
     * @param conn
     * @param tableName
     * @return
     * @throws SQLException
     */
    public boolean doesTableExist(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();
        try (ResultSet rs = dbmd.getTables(null, null, tableName, null)) {
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(tableName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
