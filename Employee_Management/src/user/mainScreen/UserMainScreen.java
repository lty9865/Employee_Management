package user.mainScreen;

import java.awt.Button;
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
import user.dimension.UserDimension;

public class UserMainScreen extends WindowAdapter implements ActionListener {
	private Frame frame;
	private TextField[] t;
	private Label[] l;
	private Label hi, name, mr;
	private Button b1, b2, b3;
	private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	private String timeStart = format.format(System.currentTimeMillis());
	private String name1;
	private String userID;

	public UserMainScreen(String userID) {
		this.userID = userID;
		UserDimension ud = new UserDimension();
		frame = new Frame("System");
		frame.setLayout(null);
		frame.setSize(ud.getWidth(), ud.getHeight());
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(this);

		// 라벨
		hi = new Label("안녕하세요.");
		hi.setSize(130, 20);
		hi.setLocation(20, 40);

		SearchNameDao sndao = new SearchNameDao();
		name1 = sndao.EmpName(userID);
		name = new Label(name1);
		name.setSize(90, 40);
		name.setLocation(hi.getLocation().x, hi.getLocation().y + hi.getSize().height);
		Fonts font = new Fonts();
		name.setFont(font.getFont2());

		mr = new Label("님");
		mr.setSize(20, 20);
		mr.setLocation(name.getLocation().x + name.getSize().width,
				name.getLocation().y + name.getSize().height - mr.getSize().height);

		l = new Label[4];
		t = new TextField[4];
		String[] arrName = { "출근", "퇴근", "시작", "종료" };
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

		b1 = new Button("출근");
		b1.setSize(260, 50);
		b1.setLocation(l[1].getLocation().x, l[1].getLocation().y + l[1].getSize().height + 5);
		b1.addActionListener(this);

		b2 = new Button("연장신청");
		b2.setSize(b1.getSize());
		b2.setLocation(b1.getLocation().x, b1.getLocation().y + b1.getSize().height + 5);
		b2.addActionListener(this);

		l[2].setLocation(b2.getLocation().x, b2.getLocation().y + b2.getSize().height + 5);
		l[3].setLocation(l[2].getLocation().x, l[2].getLocation().y + l[2].getSize().height + 5);

		for (int i = 0; i < arrName.length; i++) {
			t[i].setLocation(l[i].getLocation().x + l[i].getSize().width, l[i].getLocation().y);
			frame.add(l[i]);
			frame.add(t[i]);
		}

		b3 = new Button("로그아웃");
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
					b1.setEnabled(false);
				}
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(b3.getLabel())) {
			frame.dispose();
			new Login();
		}
		if (e.getActionCommand().equals("출근")) {
			t[0].setText(timeStart);
			new InsertTimeDao(userID, b1.getLabel());
			b1.setLabel("퇴근");
		} else if (e.getActionCommand().equals("퇴근")) {
			t[1].setText(timeStart);
			new InsertTimeDao(userID, b1.getLabel());
			b1.setEnabled(false);
		}
		if (e.getActionCommand().equals("연장신청")) {
			t[2].setText("18:30");
			b2.setLabel("종료");
		} else if (e.getActionCommand().equals("종료")) {
			t[3].setText(timeStart);
			b2.setLabel("연장신청");
		}
	}
}
