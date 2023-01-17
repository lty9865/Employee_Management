package admin.modify;

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

import admin.delete.DeleteDao;
import common.searchEmp.SearchEmpDao;
import font.RoundedButton;

public class EmpModify extends WindowAdapter implements MouseListener, ActionListener, KeyListener {
	private Frame frame;
	private RoundedButton search, ok, cancel;
	private TextField depID;
	private TextField[] t;
	private Label[] l;
	private boolean b = true;

	public EmpModify() {
		// 프레임
		frame = new Frame("사원 수정");
		frame.setLayout(null);
		frame.setSize(300, 450);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

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
			frame.add(l[i]);
		}

		// 텍스트필드
		t = new TextField[l.length];
		for (int i = 0; i < t.length; i++) {
			t[i] = new TextField();
			t[i].setSize(170, 30);
			t[i].setLocation(100, l[i].getLocation().y);
			frame.add(t[i]);
			t[i].setEditable(false);
			t[i].setFocusable(false);
		}
		t[4].addKeyListener(this);

		// 버튼
		ok = new RoundedButton("수정");
		ok.setSize(100, 50);
		ok.setLocation(l[l.length - 1].getLocation().x,
				l[l.length - 1].getLocation().y + l[l.length - 1].getSize().height + 20);
		ok.addActionListener(this);

		search = new RoundedButton("조회");
		search.setSize(60, 30);
		search.setLocation(depID.getLocation().x + depID.getSize().width + 10, depID.getLocation().y);
		search.addActionListener(this);

		cancel = new RoundedButton("취소");
		cancel.setSize(ok.getSize());
		cancel.setLocation(t[t.length - 1].getLocation().x + t[t.length - 1].getSize().width - cancel.getSize().width,
				ok.getLocation().y);
		cancel.addActionListener(this);

		frame.addWindowListener(this);
		frame.add(ok);
		frame.add(depID);
		frame.add(search);
		frame.add(cancel);

		frame.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frame.dispose();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String deptNum = depID.getText();
		if (e.getActionCommand().equals(search.getActionCommand())) {
			System.out.println("search");
			SearchEmpDao sd = new SearchEmpDao();
			String[] strArr = sd.searchDel(deptNum);
			if (strArr != null) {
				for (int i = 0; i < strArr.length; i++) {
					t[i].setText(strArr[i]);
					t[i].setEditable(true);
					t[i].setFocusable(true);
				}
				t[0].setEditable(false);
				t[0].setFocusable(false);
				depID.setEditable(false);
				depID.setFocusable(false);
			}
		} else if (e.getActionCommand().equals(ok.getActionCommand())) {
			if (depID.isEditable() == false) {
				System.out.println("Modify");
				DeleteDao dao = new DeleteDao();
				dao.ModifyDao(t[1].getText(), t[2].getText(), t[3].getText(), t[4].getText(), depID.getText());
				frame.dispose();
			}
		} else if (e.getActionCommand().equals(cancel.getActionCommand())) {
			frame.dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int max = 11;
		if (t[4].getText().length() >= max) {
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
}