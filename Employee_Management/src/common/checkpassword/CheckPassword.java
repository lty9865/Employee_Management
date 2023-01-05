package common.checkpassword;

public class CheckPassword {
	private boolean b1 = true;
	private boolean b2 = true;
	private boolean b3 = true;
	private int cNum = 0;
	private int cSym = 0;

	public CheckPassword() {
	}

	public boolean CheckPW(String password) {
		if (password.length() < 8) {
			b1 = false;
		}
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 48 && password.charAt(i) <= 57) {
				cNum++;
			} else if ((password.charAt(i) >= 33 && password.charAt(i) <= 47)
					|| (password.charAt(i) >= 58 && password.charAt(i) <= 64)
					|| (password.charAt(i) <= 91 && password.charAt(i) <= 96)
					|| (password.charAt(i) >= 123 && password.charAt(i) <= 126)) {
				cSym++;
			}
		}
		if (cNum == 0) {
			b2 = false;
		}
		if (cSym == 0) {
			b3 = false;
		}
		if (b1 && b2 && b3) {
			return true;
		}
		return false;
	}
}
