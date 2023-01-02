package mainScreen;

import java.sql.ResultSet;
import java.util.ArrayList;

import db.ConnectDB;
import db.TableVo;

public class TableDao {
	private String query;
	private ResultSet rs;

	public ArrayList<TableVo> searchAll() {
		ArrayList<TableVo> list = new ArrayList<TableVo>();
		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT EMP_NO, NAME, POS, DEPT_NAME, BIRTH, MOBILE " + "FROM EMP e, DEPARTMENT d "
					+ "WHERE E.DEPT_ID = D.DEPT_ID " + "ORDER BY DEPT_NAME ";
			rs = cn.getStmt().executeQuery(query);

			while (rs.next()) {
				String empNo1 = rs.getString("EMP_NO");
				String name1 = rs.getString("NAME");
				String pos1 = rs.getString("POS");
				String deptName1 = rs.getString("DEPT_NAME");
				String birth1 = rs.getString("BIRTH");
				String mobile1 = rs.getString("MOBILE");

				TableVo data = new TableVo(empNo1, name1, pos1, deptName1, birth1, mobile1);
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

			query = "SELECT EMP_NO, NAME, POS, DEPT_NAME, BIRTH, MOBILE " + "FROM EMP e, DEPARTMENT d "
					+ "WHERE E.DEPT_ID = D.DEPT_ID " + "AND D.DEPT_NAME LIKE '" + deptName + "'"
					+ "ORDER BY DEPT_NAME ";
			rs = cn.getStmt().executeQuery(query);

			while (rs.next()) {
				String empNo1 = rs.getString("EMP_NO");
				String name1 = rs.getString("NAME");
				String pos1 = rs.getString("POS");
				String deptName1 = rs.getString("DEPT_NAME");
				String birth1 = rs.getString("BIRTH");
				String mobile1 = rs.getString("MOBILE");

				TableVo data = new TableVo(empNo1, name1, pos1, deptName1, birth1, mobile1);
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