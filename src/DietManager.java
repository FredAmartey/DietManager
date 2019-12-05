
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import view.*;
import controller.*;

import javafx.application.Application;


public class DietManager extends Application{
    private static FoodCollection fCollection = new FoodCollection("foods.csv");
    //csv file haven't been created, just for compiling
    private static ExerciseCollection eCollection = new ExerciseCollection("exercise.csv");
    private static FoodFactory ff = new FoodFactory();
    private static Log log = new Log("log.csv", fCollection, eCollection, ff);

    private FoodController foodController = new FoodController(fCollection);
    private ExerciseController exerciseController = new ExerciseController(eCollection);
    private LogController logController = new LogController(fCollection, log, exerciseController);


    //    new IO handler?


    public void saveAndClose() {

        // add IO Handler functionality that writes to csv

    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/StaticLayout.fxml"));
        view.ViewHandler viewHandler = new ViewHandler(foodController, logController, exerciseController);
        loader.setController(viewHandler);
        Parent root = loader.load();
        primaryStage.setTitle("Wellness Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        //add IO Handler functionality that reads csv file

        //run cli
        //new CLI(fCollection, log).run();

        launch(args);




    }
}
