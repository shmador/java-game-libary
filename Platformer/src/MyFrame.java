import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class MyFrame{
	JFrame frame;
	public MyFrame(String title) {
		frame = new JFrame();
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(new Dimension(1920, 1080));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setLayout(null);

		JPanel p = new JPanel();
		p.setLayout(null);
		p.setBounds(400, 400, 50, 20);
		
		JLabel l = new JLabel();
		l.setText("dor");
		l.setBounds(0, 0, 50, 20);
		
		p.add(l);
		frame.add(p);
	}
	public void add(Component comp) {
		frame.add(comp);
	}
}
