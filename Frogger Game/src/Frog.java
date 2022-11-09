import javax.swing.JLabel;
import javax.swing.ImageIcon;
//Player Character
public class Frog extends Sprite {

	private JLabel frogLabel = new JLabel();
	private ImageIcon frogImage;
	private ImageIcon frogImageDown;
	private ImageIcon frogImageRight;
	private ImageIcon frogImageLeft;
	//Constructor
	public Frog() {
		super(0, 0, 39, 50, "frog-sprite.png");	
		this.setX(450);
		this.setY(750);
		this.setWidth(50);
		this.setHeight(39);
		this.setImage("frog-sprite.png");

		//frogLabel = new JLabel();
		frogImage = new ImageIcon(getClass().getResource(this.getImage()));
		frogImageDown = new ImageIcon(getClass().getResource("frog-sprite-down.png"));
		frogImageRight = new ImageIcon(getClass().getResource("frog-sprite-right.png"));
		frogImageLeft = new ImageIcon(getClass().getResource("frog-sprite-left.png"));
		frogLabel.setIcon(frogImage);
		frogLabel.setSize(50, 39);
		frogLabel.setLocation(this.getX(), this.getY());
	}

	    // getters and setters for imageicons
		public ImageIcon getFrogImage() {
			return frogImage;
		}
	
		public void setFrogImage(ImageIcon frogImage) {
			this.frogImage = frogImage;
		}
	
		public ImageIcon getFrogImageRight() {
			return frogImageRight;
		}
	
		public void setFrogImageRight(ImageIcon frogImageRight) {
			this.frogImageRight = frogImageRight;
		}
	
		public ImageIcon getFrogImageDown() {
			return frogImageDown;
		}
	
		public void setFrogImageDown(ImageIcon frogImageDown) {
			this.frogImageDown = frogImageDown;
		}
	
		public ImageIcon getFrogImageLeft() {
			return frogImageLeft;
		}
	
		public void setFrogImageLeft(ImageIcon frogImageLeft) {
			this.frogImageLeft = frogImageLeft;
		}
	
		// setter and getter for frogLabel
		public JLabel getFrogLabel() {
			return frogLabel;
		}
	
		public void setFrogLabel(JLabel frogLabel) {
			this.frogLabel = frogLabel;
		}
}
