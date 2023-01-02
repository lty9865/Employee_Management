package register;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import db.ConnectDB;
import db.UserVo;

public class InsertUserDao {
	private PreparedStatement pstmt = null;

	public ArrayList<UserVo> insert(String deptName, String pos, String name, String birth, String mobile) {
		ArrayList<UserVo> list = new ArrayList<UserVo>();

		try {
			ConnectDB cn = new ConnectDB();

			// 사번 만들기
			String searchQ = "SELECT DEPT_ID FROM DEPARTMENT WHERE DEPT_NAME LIKE " + "'" + deptName + "'";
			ResultSet rs = cn.getStmt().executeQuery(searchQ);
			StringBuilder sb = new StringBuilder();
			rs.next();
			String deptID = rs.getString(1);
			sb.append(deptID);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String strDate = sdf.format(date);
			sb.append(strDate);

			StringBuilder count = new StringBuilder();
			String a = "SELECT COUNT(EMP_NO) FROM EMP, DEPARTMENT WHERE EMP.DEPT_ID = DEPARTMENT.DEPT_ID ";
			String b = "AND DEPARTMENT.DEPT_NAME LIKE " + "'" + deptName + "'";
			count.append(a);
			count.append(b);
			ResultSet countRs = cn.getStmt().executeQuery(count.toString());
			countRs.next();
			int num = countRs.getInt(1);
			if (num == 0) {
				num = 1;
			} else {
				num += 1;
			}
			String c = String.format("%04d", num);
			sb.append(c);

			// 사원등록 쿼리
			String query = "INSERT INTO EMP VALUES (?,?,?,?,?,?)";
			pstmt = cn.getCon().prepareStatement(query);

			pstmt.setString(1, deptID);
			pstmt.setString(2, pos);
			pstmt.setString(3, name);
			pstmt.setString(4, birth);
			pstmt.setString(5, mobile);
			pstmt.setString(6, sb.toString());

			pstmt.executeUpdate();

			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}