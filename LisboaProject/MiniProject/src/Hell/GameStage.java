/* This is the GameStage class. This is where the scene to be set are place and the design for each scenes.
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/


package Hell;



import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameStage {
	public static final int WINDOW_HEIGHT = 2400;
	public static final int WINDOW_WIDTH = 2400;
	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;
	private Stage stage;
	private Group root;
	private Group root2;
	private Group root3;
	private Group root4;
	private Canvas canvas;
	private GraphicsContext gc;
	ImageView view;
	private GameTimer gametimer;
	private GraphicsContext gc2;
	private GraphicsContext gc3;
	boolean clicked = false;
	Media sound;
	MediaPlayer player;
	//the class constructor
	public GameStage() {   //this is where the designs and scenes are made

        this.sound = new Media(getClass().getResource("/music/metal.mp3").toExternalForm());
        this.player = new MediaPlayer(sound);
        this.player.setCycleCount(MediaPlayer.INDEFINITE);
        this.player.play();
        this.root = new Group();
		this.root2=new Group();
		this.root3 = new Group();
		this.root4 = new Group();
		this.canvas = new Canvas(800,800);
		Canvas title_canvas = new Canvas(GameStage.WINDOW_WIDTH , GameStage.WINDOW_HEIGHT);
		Canvas about_canvas = new Canvas(GameStage.WINDOW_WIDTH , GameStage.WINDOW_HEIGHT);
		Canvas instructions_canvas = new Canvas(GameStage.WINDOW_WIDTH , GameStage.WINDOW_HEIGHT);
		GraphicsContext gc3 = about_canvas.getGraphicsContext2D();
		GraphicsContext gc4 = instructions_canvas.getGraphicsContext2D();
		Image bg2 = new Image("images/hellKnight.jfif",800, 800,false,false);
		Image border = new Image("images/border.gif",2400, 2400,false,false);
		ImageView view2 = new ImageView();
		ImageView view3 = new ImageView();
		ImageView view4 = new ImageView();
		ImageView view5 = new ImageView();
		view2.setImage(bg2);
		view3.setImage(border);
		view4.setImage(bg2);
		view5.setImage(bg2);
		Font theFont = Font.font("Times New Roman", FontWeight.BOLD,50);
		Font theFont2 = Font.font("Times New Roman", FontWeight.BOLD,20);
		this.gc = this.canvas.getGraphicsContext2D();
		this.gc2 = title_canvas.getGraphicsContext2D();
		gc2.setStroke(Color.RED);
		gc2.setFill(Color.BLACK);
		gc2.setFont(theFont);
		gc2.setLineWidth(3);
		gc2.strokeText("KNIGHT FROM HELL", 150, 260);
		gc2.fillText("KNIGHT FROM HELL", 150, 260);
		gc3.setStroke(Color.RED);
		gc3.setFill(Color.WHITE);
		gc3.setFont(theFont2);
		gc3.strokeText("Created by: Inno Jed V. Lisboa\nImages retreived from:\n https://www.shutterstock.com/\nBase code templates:\n1.) https://drive.google.com/file/d/1kuWbY_MMmdECuy2WK6s\n704nDzNHbyYtD/view?usp=share_link\n2.) https://drive.google.com/file/d/1yw11c9Vazk1W_\nlkWTCvpgptgTmLUIoaW/view?usp=share_link\n3.) https://drive.google.com/file/d/1uXyJBAmatUamLvyz\n-Smsp3fhqrQsMCVz/view?usp=share_link\n\n special thanks to chatGPT for answering most of my coding questions", 100, 230);
		gc3.fillText("Created by: Inno Jed V. Lisboa\nImages retreived from:\n https://www.shutterstock.com/\nBase code templates:\n1.) https://drive.google.com/file/d/1kuWbY_MMmdECuy2WK6s\n704nDzNHbyYtD/view?usp=share_link\n2.) https://drive.google.com/file/d/1yw11c9Vazk1W_\nlkWTCvpgptgTmLUIoaW/view?usp=share_link\n3.) https://drive.google.com/file/d/1uXyJBAmatUamLvyz\n-Smsp3fhqrQsMCVz/view?usp=share_link\n\n special thanks to chatGPT for answering most of my coding questions", 100, 230);
		gc4.setStroke(Color.RED);
		gc4.setFill(Color.WHITE);
		gc4.setFont(theFont2);
		gc4.strokeText("                                                  Instructions:\n\n\nTHIS IS A GAME OF DEATH! THERE ARE ONLY TWO CHOICES\nKILL OR BE KILLED\n USE YOUR ARROW KEYS TO MOVE AND COLLECT FOOD.\nCOLLECTING FOOD MAKES YOU BIGGER.\n THERE ARE ALSO POWERUPS SPREAD ALL THROUGHT THE MAP. \n MAKE SURE YOU GET THEM!\nTHE GAME ENDS WHEN YOU KILL ALL ENEMIES OR YOU GET KILLED\n MAKE SURE YOU DON'T BURN DOWN THERE!", 80, 230);
		gc4.fillText("                                                  Instructions:\n\n\nTHIS IS A GAME OF DEATH! THERE ARE ONLY TWO CHOICES\nKILL OR BE KILLED\n USE YOUR ARROW KEYS TO MOVE AND COLLECT FOOD.\nCOLLECTING FOOD MAKES YOU BIGGER.\n THERE ARE ALSO POWERUPS SPREAD ALL THROUGHT THE MAP. \n MAKE SURE YOU GET THEM!\nTHE GAME ENDS WHEN YOU KILL ALL ENEMIES OR YOU GET KILLED\n MAKE SURE YOU DON'T BURN DOWN THERE!", 80, 230);
		Button startBtn = new Button("START GAME");
		Button aboutBtn = new Button("ABOUT");
		Button instructionsBtn = new Button("HOW TO PLAY");
		Button backBtn = new Button("Back to menu");
		Button backBtn2 = new Button("Back to menu");
		aboutBtn.setLayoutX(600);
		aboutBtn.setLayoutY(400);
		instructionsBtn.setLayoutX(100);
		instructionsBtn.setLayoutY(400);
		startBtn.setLayoutX(330);
		startBtn.setLayoutY(400);
		backBtn.setLayoutX(330);
		backBtn.setLayoutY(600);
		backBtn2.setLayoutX(330);
		backBtn2.setLayoutY(600);
		this.root.getChildren().add(view3);
		this.scene1 = new Scene(root, 800, 800, Color.WHITE);
		this.scene2 = new Scene(root2, 800, 800, Color.WHITE);
		this.scene3 = new Scene(root3, 800, 800, Color.WHITE);
		this.scene4 = new Scene(root4, 800, 800, Color.WHITE);
		this.root2.getChildren().add(view2);
		this.root2.getChildren().add(title_canvas);
		this.root2.getChildren().add(startBtn);
		this.root2.getChildren().add(instructionsBtn);
		this.root2.getChildren().add(aboutBtn);
		this.root3.getChildren().add(view5);
		this.root3.getChildren().add(instructions_canvas);
		this.root3.getChildren().add(backBtn);
		this.root4.getChildren().add(view4);
		this.root4.getChildren().add(about_canvas);
		this.root4.getChildren().add(backBtn2);
		addEventHandler(startBtn);
		addEventHandler2(instructionsBtn);
		addEventHandler3(aboutBtn);
		addEventHandler4(backBtn);
		addEventHandler4(backBtn2);

	}

	private void addEventHandler(Button btn) {  //event hander for when user clicks the exit button
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				clicked = true;
				stage.setScene(scene1);
				System.out.println(clicked);
				if (clicked){
					 player.stop();
					 sound = new Media(getClass().getResource("/music/metal.mp3").toExternalForm());
				     player = new MediaPlayer(sound);
				     player.setCycleCount(MediaPlayer.INDEFINITE);
				     player.play();
					gametimer = new GameTimer(gc,scene1);
					gametimer.start();
				}
			}
		});

	}

	private void addEventHandler2(Button btn) {  //event hander for when user clicks the exit button

		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				clicked = true;
				stage.setScene(scene3);
				System.out.println(clicked);
			}
		});

	}

	private void addEventHandler3(Button btn) {  //event hander for when user clicks the exit button
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				clicked = true;
				stage.setScene(scene4);
				System.out.println(clicked);
			}
		});

	}


	private void addEventHandler4(Button btn) {  //event hander for when user clicks the exit button
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				clicked = true;
				stage.setScene(scene2);
				System.out.println(clicked);
				if (clicked){
					gametimer = new GameTimer(gc,scene1);
					 player.stop();
					 sound = new Media(getClass().getResource("/music/metal.mp3").toExternalForm());
				     player = new MediaPlayer(sound);
				     player.setCycleCount(MediaPlayer.INDEFINITE);
				     player.play();
				}
			}
		});

	}

	public void setStage(Stage stage) {

			this.stage = stage;
			this.root.getChildren().add(this.canvas);
			this.stage.setTitle("KNIGHT FROM HELL");
			this.stage.setScene(this.scene2);
			this.stage.show();
	}

}


