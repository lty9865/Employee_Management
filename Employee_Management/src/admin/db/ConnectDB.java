package admin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectDB {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green2";
	String password = "green1234";

	private Connection con;
	private Statement stmt;

	public ConnectDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement();
			System.out.println("statement create success.");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public Statement getStmt() {
		return stmt;
	}

	public Connection getCon() {
		return con;
	}
}
