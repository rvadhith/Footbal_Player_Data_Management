package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	public static Connection getConnection() throws Exception {
		//Establishing connection
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","adhithya","ragvij07ad");
		return con;
	}
}
