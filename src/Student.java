import java.sql.*;

// import apple.laf.JRSUIConstants.State;

public class Student {

    /**
     * Create database
     * @param dbName
     * @throws SQLException
     */
    public void createDatabase (String dbName) throws SQLException {
        Connection connection = Connect.getConnection(dbName);
        if (!databaseExists(dbName)) {
            Statement statement = connection.createStatement();
            String query = "create database "+dbName;
            statement.execute(query);
            System.out.println("Database created successfully");
            connection.close();
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
    public void createTable(String tableName) throws SQLException {
        Connection connection = Connect.getConnection();
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

    public void insertData(int i, String string, String string2, String tableName) throws SQLException {
        Connection connection = Connect.getConnection();
        String query = Query.getInsertQuery(tableName);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, i);
        statement.setString(2, string);
        statement.setString(3, string2);
        statement.executeUpdate();
        System.out.println("Data inserted successfully");
        statement.close();
    }
    

    public void updateData(int i, String string, String string2, String tableName) throws SQLException {
        Connection connection = Connect.getConnection();
        String query = Query.getUpdateQuery(tableName, i);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, string);
        statement.setString(2, string2);
        statement.executeUpdate();
        System.out.println("Data updated successfully");
        statement.close();
    }

    public void deleteData(int i, String tableName) throws SQLException {
        Connection connection = Connect.getConnection();
        Statement statement = connection.createStatement();
        String query = Query.getDeleteQuery(tableName, i);
        statement.execute(query);
        System.out.println("Data deleted successfully");
        statement.close();
    }

    public void selectData(String tableName) throws SQLException {
        Connection connection = Connect.getConnection();
        Statement statement = connection.createStatement();
        String query = Query.getSelectQuery(tableName);
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
        Connection connection = Connect.getConnection();

        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getCatalogs();

        while (resultSet.next()) {
            String databaseName = resultSet.getString(1);
            if (databaseName.equals(dbName)) {
                return true;
            }
        }
        resultSet.close();
        connection.close();
        return false;
    }

    /**
     * Check if table exists
     * @param conn
     * @param tableName
     * @return
     * @throws SQLException
     */
    public boolean doesTableExist(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData dbmd = connection.getMetaData();
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
