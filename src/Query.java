public class Query {
    String query;

    public static String getInsertQuery(String tableName) {
        return "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
    }

    public static String getUpdateQuery(String tableName, int id) {
        return "UPDATE " + tableName + " SET name = ?, course = ? WHERE id = " + id;
    }

    public static String getDeleteQuery(String tableName, int id) {
        return "DELETE FROM " + tableName + " WHERE id = " + id;
    }

    public static String getSelectQuery(String tableName) {
        return "SELECT * FROM " + tableName;
    }
}
