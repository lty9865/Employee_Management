package admin.signUp;

import java.util.ArrayList;

import admin.db.ConnectDB;
import admin.login.LoginVo;

public class InsertAccountDao {
	private String query;

	public ArrayList<LoginVo> insert(String userID, String userPW) {
		ArrayList<LoginVo> list = new ArrayList<LoginVo>();

		try {
			ConnectDB cn = new ConnectDB();

			query = "INSERT INTO LOGIN VALUES ('" + userID + "', '" + userPW + "')";
			cn.getStmt().execute(query);
			
			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
