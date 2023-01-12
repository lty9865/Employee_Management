package admin.delete;

import java.awt.Button;

import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import common.searchEmp.SearchEmpDao;

public class DeleteE extends WindowAdapter implements ActionListener, MouseListener {
	private Frame frameDel;
	private Button search, ok, cancel;
	private TextField depID;
	private TextField[] t;
	private Label[] l;
	private boolean b = true;

	public DeleteE() {
		// 프레임
		frameDel = new Frame("사원 삭제");
		frameDel.setLayout(null);
		frameDel.setSize(300, 450);
		frameDel.setLocationRelativeTo(null);

		// 텍스트필드(사번)
		depID = new TextField("사원번호를 입력하세요.");
		depID.setSize(170, 30);
		depID.setLocation(30, 60);
		depID.addMouseListener(this);

		// 라벨
		String[] name = { "부서", "직급", "이름", "생년월일", "전화번호" };
		l = new Label[name.length];
		int height = depID.getLocation().y + depID.getSize().height + 20;
		for (int i = 0; i < name.length; i++) {
			l[i] = new Label(name[i]);
			l[i].setSize(70, 30);
			l[i].setLocation(30, height);
			height = height + 50;
			frameDel.add(l[i]);
		}

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
		ok.setLocation(l[l.length - 1].getLocation().x,
				l[l.length - 1].getLocation().y + l[l.length - 1].getSize().height + 20);
		ok.addActionListener(this);

		search = new Button("조회");
		search.setSize(60, 30);
		search.setLocation(depID.getLocation().x + depID.getSize().width + 10, depID.getLocation().y);
		search.addActionListener(this);

		cancel = new Button("취소");
		cancel.setSize(ok.getSize());
		cancel.setLocation(t[t.length - 1].getLocation().x + t[t.length - 1].getSize().width - cancel.getSize().width,
				ok.getLocation().y);
		cancel.addActionListener(this);

		frameDel.addWindowListener(this);
		frameDel.add(ok);
		frameDel.add(depID);
		frameDel.add(search);
		frameDel.add(cancel);

		frameDel.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frameDel.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String deptNum = depID.getText();
		if (e.getActionCommand().equals(search.getLabel())) {
			System.out.println("search");
			SearchEmpDao sd = new SearchEmpDao();
			String[] strArr = sd.searchDel(deptNum);
			if (strArr != null) {
				for (int i = 0; i < strArr.length; i++) {
					t[i].setText(strArr[i]);
				}
			}
		} else if (e.getActionCommand().equals(ok.getLabel())) {
			System.out.println("Delete");
			new DeleteDao(deptNum);
			frameDel.dispose();
		} else if (e.getActionCommand().equals(cancel.getLabel())) {
			frameDel.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getComponent().equals(depID) && b) {
			depID.setText(null);
			b = false;
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
}
