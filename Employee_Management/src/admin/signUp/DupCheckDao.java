package admin.signUp;

import java.sql.ResultSet;
import java.util.ArrayList;

import admin.db.ConnectDB;
import admin.login.LoginVo;

public class DupCheckDao {
	private String query;
	private ResultSet rs;

	public ArrayList<LoginVo> dupCheck(String userID) {
		ArrayList<LoginVo> list = new ArrayList<LoginVo>();

		try {
			ConnectDB cn = new ConnectDB();
			cn.getCon();

			query = "SELECT * FROM LOGIN";
			rs = cn.getStmt().executeQuery(query);
			while (rs.next()) {
				String userID1 = rs.getString("ADMIN_ID");

				LoginVo data = new LoginVo(userID1);
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
