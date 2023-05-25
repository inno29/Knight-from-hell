/* This is the SpeedUp class which is a child of the NormalFood class. This contains the overrides for the NormalFood class.
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/


package Hell;

import javafx.scene.image.Image;

public class SpeedUp extends NormalFood {

	private final static Image BOOTS = new Image("images/speedUp.png",NormalFood.NORMALFOOD_SIZE,NormalFood.NORMALFOOD_SIZE,false,false);
	public PowerUpTimer power;
	public PowerUpDuration duration;

	SpeedUp(int x, int y){
		super(x,y);
		this.visible = true;
		this.alive = true;
		this.loadImage(SpeedUp.BOOTS);
		this.duration = new PowerUpDuration(this);
		this.duration.start();
	}

	@Override
	void checkCollision(Warrior warrior){

		if(this.collidesWith(warrior)){
			if(!warrior.speedUp){
				warrior.speedUp=true;
				this.vanish();
				warrior.power = new PowerUpTimer(warrior);
				warrior.power.start();
			}
			else{
				warrior.power.refresh();
				this.vanish();

			}
		}
	}


	void checkCollision(Skeleton skeleton){

		if(this.collidesWith(skeleton)){

		}
	}







}
