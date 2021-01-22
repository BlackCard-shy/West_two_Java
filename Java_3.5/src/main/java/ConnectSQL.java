import java.sql.*;

public class ConnectSQL {
    public static Connection getConnection() {
        // JDBC连接的URL, 不同数据库有不同的格式:
        String JDBC_URL = "jdbc:mysql://localhost:3306/Covid_19?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "123456";
// 获取连接:
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//            try (Statement stmt = conn.createStatement()) {
//                try (ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")) {
//                    while (rs.next()) {
//                        long id = rs.getLong(1); // 注意：索引从1开始
//                        long grade = rs.getLong(2);
//                        String name = rs.getString(3);
//                        int gender = rs.getInt(4);
//                        System.out.println(id);
//                    }
//                }
//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    // 关闭连接:
    public static void endConnection(Connection conn) {
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
