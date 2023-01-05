package common.loginDao;

public class LoginVo {
	private String id;
	private String pw;
	private String empNo;

	public LoginVo() {
	}

	public LoginVo(String id) {
		this.id = id;
	}

	public LoginVo(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public LoginVo(String empNo, String id, String pw) {
		this.id = id;
		this.pw = pw;
		this.empNo = empNo;
	}

	public String getID() {
		return id;
	}

	public String getPW() {
		return pw;
	}

	public String getEmpNo() {
		return empNo;
	}
}
