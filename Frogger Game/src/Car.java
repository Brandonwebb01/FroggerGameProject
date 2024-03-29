import javax.swing.JLabel;

//this is the object that as the thread
public class Car extends Sprite implements Runnable {

	private Boolean visible, moving;
	private Thread t;
	private JLabel CarLabel;
	private Frog Frog;
	private int carID;
	
	public Car() {
		super(0, 0, 41, 75, "car-sprite.png");
		this.visible = true;
		this.moving = false;
	}

	public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }
	
	public void setCarLabel(JLabel temp) {
		this.CarLabel = temp;
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
		System.out.println("visible: " + this.visible);
		System.out.println("moving: " + this.moving);
	}
	
	public void startMoving() {
		System.out.println("Move!");
		if (!this.moving) {
			t = new Thread(this, "Car Thread");
			t.start();
		}
	}

	//stop car
	public void stopCar() {
		this.moving = false;
	}

	@Override
	public void run() {
		System.out.println("Thread started");
		this.moving = true;

		if (getCarID() == 1) {
            try {
                Thread.sleep(500 + (int)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (getCarID() == 2) {
            try {
                Thread.sleep(2500 + (int)(Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (getCarID() == 3) {
            try {
                Thread.sleep(5500 + (int)(Math.random() * 1500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

		while (this.moving) {
			//moving instructions
			
			//get current x
            int currentX = this.x;

            //increase x
            currentX += 20;

            //boundary check right-side
            if (currentX >= GameProperties.SCREEN_WIDTH) {
                currentX = -1 * this.width;
            }
            this.setX(currentX);
			
			 //check for collision
            if ( this.visible ) {
                if (isColliding(Frog)) {
					Frog.resetFrog();
                    System.out.println("BOOM!");
                }
            }
			
			//update Character2Label
			this.CarLabel.setLocation(this.x, this.y);
			
			//pause
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("End Thread");	
	}
}
