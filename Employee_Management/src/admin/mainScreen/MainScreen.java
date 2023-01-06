package admin.mainScreen;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
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
import admin.register.Register;

public class MainScreen extends WindowAdapter implements ActionListener {
	private Frame frame2;
	private TextField watch;
	private Button addB, delB, b3;
	private Button e1, e2, e3, e4, allBtn;

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

		// 버튼(등록, 삭제, ?_미정)
		addB = new Button("사원 등록");
		addB.setSize(195, 80);
		addB.setLocation(975, 100);
		addB.addActionListener(this);

		delB = new Button("사원 삭제");
		delB.setSize(addB.getSize());
		delB.setLocation(addB.getLocation().x, addB.getLocation().y + 90);
		delB.addActionListener(this);

		b3 = new Button("button3");
		b3.setSize(addB.getSize());
		b3.setLocation(delB.getLocation().x, delB.getLocation().y + 90);

		// 버튼(부서)
		allBtn = new Button("전체");
		allBtn.setSize(80, 40);
		allBtn.setLocation(560, 60);
		allBtn.setBackground(Color.gray);
		allBtn.addActionListener(this);

		e1 = new Button("총무");
		e1.setSize(allBtn.getSize());
		e1.setLocation(allBtn.getLocation().x + allBtn.getSize().width, allBtn.getLocation().y);
		e1.setBackground(Color.gray);
		e1.addActionListener(this);

		e2 = new Button("회계");
		e2.setSize(allBtn.getSize());
		e2.setLocation(e1.getLocation().x + allBtn.getSize().width, allBtn.getLocation().y);
		e2.setBackground(Color.gray);
		e2.addActionListener(this);

		e3 = new Button("인사");
		e3.setSize(allBtn.getSize());
		e3.setLocation(e2.getLocation().x + allBtn.getSize().width, allBtn.getLocation().y);
		e3.setBackground(Color.gray);
		e3.addActionListener(this);

		e4 = new Button("영업");
		e4.setSize(allBtn.getSize());
		e4.setLocation(e3.getLocation().x + allBtn.getSize().width, allBtn.getLocation().y);
		e4.setBackground(Color.gray);
		e4.addActionListener(this);

		// 테이블 설정
		model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int i, int c) {
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

		frame2.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(addB.getLabel())) {
			new Register();
		} else if (e.getActionCommand().equals(delB.getLabel())) {
			new DeleteE();

			// 부서 버튼
		} else if (e.getActionCommand().equals(allBtn.getLabel())) {
			System.out.println("전체");
			a1 = td.searchAll();
			makeTable();
		} else if (e.getActionCommand().equals(e1.getLabel())) {
			System.out.println("총무");
			allEmp = td.searchEmp(e1.getLabel().toString());
			makeTableDept();
		} else if (e.getActionCommand().equals(e2.getLabel())) {
			System.out.println("회계");
			allEmp = td.searchEmp(e2.getLabel().toString());
			makeTableDept();
		} else if (e.getActionCommand().equals(e3.getLabel())) {
			System.out.println("인사");
			allEmp = td.searchEmp(e3.getLabel().toString());
			makeTableDept();
		} else if (e.getActionCommand().equals(e4.getLabel())) {
			System.out.println("영업");
			allEmp = td.searchEmp(e4.getLabel().toString());
			makeTableDept();
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
