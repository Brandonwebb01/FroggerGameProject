import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Background extends JFrame {
	
	JLabel L1;

		public Background() {
			setTitle("A Background Image for Java");
			setSize(1000, 800);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			setVisible(true);
			
			setContentPane(new JLabel(new ImageIcon("C:\\githubFrogger\\Frogger Game\\src\\background.png")));
			setLayout(new FlowLayout());
			L1 = new JLabel();
			add(L1);
			setSize(1000, 800);
		}
		public static void main(String args[]) {
			
			new Background();
		}
}
