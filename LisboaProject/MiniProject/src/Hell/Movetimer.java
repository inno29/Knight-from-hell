/* This is the class that randomizes the bees movements
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/



package Hell;

import java.util.Random;

public class Movetimer extends Thread {

	int time;
	int random;
	Skeleton skeleton;

	Movetimer(Skeleton skeleton){

		this.skeleton = skeleton;
		Random r = new Random();
		skeleton.setRandom(r.nextInt(5));
		Random r2 = new Random();
		this.time = r2.nextInt(5);
	}

	public synchronized void countdown(){

		while(this.time!=0){
			try{
				Thread.sleep(1000);
				this.time--;
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}

		Random r = new Random();
		Random r2 = new Random();
		skeleton.setRandom(r.nextInt(5));
		this.time = r2.nextInt(5);



	}


	public void run(){
		while(true){
			countdown();
		}
	}

}
