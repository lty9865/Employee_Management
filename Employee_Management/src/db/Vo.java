package db;

public class Vo {
	private String adminID;
	private String adminPW;

	public Vo(String adminID) {
		this.adminID = adminID;
	}

	public Vo(String adminID, String adminPW) {
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
