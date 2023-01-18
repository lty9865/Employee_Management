package user.mainScreen;

import java.awt.Frame;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import admin.login.Login;
import font.Fonts;
import font.RoundedButton;
import user.dimension.UserDimension;
import user.notice.NoticeFrame;

public class UserMainScreen extends WindowAdapter implements ActionListener {
	private Frame frame;
	private TextField[] t;
	private Label[] l;
	private Label hi, name, mr;
	private RoundedButton b1, b2, b3;
	private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	private SimpleDateFormat format2 = new SimpleDateFormat("YYYY/MM/DD HH:mm:ss");
	private String timeStart = format.format(System.currentTimeMillis());
	private String timeStart2 = format2.format(System.currentTimeMillis());
	private String name1;
	private String userID;

	@SuppressWarnings("deprecation")
	public UserMainScreen(String userID) {
		this.userID = userID;
		UserDimension ud = new UserDimension();
		frame = new Frame("System");
		frame.setLayout(null);
		frame.setSize(ud.getWidth(), ud.getHeight() - 90);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(this);
		frame.setResizable(false);

		// 라벨
		hi = new Label("안녕하세요.");
		hi.setSize(130, 20);
		hi.setLocation(20, 40);

		SearchNameDao sndao = new SearchNameDao();
		name1 = sndao.EmpName(userID);
		name = new Label(name1);
		name.setSize(120, 40);
		name.setLocation(hi.getLocation().x, hi.getLocation().y + hi.getSize().height);
		Fonts font = new Fonts();
		name.setFont(font.getFont2());

		mr = new Label("님");
		mr.setSize(20, 20);
		mr.setLocation(name.getLocation().x + name.getSize().width,
				name.getLocation().y + name.getSize().height - mr.getSize().height);

		this.l = new Label[2];
		this.t = new TextField[2];
		String[] arrName = { "출근", "퇴근" };
		for (int i = 0; i < l.length; i++) {
			l[i] = new Label(arrName[i]);
			l[i].setSize(100, 40);
			t[i] = new TextField();
			t[i].setSize(160, l[i].getSize().height);
			t[i].setEditable(false);
			t[i].setFocusable(false);
		}

		l[0].setLocation(name.getLocation().x, name.getLocation().y + name.getSize().height + 5);
		l[1].setLocation(l[0].getLocation().x, l[0].getLocation().y + l[0].getSize().height + 5);

		b1 = new RoundedButton("출근");
		b1.setSize(260, 40);
		b1.setLocation(l[1].getLocation().x, l[1].getLocation().y + l[1].getSize().height + 15);
		b1.addActionListener(this);

		b2 = new RoundedButton("건의사항");
		b2.setSize(b1.getSize());
		b2.setLocation(b1.getLocation().x, b1.getLocation().y + b1.getSize().height + 15);
		b2.addActionListener(this);

		for (int i = 0; i < arrName.length; i++) {
			t[i].setLocation(l[i].getLocation().x + l[i].getSize().width, l[i].getLocation().y);
			frame.add(l[i]);
			frame.add(t[i]);
		}

		b3 = new RoundedButton("로그아웃");
		b3.setSize(60, 20);
		b3.setLocation((frame.getSize().width - 20) - b3.getSize().width, hi.getLocation().y);
		b3.addActionListener(this);

		frame.add(hi);
		frame.add(name);
		frame.add(mr);
		frame.add(b1);
		frame.add(b2);
		frame.add(b3);
		frame.setVisible(true);
		try {
			ArrayList<TimeVo> list;
			InsertTimeDao dao = new InsertTimeDao();
			list = dao.SearchWork(userID);
			String sWork = list.get(0).getStart();
			String eWork = list.get(0).getEnd();
			boolean al = list.get(0).getAl();

			if (al) {
				t[0].setText(sWork);
				if (eWork == null) {
					b1.setLabel("퇴근");
				} else {
					t[1].setText(eWork);
					b1.setVisible(false);
					b1.setEnabled(false);
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}

	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(b3.getActionCommand())) {
			frame.dispose();
			new Login();
		}
		if (e.getActionCommand().equals("출근")) {
			t[0].setText(timeStart);
			new InsertTimeDao(userID, b1.getActionCommand());
			b1.setLabel("퇴근");
		} else if (e.getActionCommand().equals("퇴근")) {
			new Alert();
		}
		if (e.getActionCommand().equals("건의사항")) {
			System.out.println("건의사항");
			new NoticeFrame(userID);
		}
	}

	public void saveEnd() {
		this.t[1].setText(timeStart);
		new InsertTimeDao(userID, b1.getActionCommand());
		b1.setEnabled(false);
	}

	// 알림 이너 클래스
	class Alert extends WindowAdapter implements ActionListener {
		private Frame frame;
		private Label l1, l2;
		private RoundedButton b1, b2;

		public void windowClosing(WindowEvent e) {
			frame.dispose();
		}

		public Alert() {
			frame = new Frame("알림");
			frame.setSize(250, 170);
			frame.setLocationRelativeTo(null);
			frame.setLayout(null);
			frame.setResizable(false);
			frame.addWindowListener(this);

			l1 = new Label("현재시간: " + timeStart2);
			l2 = new Label("퇴근 저장 하시겠습니까?");

			l1.setSize(190, 20);
			l1.setLocation(30, 40);

			l2.setSize(l1.getSize());
			l2.setLocation(l1.getLocation().x, l1.getLocation().y + l1.getSize().height + 15);
			Fonts font = new Fonts();
			l2.setFont(font.getFont1());

			b1 = new RoundedButton("확인");
			b2 = new RoundedButton("취소");

			b1.setSize(80, 40);
			b2.setSize(b1.getSize());

			b1.setLocation(frame.getSize().width / 2 - b1.getSize().width - 15,
					l2.getLocation().y + l2.getSize().height + 15);
			b2.setLocation(frame.getSize().width / 2 + 15, b1.getLocation().y);

			b1.addActionListener(this);
			b2.addActionListener(this);

			frame.add(l1);
			frame.add(l2);
			frame.add(b1);
			frame.add(b2);
			frame.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(b1.getActionCommand())) {
				frame.dispose();
				saveEnd();
			}
			if (e.getActionCommand().equals(b2.getActionCommand())) {
				frame.dispose();
			}
		}
	}
}
