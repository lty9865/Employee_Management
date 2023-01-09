package user.mainScreen;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import db.ConnectDB;

public class InsertTimeDao {
	private PreparedStatement pstmt = null;
	private ResultSet rs;

	public InsertTimeDao() {
	}

	public InsertTimeDao(String userID, String commute) {
		try {
			ConnectDB cn = new ConnectDB();

			String query = "SELECT EMP.EMP_NO FROM EMP,USERLOGIN WHERE EMP.EMP_NO = USERLOGIN.EMP_NO AND USER_ID LIKE '"
					+ userID + "'";
			rs = cn.getStmt().executeQuery(query);
			rs.next();
			String empNo = rs.getString("EMP_NO");

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sdf.format(date);

			LocalTime now = LocalTime.now();
			DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
			String strTime = now.format(df);

			query = "UPDATE EMP SET COMMUTE = '" + commute + "' WHERE EMP_NO = " + empNo;
			cn.getStmt().execute(query);
			switch (commute) {
			case "출근":
				query = "INSERT INTO COMMUTE VALUES (?,?,?,?)";
				pstmt = cn.getCon().prepareStatement(query);

				pstmt.setString(1, empNo);
				pstmt.setString(2, strDate);
				pstmt.setString(3, strTime);
				pstmt.setString(4, null);

				pstmt.executeUpdate();
				break;

			case "퇴근":
				query = "UPDATE COMMUTE SET ENDWORK = '" + strTime + "' WHERE EMP_NO = " + empNo + " AND NOWTIME = '"
						+ strDate + "'";
				cn.getStmt().execute(query);
				cn.getStmt().close();
				cn.getCon().close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<TimeVo> SearchWork(String userID) {
		ArrayList<TimeVo> list = new ArrayList<TimeVo>();

		try {
			ConnectDB cn = new ConnectDB();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sdf.format(date);

			String query = "SELECT EMP.EMP_NO, NOWTIME, STARTWORK, ENDWORK "
			             + "FROM EMP,USERLOGIN,COMMUTE "
					     + "WHERE EMP.EMP_NO = USERLOGIN.EMP_NO "
			             + "AND COMMUTE.EMP_NO = EMP.EMP_NO "
					     + "AND NOWTIME = '" + strDate + "' "
			             + "AND USER_ID LIKE '" + userID + "'";
			rs = cn.getStmt().executeQuery(query);
			while (rs.next()) {
				String startWork = rs.getString("STARTWORK");
				String endWork = rs.getString("ENDWORK");
				boolean al = true;

				TimeVo data = new TimeVo(startWork, endWork, al);
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
