package view;
import controller.ExerciseController;
import controller.FoodController;
import controller.LogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//for GUI testing
public class GUI extends Application{

    public FoodController foodController;
    public LogController logController;
    public ExerciseController exerciseController;
    private int num;

//    public GUI(FoodController foodController, LogController logController){
//        this.foodController = foodController;
//        this.logController = logController;
//    }



    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StaticLayout.fxml"));
        ViewHandler viewHandler = new ViewHandler(foodController, logController, exerciseController);
        loader.setController(viewHandler);
        Parent root = loader.load();
        primaryStage.setTitle("The wonderful Doctor of EatWell");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


}
