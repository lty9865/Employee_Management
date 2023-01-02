package login;

import java.sql.ResultSet;

import java.util.ArrayList;

import db.ConnectDB;
import db.Vo;

public class LoginDao {
	private String query;
	private ResultSet rs;

	public ArrayList<Vo> login(String userID, String userPW) {
		ArrayList<Vo> list = new ArrayList<Vo>();

		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT * FROM LOGIN";
			rs = cn.getStmt().executeQuery(query);
			while (rs.next()) {
				String userID1 = rs.getString("ADMIN_ID");
				String userPW1 = rs.getString("ADMIN_PW");

				Vo data = new Vo(userID1, userPW1);
				list.add(data);

			}
			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
