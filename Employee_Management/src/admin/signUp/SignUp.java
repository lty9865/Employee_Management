package admin.signUp;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import common.checkpassword.CheckPassword;
import common.loginDao.LoginVo;
import common.signUpDao.DupCheckDao;
import common.signUpDao.InsertAccountDao;
import font.Fonts;

public class SignUp extends WindowAdapter implements ActionListener {
	private Frame frame;
	private TextField id, pw;
	private Label l1, l2, dup;
	private Label c1, c2, c3;
	private Label w;
	private Button ok, same, ok2;
	private Dialog info;

	public SignUp() {
		// 프레임 설정
		frame = new Frame("Sign Up");
		frame.setLayout(null);
		frame.setSize(450, 450);
		frame.setLocationRelativeTo(null);

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

		dup = new Label();
		dup.setSize(300, id.getSize().height);
		dup.setLocation(id.getLocation().x, id.getLocation().y + 30);
		dup.setFocusable(false);

		// 버튼 설정
		ok = new Button("OK");
		ok.setSize(100, 50);
		ok.setLocation((frame.getSize().width / 2) - (ok.getSize().width / 2), pw.getLocation().y + 80);
		ok.addActionListener(this);

		same = new Button("중복확인");
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

		frame.setVisible(true);
	}

	public void insertD() {
		info = new Dialog(frame, "Information", true);
		info.setSize(200, 100);
		info.setLocationRelativeTo(frame);
		info.setLayout(new FlowLayout());

		w = new Label("wrong input", Label.CENTER);
		ok2 = new Button("Again");
		info.add(w);
		info.add(ok2);
		ok2.addActionListener(this);

		info.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (id.getText().equals("")) {
			dup.setText(null);
		} else if (e.getActionCommand().equals(same.getLabel())) {
			DupCheckDao dao = new DupCheckDao();
			LoginVo v = new LoginVo(id.getText());
			ArrayList<LoginVo> list = dao.dupCheck(v.getID());
			Fonts f1 = new Fonts();

			for (int i = 0; i < list.size(); i++) {
				LoginVo data = (LoginVo) list.get(i);
				String userID = data.getID();

				dup.setFont(f1.getFont1());
				if (userID.equals(id.getText().toUpperCase())) {
					dup.setForeground(Color.red);
					dup.setText("사용할 수 없는 아이디 입니다.");
					break;
				} else {
					dup.setForeground(Color.blue);
					dup.setText("사용할 수 있는 아이디 입니다.");
				}
			}
		} else if (e.getActionCommand().equals(ok.getLabel())) {
			CheckPassword ch = new CheckPassword();
			if (id.getText().isEmpty() && pw.getText().isEmpty()) {
				dup.setText(null);
			} else if (ch.CheckPW(pw.getText())) {
				InsertAccountDao dao = new InsertAccountDao();
				LoginVo v = new LoginVo(id.getText().toUpperCase(), pw.getText());
				dao.insert(v.getID(), v.getPW());
				frame.dispose();
			} else {
				insertD();
			}
		} else if (e.getActionCommand().equals(ok2.getLabel())) {
			info.dispose();
		}
	}
}
