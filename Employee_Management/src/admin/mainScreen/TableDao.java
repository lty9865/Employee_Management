package admin.mainScreen;

import java.sql.ResultSet;
import java.util.ArrayList;

import db.ConnectDB;

public class TableDao {
	private String query;
	private ResultSet rs;

	public ArrayList<TableVo> searchAll() {
		ArrayList<TableVo> list = new ArrayList<TableVo>();
		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT EMP_NO, NAME, POS, DEPT_NAME, BIRTH, MOBILE, COMMUTE " + "FROM EMP e, DEPARTMENT d "
					+ "WHERE e.DEPT_ID = d.DEPT_ID " + "ORDER BY EMP_NO";
			rs = cn.getStmt().executeQuery(query);

			while (rs.next()) {
				String empNo1 = rs.getString("EMP_NO");
				String name1 = rs.getString("NAME");
				String pos1 = rs.getString("POS");
				String deptName1 = rs.getString("DEPT_NAME");
				String birth1 = rs.getString("BIRTH");
				String mobile1 = rs.getString("MOBILE");
				String commute1 = rs.getString("COMMUTE");

				TableVo data = new TableVo(empNo1, name1, pos1, deptName1, birth1, mobile1, commute1);
				list.add(data);
			}
			cn.getStmt().close();
			cn.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TableVo> searchEmp(String deptName) {
		ArrayList<TableVo> list = new ArrayList<TableVo>();
		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT EMP_NO, NAME, POS, DEPT_NAME, BIRTH, MOBILE, COMMUTE " + "FROM EMP e, DEPARTMENT d "
					+ "WHERE E.DEPT_ID = D.DEPT_ID " + "AND D.DEPT_NAME LIKE '" + deptName + "'" + "ORDER BY EMP_NO";
			rs = cn.getStmt().executeQuery(query);

			while (rs.next()) {
				String empNo1 = rs.getString("EMP_NO");
				String name1 = rs.getString("NAME");
				String pos1 = rs.getString("POS");
				String deptName1 = rs.getString("DEPT_NAME");
				String birth1 = rs.getString("BIRTH");
				String mobile1 = rs.getString("MOBILE");
				String commute1 = rs.getString("COMMUTE");

				TableVo data = new TableVo(empNo1, name1, pos1, deptName1, birth1, mobile1, commute1);
				list.add(data);
			}
			cn.getStmt().close();
			cn.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TableVo> searchSugg() {
		ArrayList<TableVo> list = new ArrayList<TableVo>();
		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT * FROM DEPARTMENT d, SUGGESTIONS s WHERE d.DEPT_ID = s.DEPT_ID";
			rs = cn.getStmt().executeQuery(query);

			while (rs.next()) {
				String sugTitle = rs.getString("SUGTITLE");
				String deptName = rs.getString("DEPT_NAME");
				String writeDay = rs.getString("WRITEDAY");
				String stat = rs.getString("STAT");
				String sugNum = rs.getString("SUGNUM");

				TableVo data = new TableVo(sugTitle, deptName, writeDay, stat, sugNum);
				list.add(data);
			}
			cn.getStmt().close();
			cn.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TableVo> searchSugg(int row) {
		ArrayList<TableVo> list = new ArrayList<TableVo>();
		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT SUGTITLE, SUGTEXT, SUGNUM FROM SUGGESTIONS s ";
			rs = cn.getStmt().executeQuery(query);
			int count = 0;
			while (rs.next()) {
				if (row == count) {
					String sugTitle = rs.getString("SUGTITLE");
					String sugText = rs.getString("SUGTEXT");
					String sugNum = rs.getString("SUGNUM");
					TableVo data = new TableVo(sugTitle, sugText, sugNum);
					list.add(data);
					break;
				}
				count++;
			}
			cn.getStmt().close();
			cn.getCon().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void updateResult(String sugNum) {
		try {
			ConnectDB cn = new ConnectDB();

			query = "UPDATE SUGGESTIONS SET STAT = '처리완료' WHERE SUGNUM = '" + sugNum + "'";
			cn.getStmt().executeUpdate(query);

			cn.getStmt().close();
			cn.getCon().close();
		} catch (Exception e) {

		}
	}
}