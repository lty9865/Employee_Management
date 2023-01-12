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

public class MainScreen extends WindowAdapter implements ActionListener {
	private Frame frame2;
	private TextField watch;
	private Button addB, delB, b3, allBtn, logOut, sugg;
	private Button e1, e2, e3, e4;
	private Label title, temp, temp1, PTY, RN1;

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

	public void ThreadTime() {

		t1 = new Thread() {
			public void run() {

				while (true) {
					Date date = new Date();
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

	Color selectColor = new Color(0, 153, 255);

	@SuppressWarnings("serial")
	public MainScreen() {
		// 프레임 설정
		frame2 = new Frame("Employee Management");
		frame2.setLayout(null);
		frame2.setSize(1200, 800);
		frame2.setLocationRelativeTo(null);
		frame2.setResizable(false);

		// 시계 설정
		watch = new TextField();
		watch.setSize(270, 30);
		watch.setLocation(30, 60);
		watch.setFocusable(false);
		watch.setEditable(false);
		ThreadTime();

		// 버튼(등록, 삭제, 수정)
		addB = new Button("사원 등록");
		addB.setSize(195, 80);
		addB.setLocation(975, 100);
		addB.addActionListener(this);

		delB = new Button("사원 삭제");
		delB.setSize(addB.getSize());
		delB.setLocation(addB.getLocation().x, addB.getLocation().y + 90);
		delB.addActionListener(this);

		b3 = new Button("사원 수정");
		b3.setSize(addB.getSize());
		b3.setLocation(delB.getLocation().x, delB.getLocation().y + 90);
		b3.addActionListener(this);

		logOut = new Button("로그아웃");
		logOut.setSize(100, watch.getSize().height);
		logOut.setLocation(watch.getLocation().x + watch.getSize().width, watch.getLocation().y);
		logOut.addActionListener(this);

		sugg = new Button("건의사항");
		sugg.setSize(addB.getSize());
		sugg.setLocation(b3.getLocation().x, b3.getLocation().y + 90);
		sugg.addActionListener(this);

		// 버튼(부서)
		allBtn = new Button("전체");
		allBtn.setSize(80, 40);
		allBtn.setLocation(560, 60);
		allBtn.setBackground(Color.gray);
		allBtn.addActionListener(this);
		allBtn.setBackground(selectColor);

		e1 = new Button("총무");
		e1.setSize(allBtn.getSize());
		e1.setLocation(allBtn.getLocation().x + allBtn.getSize().width, allBtn.getLocation().y);
		e1.setBackground(Color.white);
		e1.addActionListener(this);

		e2 = new Button("회계");
		e2.setSize(allBtn.getSize());
		e2.setLocation(e1.getLocation().x + allBtn.getSize().width, allBtn.getLocation().y);
		e2.setBackground(Color.white);
		e2.addActionListener(this);

		e3 = new Button("인사");
		e3.setSize(allBtn.getSize());
		e3.setLocation(e2.getLocation().x + allBtn.getSize().width, allBtn.getLocation().y);
		e3.setBackground(Color.white);
		e3.addActionListener(this);

		e4 = new Button("영업");
		e4.setSize(allBtn.getSize());
		e4.setLocation(e3.getLocation().x + allBtn.getSize().width, allBtn.getLocation().y);
		e4.setBackground(Color.white);
		e4.addActionListener(this);

		// 테이블 설정
		model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table = new JTable(model);
		table.setRowHeight(30);
		sp = new JScrollPane(table);
		td = new TableDao();
		a1 = td.searchAll();
		makeTable();
		sp.setSize(930, 640);
		sp.setLocation(30, 100);
		frame2.add(sp);

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
		title.setSize(195, 50);
		title.setLocation(b3.getLocation().x, b3.getLocation().y + b3.getSize().height + 180);

		temp1 = new Label("현재 기온");
		temp1.setSize(title.getSize().width / 2, title.getSize().height);
		temp1.setLocation(title.getLocation().x, title.getLocation().y + title.getSize().height + 30);

		Fonts font = new Fonts();
		String strTemp = String.valueOf(weather.getT1H());
		temp = new Label(strTemp);
		temp.setSize(temp1.getSize());
		temp.setLocation(temp1.getLocation().x + temp1.getSize().width, temp1.getLocation().y);
		temp.setFont(font.getFont2());

		PTY = new Label(strPTY);
		PTY.setSize(temp1.getSize());
		PTY.setLocation(temp1.getLocation().x, temp1.getLocation().y + temp1.getSize().height + 30);

		String strRN1 = String.valueOf(weather.getRN1()) + " mm";
		RN1 = new Label(strRN1);
		RN1.setSize(PTY.getSize());
		RN1.setLocation(PTY.getLocation().x + PTY.getSize().width, PTY.getLocation().y);

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
			e1.setBackground(Color.white);
			e2.setBackground(Color.white);
			e3.setBackground(Color.white);
			e4.setBackground(Color.white);
			a1 = td.searchAll();
			makeTable();
		} else if (e.getActionCommand().equals(e1.getActionCommand())) {
			System.out.println("총무");
			e1.setBackground(selectColor);
			allBtn.setBackground(Color.white);
			e2.setBackground(Color.white);
			e3.setBackground(Color.white);
			e4.setBackground(Color.white);
			allEmp = td.searchEmp(e1.getLabel().toString());
			makeTableDept();
		} else if (e.getActionCommand().equals(e2.getActionCommand())) {
			System.out.println("회계");
			e2.setBackground(selectColor);
			e1.setBackground(Color.white);
			allBtn.setBackground(Color.white);
			e3.setBackground(Color.white);
			e4.setBackground(Color.white);
			allEmp = td.searchEmp(e2.getLabel().toString());
			makeTableDept();
		} else if (e.getActionCommand().equals(e3.getActionCommand())) {
			System.out.println("인사");
			e3.setBackground(selectColor);
			e1.setBackground(Color.white);
			e2.setBackground(Color.white);
			allBtn.setBackground(Color.white);
			e4.setBackground(Color.white);
			allEmp = td.searchEmp(e3.getLabel().toString());
			makeTableDept();
		} else if (e.getActionCommand().equals(e4.getActionCommand())) {
			System.out.println("영업");
			e4.setBackground(selectColor);
			e1.setBackground(Color.white);
			e2.setBackground(Color.white);
			e3.setBackground(Color.white);
			allBtn.setBackground(Color.white);
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
