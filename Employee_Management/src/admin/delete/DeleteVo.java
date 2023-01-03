package admin.delete;

public class DeleteVo {
	private String deptName;
	private String pos;
	private String name;
	private String birth;
	private String mobile;

	public DeleteVo(String deptName, String name, String pos, String birth, String mobile) {
		this.deptName = deptName;
		this.name = name;
		this.pos = pos;
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