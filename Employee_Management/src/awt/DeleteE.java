package awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteE extends WindowAdapter implements ActionListener {
	private Frame frameDel;
	private Button search, ok;
	private TextField depID;
	private TextField[] t;
	private Label[] l;

	public DeleteE() {
		// 프레임
		frameDel = new Frame("Employee registration");
		frameDel.setLayout(null);
		frameDel.setSize(300, 450);
		frameDel.setLocationRelativeTo(null);

		// 라벨
		String[] name = { "부서", "직급", "이름", "생년월일", "전화번호" };
		l = new Label[name.length];
		int height = 190;
		for (int i = 0; i < name.length; i++) {
			l[i] = new Label(name[i]);
			l[i].setSize(70, 30);
			l[i].setLocation(30, height);
			height = height + 50;
			frameDel.add(l[i]);
		}

		// 텍스트필드(사번)
		depID = new TextField("사원번호를 입력하세요.");
		depID.setSize(240, 30);
		depID.setLocation(30, 60);

		// 텍스트필드
		t = new TextField[l.length];
		for (int i = 0; i < t.length; i++) {
			t[i] = new TextField();
			t[i].setSize(170, 30);
			t[i].setLocation(100, l[i].getLocation().y);
			frameDel.add(t[i]);
			t[i].setEditable(false);
			t[i].setFocusable(false);
		}

		// 버튼
		ok = new Button("삭제");
		ok.setSize(100, 50);
		ok.setLocation(170, ((depID.getLocation().y) + 50));
		ok.addActionListener(this);

		search = new Button("조회");
		search.setSize(ok.getSize());
		search.setLocation(30, ok.getLocation().y);
		search.addActionListener(this);

		frameDel.addWindowListener(this);
		frameDel.add(ok);
		frameDel.add(depID);
		frameDel.add(search);

		frameDel.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frameDel.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(search.getLabel())) {
			System.out.println("search");
		} else if (e.getActionCommand().equals(ok.getLabel())) {
			frameDel.dispose();
		}
	}
}
