package admin.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import admin.login.Login;
import admin.mainScreen.TableDao;

public class Main {
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(date);

		TableDao dao = new TableDao();
		dao.updateComm(strDate);
		dao.refreshSugg(strDate);

		new Login();
	}
}
