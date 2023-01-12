package user.mainScreen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.ConnectDB;

public class SearchNameDao {
	private String query;
	private ResultSet rs;
	private String a;
	private PreparedStatement pstmt = null;

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

	public void suggestions(String userID, String sugTitle, String sugText) {

		try {
			ConnectDB cn = new ConnectDB();
			int num = 1;
			query = "SELECT COUNT(*) c FROM SUGGESTIONS";
			ResultSet countRs = cn.getStmt().executeQuery(query);
			countRs.next();
			if (countRs.getInt("c") == 0) {
			} else {
				num = countRs.getInt("c") + 1;
			}
			String c = String.format("%06d", num);

			query = "SELECT  DEPT_ID FROM EMP e,(SELECT EMP_NO FROM USERLOGIN WHERE user_ID = '" + userID
					+ "') u WHERE e.EMP_NO = u.EMP_NO";
			rs = cn.getStmt().executeQuery(query);

			if (rs.next()) {
				String deptID = rs.getString("DEPT_ID");

				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = sdf.format(date);

				StringBuilder sb = new StringBuilder();
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
				String strDate1 = sdf1.format(date);
				sb.append(strDate1);
				sb.append(c);

				query = "INSERT INTO SUGGESTIONS VALUES (?,?,?,?,?,?)";
				pstmt = cn.getCon().prepareStatement(query);

				pstmt.setString(1, deptID);
				pstmt.setString(2, sugTitle);
				pstmt.setString(3, sugText);
				pstmt.setString(4, strDate);
				pstmt.setString(5, sb.toString());
				pstmt.setString(6, null);

				pstmt.execute();
			}
			cn.getCon().close();
			cn.getStmt().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}