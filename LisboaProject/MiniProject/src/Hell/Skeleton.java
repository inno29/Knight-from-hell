/* This is the Skeleton class for the skeleton objects
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/



package Hell;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import java.util.Random;


public class Skeleton extends Sprite {

	public final static Image ENEMY = new Image("images/leftSkeleton.gif",Skeleton.SKELETON_WIDTH,Skeleton.SKELETON_WIDTH,false,false);
	public final static int SKELETON_WIDTH=40;
	boolean alive;
	private boolean moveRight;
	private boolean moveUp;
	private double speed = 1;
	private static double edgeX = -1800;
	private static double edgeXNegative = 320;
	private static double borderY = 450;
	private static double borderYNegative = -1600;
	private double time;
	private double absoluteX;
	private double absoluteY;
	private int random;
	private Movetimer move;

	Skeleton(int x, int y){

		super(x,y);
		this.move = new Movetimer(this);
		this.move.start();
		this.alive = true;
		this.loadImage(Skeleton.ENEMY);
		this.absoluteX = this.x-500;
		this.absoluteX = this.absoluteX*-1;
		this.absoluteY = this.y-500;
		this.absoluteY = this.absoluteY*-1;


		int random_number = this.random;



		if( random_number == 0){
			this.moveRight = true;
		}
		else if(random_number == 1){
			this.moveRight = false;
		}

		else if( random_number == 2){
			this.moveUp = true;
		}
		else if(random_number == 3){
			this.moveUp = false;
		}



	}

	
	void move(){

		speed = (120/this.getWidth());

		int random_number = this.random;

		if( random_number == 0){
			this.moveRight = true;
		}
		else if(random_number == 1){
			this.moveRight = false;
		}

		else if( random_number == 2){
			this.moveUp = true;
		}
		else if(random_number == 3){
			this.moveUp = false;
		}

		if(this.absoluteX >= this.edgeXNegative){
			this.moveRight = true;
		}

		else if(this.absoluteX <= this.edgeX){
			this.moveRight = false;
		}

		if(this.absoluteY >= this.borderY){
			this.moveUp = false;
		}

		if(this.absoluteY <= this.borderYNegative){
			this.moveUp = true;
		}

		if(this.moveRight == true){
			this.x += speed;
			this.absoluteX -= speed;
		}
		else{
			this.x -= speed;
			this.absoluteX += speed ;
		}

		if(this.moveUp == true){
			this.y -= speed;
			this.absoluteY += speed;
		}
		else{
			this.y += speed;
			this.absoluteY -= speed ;
		}



	}



	//getter
	public boolean isAlive() {
		return this.alive;
	}



	double getTrueLocX(){
		return(this.absoluteX);
	}

	void checkCollision(Warrior warrior){

		if(this.collidesWith(warrior)){

			if(warrior.getWidth()>this.getWidth()){

				warrior.setWidth(warrior.getWidth() + this.getWidth());
				warrior.setHeight(warrior.height + this.height);
				if(warrior.faceToRight && !warrior.immunity){
					warrior.img = new Image("images/rightWarrior.gif",warrior.getWidth(),warrior.getWidth(),false,false);
				}
				else if (warrior.faceToRight && !warrior.immunity) {
					warrior.img = new Image("images/leftWarrior.gif",warrior.getWidth(),warrior.getWidth(),false,false);
				}

				else if (!warrior.faceToRight && !warrior.immunity) {
					warrior.img = new Image("images/leftWarrior.gif",warrior.getWidth(),warrior.getWidth(),false,false);
				}

				else if(!warrior.faceToRight && warrior.immunity){
					warrior.img = new Image("images/armored.png",warrior.getWidth(),warrior.getWidth(),false,false);
				}
				this.alive = false;
				this.vanish();
				warrior.killed++;
			}

			else if(warrior.getWidth()<this.getWidth() && !warrior.immunity){
				warrior.alive = false;
				System.out.println("warrior died!");

			}
		}
	}

		void checkCollision2(Skeleton skeleton){

			if(this.collidesWith(skeleton)){

				if(skeleton.getWidth()>this.getWidth()){

					skeleton.setWidth(skeleton.getWidth() + this.getWidth());
					skeleton.setHeight(skeleton.height + this.height);
					skeleton.img = new Image("images/leftSkeleton.gif",skeleton.getWidth(),skeleton.getWidth(),false,false);
					this.alive = false;
					this.vanish();
				}
				else if(skeleton.getWidth()<this.getWidth()){
					skeleton.vanish();
				}
			}
	}


	public void setRandom(int number){
		this.random = number;
	}


}


