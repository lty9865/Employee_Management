package admin.signUp;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.regex.Pattern;

import common.checkpassword.CheckPassword;
import common.loginDao.LoginVo;
import common.signUpDao.DupCheckDao;
import common.signUpDao.InsertAccountDao;
import font.Fonts;
import font.RoundedButton;

public class SignUp extends WindowAdapter implements ActionListener {
	private Frame frame;
	private TextField id, pw;
	private Label l1, l2, dup, check;
	private Label c1, c2, c3;
	private RoundedButton ok, same;

	public SignUp() {
		// 프레임 설정
		frame = new Frame("Sign Up");
		frame.setLayout(null);
		frame.setSize(450, 450);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// 라벨
		l1 = new Label("ID");
		l1.setSize(50, 40);
		l1.setLocation(30, 60);
		l2 = new Label("PW");
		l2.setSize(l1.getSize());
		l2.setLocation(30, l1.getLocation().y + 80);

		// 텍스트 필드 설정
		id = new TextField();
		id.setSize(230, 40);
		id.setLocation(l1.getLocation().x + 50, l1.getLocation().y);

		pw = new TextField();
		pw.setSize(id.getSize());
		pw.setLocation(id.getLocation().x, l2.getLocation().y);
		pw.setEchoChar('*');

		dup = new Label();
		dup.setSize(300, id.getSize().height);
		dup.setLocation(id.getLocation().x, id.getLocation().y + 30);
		dup.setFocusable(false);

		check = new Label();
		check.setSize(dup.getSize());
		check.setLocation(pw.getLocation().x, pw.getLocation().y + 30);
		check.setFocusable(false);

		// 버튼 설정
		ok = new RoundedButton("OK");
		ok.setSize(100, 50);
		ok.setLocation((frame.getSize().width / 2) - (ok.getSize().width / 2), pw.getLocation().y + 80);
		ok.addActionListener(this);

		same = new RoundedButton("중복확인");
		same.setSize(80, 40);
		same.setLocation(id.getLocation().x + 250, id.getLocation().y);
		same.addActionListener(this);

		// 비밀번호 주의사항
		c1 = new Label("X     비밀번호는 영문자, 숫자, 특수기호 조합으로 8자 이상");
		c1.setSize(frame.getSize().width - 60, 30);
		c1.setLocation(l1.getLocation().x, ok.getLocation().y + 80);
		c2 = new Label("X     숫자를 1자 이상 입력하세요.");
		c2.setSize(c1.getSize());
		c2.setLocation(c1.getLocation().x, c1.getLocation().y + 40);
		c3 = new Label("X     특수기호를 1자 이상 입력하세요.");
		c3.setSize(c2.getSize());
		c3.setLocation(c2.getLocation().x, c2.getLocation().y + 40);

		frame.addWindowListener(this);
		frame.add(id);
		frame.add(pw);
		frame.add(ok);
		frame.add(l1);
		frame.add(l2);
		frame.add(c1);
		frame.add(c2);
		frame.add(c3);
		frame.add(same);
		frame.add(dup);
		frame.add(check);

		frame.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (id.getText().equals("")) {
			dup.setText(null);
		} else if (e.getActionCommand().equals(same.getActionCommand())) {
			DupCheckDao dao = new DupCheckDao();
			LoginVo v = new LoginVo(id.getText());
			ArrayList<LoginVo> list = dao.dupCheck(v.getID());
			Fonts f1 = new Fonts();
			boolean b2 = id.getText().length() >= 5;
			dup.setFont(f1.getFont1());

			for (int i = 0; i < list.size(); i++) {
				LoginVo data = (LoginVo) list.get(i);
				String userID = data.getID();
				boolean b1 = Pattern.matches("^[a-zA-Z0-9]*$", id.getText());
				if (b2 == false) {
					dup.setForeground(Color.red);
					dup.setText("5자 이상 입력하세요.");
				} else if (userID.equals(id.getText().toUpperCase()) || b1 == false) {
					dup.setForeground(Color.red);
					dup.setText("사용할 수 없는 아이디 입니다.");
					break;
				} else {
					dup.setForeground(Color.blue);
					dup.setText("사용할 수 있는 아이디 입니다.");
				}
			}
		}
		if (e.getActionCommand().equals(ok.getActionCommand())) {
			CheckPassword ch = new CheckPassword();
			if (id.getText().isEmpty() && pw.getText().isEmpty()) {
				dup.setText(null);
			} else if (ch.CheckPW(pw.getText())) {
				InsertAccountDao dao = new InsertAccountDao();
				LoginVo v = new LoginVo(id.getText().toUpperCase(), pw.getText());
				dao.insert(v.getID(), v.getPW());
				frame.dispose();
			} else {
				check.setForeground(Color.red);
				check.setText("사용할 수 없는 비밀번호입니다.");
			}
		}
	}
}
