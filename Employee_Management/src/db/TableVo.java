package db;

public class TableVo {
	private String empNo;
	private String name;
	private String pos;
	private String deptName;
	private String birth;
	private String mobile;
	private int num;

	public TableVo() {
	}

	public TableVo(String empNo, String name, String pos, String deptName, String birth, String mobile, int num) {
		this.empNo = empNo;
		this.name = name;
		this.pos = pos;
		this.deptName = deptName;
		this.birth = birth;
		this.mobile = mobile;
		this.num = num;
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

	public int getNum() {
		return num;
	}
}
