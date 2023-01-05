package user.login;

import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import common.loginDao.LoginDao;
import common.loginDao.LoginVo;
import user.mainScreen.MainScreen;
import user.signup.SignUp;

public class Login extends WindowAdapter implements ActionListener, MouseListener, KeyListener {
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
		id.addMouseListener(this);
		id.addKeyListener(this);
		pw.setSize(id.getSize());
		pw.setLocation(id.getLocation().x, id.getLocation().y + pw.getSize().height + 10);
		pw.addMouseListener(this);
		pw.addKeyListener(this);

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
		if (e.getActionCommand().equals(b1.getLabel())) {
			String id1 = id.getText();
			String pw1 = pw.getText();
			LoginDao dao = new LoginDao();
			ArrayList<LoginVo> list = dao.userLogin(id1, pw1);
			boolean bo3 = true;
			for (int i = 0; i < list.size(); i++) {
				LoginVo data = (LoginVo) list.get(i);
				String userID = data.getID();
				String userPW = data.getPW();

				if (userID.equals(id.getText().toUpperCase()) && userPW.equals(pw.getText())) {
					frame.dispose();
					new MainScreen(userID);
					bo3 = false;
					break;
				}
			}
			if (bo3) {
				new AlertFrame();
			}
		}
		if (e.getActionCommand().equals(b2.getLabel()))

		{
			new SignUp();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(id)) {
			id.setText(null);
		} else if (e.getComponent().equals(pw)) {
			pw.setText(null);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getComponent().equals(id) && id.getText().equals("아이디를 입력하세요.")) {
			id.setText(null);
		} else if (e.getComponent().equals(pw) && pw.getText().equals("패스워드를 입력하세요.")) {
			pw.setText(null);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
