package user.mainScreen;

import java.sql.ResultSet;

import db.ConnectDB;

public class SearchNameDao {
	private String query;
	private ResultSet rs;
	private String a;

	public SearchNameDao() {
	}

	public String EmpName(String userID) {

		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT NAME FROM EMP,USERLOGIN WHERE EMP.EMP_NO=USERLOGIN.EMP_NO AND USER_ID LIKE '"
					+ userID.toUpperCase() + "'";
			rs = cn.getStmt().executeQuery(query);
			while (rs.next()) {
				a = rs.getString("NAME");
			}

			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
}
