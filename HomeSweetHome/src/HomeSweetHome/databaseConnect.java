package HomeSweetHome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;




public class databaseConnect {
	
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/homesweethome";

    private static final String USER = personal.Pid;
    private static final String PW = personal.Ppw;

    private static final String USER = "root";
    private static final String PW = "";


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
    
    private Connection getConnection() throws SQLException {
        // MySQL 서버의 JDBC URL, 사용자 이름 및 암호
        String url = "jdbc:mysql://localhost:3306/homesweethome";
        String user = personal.Pid;
        String password = personal.Ppw;

        // 연결을 설정합니다.
        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;
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

    
    //로그인에 관한 코드
    public static boolean storeUserInDatabase(String username, String password) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = connect();

            String query = "INSERT INTO user (user_ID, user_password) VALUES (?, ?)";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } finally {
            close(conn, preparedStatement, null);
        }
    }

    public static boolean isUserExists(String username) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = connect();

            String query = "SELECT * FROM user WHERE user_ID = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            // 결과가 존재하면 사용자가 이미 존재함
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // 예외 처리 (로그 또는 다른 처리 방법을 선택할 수 있음)
            return false;
        } finally {
            close(conn, preparedStatement, resultSet);
        }
    }
    
    public static String checkPassword(String username, String password) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = connect();

            String query = "SELECT user_ID FROM user WHERE user_ID = ? AND user_password = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            // 결과가 존재하면 사용자가 존재하고 비밀번호도 일치함
            if (resultSet.next()) {
                return resultSet.getString("user_ID");
            } else {
                return null; // 로그인 실패
            }
        } finally {
            close(conn, preparedStatement, resultSet);
        }
    }


    //메인페이지 찜에 관한 코드
    
    public void addToWishlist(String userID, int productID) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            // 이미 위시리스트에 해당 상품이 있는지 확인
            String checkQuery = "SELECT * FROM wishlist WHERE wishlist_user_ID = ? AND wishlist_product_ID = ?";
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, userID);
            preparedStatement.setInt(2, productID);
            resultSet = preparedStatement.executeQuery();

            // 이미 위시리스트에 있는 경우 추가하지 않고 종료
            if (resultSet.next()) {
                System.out.println("이미 위시리스트에 있는 상품입니다.");
                return;
            }

            // 위시리스트에 추가
            String insertQuery = "INSERT INTO wishlist (wishlist_user_ID, wishlist_product_ID) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, userID);
            preparedStatement.setInt(2, productID);
            preparedStatement.executeUpdate();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }


    public void removeFromWishlist(String userID, int productID) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String query = "DELETE FROM wishlist WHERE wishlist_user_ID = ? AND wishlist_product_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            preparedStatement.setInt(2, productID);
            preparedStatement.executeUpdate();
        } finally {
            close(connection, preparedStatement, null);
        }
    }

    //아이디 동일성 판단 후 찜한 상품 판단
    public static ResultSet getWishlistProducts(String userID) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = connect();

            String query = "SELECT product.* FROM product INNER JOIN wishlist ON product.product_ID = wishlist.wishlist_product_ID WHERE wishlist.wishlist_user_ID = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, userID);

            resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            //close(null, preparedStatement, resultSet);
        }
    }
    
 // 위시리스트 테이블에 해당 상품이 있는지 확인하는 메서드
    public boolean checkIfInWishlist(String userID, int productID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            String query = "SELECT * FROM wishlist WHERE wishlist_user_ID = ? AND wishlist_product_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userID);
            preparedStatement.setInt(2, productID);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // 결과가 있으면 true, 없으면 false 반환
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }


    public ResultSet getProductInfo(int productID) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = connect();

            String query = "SELECT * FROM product WHERE product_ID = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productID);

            resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn, preparedStatement);
        }

        return resultSet;
    }





 // 위시리스트에서 제품 정보 가져오기
    public ResultSet getWishlistProductInfo(String userID) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = connect();

            String query = "SELECT product.* FROM product INNER JOIN wishlist ON product.product_ID = wishlist.wishlist_product_ID WHERE wishlist.wishlist_user_ID = ?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, userID);

            resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn, preparedStatement, null);
        }
    }


    
    

  
  
}