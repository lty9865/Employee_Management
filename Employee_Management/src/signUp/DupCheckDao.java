package signUp;

import java.sql.ResultSet;
import java.util.ArrayList;

import db.ConnectDB;
import db.Vo;

public class DupCheckDao {
	private String query;
	private ResultSet rs;

	public ArrayList<Vo> dupCheck(String userID) {
		ArrayList<Vo> list = new ArrayList<Vo>();

		try {
			ConnectDB cn = new ConnectDB();
			cn.getCon();

			query = "SELECT * FROM LOGIN";
			rs = cn.getStmt().executeQuery(query);
			while (rs.next()) {
				String userID1 = rs.getString("ADMIN_ID");

				Vo data = new Vo(userID1);
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
