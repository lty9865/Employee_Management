package admin.delete;

import db.ConnectDB;

public class DeleteDao {
	private String query;

	public DeleteDao(String deptNum) {

		try {
			ConnectDB cn = new ConnectDB();

			query = "DELETE FROM EMP WHERE EMP_NO LIKE " + deptNum;
			cn.getStmt().executeUpdate(query);

			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
