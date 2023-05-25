/* This is the timer class for how long the a warrior will be sped up
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/


package Hell;

public class PowerUpTimer extends Thread {

	private Warrior warrior;
	private int time;
	private final static int UPGRADED_TIME = 5;

	PowerUpTimer(Warrior warrior){
		this.warrior = warrior;
		this.time = 5;
	}

	void refresh(){
		this.time = PowerUpTimer.UPGRADED_TIME;
	}
	private synchronized void countDown(){

		while(this.time!=0){
			try{
				Thread.sleep(1000);
				this.time--;
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}

		this.warrior.speedUp = false;


	}

	public void run(){

			countDown();

	}



}
