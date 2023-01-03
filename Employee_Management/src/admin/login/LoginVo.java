package admin.login;

public class LoginVo {
	private String adminID;
	private String adminPW;

	public LoginVo(String adminID) {
		this.adminID = adminID;
	}

	public LoginVo(String adminID, String adminPW) {
		this.adminID = adminID;
		this.adminPW = adminPW;
	}

	public String getID() {
		return adminID;
	}

	public String getPW() {
		return adminPW;
	}
}
