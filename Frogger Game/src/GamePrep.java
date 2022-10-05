import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePrep extends JFrame implements KeyListener {
	//Character Variables
	private Frog frog;
	
	//Graphical Elements
	private Container content;
	private JLabel frogLabel;
	private ImageIcon frogImage;
	
public GamePrep() {
	//Character 1 Attributes
	frog = new Frog();
	frog.setX(0);
	frog.setY(200);
	frog.setImage("frog-sprite.png");
	frog.setWidth(50);
	frog.setHeight(39);
	
	//Set up Graphic
	setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
	content = getContentPane();
	content.setBackground(Color.BLACK);
	setLayout(null);
	
	frogLabel = new JLabel();
	frogImage = new ImageIcon(getClass().getResource(frog.getImage()));
	frogLabel.setIcon(frogImage);
	frogLabel.setSize(frog.getWidth(), frog.getHeight());
	frogLabel.setLocation(frog.getX(), frog.getY());
	
	add(frogLabel);
}

	public static void main(String args []) {
		
		GamePrep myGame = new GamePrep();
		myGame.setVisible(true);
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int x = frog.getX();
		int y = frog.getY();
		
		//modify position
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			y -= GameProperties.CHARACTER_STEP;
			
			if (y + frog.getHeight() <= 0) {
				y = GameProperties.SCREEN_HEIGHT;
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			y += GameProperties.CHARACTER_STEP;
			
			if (y >= GameProperties.SCREEN_HEIGHT) {
				y = -1 * frog.getHeight();
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= GameProperties.CHARACTER_STEP;
			
			if (x + frog.getWidth() <= 0) {
				x = GameProperties.SCREEN_WIDTH;
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += GameProperties.CHARACTER_STEP;
			
			if (x >= GameProperties.SCREEN_WIDTH) {
				x = -1 * frog.getWidth();
			}
			
		} else {
			System.out.println("invalid operation");
			
		}
		frog.setX(x);
		frog.setY(y);
		//update graphic
		frogLabel.setLocation(frog.getX(), frog.getY());
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
}
