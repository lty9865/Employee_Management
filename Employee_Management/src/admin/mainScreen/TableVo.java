package admin.mainScreen;

public class TableVo {
	private String empNo;
	private String name;
	private String pos;
	private String deptName;
	private String birth;
	private String mobile;
	private String commute;

	private String sugTitle;
	private String writeDay;
	private String stat;
	private String sugNum;

	private String title;
	private String area;

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

	public TableVo(String sugTitle, String deptName, String writeDay, String stat, String sugNum) {
		this.sugTitle = sugTitle;
		this.deptName = deptName;
		this.writeDay = writeDay;
		this.stat = stat;
		this.sugNum = sugNum;
	}

	public TableVo(String title, String area, String sugNum) {
		this.title = title;
		this.area = area;
		this.sugNum = sugNum;
	}

	public String getSugNum() {
		return sugNum;
	}

	public String getTitle() {
		return title;
	}

	public String getArea() {
		return area;
	}

	public String getSugTitle() {
		return sugTitle;
	}

	public String getWriteDay() {
		return writeDay;
	}

	public String getStat() {
		return stat;
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
