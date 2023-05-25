/* This is the timer class for how long the the warrior will remain immune
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/



package Hell;

public class ImmunityTimer extends Thread {

	private Warrior warrior;
	private int time;
	private final static int UPGRADED_TIME = 5;

	ImmunityTimer(Warrior warrior){
		this.warrior = warrior;
		this.time = 5;

	}

	void refresh(){
		this.time = ImmunityTimer.UPGRADED_TIME;
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

		this.warrior.immunity = false;


	}

	public void run(){

			countDown();

	}



}
