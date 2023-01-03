package user.login;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AlertFrame extends WindowAdapter implements ActionListener {
	private Frame frame;
	private Label l1;
	private Button ok;

	public AlertFrame() {
		frame = new Frame("Alert");
		frame.setLayout(null);
		frame.setSize(250, 150);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(this);

		// 라벨
		l1 = new Label("아이디 또는 패스워드가 틀립니다.");
		l1.setSize(190, 45);
		l1.setLocation(20, 60);

		ok = new Button("OK");
		ok.setSize(40, 30);
		ok.setLocation(frame.getSize().width - ok.getSize().width - 15,
				frame.getSize().height - ok.getSize().height - 15);
		ok.addActionListener(this);

		frame.add(l1);
		frame.add(ok);
		frame.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		frame.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(ok.getLabel())) {
			frame.dispose();
		}
	}
}
