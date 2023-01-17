package common.searchEmp;

import java.sql.ResultSet;

import java.util.ArrayList;

import db.ConnectDB;

public class SearchEmpDao {
	private String query;
	private ResultSet rs;
	String[] searchDel;
	private boolean is;

	public String[] searchDel(String deptNum) {
		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT DEPT_NAME, POS, NAME, BIRTH, MOBILE " + "FROM EMP e, DEPARTMENT d "
					+ "WHERE E.DEPT_ID = D.DEPT_ID " + "AND e.EMP_NO LIKE '" + deptNum + "'";
			rs = cn.getStmt().executeQuery(query);

			if (rs.next()) {
				searchDel = new String[5];
				searchDel[0] = rs.getString("DEPT_NAME");
				searchDel[1] = rs.getString("POS");
				searchDel[2] = rs.getString("NAME");
				searchDel[3] = rs.getString("BIRTH");
				searchDel[4] = rs.getString("MOBILE");
			}

			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDel;
	}

	public ArrayList<SearchEmpVo> searchEmpNo(String empNo) {
		ArrayList<SearchEmpVo> list = new ArrayList<SearchEmpVo>();

		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT EMP_NO FROM EMP";
			rs = cn.getStmt().executeQuery(query);
			while (rs.next()) {
				String empNo1 = rs.getString("EMP_NO");

				SearchEmpVo data = new SearchEmpVo(empNo1);
				list.add(data);
			}
			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean CheckEmpNo(String empNo) {

		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT EMP_NO FROM USERLOGIN WHERE EMP_NO = " + empNo;
			rs = cn.getStmt().executeQuery(query);
			while (rs.next()) {
				String a = rs.getString("EMP_NO");
				if (empNo.equals(a)) {
					return true;
				}
			}
			cn.getStmt().close();
			cn.getCon().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean getIs() {
		return is;
	}
}
