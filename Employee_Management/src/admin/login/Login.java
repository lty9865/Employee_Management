package admin.login;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import admin.font.Fonts;
import admin.mainScreen.MainScreen;
import admin.signUp.SignUp;

public class Login extends WindowAdapter implements ActionListener {
	private Frame frame;
	private TextField id, pw;
	private Button ok, sign;
	private Label l;

	public Login() {
		// 프레임 설정
		frame = new Frame("Employee Management");
		frame.setLayout(null);
		frame.setSize(300, 450);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// 텍스트 필드 설정
		id = new TextField();
		id.setSize((frame.getSize().width - 60), 40);
		id.setLocation((frame.getSize().width / 2) - ((id.getSize().width / 2)),
				(frame.getSize().height / 2) - ((id.getSize().height) / 2) - 30);
		pw = new TextField();
		pw.setSize((frame.getSize().width - 60), 40);
		pw.setLocation(id.getLocation().x, id.getLocation().y + 60);
		pw.setEchoChar('*');

		// 버튼 설정
		ok = new Button("로그인");
		ok.setSize(100, 50);
		ok.setLocation(30, pw.getLocation().y + 80);
		ok.addActionListener(this);

		sign = new Button("회원가입");
		sign.setSize(ok.getSize());
		sign.setLocation(170, ok.getLocation().y);
		sign.addActionListener(this);

		// 라벨
		l = new Label("");
		l.setSize(id.getSize());
		l.setLocation(ok.getLocation().x, ok.getLocation().y + 65);

		frame.addWindowListener(this);
		frame.add(id);
		frame.add(pw);
		frame.add(ok);
		frame.add(sign);
		frame.add(l);

		frame.setVisible(true);

	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(ok.getLabel())) {
			LoginDao dao = new LoginDao();
			LoginVo v = new LoginVo(id.getText());
			ArrayList<LoginVo> list = dao.login(v.getID(), v.getPW());

			for (int i = 0; i < list.size(); i++) {
				LoginVo data = (LoginVo) list.get(i);
				String userID = data.getID();
				String userPW = data.getPW();

				if (userID.equals(id.getText().toUpperCase()) && userPW.equals(pw.getText())) {
					frame.setVisible(false);
					new MainScreen();
					break;
				} else {
					Fonts f1 = new Fonts();
					l.setFont(f1.getFont1());
					l.setForeground(Color.RED);
					l.setText("가입된 정보가 없습니다.");
				}
			}
		} else if (e.getActionCommand().equals(sign.getLabel())) {
			new SignUp();
		}
	}
}
