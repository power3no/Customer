import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class CustomerDAO {
	private CustomerDAO() {}
	
	private static CustomerDAO instance = new CustomerDAO();

	public static final CustomerDAO getInstance() {
		return instance;
	}
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String ip;
	
	// DB 연결 작업 수행 후 연결 객체(Connection) 를 리턴하는 getConnection() 메서드 정의
	private Connection getConnection(String ip) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://" + ip + ":3306/java5";
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return con;
	}
	
	private void closeDb() {
		if(rs != null) try { rs.close(); } catch (Exception e) {}
		if(pstmt != null) try { pstmt.close(); } catch (Exception e) {}
		if(con != null) try { con.close(); } catch (Exception e) {}
	}
	
	// IP주소, 계정명, 패스워드를 전달받아 로그인 기능을 수행하는 login() 메서드 정의
	public boolean login(String ip, String dbUsername, String dbPassword) throws LoginFailException {
		// getConnection() 메서드를 호출하여 Connection 객체 가져오기
		con = getConnection(ip);
		this.ip = ip;

		
		boolean result = false; // 로그인 결과
		
		try {
			String sql = "SELECT password FROM customer WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dbUsername);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dbPassword.equals(rs.getString(1))) {
					result = true;
				} else {
					throw new LoginFailException("패스워드 틀림");
				}
			} else {
				throw new LoginFailException("존재하지 않는 아이디");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDb();
		}
		
		return result;
	}

	public boolean insert(String name, String id, String password, String jumin) {
		boolean result = false;
		con = getConnection(ip);
		try {
			String sql = "INSERT INTO customer VALUES(0,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, password);
			pstmt.setString(4, jumin);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			closeDb();
		}
		
		return result;
	}

	public boolean delete(String inputData) {
		
		boolean result = false;
		con = getConnection(ip);
		int idx = Integer.parseInt(inputData);
		try {
			String sql = "DELETE FROM customer WHERE idx=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDb();
		}
		return result;
	}

	public Vector<Vector> select() {
		Vector<Vector> vector = new Vector<Vector>();
		con = getConnection(ip);
		try {
			String sql = "SELECT * FROM customer";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector<String> obj = new Vector<String>();
				obj.addElement(rs.getString("idx"));
				obj.addElement(rs.getString("name"));
				obj.addElement(rs.getString("id"));
				obj.addElement(rs.getString("password"));
				obj.addElement(rs.getString("jumin"));
				vector.addElement(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDb();
		}
		
		return vector;
	}
	
}

