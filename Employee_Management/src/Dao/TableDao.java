package Dao;

import java.sql.ResultSet;

import db.ConnectDB;
import db.TableVo;

public class TableDao {
	private String query;
	private ResultSet rs, rs2;

	public TableDao() {
		try {
			ConnectDB cn = new ConnectDB();

			query = "SELECT EMP_NO, NAME, POS, DEPT_NAME, BIRTH, MOBILE " + "FROM EMP e, DEPARTMENT d "
					+ "WHERE E.DEPT_ID = D.DEPT_ID " + "ORDER BY DEPT_NAME ";
			rs = cn.getStmt().executeQuery(query);
			
			while(rs.next()) {
			model.	
			}

			
			String count = "SELECT count(*) FROM EMP";
			rs2 = cn.getStmt().executeQuery(count);
			rs2.next();
			int num = rs2.getInt(1);

			new TableVo(q[0], q[1], q[2], q[3], q[4], q[5], num);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}