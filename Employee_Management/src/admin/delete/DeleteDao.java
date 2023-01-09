package admin.delete;

import db.ConnectDB;

public class DeleteDao {
	private String query;

	public DeleteDao() {

	}

	public DeleteDao(String deptNum) {

		try {
			ConnectDB cn = new ConnectDB();

			query = "DELETE FROM commute WHERE EMP_NO LIKE " + deptNum;
			cn.getStmt().executeUpdate(query);
			query = "DELETE FROM EMP WHERE EMP_NO LIKE " + deptNum;
			cn.getStmt().executeUpdate(query);

			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ModifyDao(String pos, String name, String birth, String mobile, String empNo) {
		try {
			ConnectDB cn = new ConnectDB();

			query = "UPDATE EMP " + "SET POS = '" + pos + "', " + "NAME = '" + name + "', " + "BIRTH = '" + birth
					+ "', " + "MOBILE = '" + mobile + "' " + "WHERE EMP_NO = '" + empNo + "'";
			cn.getStmt().executeUpdate(query);

			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
