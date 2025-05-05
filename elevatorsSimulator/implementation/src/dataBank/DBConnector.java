//package dataBank;
//
//import dataStructure.UserQueue;
//import run.User;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//
//public class DBConnector {
//        private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
//        private static final String USER = "postgres";
//        private static final String BD_PASSWD = System.getenv("BD_PASSWD");
//
//        public static UserQueue searchForUsers() {
//            UserQueue users = new UserQueue();
//
//            String sql = "SELECT id, name, origin_floor, destiny_floor FROM users";
//
//            try (Connection conn = DatabaseConnector.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery()) {
//
//                while (rs.next()) {
//                    int id = rs.getInt("id");
//                    String name = rs.getString("name");
//                    int origin_floor = rs.getInt("origin_floor");
//                    int destiny_floor = rs.getInt("destiny_floor");
//
//                    User user = new User(id, name, origin_floor, destiny_floor, true);
//                    users.append(user);
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return users;
//        }
//    }
