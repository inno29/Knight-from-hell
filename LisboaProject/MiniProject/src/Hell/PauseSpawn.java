/* This is the timer class for how long the the speed ups will disappear from the Map;
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/



package Hell;

import java.util.ArrayList;
import java.util.Random;

public class PauseSpawn extends Thread {


	private int time;
	private ArrayList<NormalFood> foods;
	private Warrior myWarrior;

	PauseSpawn(ArrayList<NormalFood> foods, Warrior Warrior){
		this.foods = foods;
		this.time = 15;
		this.myWarrior = Warrior;

	}

	private synchronized void spawnSpeedUp(){

		Random r = new Random();
		for(int i=0;i<10;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH-(int)this.myWarrior.x);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT-(int)this.myWarrior.x);

			this.foods.add(new SpeedUp(x,y));
		}

	}

	private  void spawnImmunity(){
		Random r = new Random();
		for(int i=0;i<10;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT);


			this.foods.add(new Immunity(x,y));
		}

	}

	private void countDown(){

		System.out.println("started spawn");

		while(this.time!=0){
			try{
				Thread.sleep(1000);
				this.time--;

			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}

		spawnImmunity();
		spawnSpeedUp();
		this.time = 15;
		System.out.println("spawned Power Up");

	}

	public void run(){
		while(true){
			countDown();
		}

	}



}
