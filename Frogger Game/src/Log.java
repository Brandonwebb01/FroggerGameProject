import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

//Player Character
public class Log extends Sprite implements Runnable {
	
	private Boolean visible, moving;
	private Thread t;
	private JLabel logLabel;
	private Frog Frog;
	private int logID;
	
	//Constructor
	public Log() {
		super(0, 0, 75, 75, "log-sprite.png");
		this.visible = true;
		this.moving = false;
	}

	public int getLogID() {
        return logID;
    }

    public void setLogID(int carID) {
        this.logID = carID;
    }
	
	public void setLogLabel(JLabel temp) {
		this.logLabel = temp;
	}
	
	public void setFrog(Frog temp) {
		this.Frog = temp;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	public void show() {
		this.visible = true;
	}
	
	public void hide() {
		this.visible = true;
	}
	
	public void Display () {
		System.out.println("x,y: " + this.x + "," + this.y);
		System.out.println("width,height: " + this.width + "," + height);
		System.out.println("image: " + this.image);
		//super.Display();
		System.out.println("visible: " + this.visible);
		System.out.println("moving: " + this.moving);
	}
	
	public void startMoving() {
		System.out.println("Move!");
		if (!this.moving) {
			t = new Thread(this, "Log Thread");
			t.start();
		}
	}

	@Override
	public void run() {
		System.out.println("Thread started");
		this.moving = true;

		if (getLogID() == 1) {
            try {
                Thread.sleep(500 + (int)(Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (getLogID() == 2) {
            try {
                Thread.sleep(2500 + (int)(Math.random() * 4000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (getLogID() == 3) {
            try {
                Thread.sleep(5500 + (int)(Math.random() * 3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

		while (this.moving) {
			//moving instructions
			
			//get current x
            int currentX = this.x;

            //increase x
            currentX += GameProperties.CHARACTER_STEP;

            //boundary check right-side
            if (currentX >= GameProperties.SCREEN_WIDTH) {
                currentX = -1 * this.width;
            }
            this.setX(currentX);
            // System.out.println("X, Y: " + this.x + "," + this.y);

			 //check for collision
            if ( this.visible ) {
                if (isColliding(Frog)) {
                    System.out.println("On Log");
                }
            }
			
			//update Character2Label
			this.logLabel.setLocation(this.x, this.y);
			
			//pause
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("End Thread");	
	}
}