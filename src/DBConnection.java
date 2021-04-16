import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * @author Alex Heaton (w19006404)
 *
 */
public class DBConnection {

	private Connection conn;
	
	public DBConnection() {
		
		conn = null;
		 
	}
	
	public boolean Connect(String filename) {
		
		try {
			String url = "jdbc:sqlite:" + filename;
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to " + filename + " has been established.");
		}
		catch (SQLException e) {
			System.out.println("Failed to connect to " + filename);
			System.out.println("###########RETURNED MESSAGE###########");
			System.out.println(e.getMessage());
			System.out.println("################GABAGOO###############");
			return false;
		}
		return true;
	}
	
	public boolean RunSQL(String sql){
		if(conn == null) {
			System.out.println("There is no database loaded. Cannot run SQL.");
			return false;
		}
		try {
			Statement sqlStatement = conn.createStatement();
			sqlStatement.execute(sql);
		}
		catch(SQLException e) {
			System.out.println("Failed to execute: " + sql);
			System.out.println("###########RETURNED MESSAGE###########");
			System.out.println(e.getMessage());
			System.out.println("################GABAGOO###############");
			return false;
		}
		return true;
	}
	
	public ResultSet RunSQLQuery(String sql){
		ResultSet result;
		if(conn == null) {
			System.out.println("There is no database loaded. Cannot run SQL.");
			return null;
		}
		try {
			Statement sqlStatement = conn.createStatement();
			result = sqlStatement.executeQuery(sql);
		}
		catch (SQLException e) {
			System.out.println("Failed to execute: " + sql);
			System.out.println("###########RETURNED MESSAGE###########");
			System.out.println(e.getMessage());
			System.out.println("################GABAGOO###############");
			return null;
		}
		return result;
	}
}
