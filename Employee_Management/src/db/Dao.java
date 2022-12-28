package db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Dao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green2";
	String password = "green1234";
	String query;

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	public ArrayList<Vo> insert(String userID, String userPW) {
		ArrayList<Vo> list = new ArrayList<Vo>();

		try {
			connDB();

			query = "INSERT INTO LOGIN VALUES ('" + userID + "', '" + userPW + "')";
			boolean b = stmt.execute(query);

			if (!b) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Vo> dupCheck(String userID) {
		ArrayList<Vo> list = new ArrayList<Vo>();

		try {
			connDB();

			query = "SELECT * FROM LOGIN";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String userID1 = rs.getString("ADMIN_ID");

				Vo data = new Vo(userID1);
				list.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Vo> login(String userID, String userPW) {
		ArrayList<Vo> list = new ArrayList<Vo>();

		try {
			connDB();

			query = "SELECT * FROM LOGIN";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String userID1 = rs.getString("ADMIN_ID");
				String userPW1 = rs.getString("ADMIN_PW");

				Vo data = new Vo(userID1, userPW1);
				list.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void connDB() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
