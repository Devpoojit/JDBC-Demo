import java.sql.*;
import java.util.Scanner;

// JDBC Connection
public class JDBCFirst {
    /**
     * JDBC Connection
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        Student student = new Student(); // Create Student object
        Scanner scanner = new Scanner(System.in); // Create Scanner object
        System.out.println("Enter name of database to use or create: "); 
        String dbName = scanner.nextLine(); // Get user input for database name
        System.out.println("Enter name of table to use or create: ");
        String tableName = scanner.nextLine(); // Get user input for table name
        student.createDatabase(dbName); // Create database
        student.createTable(dbName, tableName); // Create table
        boolean flag = true;

        while (flag) {
            System.out.println("Whcih operation do you want to perform?");
            System.out.println("INSERT | UPDATE | DELETE | SELECT | EXIT");
            String operation = scanner.nextLine();
            switch (operation) {
                case "INSERT":
                case "insert":
                    System.out.println("Enter id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter course: ");
                    String course = scanner.nextLine();
                    student.insertData(id, name, course);
                    // student.insertData(1, "John", "Java");
                    break;
                case "UPDATE":
                case "update":
                    System.out.println("Enter id: ");
                    int id1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter new name: ");
                    String name1 = scanner.nextLine();
                    System.out.println("Enter new course: ");
                    String course1 = scanner.nextLine();
                    student.updateData(id1, name1, course1);
                    break;
                case "DELETE":
                case "delete":
                    System.out.println("Enter id: ");
                    int id2 = scanner.nextInt();
                    student.deleteData(id2);
                    break;
                case "SELECT":
                case "select":
                    student.selectData(tableName);
                    break;
                case "EXIT":
                case "exit":
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid operation");
                    break;
            }
            
        }
        scanner.close();
    }
}
