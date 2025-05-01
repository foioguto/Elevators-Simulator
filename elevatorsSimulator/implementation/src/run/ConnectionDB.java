package run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConnectionDB {
    private static final String BD_PASSWD = System.getenv("BD_PASSWD");
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";

    public void start() {

        try (Connection conn = DriverManager.getConnection(URL, USER, BD_PASSWD)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            System.out.println("Success!");

            while (rs.next()) {
                System.out.println(rs.getString("first_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public static String getBdPasswd() {
        return BD_PASSWD;
    }

    public String getUrl() {
        return URL;
    }

    public String getUser() {
        return USER;
    }

}
