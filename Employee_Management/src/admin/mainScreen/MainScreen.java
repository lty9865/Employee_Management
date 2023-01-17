package admin.mainScreen;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import admin.delete.DeleteE;
import admin.login.Login;
import admin.modify.EmpModify;
import admin.notice.NoticeFrame;
import admin.register.Register;
import common.openApi.OpenApiWeather;
import common.openApi.WeatherVo;
import font.Fonts;
import font.RoundedButton;

public class MainScreen extends WindowAdapter implements ActionListener {
	private Frame frame2;
	private TextField watch;
	private RoundedButton addB, delB, b3, sugg, logOut;
	private Button e1, e2, e3, e4, allBtn;
	private Label title, temp, temp1, PTY, RN1, RN2;
	private Label deptList, empList, suggestion, logoutTitle;

	// 테이블
	private JTable table;
	private JScrollPane sp;
	private DefaultTableModel model;
	private String header[] = { "사원번호", "이름", "직급", "부서", "생년월일", "전화번호", "출근/퇴근" };
	private TableDao td;
	private ArrayList<TableVo> a1, allEmp;
	private String[] contents;

	// 시계
	private Thread t1;
	Date date = new Date();

	public void ThreadTime() {

		t1 = new Thread() {
			public void run() {

				while (true) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 ");
					String strDate = sdf.format(date);

					LocalTime now = LocalTime.now();
					DateTimeFormatter df = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
					String strNow = now.format(df);

					watch.setText(strDate + strNow);

					try {

						Thread.sleep(1000);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t1.start();
	}

	Color selectColor = new Color(0, 76, 153);

	@SuppressWarnings("serial")
	public MainScreen() {
		Fonts font = new Fonts();
		// 프레임 설정
		frame2 = new Frame("Employee Management");
		frame2.setLayout(null);
		frame2.setSize(1200, 800);
		frame2.setLocationRelativeTo(null);
		frame2.setResizable(false);

		// 라벨
		deptList = new Label("부서 목록");
		empList = new Label("사원 목록");
		suggestion = new Label("건의 사항");
		logoutTitle = new Label("로그아웃");

		deptList.setSize(190, 30);
		deptList.setLocation(30, 60);
		deptList.setFont(font.getFont4());
		frame2.add(deptList);

		empList.setSize(deptList.getSize());
		empList.setLocation(250, 60);
		empList.setFont(font.getFont4());
		frame2.add(empList);

		suggestion.setSize(empList.getSize());
		suggestion.setLocation(deptList.getLocation().x, 350);
		suggestion.setFont(font.getFont4());
		frame2.add(suggestion);

		logoutTitle.setSize(suggestion.getSize());
		logoutTitle.setLocation(deptList.getLocation().x, 480);
		logoutTitle.setFont(font.getFont4());
		frame2.add(logoutTitle);

		// 시계 설정
		watch = new TextField();
		watch.setSize(270, 30);
		watch.setLocation(frame2.getSize().width - watch.getSize().width - 20, 60);
		watch.setFocusable(false);
		watch.setEditable(false);
		ThreadTime();

		// 테이블 설정
		model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table = new JTable(model);
		table.setRowHeight(30);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		sp = new JScrollPane(table);
		td = new TableDao();
		a1 = td.searchAll();
		makeTable();
		sp.setSize(930, 680);
		sp.setLocation(250, 100);
		frame2.add(sp);

		// 버튼(부서)
		allBtn = new Button("전체");
		allBtn.setSize(deptList.getSize().width, 40);
		allBtn.setLocation(deptList.getLocation().x, sp.getLocation().y);
		allBtn.setBackground(Color.gray);
		allBtn.addActionListener(this);
		allBtn.setBackground(selectColor);
		allBtn.setForeground(Color.white);

		e1 = new Button("총무");
		e1.setSize(allBtn.getSize());
		e1.setLocation(allBtn.getLocation().x, allBtn.getLocation().y + allBtn.getSize().height);
		e1.setBackground(Color.white);
		e1.addActionListener(this);

		e2 = new Button("회계");
		e2.setSize(allBtn.getSize());
		e2.setLocation(e1.getLocation().x, e1.getLocation().y + e1.getSize().height);
		e2.setBackground(Color.white);
		e2.addActionListener(this);

		e3 = new Button("인사");
		e3.setSize(allBtn.getSize());
		e3.setLocation(e2.getLocation().x, e2.getLocation().y + e2.getSize().height);
		e3.setBackground(Color.white);
		e3.addActionListener(this);

		e4 = new Button("영업");
		e4.setSize(allBtn.getSize());
		e4.setLocation(e3.getLocation().x, e3.getLocation().y + e3.getSize().height);
		e4.setBackground(Color.white);
		e4.addActionListener(this);

		// 버튼(등록, 삭제, 수정)
		addB = new RoundedButton("등록");
		addB.setSize(60, watch.getSize().height);
		addB.setLocation(frame2.getSize().width / 2 - (addB.getSize().width - 10), watch.getLocation().y);
		addB.addActionListener(this);

		b3 = new RoundedButton("편집");
		b3.setSize(addB.getSize());
		b3.setLocation(addB.getLocation().x + b3.getSize().width + 10, addB.getLocation().y);
		b3.addActionListener(this);

		delB = new RoundedButton("삭제");
		delB.setSize(b3.getSize());
		delB.setLocation(b3.getLocation().x + b3.getSize().width + 10, b3.getLocation().y);
		delB.addActionListener(this);

		sugg = new RoundedButton("건의사항");
		sugg.setSize(e4.getSize());
		sugg.setLocation(suggestion.getLocation().x, suggestion.getLocation().y + suggestion.getSize().height + 5);
		sugg.setBackground(Color.white);
		sugg.addActionListener(this);

		logOut = new RoundedButton("로그아웃");
		logOut.setSize(sugg.getSize());
		logOut.setLocation(logoutTitle.getLocation().x, logoutTitle.getLocation().y + logoutTitle.getSize().height + 5);
		logOut.setBackground(Color.white);
		logOut.addActionListener(this);

		// 날씨 가져오기
		String strPTY = null;
		WeatherVo weather = null;
		try {
			weather = new OpenApiWeather().weatherVo();
			double intPTY = weather.getPTY();
			if (intPTY == 0) {
				strPTY = "강수없음";
			} else if (intPTY <= 1) {
				strPTY = "비";
			} else if (intPTY <= 2) {
				strPTY = "비/눈";
			} else if (intPTY <= 3) {
				strPTY = "눈";
			} else if (intPTY <= 4) {
				strPTY = "소나기";
			} else if (intPTY <= 5) {
				strPTY = "빗방울";
			} else if (intPTY <= 6) {
				strPTY = "빗방울눈날림";
			} else if (intPTY <= 7) {
				strPTY = "눈날림";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		title = new Label("현재 날씨");
		title.setSize(deptList.getSize());
		title.setLocation(logoutTitle.getLocation().x,
				logoutTitle.getLocation().y + logoutTitle.getSize().height + 100);
		title.setFont(font.getFont4());

		temp1 = new Label("현재 기온");
		temp1.setSize(title.getSize().width / 2, title.getSize().height);
		temp1.setLocation(title.getLocation().x, title.getLocation().y + title.getSize().height + 30);

		String strTemp = String.valueOf(weather.getT1H());
		temp = new Label(strTemp);
		temp.setSize(50, 50);
		temp.setLocation(temp1.getLocation().x + temp1.getSize().width, temp1.getLocation().y - 10);
		temp.setFont(font.getFont2());
		temp.setAlignment(2);

		PTY = new Label(strPTY);
		PTY.setSize(temp1.getSize());
		PTY.setLocation(temp1.getLocation().x, temp1.getLocation().y + temp1.getSize().height + 30);

		String strRN1 = String.valueOf(weather.getRN1());
		RN1 = new Label(strRN1);
		RN1.setSize(temp.getSize());
		RN1.setLocation(PTY.getLocation().x + PTY.getSize().width, PTY.getLocation().y - 10);
		RN1.setFont(font.getFont2());
		RN1.setAlignment(2);

		RN2 = new Label("mm");
		RN2.setSize(PTY.getSize());
		RN2.setLocation(RN1.getLocation().x + RN1.getSize().width, PTY.getLocation().y);

		frame2.addWindowListener(this);
		frame2.add(watch);
		frame2.add(addB);
		frame2.add(delB);
		frame2.add(b3);
		frame2.add(allBtn);
		frame2.add(e1);
		frame2.add(e2);
		frame2.add(e3);
		frame2.add(e4);
		frame2.add(title);
		frame2.add(temp);
		frame2.add(temp1);
		frame2.add(PTY);
		frame2.add(RN1);
		frame2.add(logOut);
		frame2.add(sugg);
		frame2.add(RN2);

		frame2.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(addB.getActionCommand())) {
			new Register();
		} else if (e.getActionCommand().equals(delB.getActionCommand())) {
			new DeleteE();

			// 부서 버튼
		} else if (e.getActionCommand().equals(allBtn.getActionCommand())) {
			System.out.println("전체");
			allBtn.setBackground(selectColor);
			allBtn.setForeground(Color.white);
			e1.setBackground(Color.white);
			e1.setForeground(Color.BLACK);
			e2.setBackground(Color.white);
			e2.setForeground(Color.BLACK);
			e3.setBackground(Color.white);
			e3.setForeground(Color.BLACK);
			e4.setBackground(Color.white);
			e4.setForeground(Color.BLACK);
			a1 = td.searchAll();
			makeTable();
		} else if (e.getActionCommand().equals(e1.getActionCommand())) {
			System.out.println("총무");
			e1.setBackground(selectColor);
			e1.setForeground(Color.white);
			allBtn.setBackground(Color.white);
			allBtn.setForeground(Color.black);
			e2.setBackground(Color.white);
			e2.setForeground(Color.black);
			e3.setBackground(Color.white);
			e3.setForeground(Color.black);
			e4.setBackground(Color.white);
			e4.setForeground(Color.black);
			allEmp = td.searchEmp(e1.getLabel().toString());
			makeTableDept();
		} else if (e.getActionCommand().equals(e2.getActionCommand())) {
			System.out.println("회계");
			e2.setBackground(selectColor);
			e2.setForeground(Color.white);
			e1.setBackground(Color.white);
			e1.setForeground(Color.black);
			allBtn.setBackground(Color.white);
			allBtn.setForeground(Color.black);
			e3.setBackground(Color.white);
			e3.setForeground(Color.black);
			e4.setBackground(Color.white);
			e4.setForeground(Color.black);
			allEmp = td.searchEmp(e2.getLabel().toString());
			makeTableDept();
		} else if (e.getActionCommand().equals(e3.getActionCommand())) {
			System.out.println("인사");
			e3.setBackground(selectColor);
			e3.setForeground(Color.white);
			e1.setBackground(Color.white);
			e1.setForeground(Color.black);
			e2.setBackground(Color.white);
			e2.setForeground(Color.black);
			allBtn.setBackground(Color.white);
			allBtn.setForeground(Color.black);
			e4.setBackground(Color.white);
			e4.setForeground(Color.black);
			allEmp = td.searchEmp(e3.getLabel().toString());
			makeTableDept();
		} else if (e.getActionCommand().equals(e4.getActionCommand())) {
			System.out.println("영업");
			e4.setBackground(selectColor);
			e4.setForeground(Color.white);
			e1.setBackground(Color.white);
			e1.setForeground(Color.black);
			e2.setBackground(Color.white);
			e2.setForeground(Color.black);
			e3.setBackground(Color.white);
			e3.setForeground(Color.black);
			allBtn.setBackground(Color.white);
			allBtn.setForeground(Color.black);
			allEmp = td.searchEmp(e4.getLabel().toString());
			makeTableDept();
		}

		if (e.getActionCommand().equals(b3.getActionCommand())) {
			new EmpModify();
		}

		if (e.getActionCommand().equals(logOut.getActionCommand())) {
			frame2.dispose();
			new Login();
		}

		if (e.getActionCommand().equals(sugg.getActionCommand())) {
			new NoticeFrame();

		}
	}

	public void makeTable() {
		model.setNumRows(0);
		contents = new String[header.length];
		for (int i = 0; i < a1.size(); i++) {
			contents[0] = a1.get(i).getEmpNo();
			contents[1] = a1.get(i).getName();
			contents[2] = a1.get(i).getPos();
			contents[3] = a1.get(i).getDeptName();
			contents[4] = a1.get(i).getBirth();
			contents[5] = a1.get(i).getMobile();
			contents[6] = a1.get(i).getCommute();
			model.addRow(contents);
		}
	}

	public void makeTableDept() {
		model.setNumRows(0);
		contents = new String[header.length];
		for (int i = 0; i < allEmp.size(); i++) {
			contents[0] = allEmp.get(i).getEmpNo();
			contents[1] = allEmp.get(i).getName();
			contents[2] = allEmp.get(i).getPos();
			contents[3] = allEmp.get(i).getDeptName();
			contents[4] = allEmp.get(i).getBirth();
			contents[5] = allEmp.get(i).getMobile();
			contents[6] = allEmp.get(i).getCommute();
			model.addRow(contents);
		}
	}
}
