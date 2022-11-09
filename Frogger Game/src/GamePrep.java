import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePrep extends JFrame implements KeyListener, ActionListener {
	
	//instances of our data classes (store position, etc here)
	private Frog frog;
	private Car car;
	private Background background;

	//array of cars
	private Car[] cars = new Car[3];
	private Car[] cars2 = new Car[3];
	private Car[] cars3 = new Car[3];
	private Car[] cars4 = new Car[3];
	private Car[] cars5 = new Car[3];

	//array of logs
	private Log[] logs = new Log[3];
	private Log[] logs2 = new Log[3];
	private Log[] logs3 = new Log[3];
	private Log[] logs4 = new Log[3];
	private Log[] logs5 = new Log[3];
	private Log[] logs6 = new Log[3];
	
	//graphic elements
	private Container content;
	private JLabel frogLabel, carLabel, backgroundLabel, logLabel;
	private ImageIcon carImage, backgroundImage, logImage;
	
	//buttons
	private JButton StartButton;
	private JButton VisibilityButton;
	
	public GamePrep() {
		
			
		//set up background
		background = new Background();
		background.setX(0);
		background.setY(0);
		background.setImage("background.png");
		background.setWidth(1000);
		background.setHeight(800);

		//graphic element instantiation and add to screen
		backgroundLabel = new JLabel();
		backgroundImage = new ImageIcon(getClass().getResource(background.getImage()));
		backgroundLabel.setIcon(backgroundImage);
		backgroundLabel.setSize(background.getWidth(), background.getHeight());
		backgroundLabel.setLocation(background.getX(),background.getY());

		frog = new Frog();
		frogLabel = frog.getFrogLabel();

		//set up screen
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		content = getContentPane();
		content.setBackground(Color.gray);
		setLayout(null);
		
		createCars(cars, -75, 450);
		createCars(cars2, -75, 510);
		createCars(cars3, -75, 570);
		createCars(cars4, -75, 630);
		createCars(cars5, -75, 690);

		createLogs(logs, -75, 50, true);
		createLogs(logs2, -75, 100, false);
		createLogs(logs3, -75, 150, true);
		createLogs(logs4, -75, 200, false);
		createLogs(logs5, -75, 250, true);
		createLogs(logs6, -75, 300, false);
		
		//add a start button
		StartButton = new JButton ("Start");
		StartButton.setSize(100, 100);
		StartButton.setLocation(GameProperties.SCREEN_WIDTH-100, 
				                GameProperties.SCREEN_HEIGHT-200);
		StartButton.setFocusable(false);
		
		
		//add a disappear button
		VisibilityButton = new JButton ("Hide");
		VisibilityButton.setSize(100, 50);
		VisibilityButton.setLocation(GameProperties.SCREEN_WIDTH-100, 
				                     GameProperties.SCREEN_HEIGHT-100);
		VisibilityButton.setFocusable(false);
		
		//populate screen
		add(StartButton);
		StartButton.addActionListener(this);
		add(VisibilityButton);
		VisibilityButton.addActionListener(this);
		add(frogLabel);
		add(carLabel);
		add(logLabel);
		add(backgroundLabel);
		
		
		content.addKeyListener(this);
		content.setFocusable(true);
		content.setComponentZOrder(frogLabel, 0);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public static void main( String args []) {
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
			frogLabel.setIcon(frog.getFrogImage());
			if (y + frog.getHeight() <= 0) {
				y = GameProperties.SCREEN_HEIGHT;
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			y += GameProperties.CHARACTER_STEP;
			frogLabel.setIcon(frog.getFrogImageDown());
			if (y >= GameProperties.SCREEN_HEIGHT) {
				y = -1 * frog.getHeight();
			}
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= GameProperties.CHARACTER_STEP;	
			frogLabel.setIcon(frog.getFrogImageLeft());
			if (x + frog.getWidth() <= 0) {
				x = GameProperties.SCREEN_WIDTH;
			}			
			
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += GameProperties.CHARACTER_STEP;	
			frogLabel.setIcon(frog.getFrogImageRight());
			if (x >= GameProperties.SCREEN_WIDTH) {
				x = -1 * frog.getWidth();
			}

		} else {
			System.out.println("Invalid operation");
		}
		frog.setX(x);
		frog.setY(y);
		
		//update graphic
		frogLabel.setLocation(frog.getX(), frog.getY());
	}


	@Override
	public void keyReleased(KeyEvent e) {}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		//distinguish among buttons
		if (e.getSource() == StartButton) {

			showCarsArray(cars);
			showCarsArray(cars2);
			showCarsArray(cars3);
			showCarsArray(cars4);
			showCarsArray(cars5);

			showLogsArray(logs);
			showLogsArray(logs2);
			showLogsArray(logs3);
			showLogsArray(logs4);
			showLogsArray(logs5);
			showLogsArray(logs6);
			
					
		} else if (e.getSource() == VisibilityButton) {
			//check the visibility of the character2 object
			if ( car.getVisible() ) {
				//if visible, hide, change text of button to show
				car.setVisible(false);
				carLabel.setVisible(car.getVisible());
				VisibilityButton.setText("Show");
			} else {
				//if hidden, show, change text of button to hide
				car.setVisible(true);
				carLabel.setVisible(car.getVisible());
				VisibilityButton.setText("Hide");
			}
		}
	}

	public void createCars(Car[] car, int x, int y) {
        for (int i = 0; i < car.length; i++) {
            car[i] = new Car();
            car[i].setY(y);
            car[i].setX(x);
            car[i].setFrog(frog);

            carLabel = new JLabel();
            carImage = new ImageIcon(getClass().getResource(car[i].getImage()));
            carLabel.setIcon(carImage);
            carLabel.setSize(car[i].getWidth(), car[i].getHeight());
            carLabel.setLocation(car[i].getX(), car[i].getY());
            car[i].setCarLabel(carLabel);
            content.add(carLabel);
        }
    }

	//create logs
	public void createLogs(Log[] log, int x, int y, boolean rDirection) {
		for (int i = 0; i < log.length; i++) {
			log[i] = new Log();
			log[i].setY(y);
			log[i].setX(x);
			log[i].setFrog(frog);
			log[i].setReverseDirection(rDirection);

			logLabel = new JLabel();
			logImage = new ImageIcon(getClass().getResource(log[i].getImage()));
			logLabel.setIcon(logImage);
			logLabel.setSize(log[i].getWidth(), log[i].getHeight());
			logLabel.setLocation(log[i].getX(), log[i].getY());
			log[i].setLogLabel(logLabel);
			content.add(logLabel);
		}
	}

	public void showCarsArray(Car[] carArray) {
		for (int i = 0; i < carArray.length; i++) {
			carArray[i].setCarID(i);
			if ( carArray[i].getMoving()) {
				carArray[i].setVisible(false);
				carArray[i].setMoving(false);
			} else {
				carArray[i].setVisible(true);
				carArray[i].startMoving();
			}
		}
	}

	public void showLogsArray(Log[] logArray) {
		for (int i = 0; i < logArray.length; i++) {
			logArray[i].setLogID(i);
			if ( logArray[i].getMoving()) {
				logArray[i].setVisible(false);
				logArray[i].setMoving(false);
			} else {
				logArray[i].setVisible(true);
				logArray[i].startMoving();
			}
		}
	}
}

