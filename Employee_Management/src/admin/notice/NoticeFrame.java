package admin.notice;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import admin.mainScreen.TableDao;
import admin.mainScreen.TableVo;

public class NoticeFrame extends WindowAdapter implements ActionListener {
	private Frame frame;
	private Button cancel;

	// 테이블
	private JTable table;
	private JScrollPane sp;
	private DefaultTableModel model;
	private String header[] = { "제목", "부서", "작성일", "결과" };
	private String[] contents;
	private TableDao td;
	private ArrayList<TableVo> a1;

	@SuppressWarnings("serial")
	public NoticeFrame() {
		frame = new Frame("건의 사항");
		frame.setSize(350, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.addWindowListener(this);

		cancel = new Button("취소");
		cancel.setSize(70, 35);
		cancel.setLocation(frame.getSize().width / 2 - cancel.getSize().width / 2,
				frame.getSize().height - cancel.getSize().height - 15);
		cancel.addActionListener(this);
		frame.add(cancel);

		// 테이블
		model = new DefaultTableModel(header, 0) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					TableDao dao = new TableDao();
					ArrayList<TableVo> list = dao.searchSugg(row);
					String title = list.get(0).getTitle();
					String area = list.get(0).getArea();
					String sugNum = list.get(0).getSugNum();
					new NoticeFrame2(title, area, sugNum);
					frame.dispose();
				}
			}
		});
		table.setRowHeight(30);
		sp = new JScrollPane(table);
		td = new TableDao();
		a1 = td.searchSugg();
		makeTable();
		sp.setSize(frame.getSize().width - 30, frame.getSize().height - 140);
		sp.setLocation(frame.getSize().width / 2 - sp.getSize().width / 2, 40);
		frame.add(sp);

		frame.setVisible(true);
	}

	public void makeTable() {
		model.setNumRows(0);
		contents = new String[header.length];
		for (int i = 0; i < a1.size(); i++) {
			contents[0] = a1.get(i).getSugTitle();
			contents[1] = a1.get(i).getDeptName();
			contents[2] = a1.get(i).getWriteDay();
			contents[3] = a1.get(i).getStat();
			model.addRow(contents);
		}
	}

	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(cancel.getActionCommand())) {
			frame.dispose();
		}
	}

	public Frame getFrame() {
		return frame;
	}
}
