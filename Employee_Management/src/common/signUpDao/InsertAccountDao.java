package common.signUpDao;

import db.ConnectDB;

public class InsertAccountDao {
	private String query;
	private boolean is;

	public void insert(String userID, String userPW) {

		try {
			ConnectDB cn = new ConnectDB();

			query = "INSERT INTO LOGIN VALUES ('" + userID + "', '" + userPW + "')";
			is = cn.getStmt().execute(query);

			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void userInsert(String empNo, String userID, String userPW) {

		try {
			ConnectDB cn = new ConnectDB();

			query = "INSERT INTO USERLOGIN VALUES ('" + empNo + "','" + userID + "','" + userPW + "')";
			is = cn.getStmt().execute(query);

			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean getIs() {
		return is;
	}
}
