/* This is the timer class for how long the the speed ups will remain in the map
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/


package Hell;

public class PowerUpDuration extends Thread {

	private SpeedUp speed;
	private int time;
	private final static int DURATION = 5;


	PowerUpDuration(SpeedUp speed){
		this.speed = speed;
		this.time = 5;
	}

	private void countDown(){

		while(this.time!=0){
			try{
				Thread.sleep(1000);
				this.time--;

			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}
		speed.vanish();


	}

	public void run(){

			countDown();

	}



}
