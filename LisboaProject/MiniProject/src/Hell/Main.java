package Hell;
/* This is the Main class. This is where the stage is set a
*
* @author Inno Jed V. Lisboa
* @created_date 2023-10-01 21:31
*/
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage){
		GameStage theGameStage = new GameStage();
		theGameStage.setStage(stage);
	}

}
