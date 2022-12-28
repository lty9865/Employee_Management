package Dao;

import java.util.ArrayList;

import db.ConnectDB;
import db.Vo;

public class InsertAccountDao {
	private String query;

	public ArrayList<Vo> insert(String userID, String userPW) {
		ArrayList<Vo> list = new ArrayList<Vo>();

		try {
			ConnectDB cn = new ConnectDB();

			query = "INSERT INTO LOGIN VALUES ('" + userID + "', '" + userPW + "')";
			cn.getStmt().execute(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
