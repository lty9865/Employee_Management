package admin.login;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import admin.demension.AdminDimension;
import admin.mainScreen.MainScreen;
import admin.signUp.SignUp;
import common.loginDao.LoginDao;
import common.loginDao.LoginVo;
import font.Fonts;
import font.RoundedButton;
import user.mainScreen.UserMainScreen;
import user.signup.UserSignUp;

@SuppressWarnings("serial")
class ImagePanel extends JPanel {
	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		ImageIcon image = new ImageIcon("./image/Company.jpg");
		g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
	}
}

public class Login extends WindowAdapter implements ActionListener {
	private Frame frame;
	private TextField id, pw;
	private RoundedButton ok, sign;
	private Label l, id1, pw1;
//	private Label title1, title2, title3;
	private ImagePanel p;
	private Checkbox cb;

	Fonts font = new Fonts();
	AdminDimension ad = new AdminDimension();
	Color selectColor = new Color(0, 153, 255);

	public Login() {

		// 프레임 설정
		frame = new Frame("Employee Management");
		frame.setLayout(null);
		frame.setSize(ad.getWidth(), ad.getHeight());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// 입력창 설정
		id1 = new Label("USERNAME");
		id1.setSize(80, 20);
		id1.setLocation(30, (frame.getSize().height / 2) - ((id1.getSize().height) / 2) - 70);
		id1.setFont(font.getFont3());
		id = new TextField();
		id.setSize(330, 40);
		id.setLocation(id1.getLocation().x, id1.getLocation().y + id1.getSize().height);
		id.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == ' ') {
					e.consume();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		pw1 = new Label("PASSWORD");
		pw1.setSize(id1.getSize());
		pw1.setLocation(id.getLocation().x, id.getLocation().y + id.getSize().height + 20);
		pw1.setFont(font.getFont3());

		pw = new TextField();
		pw.setSize(id.getSize());
		pw.setLocation(pw1.getLocation().x, pw1.getLocation().y + pw1.getSize().height);
		pw.setEchoChar('*');
		pw.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == ' ') {
					e.consume();
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// 버튼 설정
		ok = new RoundedButton("로그인");
		ok.setSize(id.getSize());
		ok.setLocation(pw.getLocation().x, pw.getLocation().y + 90);
		ok.addActionListener(this);

		sign = new RoundedButton("회원가입");
		sign.setSize(ok.getSize());
		sign.setLocation(ok.getLocation().x, ok.getLocation().y + ok.getSize().height + 10);
		sign.addActionListener(this);

		// 라벨
		l = new Label("");
		l.setSize(id.getSize());
		l.setLocation(pw.getLocation().x, pw.getLocation().y + pw.getSize().height);

		// 패널
		p = new ImagePanel();
		p.setBounds(id.getLocation().x + id.getSize().width + 15, 0,
				frame.getSize().width - (id.getLocation().x + id.getSize().width) + 10, frame.getSize().height);
		p.setLayout(null);

		// 체크박스
		cb = new Checkbox("관리자");
		cb.setSize(55, 30);
		cb.setLocation(id1.getLocation().x + id.getSize().width - cb.getSize().width,
				id.getLocation().y + id.getSize().height);

		frame.addWindowListener(this);
		frame.add(id1);
		frame.add(pw1);
		frame.add(id);
		frame.add(pw);
		frame.add(ok);
		frame.add(sign);
		frame.add(l);
		frame.add(p);
		frame.add(cb);

		frame.setVisible(true);

	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	ArrayList<LoginVo> list;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(ok.getActionCommand())) {
			LoginDao dao = new LoginDao();
			LoginVo v = new LoginVo(id.getText());
			if (cb.getState()) {
				list = dao.adminLogin(v.getID(), v.getPW());
			} else {
				list = dao.userLogin(v.getID(), v.getPW());
			}

			for (int i = 0; i < list.size(); i++) {
				LoginVo data = (LoginVo) list.get(i);
				String userID = data.getID();
				String userPW = data.getPW();

				if (userID.equals(id.getText().toUpperCase()) && userPW.equals(pw.getText())) {
					frame.setVisible(false);
					if (cb.getState()) {
						new MainScreen();
						break;
					} else {
						new UserMainScreen(userID);
						break;
					}
				}
			}
			l.setFont(font.getFont1());
			l.setForeground(Color.RED);
			l.setText("가입된 정보가 없습니다.");
		} else if (e.getActionCommand().equals(sign.getActionCommand())) {
			if (cb.getState()) {
				frame.setFocusable(false);
				new SignUp();
				frame.setFocusable(true);
			} else {
				frame.setFocusable(false);
				new UserSignUp();
				frame.setFocusable(true);
			}
		}
	}
}
