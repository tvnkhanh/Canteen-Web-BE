package ptit.cnpm.becanteenweb.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    public static Connection openConnection() throws SQLException {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=CANTEEN;user=sa;password=123456;encrypt=false";
        Connection connection = DriverManager.getConnection(connectionUrl);
        return connection;
    }
}
