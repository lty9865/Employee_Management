package admin.delete;

import java.sql.ResultSet;

import admin.db.ConnectDB;

public class SearchDel {
	private String query;
	private ResultSet rs;
	String[] strDel;

	public String[] strDel(String deptNum) {

		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT DEPT_NAME, POS, NAME, BIRTH, MOBILE "
					+"FROM EMP e, DEPARTMENT d "
					+"WHERE E.DEPT_ID = D.DEPT_ID "
					+"AND e.EMP_NO LIKE " + deptNum;
			rs = cn.getStmt().executeQuery(query);

			rs.next();

			strDel = new String[5];
			strDel[0] = rs.getString("DEPT_NAME");
			strDel[1] = rs.getString("NAME");
			strDel[2] = rs.getString("POS");
			strDel[3] = rs.getString("BIRTH");
			strDel[4] = rs.getString("MOBILE");

			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDel;
	}
}
