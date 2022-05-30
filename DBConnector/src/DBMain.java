import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connect = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc::mysql://localhost:19006/insurance?serverTimezone=UTC&useSSL=false", "root", "^^lloopp9909JY");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
