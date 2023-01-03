package admin.mainScreen;

public class TableVo {
	private String empNo;
	private String name;
	private String pos;
	private String deptName;
	private String birth;
	private String mobile;
	private String commute;

	public TableVo() {
	}

	public TableVo(String empNo, String name, String pos, String deptName, String birth, String mobile,
			String commute) {
		this.empNo = empNo;
		this.name = name;
		this.pos = pos;
		this.deptName = deptName;
		this.birth = birth;
		this.mobile = mobile;
		this.commute = commute;
	}

	public String getEmpNo() {
		return empNo;
	}

	public String getName() {
		return name;
	}

	public String getPos() {
		return pos;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getBirth() {
		return birth;
	}

	public String getMobile() {
		return mobile;
	}

	public String getCommute() {
		return commute;
	}
}
