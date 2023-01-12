package user.notice;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import user.mainScreen.SearchNameDao;

public class NoticeFrame extends WindowAdapter implements ActionListener, MouseListener {
	private Frame frame;
	private TextField title;
	private TextArea area;
	private Checkbox part;
	private Button ok, cancel;

	private String userID;

	public NoticeFrame() {
	}

	public NoticeFrame(String userID) {
		this.userID = userID;

		frame = new Frame("건의사항");
		frame.setSize(350, 315);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.addWindowListener(this);

		title = new TextField();
		title.setSize(frame.getSize().width - 90, 30);
		title.setLocation(15, 35);
		frame.add(title);

		part = new Checkbox("비품", false);
		part.setSize(60, title.getSize().height);
		part.setLocation(title.getLocation().x + title.getSize().width + 5, title.getLocation().y);
		frame.add(part);
		part.addMouseListener(this);

		area = new TextArea();
		area.setSize(frame.getSize().width - 30, 175);
		area.setLocation(title.getLocation().x, title.getLocation().y + title.getSize().height + 10);
		frame.add(area);

		ok = new Button("확인");
		ok.setSize(60, 35);
		ok.setLocation(frame.getSize().width / 2 - ok.getSize().width - 10,
				area.getLocation().y + area.getSize().height + 10);
		frame.add(ok);
		ok.addActionListener(this);

		cancel = new Button("취소");
		cancel.setSize(ok.getSize());
		cancel.setLocation(frame.getSize().width / 2 + 10, ok.getLocation().y);
		frame.add(cancel);
		cancel.addActionListener(this);
	}

	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(cancel.getActionCommand())) {
			frame.dispose();
		}

		if (e.getActionCommand().equals(ok.getActionCommand())) {
			new SearchNameDao().suggestions(userID, title.getText(), area.getText());
			frame.dispose();
		}

		if (e.getActionCommand().equals(cancel.getActionCommand())) {
			frame.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getComponent().equals(part)) {
			if (part.getState() == false) {
				title.setText("비품 건의");
				area.setText("<비품>\n\n제품명 : \n\n필요개수 : ");
			} else {
				title.setText(null);
				area.setText(null);
			}
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
