package HomeSweetHome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class databaseConnect {
	
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/homesweethome";
    private static final String USER = "root";
    private static final String PW = "wkrdn6808!";

    public static Connection connect() throws Exception {
    	try {
    		Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PW);
    	} catch (ClassNotFoundException | SQLException e) {
            // ClassNotFoundException은 클래스를 찾을 수 없는 경우 발생
            // SQLException은 데이터베이스 연결 등에 문제가 있는 경우 발생
            // 이 예외들을 SQLException으로 묶어서 던집니다.
            throw new SQLException("DB 연결 중 오류 발생", e);
        }
        
    }

    public static void close(Connection conn, Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
            System.out.println("Database Connection Terminated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
                System.out.println("데이터베이스 연결 종료");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ResultSet getProducts(int styleCode) throws Exception {
    	Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = connect();
            
            String query;
            if (styleCode > 0) {
                query = "SELECT * FROM product WHERE product_stylecode = ?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, styleCode);}

            resultSet = preparedStatement.executeQuery();
            return resultSet;  // 여기서 닫을 필요 없음
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            //close(null, preparedStatement, resultSet);
        }
    }
}