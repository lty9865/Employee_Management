package admin.signUp;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
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
import java.util.regex.Pattern;

import common.checkpassword.CheckPassword;
import common.loginDao.LoginVo;
import common.signUpDao.DupCheckDao;
import common.signUpDao.InsertAccountDao;
import font.Fonts;
import font.RoundedButton;
import user.dimension.UserDimension;

public class SignUp extends WindowAdapter implements ActionListener, KeyListener {
	private Frame frame;
	private TextField id, pw, pw2;
	private Label l1, l2, dup, check;
	private RoundedButton ok, same;

	public SignUp() {
		UserDimension ud = new UserDimension();
		// 프레임 설정
		frame = new Frame("Sign Up");
		frame.setLayout(null);
		frame.setSize(ud.getWidth(), ud.getHeight() - 50);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// 라벨 ID
		l1 = new Label("ID");
		l1.setSize(50, 30);
		l1.setLocation(20, 25);

		// 텍스트 필드 설정
		id = new TextField();
		id.setSize(185, 40);
		id.setLocation(l1.getLocation().x, l1.getLocation().y + l1.getSize().height);
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
				if (e.getKeyChar() == KeyEvent.VK_TAB) {
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// 라벨 PW
		l2 = new Label("PW");
		l2.setSize(l1.getSize());
		l2.setLocation(id.getLocation().x, id.getLocation().y + id.getSize().height + 15);

		pw = new TextField("비밀번호를 입력하세요.");
		pw.setSize(260, id.getSize().height);
		pw.setLocation(l2.getLocation().x, l2.getLocation().y + l2.getSize().height);
		pw.setEditable(false);
		pw.setFocusable(false);
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
				if (e.getKeyChar() == KeyEvent.VK_TAB) {
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		pw2 = new TextField("비밀번호를 다시 한번 입력하세요.");
		pw2.setSize(pw.getSize());
		pw2.setLocation(pw.getLocation().x, pw.getLocation().y + pw.getSize().height + 10);
		pw2.setEditable(false);
		pw2.setFocusable(false);
		pw2.addKeyListener(new KeyListener() {
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
				if (e.getKeyChar() == KeyEvent.VK_TAB) {
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		dup = new Label();
		dup.setSize(300, id.getSize().height);
		dup.setLocation(id.getLocation().x, id.getLocation().y + id.getSize().height - 10);
		dup.setFocusable(false);

		check = new Label();
		check.setSize(dup.getSize());
		check.setLocation(pw2.getLocation().x, pw2.getLocation().y + pw2.getSize().height - 10);
		check.setFocusable(false);
		check.setForeground(Color.red);

		// 버튼 설정
		ok = new RoundedButton("OK");
		ok.setSize(100, 50);
		ok.setLocation((frame.getSize().width / 2) - (ok.getSize().width / 2), pw2.getLocation().y + 80);
		ok.addActionListener(this);

		same = new RoundedButton("중복확인");
		same.setSize(60, 40);
		same.setLocation(id.getLocation().x + id.getSize().width + 15, id.getLocation().y);
		same.addActionListener(this);

		frame.addWindowListener(this);
		frame.add(id);
		frame.add(pw);
		frame.add(pw2);
		frame.add(ok);
		frame.add(l1);
		frame.add(l2);
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
					pw.setEditable(true);
					pw.addKeyListener(this);
					pw2.addKeyListener(this);
					pw2.setEditable(true);
					pw.setFocusable(true);
					pw2.setFocusable(true);
					pw2.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							if (pw2.getText().equals("비밀번호를 다시 한번 입력하세요.")) {
								pw2.setText(null);
								pw2.setEchoChar('*');
							}
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub

						}
					});
					pw.addMouseListener(new MouseListener() {

						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							if (pw.getText().equals("비밀번호를 입력하세요.")) {
								pw.setText(null);
								pw.setEchoChar('*');
							}
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub

						}
					});
				}
			}
		}
		if (e.getActionCommand().equals(ok.getActionCommand())) {
			CheckPassword ch = new CheckPassword();
			if (id.getText().isEmpty() && pw.getText().isEmpty()) {
				dup.setText(null);
			} else if (pw.getText().equals(pw2.getText()) == false) {
				check.setText("비밀번호가 다릅니다.");
			} else if (ch.CheckPW(pw.getText())) {
				InsertAccountDao dao = new InsertAccountDao();
				LoginVo v = new LoginVo(id.getText().toUpperCase(), pw.getText());
				dao.insert(v.getID(), v.getPW());
				frame.dispose();
			} else {
				check.setText("영문자, 숫자, 특수기호 포함 8자 이상 사용가능");
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getComponent().equals(pw) && pw.getText().equals("비밀번호를 입력하세요.")) {
			pw.setText(null);
			pw.setEchoChar('*');
		} else if (e.getComponent().equals(pw2) && pw2.getText().equals("비밀번호를 다시 한번 입력하세요.")) {
			pw2.setText(null);
			pw2.setEchoChar('*');
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
