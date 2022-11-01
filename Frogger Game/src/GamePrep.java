import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
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
	private Log log;

	//make array of cars
	private Car[] cars = new Car[3];
	
	//graphic elements
	private Container content;
	private JLabel frogLabel, carLabel, backgroundLabel, logLabel;
	private ImageIcon carImage, frogImage, backgroundImage, logImage;
	
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
		
		//set up frog
		frog = new Frog();
		frog.setX(0);
		frog.setY(200);
		frog.setWidth(50);
		frog.setHeight(39);
		frog.setImage("frog-sprite.png");

		for (int i = 0; i < cars.length; i++) {
			cars[i] = new Car();
			cars[i].setY(500);
			cars[i].setFrog(frog);
		}
		//set up car
		// car = new Car();
		// car.setX(0);
		// car.setY(0);
		// car.setWidth(75);
		// car.setHeight(41);
		// car.setVisible(true);
		// car.setMoving(false);
		// car.setImage("car-sprite.png");
		// car.setFrog(frog);
		
		//set up log
		log = new Log();
		log.setX(200);
		log.setY(200);
		log.setWidth(75);
		log.setHeight(75);
		log.setVisible(true);
		log.setMoving(false);
		log.setImage("log-sprite.png");
		log.setFrog(frog);

		//set up screen
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		content = getContentPane();
		content.setBackground(Color.gray);
		setLayout(null);
		
		//graphic element instantiation and add to screen
		frogLabel = new JLabel();
		frogImage = new ImageIcon(getClass().getResource(frog.getImage()));
		frogLabel.setIcon(frogImage);
		frogLabel.setSize(frog.getWidth(), frog.getHeight());
		frogLabel.setLocation(frog.getX(),frog.getY());
		//car.setFrogLabel(frogLabel);
		
		for (int i = 0; i < cars.length; i++) {
            carLabel = new JLabel();
            carImage = new ImageIcon(getClass().getResource(cars[i].getImage()));
            carLabel.setIcon(carImage);
            carLabel.setSize(cars[i].getWidth(), cars[i].getHeight());
            carLabel.setLocation(cars[i].getX(), cars[i].getY());
            cars[i].setCarLabel(carLabel);
            content.add(carLabel);
        }
		
		logLabel = new JLabel();
		logImage = new ImageIcon(getClass().getResource(log.getImage()));
		logLabel.setIcon(logImage);
		logLabel.setSize(log.getWidth(), log.getHeight());
		logLabel.setLocation(log.getX(), log.getY());
		log.setLogLabel(logLabel);
		
		backgroundLabel = new JLabel();
		backgroundImage = new ImageIcon(getClass().getResource(background.getImage()));
		backgroundLabel.setIcon(backgroundImage);
		backgroundLabel.setSize(background.getWidth(), background.getHeight());
		backgroundLabel.setLocation(background.getX(),background.getY());
		
		//add a start button
		StartButton = new JButton ("Start");
		StartButton.setSize(100, 100);
		StartButton.setLocation(GameProperties.SCREEN_WIDTH-100, 
				                GameProperties.SCREEN_HEIGHT-200);
		StartButton.setFocusable(false);
		// car.setStartButton(StartButton);
		log.setStartButton(StartButton);
		
		
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

			for (int i = 0; i < cars.length; i++) {
                cars[i].setCarID(i);
                if ( cars[i].getMoving()) {
                    //stop, update button text to start
                    cars[i].setVisible(false);
                    cars[i].setMoving(false);
                    //log.setMoving(false);
                    //StartButton.setText("Start");
                } else {
                    //start, update button text to stop
                    //character2.setMoving(true);
                    cars[i].setVisible(true);
                    cars[i].startMoving();
                    //log.startMoving();
                    //StartButton.setText("Stop");
                }
            }
			// if ( car.getMoving() || log.getMoving()) {
			// 	//stop, update button text to start
			// 	car.setMoving(false);
			// 	log.setMoving(false);
			// 	//StartButton.setText("Start");
			// } else {
			// 	//start, update button text to stop
			// 	//character2.setMoving(true);
			// 	car.startMoving();
			// 	log.startMoving();
			// 	//StartButton.setText("Stop");
			// }
			
			
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
}
