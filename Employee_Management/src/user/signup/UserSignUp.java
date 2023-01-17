package user.signup;

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
import common.searchEmp.SearchEmpDao;
import common.searchEmp.SearchEmpVo;
import common.signUpDao.DupCheckDao;
import common.signUpDao.InsertAccountDao;
import font.Fonts;
import font.RoundedButton;
import user.dimension.UserDimension;

public class UserSignUp extends WindowAdapter implements ActionListener, MouseListener, KeyListener {
	private Frame frame;
	private TextField empNo, id, pw1, pw2;
	private RoundedButton search, dupli, ok, cancel;
	private Label l1, l2, l3, l4, l5;

	DupCheckDao dao = new DupCheckDao();
	CheckPassword ch = new CheckPassword();

	public UserSignUp() {
		UserDimension ud = new UserDimension();
		frame = new Frame("Sign Up");
		frame.setLayout(null);
		frame.setSize(ud.getWidth(), ud.getHeight());
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(this);
		frame.setResizable(false);

		// 사원번호
		l4 = new Label("사원번호");
		l4.setSize(50, 40);
		l4.setLocation(20, 20);

		empNo = new TextField();
		empNo.setSize(190, 40);
		empNo.setLocation(20, 50);
		empNo.addMouseListener(this);
		empNo.addKeyListener(this);

		l1 = new Label();
		l1.setSize(frame.getSize().width - 40, 20);
		l1.setLocation(empNo.getLocation().x, empNo.getLocation().y + empNo.getSize().height + 5);
		Fonts font = new Fonts();
		l1.setFont(font.getFont1());
		l1.setForeground(Color.blue);

		search = new RoundedButton("조회");
		search.setSize(60, empNo.getSize().height);
		search.setLocation(empNo.getLocation().x + empNo.getSize().width + 10, empNo.getLocation().y);
		search.addActionListener(this);

		// 아이디
		l5 = new Label("ID");
		l5.setSize(50, 20);
		l5.setLocation(l1.getLocation().x, l1.getLocation().y + l1.getSize().height);

		id = new TextField();
		id.setSize(empNo.getSize());
		id.setLocation(empNo.getLocation().x, empNo.getLocation().y + empNo.getSize().height + 45);
		id.addMouseListener(this);
		id.addKeyListener(this);
		id.setEditable(false);
		id.setFocusable(false);

		l2 = new Label();
		l2.setSize(l1.getSize());
		l2.setLocation(id.getLocation().x, id.getLocation().y + id.getSize().height + 5);
		l2.setFont(font.getFont1());
		l2.setForeground(Color.blue);

		dupli = new RoundedButton("중복확인");
		dupli.setSize(search.getSize());
		dupli.setLocation(id.getLocation().x + id.getSize().width + 10, id.getLocation().y);
		dupli.addActionListener(this);

		// 비밀번호
		pw1 = new TextField("비밀번호를 입력하세요.");
		pw1.setSize(frame.getSize().width - 40, id.getSize().height);
		pw1.setLocation(id.getLocation().x, id.getLocation().y + id.getSize().height + 40);
		pw1.addKeyListener(this);
		pw1.setEditable(false);
		pw1.setFocusable(false);

		pw2 = new TextField("비밀번호를 다시 한번 입력하세요.");
		pw2.setSize(pw1.getSize());
		pw2.setLocation(pw1.getLocation().x, pw1.getLocation().y + pw1.getSize().height + 10);
		pw2.addKeyListener(this);
		pw2.setEditable(false);
		pw2.setFocusable(false);

		l3 = new Label();
		l3.setSize(l2.getSize());
		l3.setLocation(pw2.getLocation().x, pw2.getLocation().y + pw2.getSize().height + 5);
		l3.setFont(font.getFont1());
		l3.setForeground(Color.red);

		ok = new RoundedButton("확인");
		ok.setSize(120, 50);
		ok.setLocation(l3.getLocation().x, l3.getLocation().y + l3.getSize().height + 10);
		ok.addActionListener(this);

		cancel = new RoundedButton("취소");
		cancel.setSize(ok.getSize());
		cancel.setLocation(ok.getLocation().x + ok.getSize().width + 10, ok.getLocation().y);
		;
		cancel.addActionListener(this);

		frame.add(empNo);
		frame.add(l1);
		frame.add(id);
		frame.add(l2);
		frame.add(pw1);
		frame.add(pw2);
		frame.add(l3);
		frame.add(search);
		frame.add(dupli);
		frame.add(ok);
		frame.add(cancel);
		frame.add(l4);
		frame.add(l5);
		frame.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(cancel.getActionCommand())) {
			frame.dispose();
		}
		if (e.getActionCommand().equals(search.getActionCommand())) {
			if (empNo.getText().isEmpty() == false) {
				String empNo1 = empNo.getText();
				SearchEmpDao dao = new SearchEmpDao();
				if (dao.CheckEmpNo(empNo1)) {
					l1.setForeground(Color.red);
					l1.setText("이미 가입되어 있습니다.");
				} else {
					ArrayList<SearchEmpVo> list = dao.searchEmpNo(empNo1);
					for (int i = 0; i < list.size(); i++) {
						SearchEmpVo data = (SearchEmpVo) list.get(i);
						String empNo2 = data.getEmpNo();
						if (empNo2.equals(empNo1)) {
							l1.setForeground(Color.blue);
							l1.setText("조회되었습니다.");
							empNo.setEditable(false);
							id.setEditable(true);
							id.setFocusable(true);
							pw1.setEditable(true);
							pw1.setFocusable(true);
							pw2.setEditable(true);
							pw2.setFocusable(true);
							pw1.addMouseListener(this);
							pw2.addMouseListener(this);
							break;
						} else {
							l1.setForeground(Color.red);
							l1.setText("등록되지 않은 사원번호입니다.");
						}
					}
				}
			}
		}
		if (e.getActionCommand().equals(dupli.getActionCommand())) {
			String id1 = id.getText();
			boolean b1 = true;
			boolean b2 = true;
			ArrayList<LoginVo> list = dao.userDupCheck(id1);
			for (int i = 0; i < list.size(); i++) {
				LoginVo data = (LoginVo) list.get(i);
				String userID = data.getID();
				if (userID.equals(id1.toUpperCase())) {
					b1 = false;
				}
			}

			if (id1.isEmpty()) {
				b2 = false;
			}
			boolean b3 = Pattern.matches("^[a-zA-Z0-9]*$", id1);
			boolean b4 = id1.length() >= 5;
			if (b1 && b2 && b3 && b4) {
				l2.setForeground(Color.blue);
				l2.setText("사용가능한 아이디입니다.");
			} else if (b2 == false) {
				l2.setForeground(Color.red);
				l2.setText("아이디를 입력하세요.");
			} else if (b4 == false) {
				l2.setForeground(Color.red);
				l2.setText("5자 이상 입력하세요.");
			} else if (b2 == false || b3 == false) {
				l2.setForeground(Color.red);
				l2.setText("사용불가능한 아이디입니다.");
			}
		}
		if (e.getActionCommand().equals(ok.getActionCommand())) {
			if ((pw1.getText().equals(pw2.getText()) == false)) {
				l3.setText("비밀번호가 다릅니다.");
			} else if ((pw1.getText().isEmpty() && pw2.getText().isEmpty()) == false
					&& (ch.CheckPW(pw1.getText()) == false)) {
				l3.setText("사용할 수 없는 비밀번호입니다.");
			} else if (l1.getText().equals("조회되었습니다.") && l2.getText().equals("사용가능한 아이디입니다.")) {
				InsertAccountDao dao = new InsertAccountDao();
				LoginVo v = new LoginVo(empNo.getText(), id.getText().toUpperCase(), pw1.getText());
				dao.userInsert(v.getEmpNo(), v.getID(), v.getPW());
				frame.dispose();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(empNo)) {
			if (empNo.getText().equals("사원번호를 입력하세요.")) {
				empNo.setText(null);
			}
		}
		if (e.getComponent().equals(id)) {
			if (id.getText().equals("아이디를 입력하세요.")) {
				id.setText(null);
			}
		}
		if (e.getComponent().equals(pw1)) {
			if (pw1.getText().equals("비밀번호를 입력하세요.")) {
				pw1.setText(null);
				pw1.setEchoChar('*');
			}
		}
		if (e.getComponent().equals(pw2)) {
			if (pw2.getText().equals("비밀번호를 다시 한번 입력하세요.")) {
				pw2.setText(null);
				pw2.setEchoChar('*');
			}
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
		if (e.getComponent().equals(empNo)) {
			int max = 11;
			if (empNo.getText().length() >= max) {
				e.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getComponent().equals(empNo) && empNo.getText().equals("사원번호를 입력하세요.")) {
			empNo.setText(null);
		} else if (e.getComponent().equals(id) && id.getText().equals("아이디를 입력하세요.")) {
			id.setText(null);
		} else if (e.getComponent().equals(pw1) && pw1.getText().equals("비밀번호를 입력하세요.")) {
			pw1.setText(null);
			pw1.setEchoChar('*');
		} else if (e.getComponent().equals(pw2) && pw2.getText().equals("비밀번호를 다시 한번 입력하세요.")) {
			pw2.setText(null);
			pw2.setEchoChar('*');
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
