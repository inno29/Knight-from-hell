/* This is the Immunity class which is a child of the NormalFood class. This contains the overrides for the NormalFood class.
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/


package Hell;

import javafx.scene.image.Image;

	public class Immunity extends NormalFood {
	private final static Image ARMOR = new Image("images/immunity.png",NormalFood.NORMALFOOD_SIZE,NormalFood.NORMALFOOD_SIZE,false,false);

	public ImmunityDuration duration;

	Immunity(int x, int y){
		super(x,y);
		this.visible = true;
		this.alive = true;
		this.loadImage(Immunity.ARMOR);
		this.duration = new ImmunityDuration(this);
		this.duration.start();
	}

	void checkCollision(Warrior Warrior){

		if(this.collidesWith(Warrior)){
			if(!Warrior.immunity){
				Warrior.immunity = true;
				this.vanish();
				Warrior.immune = new ImmunityTimer(Warrior);
				Warrior.immune.start();

				if(Warrior.immunity){
					Warrior.changeWarriorImage();
				}
			}
			else{
				Warrior.immune.refresh();
				this.vanish();
			}
		}

	}

	void checkCollision(Skeleton skeleton){

		if(this.collidesWith(skeleton)){

		}
	}
}
