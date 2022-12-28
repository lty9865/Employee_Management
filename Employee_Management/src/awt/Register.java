package awt;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Register extends WindowAdapter implements ActionListener {
	private Frame frameRes;
	private Button ok;
	private TextField[] t;
	private Label[] l;
	private Choice ch;

	public Register() {
		// 프레임
		frameRes = new Frame("Employee registration");
		frameRes.setLayout(null);
		frameRes.setSize(300, 450);
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
		for (int i = 1; i < t.length; i++) {
			t[i] = new TextField();
			t[i].setSize(170, 30);
			t[i].setLocation(100, l[i].getLocation().y);
			frameRes.add(t[i]);
		}

		// 버튼
		ok = new Button("추가");
		ok.setSize(100, 50);
		ok.setLocation((frameRes.getSize().width / 2) - (ok.getSize().width / 2),
				(t[t.length - 1].getLocation().y) + 50);
		ok.addActionListener(this);

		frameRes.addWindowListener(this);
		frameRes.add(ok);

		frameRes.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frameRes.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(ok.getLabel())) {
			frameRes.dispose();
		}
	}
}
