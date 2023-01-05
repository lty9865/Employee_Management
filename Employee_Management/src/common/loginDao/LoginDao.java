package common.loginDao;

import java.sql.ResultSet;

import java.util.ArrayList;

import db.ConnectDB;

public class LoginDao {
	private String query;
	private ResultSet rs;

	public ArrayList<LoginVo> adminLogin(String userID, String userPW) {
		ArrayList<LoginVo> list = new ArrayList<LoginVo>();

		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT * FROM LOGIN";
			rs = cn.getStmt().executeQuery(query);
			while (rs.next()) {
				String userID1 = rs.getString("ADMIN_ID");
				String userPW1 = rs.getString("ADMIN_PW");

				LoginVo data = new LoginVo(userID1, userPW1);
				list.add(data);

			}
			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<LoginVo> userLogin(String userID, String userPW) {
		ArrayList<LoginVo> list = new ArrayList<LoginVo>();

		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT * FROM USERLOGIN";
			rs = cn.getStmt().executeQuery(query);
			while (rs.next()) {
				String userID1 = rs.getString("USER_ID");
				String userPW1 = rs.getString("USER_PW");

				LoginVo data = new LoginVo(userID1, userPW1);
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
