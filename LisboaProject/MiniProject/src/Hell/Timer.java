/* This is the timer class for how long the warrior is alive
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/

package Hell;

public class Timer extends Thread {

	private int time = 0;
	Warrior warrior;

	Timer(Warrior warrior){
		this.warrior = warrior;
	}

	private synchronized void countDown(){

		while(this.warrior.alive){
			try{
				Thread.sleep(1000);
				this.warrior.timeAlive+=1;
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}this.warrior.timeAlive-=1;
	}

	public void run(){

			countDown();

	}



}
