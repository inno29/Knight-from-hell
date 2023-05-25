/* This is the calss for the Normal food. It is also a parent class for th other Power Ups
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/


package Hell;



import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class NormalFood extends Sprite {
	public final static int NORMALFOOD_SIZE = 20;
	private final static Image NORMAL = new Image("images/normalFood.gif",  NormalFood.NORMALFOOD_SIZE, NormalFood.NORMALFOOD_SIZE,false,false);
	protected boolean alive;
	protected final static int GAIN = 1;
	public final static int addWith = 10;


	NormalFood(int x, int y) {
		super(x,y);
		this.visible = true;
		this.alive = true;
		this.loadImage(NormalFood.NORMAL);
	}

	public boolean isAlive() {
		return this.alive;
	}


	void checkCollision(Warrior warrior){

		if(this.collidesWith(warrior)){

			this.vanish();
			warrior.gainFood(NormalFood.GAIN);
			warrior.setWidth(warrior.getWidth()+this.addWith);
			warrior.setHeight(warrior.getWidth()+this.addWidth);
			if(warrior.faceToRight && !warrior.immunity){
				warrior.img = new Image("images/rightWarrior.gif",warrior.getWidth(),warrior.getWidth(),false,false);
			}
			else if (!warrior.faceToRight && !warrior.immunity) {
				warrior.img = new Image("images/leftWarrior.gif",warrior.getWidth(),warrior.getWidth(),false,false);
			}
			else if(!warrior.faceToRight && warrior.immunity){
				warrior.img = new Image("images/armored.png",warrior.getWidth(),warrior.getWidth(),false,false);
			}
			else if(warrior.faceToRight && warrior.immunity){
				warrior.img = new Image("images/armored.png",warrior.getWidth(),warrior.getWidth(),false,false);
			}

		}
	}



	void checkCollision(Skeleton skeleton){

		if(this.collidesWith(skeleton)){

			this.vanish();
			skeleton.setWidth(skeleton.getWidth()+this.addWidth);
			skeleton.setHeight(skeleton.getWidth()+this.addWidth);
			skeleton.img = new Image("images/leftSkeleton.gif",skeleton.width,skeleton.width,false,false);

		}
	}






}



