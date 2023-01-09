package user.mainScreen;

public class TimeVo {
	private String startWork;
	private String endWork;
	private boolean al;

	public TimeVo() {
	}

	public TimeVo(String startWork, String endWork, boolean al) {
		this.startWork = startWork;
		this.endWork = endWork;
		this.al = al;
	}

	public String getStart() {
		return startWork;
	}

	public String getEnd() {
		return endWork;
	}

	public boolean getAl() {
		return al;
	}
}
