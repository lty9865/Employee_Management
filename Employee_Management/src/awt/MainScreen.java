package awt;

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

import Dao.TableDao;
import db.TableVo;

public class MainScreen extends WindowAdapter implements ActionListener {
	private Frame frame2;
	private TextField watch;
	private Button addB, delB, b3;
	private Button e1, e2, e3, e4;
	private JTable table;
	private JScrollPane sp;

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

	public MainScreen() {
		// 프레임 설정
		frame2 = new Frame("Employee Management");
		frame2.setLayout(null);
		frame2.setSize(1200, 800);
		frame2.setLocationRelativeTo(null);

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
		delB.setSize(195, 80);
		delB.setLocation(975, 205);
		delB.addActionListener(this);

		b3 = new Button("button3");
		b3.setSize(195, 80);
		b3.setLocation(975, 310);

		// 버튼(부서)
		e1 = new Button("총무");
		e1.setSize(80, 40);
		e1.setLocation(640, 60);
		e1.setBackground(Color.gray);

		e2 = new Button("회계");
		e2.setSize(80, 40);
		e2.setLocation(720, 60);
		e2.setBackground(Color.gray);

		e3 = new Button("인사");
		e3.setSize(80, 40);
		e3.setLocation(800, 60);
		e3.setBackground(Color.gray);

		e4 = new Button("영업");
		e4.setSize(80, 40);
		e4.setLocation(880, 60);
		e4.setBackground(Color.gray);

		// 테이블 설정
		String header[] = { "사원번호", "이름", "직급", "부서", "생년월일", "전화번호" };
		TableDao td = new TableDao();
		ArrayList<TableVo> a1 = td.searchTable();
		String[][] contents = new String[a1.size()][header.length];
		for (int i = 0; i < contents.length; i++) {
			contents[i][0] = a1.get(i).getEmpNo();
			contents[i][1] = a1.get(i).getName();
			contents[i][2] = a1.get(i).getPos();
			contents[i][3] = a1.get(i).getDeptName();
			contents[i][4] = a1.get(i).getBirth();
			contents[i][5] = a1.get(i).getMobile();

		}
		table = new JTable(contents, header);
		sp = new JScrollPane(table);
		sp.setSize(930, 640);
		sp.setLocation(30, 100);
		frame2.add(sp);

		frame2.addWindowListener(this);
		frame2.add(watch);
		frame2.add(addB);
		frame2.add(delB);
		frame2.add(b3);
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
		}
	}
}
