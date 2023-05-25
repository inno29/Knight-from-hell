/* This is the GameTimer. This is Where the animation timer is located.
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/

package Hell;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;

public class GameTimer extends AnimationTimer{

	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;
	private GraphicsContext gc;
	private Scene theScene;
	private Warrior myWarrior;
	private ArrayList<NormalFood> foods;
	public static final int MAX_NUM_FOODS = 50;
	public static final int MAX_NUM_IMMUNITY = 1;
	public static final int MAX_NUM_SPEEDUP = 1;
	private ArrayList<Skeleton> Skeletons;
	public static final int MAX_NUM_SKELETONS = 1;
	private static final int MOVEMENT = 30;
	private double mapX = 0;
	private double mapY = 0;
	private static double borderX = -1900;
	private static double borderXNegative = 280;
	private static double borderY = 450;
	private static double borderYNegative = -1901.5;
	Image bg = new Image("images/bg.jpg",GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT,false,false);
	Scale scale = new Scale(0.65,0.65);
	Scale scale2 = new Scale(0.85,0.85);
	Scale scale3 = new Scale(0.4,0.4);
	Scale scale4 = new Scale(1,1);
	int basicFood_count = 50;
	int speedUp_count = 1;
	int immunity_count = 1;
	boolean scaledUp = false;
	boolean scaledUp2 = false;
	boolean scaledUp3 = false;
	boolean collidedY = true;
	boolean collidedX = true;
	boolean faceChanged = false;
	PauseSpawn pause;
	Timer timer;


	GameTimer(GraphicsContext gc, Scene theScene){ //class constructor
		this.gc = gc;
		this.gc.drawImage( bg, this.mapX, this.mapY );
		this.theScene = theScene;
		this.myWarrior = new Warrior("Going merry",360,400);
		this.foods = new ArrayList<NormalFood>();
		this.Skeletons = new ArrayList<Skeleton>();
		this.spawnFoods();
		this.spawnSkeleton();
		this.pause = new PauseSpawn(this.foods,this.myWarrior);
		this.pause.start();
		this.timer = new Timer(this.myWarrior);
		this.timer.start();
		this.handleKeyPressEvent();
	}


	@Override
	public void handle(long currentNanoTime) { //animation handle

		dontMoveBackground();
		this.myWarrior.render(this.gc);
		this.myWarrior.changeWarriorImage();

		for(NormalFood food : this.foods){

			if(food!=null&& !(food instanceof Immunity)&&!(food instanceof SpeedUp) && basicFood_count<MAX_NUM_FOODS){

				spawnOneFood();
				basicFood_count+=1;

			}

		}


		for(NormalFood normalFood: this.foods) {

			normalFood.render(this.gc);
			if(normalFood.getVisible()){

				normalFood.checkCollision(this.myWarrior);

				for(Skeleton skeleton: this.Skeletons) {

					normalFood.checkCollision(skeleton);
					if(this.myWarrior.width>300 && !this.scaledUp){
						gc.setTransform(scale2.getMxx(), scale2.getMyx(), scale2.getMxy(), scale2.getMyy(), scale2.getTx(), scale2.getTy());
						this.scaledUp = true;

					}
					if(this.myWarrior.width>500 && !this.scaledUp2){
						gc.setTransform(scale.getMxx(), scale.getMyx(), scale.getMxy(), scale.getMyy(), scale.getTx(), scale.getTy());
						this.scaledUp2 = true;

					}
					if(this.myWarrior.width>900 && !this.scaledUp3){
						gc.setTransform(scale3.getMxx(), scale3.getMyx(), scale3.getMxy(), scale3.getMyy(), scale3.getTx(), scale3.getTy());
						this.scaledUp3 = true;

					}
				}
			}

			else{


				if(!(normalFood instanceof SpeedUp) && !(normalFood instanceof Immunity)){
					this.basicFood_count-=1;
				}
				else if(normalFood instanceof SpeedUp){
					this.speedUp_count-=1;
				}
				else if(normalFood instanceof Immunity){
					this.immunity_count-=1;
				}
				this.foods.remove(normalFood);

			}

		}

		for(Skeleton skeleton: this.Skeletons) {
			skeleton.render(this.gc);
			skeleton.move();
			if(skeleton.getVisible()){
				skeleton.checkCollision(this.myWarrior);
				for(Skeleton skeleton2:this.Skeletons){
					skeleton.checkCollision2(skeleton2);
				}

				if(this.myWarrior.width>300 && !this.scaledUp){
						gc.setTransform(scale2.getMxx(), scale2.getMyx(), scale2.getMxy(), scale2.getMyy(), scale2.getTx(), scale2.getTy());
						this.scaledUp = true;

				}
				if(this.myWarrior.width>500 && !this.scaledUp2){
						gc.setTransform(scale.getMxx(), scale.getMyx(), scale.getMxy(), scale.getMyy(), scale.getTx(), scale.getTy());
						this.scaledUp2 = true;
				}
				if(this.myWarrior.width>900 && !this.scaledUp3){
					gc.setTransform(scale3.getMxx(), scale3.getMyx(), scale3.getMxy(), scale3.getMyy(), scale3.getTx(), scale3.getTy());
					this.scaledUp3 = true;
				}
			}
			else{
				this.Skeletons.remove(skeleton);
			}
		}

		this.drawScores();

		if(!myWarrior.alive){
			gc.setTransform(scale4.getMxx(), scale4.getMyx(), scale4.getMxy(), scale4.getMyy(), scale4.getTx(), scale4.getTy());
			gc.clearRect(0, 0, GameStage.WINDOW_HEIGHT, GameStage.WINDOW_HEIGHT);
			Font theFont = Font.font("Times New Roman", FontWeight.BOLD,30);
			gc.setFont(theFont);
			gc.setStroke(Color.RED);
			gc.setFill(Color.BLACK);
			gc.setLineWidth(3);
			gc.strokeText("skeletons eaten: "+myWarrior.killed, 280, 300);
			gc.fillText("skeletons eaten: "+myWarrior.killed, 280, 300);
			gc.strokeText("Food eaten: "+myWarrior.food, 280, 400);
			gc.fillText("Food eaten: "+myWarrior.food, 280, 400);
			gc.strokeText("Seconds Alive: "+(myWarrior.timeAlive), 280, 500);
			gc.fillText("Seconds Alive: "+(myWarrior.timeAlive), 280, 500);
			gc.strokeText("YOU BURNED IN HELL", 250, 230);
			gc.fillText("YOU BURNED IN HELL", 250, 230);

		}


		if(this.Skeletons.size()==0){
			this.myWarrior.alive=false;
			gc.setTransform(scale4.getMxx(), scale4.getMyx(), scale4.getMxy(), scale4.getMyy(), scale4.getTx(), scale4.getTy());
			gc.clearRect(0, 0, GameStage.WINDOW_HEIGHT, GameStage.WINDOW_HEIGHT);
			Image img = new Image("images/heaven.jpg",800,800,false,false);
			gc.drawImage(img,0,0);
			Font theFont = Font.font("Times New Roman", FontWeight.BOLD,30);
			gc.setFont(theFont);
			gc.setStroke(Color.BLUE);
			gc.setFill(Color.WHITE);
			gc.setLineWidth(3);
			gc.strokeText("YOU ESCAPED HELL", 250, 230);
			gc.fillText("YOU ESCAPED HELL", 250, 230);
			gc.strokeText("skeletons eaten: "+myWarrior.killed, 280, 300);
			gc.fillText("skeletons eaten: "+myWarrior.killed, 280, 300);
			gc.strokeText("Food eaten: "+myWarrior.food, 280, 400);
			gc.fillText("Food eaten: "+myWarrior.food, 280, 400);
			gc.strokeText("Seconds Alive: "+(myWarrior.timeAlive), 280, 500);
			gc.fillText("Seconds Alive: "+(myWarrior.timeAlive), 280, 500);

		}


	}


	private void drawScores(){


		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.gc.setFill(Color.RED);
		this.gc.fillText("Food Eaten:", 20, 20);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(myWarrior.getFoodEaten() +"", 140, 20);


		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.gc.setFill(Color.BLACK);
		this.gc.fillText("Enemy Eaten:", 200, 20);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(myWarrior.getEnemyEaten() +"", 340, 20);


		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.gc.setFill(Color.YELLOW);
		this.gc.fillText("Size:", 400, 20);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(myWarrior.width +"", 460, 20);

		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.gc.setFill(Color.BLUE);
		this.gc.fillText("Seconds Alive:", 520, 20);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(this.myWarrior.timeAlive +"", 650, 20);



	}


	private void dontMoveBackground(){

		  this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);


	      this.gc.drawImage( bg, this.mapX, this.mapY );



	}


	private void moveBackgroundX(int value){ //function that moves the background in the X axis

		  this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);

		  this.gc.drawImage( bg, this.mapX, this.mapY );

		  if(this.myWarrior.speedUp){

			  if(this.mapX >= this.borderXNegative){

	        		this.collidedX = false;
	        		this.mapX = this.mapX-50;

	        	}

	        	else if(this.mapX <= this.borderX){
	        		this.collidedX = false;
	        		this.mapX = this.mapX+50;

	        	}

	        	else{
	        		System.out.println("This is the warrior loc:"+this.mapX);
	        		System.out.println("This is the border:"+this.borderXNegative);
	        		this.collidedX = true;
	        		this.mapX += 2*(120/myWarrior.width)*value*-1;

	        	}
			}
		  else{

			  if(this.mapX >= this.borderXNegative){
				  	System.out.println("collided");
	        		this.collidedX = false;
	        		this.mapX = this.mapX-50;

	        	}

	        	else if(this.mapX <= this.borderX){
	        		this.collidedX = false;
	        		this.mapX = this.mapX+50;

	        	}

	        	else{
	        		this.collidedX = true;
	        		this.mapX += (120/myWarrior.width)*value*-1;

	        	}
			}


	        for(NormalFood normalFood: this.foods) {
	        	if(this.myWarrior.speedUp){
	        		if(this.mapX<=this.borderX){

						normalFood.x = normalFood.x+2*((120/myWarrior.width)*value*-1)+50;
					}
					else if(this.mapX >= this.borderXNegative){

						normalFood.x = normalFood.x+2*((120/myWarrior.width)*value*-1)-50;
		        	}
					else if (collidedX){
						normalFood.x = normalFood.x+2*((120/myWarrior.width)*value*-1);
					}
				}
				else{
					if(this.mapX<=this.borderX){

						normalFood.x = normalFood.x+((120/myWarrior.width)*value*-1)+50;
					}
					else if(this.mapX >= this.borderXNegative){

						normalFood.x = normalFood.x+((120/myWarrior.width)*value*-1)-50;
		        	}
					else if (collidedX){
						normalFood.x = normalFood.x+((120/myWarrior.width)*value*-1);
					}
				}
			}


			for(Skeleton skeleton: this.Skeletons) {

				if(this.myWarrior.speedUp){

	        		if(this.mapX<=this.borderX){

						skeleton.x = skeleton.x+2*((120/myWarrior.width)*value*-1)+50;
					}
					else if(this.mapX >= this.borderXNegative){

						skeleton.x = skeleton.x+2*((120/myWarrior.width)*value*-1)-50;
		        	}
					else if (collidedX){
						skeleton.x = skeleton.x+2*((120/myWarrior.width)*value*-1);
					}

				}
				else{
					if(this.mapX<=this.borderX){

						skeleton.x = skeleton.x+((120/myWarrior.width)*value*-1)+50;
					}
					else if(this.mapX >= this.borderXNegative){

						skeleton.x = skeleton.x+((120/myWarrior.width)*value*-1)-50;
		        	}
					else if (collidedX){
						skeleton.x = skeleton.x+((120/myWarrior.width)*value*-1);
					}
				}


			}


	}


	private void moveBackgroundY(int Value){//method that moves the map in the y axis

		  this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		  this.gc.drawImage( bg, this.mapX, this.mapY );

		  if(this.myWarrior.speedUp){

	        	if(this.mapY >= this.borderY){
	        		this.collidedY = false;
	        		this.mapY = this.mapY-50;

	        	}

	        	else if(this.mapY <= this.borderYNegative){
	        		this.collidedY = false;
	        		this.mapY = this.mapY+50;
	        	}

	        	else{
	        		this.collidedY = true;
	        		this.mapY += 2*(120/myWarrior.width)*Value;
	        		System.out.println("This is the warrior loc:"+this.mapY);
	        	}


			}
		  else{

				if(this.mapY >= this.borderY){
					this.collidedY = false;
					this.mapY = this.mapY-50;
	        		System.out.println("This is the warrior loc:"+this.mapY);
	        	}

				else if(this.mapY <= this.borderYNegative){
					this.collidedY = false;
	        		this.mapY = this.mapY+50;
	        	}

	        	else{
	        		this.collidedY = true;
	        		this.mapY += (120/myWarrior.width)*Value;
	        		System.out.println("This is the warrior loc:"+this.mapY);
	        	}


			}


	        for(NormalFood normalFood: this.foods) {
					if(this.myWarrior.speedUp){
						if(this.mapY>=this.borderY){
							normalFood.y = normalFood.y+2*((120/myWarrior.width)*Value)-50;
						}
						else if(this.mapY <= this.borderYNegative){
							normalFood.y = normalFood.y+2*((120/myWarrior.width)*Value)+50;
			        	}
						else if (collidedY){
							normalFood.y = normalFood.y+2*((120/myWarrior.width)*Value);
						}

					}
					else{
						if(this.mapY>=this.borderY){
							normalFood.y = normalFood.y+((120/myWarrior.width)*Value)-50;
						}
						else if(this.mapY <= this.borderYNegative){
							normalFood.y = normalFood.y+((120/myWarrior.width)*Value)+50;
			        	}
						else if (collidedY){
							normalFood.y = normalFood.y+((120/myWarrior.width)*Value);
						}
					}

			}

	        for(Skeleton skeleton: this.Skeletons) {

	        	if(this.myWarrior.speedUp){
					if(this.mapY>=this.borderY){
						skeleton.y = skeleton.y+2*((120/myWarrior.width)*Value)-50;
					}
					else if(this.mapY <= this.borderYNegative){
						skeleton.y = skeleton.y+2*((120/myWarrior.width)*Value)+50;
		        	}
					else if (collidedY){
						skeleton.y = skeleton.y+2*((120/myWarrior.width)*Value);
					}

				}
				else{
					if(this.mapY>=this.borderY){
						skeleton.y = skeleton.y+((120/myWarrior.width)*Value)-50;
					}
					else if(this.mapY <= this.borderYNegative){
						skeleton.y = skeleton.y+((120/myWarrior.width)*Value)+50;
		        	}
					else if (collidedY){
						skeleton.y = skeleton.y+((120/myWarrior.width)*Value);
					}
				}


			}


	}


	private void spawnFoods(){ //method that initially spawns the all the food types


		Random r = new Random();
		Random r2 = new Random();
		int speedUps = 0;

		for(int i=0;i<MAX_NUM_FOODS;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH-(int)this.myWarrior.x-(int)this.mapX);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT-(int)this.myWarrior.y);
			this.foods.add(new NormalFood(x,y));
		}

		for(int i=0;i<MAX_NUM_SPEEDUP;i++){

			int x = r.nextInt(GameStage.WINDOW_WIDTH-(int)this.myWarrior.x);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT+(int)this.myWarrior.y);
			this.foods.add(new SpeedUp(x,y));
		}
		for(int i=0;i<MAX_NUM_IMMUNITY;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH-(int)this.mapX);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT+(int)this.mapY+200);
			this.foods.add(new Immunity(x,y));
		}


	}

	private void spawnSkeleton(){//initially spawns the skeletons
		Random r = new Random();
		for(int i=0;i<20;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH-200);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT-300);

			this.Skeletons.add(new Skeleton(x,y));
		}

	}

	private void spawnOneFood(){ // refreshes the skeletons in the map
		Random r = new Random();
		for(int i=0;i<1;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH+(int)this.myWarrior.x-(int)this.mapX);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT-500);

			this.foods.add(new NormalFood(x,y));
		}
	}


	private void handleKeyPressEvent() { //event handlers for when keys are pressed
		this.theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveMyWarrior(code);

			}
		});

		this.theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		            public void handle(KeyEvent e){
		            	KeyCode code = e.getCode();
		                stopMyWarrior(code);

		            }
		        });
    }

	private void moveMyWarrior(KeyCode ke) {
		if(ke==KeyCode.UP) {
			moveBackgroundY(10);
			up = true;

		}

		if(ke==KeyCode.DOWN) {
			moveBackgroundY(-10);
			down = true;

		};


		if(ke==KeyCode.LEFT) {
			moveBackgroundX(-10);
			left = true;
			myWarrior.faceToLeftOrig = myWarrior.faceToRight;
			myWarrior.faceToRight = false;
			this.faceChanged = true;

			if(faceChanged && myWarrior.faceToLeftOrig != myWarrior.faceToRight){
				faceChanged = !faceChanged;
				this.myWarrior.changeDirection(this.myWarrior.faceToRight);
			}
		};

		if(ke==KeyCode.RIGHT) {

			moveBackgroundX(10);
			right = true;
			myWarrior.faceToRightOrig = myWarrior.faceToRight;
			myWarrior.faceToRight = true;
			this.faceChanged = true;

			if(faceChanged && myWarrior.faceToRightOrig!=myWarrior.faceToRight){
				faceChanged = !faceChanged;
				this.myWarrior.changeDirection(this.myWarrior.faceToRight);
			}

		};

		if(ke == null){
			dontMoveBackground();
		}


		System.out.println(ke+" key pressed.");
   	}


	private void stopMyWarrior(KeyCode ke){



		if(ke==KeyCode.UP) {
			up = false;
		};

		if(ke==KeyCode.LEFT) {
			left = false;
		};

		if(ke==KeyCode.DOWN) {
			down = false;
		};

		if(ke==KeyCode.RIGHT) {
			right = false;
		};

		System.out.println(ke+" key released.");
	}


	public double getPosX(){
		return this.mapX;
	}

	public double getPosY(){
		return this.mapY;
	}

}



















