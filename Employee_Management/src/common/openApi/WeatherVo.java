package common.openApi;

public class WeatherVo {
	private double PTY; // 강수형태
	private double REH; // 습도
	private double RN1; // 습도
	private double T1H; // 1시간 기온
	private double UUU; // 풍속
	private double VVV; // 풍속
	private double VEC; // 풍향
	private double WSD; // 풍속

	public void setPTY(double PTY) {
		this.PTY = PTY;
	}

	public void setREH(double REH) {
		this.REH = REH;
	}

	public void setRN1(double RN1) {
		this.RN1 = RN1;
	}

	public void setT1H(double T1H) {
		this.T1H = T1H;
	}

	public void setUUU(double UUU) {
		this.UUU = UUU;
	}

	public void setVVV(double VVV) {
		this.VVV = VVV;
	}

	public void setVEC(double VEC) {
		this.VEC = VEC;
	}

	public void setWSD(double WSD) {
		this.WSD = WSD;
	}

	public double getPTY() {
		return PTY;
	}

	public double getREH() {
		return REH;
	}

	public double getRN1() {
		return RN1;
	}

	public double getT1H() {
		return T1H;
	}

	public double getUUU() {
		return UUU;
	}

	public double getVVV() {
		return VVV;
	}

	public double getVEC() {
		return VEC;
	}

	public double getWSD() {
		return WSD;
	}

	public String toString() {
		return "\nPTY = " + PTY + "\nREH = " + REH + "\nRN1 = " + RN1 + "\nT1H = " + T1H + "\nUUU = " + UUU + "\nVEC = "
				+ VEC + "\nVVV = " + VVV + "\nWSD = " + WSD;
	}
}
