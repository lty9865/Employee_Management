package admin.notice;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import admin.mainScreen.TableDao;
import font.RoundedButton;

public class NoticeFrame2 extends WindowAdapter implements ActionListener {
	private Frame frame;
	private TextField title;
	private TextArea area;
	private RoundedButton ok, cancel;
	private String sugNum;

	public NoticeFrame2(String titleT, String areaT, String sugNum, String stat) {
		this.sugNum = sugNum;

		frame = new Frame("건의 내용");
		frame.setSize(350, 350);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.addWindowListener(this);

		title = new TextField(titleT);
		title.setSize(frame.getSize().width - 30, 30);
		title.setLocation(15, 35);
		frame.add(title);
		title.setEditable(false);
		title.setFocusable(false);

		area = new TextArea(areaT);
		area.setSize(title.getSize().width, 200);
		area.setLocation(title.getLocation().x, title.getLocation().y + title.getSize().height + 15);
		frame.add(area);
		area.setEditable(false);
		area.setFocusable(false);

		ok = new RoundedButton("처리완료");
		ok.setSize(60, 35);
		ok.setLocation(frame.getSize().width / 2 - ok.getSize().width - 15,
				area.getLocation().y + area.getSize().height + 15);
		frame.add(ok);
		ok.addActionListener(this);

		cancel = new RoundedButton("취소");
		cancel.setSize(ok.getSize());
		cancel.setLocation(frame.getSize().width / 2 + 15, ok.getLocation().y);
		frame.add(cancel);
		cancel.addActionListener(this);

		if (stat != null) {
			if (stat.equals("처리완료")) {
				frame.remove(ok);
				cancel.setLocation(frame.getSize().width / 2 - cancel.getSize().width / 2, ok.getLocation().y);
			}
		}

		frame.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(cancel.getActionCommand())) {
			frame.dispose();
			new NoticeFrame();
		}
		if (e.getActionCommand().equals(ok.getActionCommand())) {
			new TableDao().updateResult(sugNum);
			frame.dispose();
			new NoticeFrame();
		}
	}
}
