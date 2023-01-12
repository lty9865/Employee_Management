package admin.register;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

public class Register extends WindowAdapter implements ActionListener, KeyListener {
	private Frame frameRes;
	private Button ok;
	private TextField[] t;
	private TextField moT;
	private Label[] l;
	private Choice ch, mo, y, m, d;

	public Register() {
		// 프레임
		frameRes = new Frame("사원 추가");
		frameRes.setLayout(null);
		frameRes.setSize(320, 450);
		frameRes.setLocationRelativeTo(null);

		// 라벨
		String[] name = { "부서", "직급", "이름", "생년월일", "전화번호" };
		l = new Label[name.length];
		int height = 100;
		for (int i = 0; i < name.length; i++) {
			l[i] = new Label(name[i]);
			l[i].setSize(70, 30);
			l[i].setLocation(30, height);
			height = height + 50;
			frameRes.add(l[i]);
		}

		// 초이스
		ch = new Choice();
		ch.add("총무");
		ch.add("회계");
		ch.add("인사");
		ch.add("영업");
		ch.setSize(70, 30);
		ch.setLocation(100, l[0].getLocation().y);
		frameRes.add(ch);

		// 텍스트필드
		t = new TextField[l.length];
		for (int i = 1; i <= 2; i++) {
			t[i] = new TextField();
			t[i].setSize(190, 30);
			t[i].setLocation(100, l[i].getLocation().y);
			frameRes.add(t[i]);
		}

		// 생년월일
		LocalDate now = LocalDate.now();
		y = new Choice();
		for (int i = 1950, j = now.getYear(); i <= j; i++) {
			y.add(Integer.toString(i));
		}
		y.setSize(70, 30);
		y.setLocation(100, l[3].getLocation().y);
		frameRes.add(y);

		m = new Choice();
		for (int i = 1; i <= 12; i++) {
			m.add(Integer.toString(i));
		}
		m.setSize(50, 30);
		m.setLocation(y.getLocation().x + 80, l[3].getLocation().y);
		frameRes.add(m);

		d = new Choice();
		for (int i = 1; i <= 31; i++) {
			d.add(Integer.toString(i));
		}
		d.setSize(m.getSize());
		d.setLocation(m.getLocation().x + 60, l[3].getLocation().y);
		frameRes.add(d);

		// 전화번호
		mo = new Choice();
		mo.add("010");
		mo.add("011");
		mo.setSize(d.getSize());
		mo.setLocation(100, l[4].getLocation().y);
		frameRes.add(mo);

		moT = new TextField(null, 8);
		moT.setSize(130, 30);
		moT.setLocation(mo.getLocation().x + 60, mo.getLocation().y);
		moT.addKeyListener(this);
		frameRes.add(moT);

		// 버튼
		ok = new Button("추가");
		ok.setSize(100, 50);
		ok.setLocation((frameRes.getSize().width / 2) - (ok.getSize().width / 2),
				(l[l.length - 1].getLocation().y) + 50);
		ok.addActionListener(this);

		frameRes.addWindowListener(this);
		frameRes.add(ok);

		frameRes.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frameRes.dispose();
	}

	// 추가 버튼 누를 때
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (t[1].getText().isEmpty() || t[2].getText().isEmpty() || moT.getText().isEmpty()) {
		} else if (e.getActionCommand().equals(ok.getLabel())) {
			StringBuilder birth = new StringBuilder();
			birth.append(y.getSelectedItem());
			int mi = Integer.parseInt(m.getSelectedItem());
			int di = Integer.parseInt(d.getSelectedItem());
			birth.append(String.format("%02d", mi));
			birth.append(String.format("%02d", di));

			StringBuilder mobile = new StringBuilder();
			mobile.append(mo.getSelectedItem());
			mobile.append(moT.getText());

			InsertUserDao dao = new InsertUserDao();
			UserVo v = new UserVo(ch.getSelectedItem(), t[1].getText(), t[2].getText(), birth.toString(),
					mobile.toString());
			dao.insert(v.getDeptName(), v.getPos(), v.getName(), v.getBirth(), v.getMobile());
			frameRes.dispose();
		}
	}

	// 키 리스너
	@Override
	public void keyTyped(KeyEvent e) {
		int max = 8;
		if (moT.getText().length() >= max) {
			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
