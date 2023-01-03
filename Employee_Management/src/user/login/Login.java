package user.login;

import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import user.signup.SignUp;

public class Login extends WindowAdapter implements ActionListener {
	private Frame frame;
	private TextField id, pw;
	private Button b1, b2;

	public void userLogin() {
		// 프레임
		frame = new Frame("Login");
		frame.setLayout(null);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(this);

		// 입력란
		id = new TextField("아이디를 입력하세요.");
		pw = new TextField("패스워드를 입력하세요.");
		id.setSize(240, 40);
		id.setLocation(30, 60);
		pw.setSize(id.getSize());
		pw.setLocation(id.getLocation().x, id.getLocation().y + pw.getSize().height + 10);

		// 버튼
		b1 = new Button("로그인");
		b2 = new Button("회원가입");
		b1.setSize(pw.getSize());
		b1.setLocation(pw.getLocation().x, pw.getLocation().y + pw.getSize().height + 30);
		b2.setSize(b1.getSize());
		b2.setLocation(b1.getLocation().x, b1.getLocation().y + b1.getSize().height + 10);
		b1.addActionListener(this);
		b2.addActionListener(this);

		frame.add(id);
		frame.add(pw);
		frame.add(b1);
		frame.add(b2);
		frame.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(b1.getLabel())) {
			new AlertFrame();
		} else if (e.getActionCommand().equals(b2.getLabel())) {
			new SignUp();
		}

	}
}
