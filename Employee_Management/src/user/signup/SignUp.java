package user.signup;

import java.awt.Button;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import font.Fonts;

public class SignUp extends WindowAdapter implements ActionListener {
	private Frame frame;
	private TextField empNo, id, pw1, pw2;
	private Button search, dupli, ok, cancel;
	private Label l1, l2, l3;

	public SignUp() {
		frame = new Frame("Sign Up");
		frame.setLayout(null);
		frame.setSize(300, 420);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(this);

		// 사원번호
		empNo = new TextField("사원번호를 입력하세요.");
		empNo.setSize(190, 40);
		empNo.setLocation(20, 50);

		l1 = new Label();
		l1.setSize(frame.getSize().width - 40, 20);
		l1.setLocation(empNo.getLocation().x, empNo.getLocation().y + empNo.getSize().height + 5);
		Fonts font = new Fonts();
		l1.setFont(font.getFont1());
		l1.setForeground(Color.blue);

		search = new Button("조회");
		search.setSize(60, empNo.getSize().height);
		search.setLocation(empNo.getLocation().x + empNo.getSize().width + 10, empNo.getLocation().y);
		search.addActionListener(this);

		// 아이디
		id = new TextField("아이디를 입력하세요.");
		id.setSize(empNo.getSize());
		id.setLocation(empNo.getLocation().x, empNo.getLocation().y + empNo.getSize().height + 40);

		l2 = new Label();
		l2.setSize(l1.getSize());
		l2.setLocation(id.getLocation().x, id.getLocation().y + id.getSize().height + 5);
		l2.setFont(font.getFont1());
		l2.setForeground(Color.blue);

		dupli = new Button("중복확인");
		dupli.setSize(search.getSize());
		dupli.setLocation(id.getLocation().x + id.getSize().width + 10, id.getLocation().y);
		dupli.addActionListener(this);

		// 비밀번호
		pw1 = new TextField("비밀번호를 입력하세요.");
		pw1.setSize(frame.getSize().width - 40, id.getSize().height);
		pw1.setLocation(id.getLocation().x, id.getLocation().y + id.getSize().height + 40);

		pw2 = new TextField("비밀번호를 다시 한번 입력하세요.");
		pw2.setSize(pw1.getSize());
		pw2.setLocation(pw1.getLocation().x, pw1.getLocation().y + pw1.getSize().height + 10);

		l3 = new Label();
		l3.setSize(l2.getSize());
		l3.setLocation(pw2.getLocation().x, pw2.getLocation().y + pw2.getSize().height + 5);
		l3.setFont(font.getFont1());
		l3.setForeground(Color.red);

		ok = new Button("확인");
		ok.setSize(120, 50);
		ok.setLocation(l3.getLocation().x, l3.getLocation().y + l3.getSize().height + 10);
		ok.addActionListener(this);

		cancel = new Button("취소");
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
		frame.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(cancel.getLabel())) {
			frame.dispose();
		} else if (e.getActionCommand().equals(search.getLabel())) {
			l1.setText("조회되었습니다.");
		} else if (e.getActionCommand().equals(dupli.getLabel())) {
			l2.setText("사용가능한 아이디입니다.");
		} else if (e.getActionCommand().equals(ok.getLabel())) {
			l3.setText("비밀번호가 다릅니다.");
		}
	}
}
