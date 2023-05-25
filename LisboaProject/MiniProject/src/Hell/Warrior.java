/* This is the warrior class for the warrior objects
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/


package Hell;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;



public class Warrior extends Sprite{

	private String name;
	private int strength;
	public boolean alive;
	public int food;
	public int killed;
	public int timeAlive;
	private int enemy;
	public static  int WARRIOR_WIDTH = 40;
	public final static Image RIGHT = new Image("images/rightWarrior.gif",WARRIOR_WIDTH,WARRIOR_WIDTH,false,false);
	public final static Image LEFT = new Image("images/leftWarrior.gif",WARRIOR_WIDTH,WARRIOR_WIDTH,false,false);
	public boolean speedUp = false;
	public boolean immunity = false;
	public PowerUpTimer power;
	public ImmunityTimer immune;
	double speedVal;
	boolean faceToRight = true;
	boolean faceToRightOrig = false;
	boolean faceToLeftOrig = false;
	boolean transformToImmunitywarriior = false;
	boolean imageChanged = false;


	public Warrior(String name, int x, int y){
		super(x,y);
		this.power = new PowerUpTimer(this);
		this.immune = new ImmunityTimer(this);
		this.name = name;
		this.alive = true;
		this.loadImage(Warrior.RIGHT);
	}

	public boolean isAlive(){
		if(this.alive) return true;
		return false;
	}
	public String getName(){
		return this.name;
	}

	public void die(){
    	this.alive = false;
    }


	public void move() {
		this.x += this.dx;
		this.y += this.dy;
	}

	void gainFood(int increment){
    	this.food+=increment;
    }

	void gainEnemy(double increment){
    	this.enemy+=increment;
    }

	public int getFoodEaten() {
		return this.food;
	}

	public int getEnemyEaten() {
		return this.killed;
	}


	void changeWarriorImage() {
		if(this.immunity) {
			this.img = new Image("images/armored.png",this.getWidth(),this.getWidth(),false,false);
		}
	}

	void changeDirection(Boolean bool){
		if (bool && !this.immunity){
			this.img = new Image("images/rightWarrior.gif",this.getWidth(),this.getWidth(),false,false);
		}
		else if(!bool && !this.immunity){
			this.img = new Image("images/leftWarrior.gif",this.getWidth(),this.getWidth(),false,false);
		}
	}




}
