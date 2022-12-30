package db;

public class UserVo {
	private String deptName;
	private String pos;
	private String name;
	private String birth;
	private String mobile;

	public UserVo(String deptName, String pos, String name, String birth, String mobile) {
		this.deptName = deptName;
		this.pos = pos;
		this.name = name;
		this.birth = birth;
		this.mobile = mobile;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getPos() {
		return pos;
	}

	public String getName() {
		return name;
	}

	public String getBirth() {
		return birth;
	}

	public String getMobile() {
		return mobile;
	}
}
