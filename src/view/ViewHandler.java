package view;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.chart.*;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import model.*;
import controller.*;


import javax.naming.Name;

import java.time.LocalDate;

import java.util.*;

public class ViewHandler{

    private FoodController foodController;
    private LogController logController;
    private ExerciseController exerciseController;

    @FXML
    private TabPane tabPaneMain;

    @FXML
    private Pane addFoodPane;

    @FXML
    private TextField addFoodNametf;

    @FXML
    private TextField addFoodCaltf;

    @FXML
    private TextField addFoodFattf;

    @FXML
    private TextField addFoodCarbtf;

    @FXML
    private TextField addFoodProteintf;

    @FXML
    private RadioButton addFoodrb;

    @FXML
    private ToggleGroup addFoodItemGrp;

    @FXML
    private RadioButton addReciperb;

    @FXML
    private Pane addRecipePane;

    @FXML
    private ComboBox<String> addRecipecb;

    @FXML
    private TextArea addRecipeta;

    @FXML
    private TextField addRecipeAmounttf;

    @FXML
    private ComboBox<String> deleteFoodItemcb;

    @FXML
    private Button deleteFoodItembtn;

    @FXML
    private TextField addExerciseNametf;

    @FXML
    private TextField addExerciseCaltf;

    @FXML
    private Button addExerciseSubmitbtn;

    @FXML
    private ComboBox<String> deleteExercisecb;

    @FXML
    private Button deleteExercisebtn;

    @FXML
    private RadioButton logFoodrb;

    @FXML
    private ToggleGroup logItemgrp;

    @FXML
    private RadioButton logCalrb;

    @FXML
    private RadioButton logWeightrb;

    @FXML
    private RadioButton logExerciserb;

    @FXML
    private Button logItemSubmitbtn;

    @FXML
    private Label logAmountlbl;

    @FXML
    private TextField logAmounttf;

    @FXML
    private ComboBox<String> logItemcb;

    @FXML
    private Label calGoalLbl;

    @FXML
    private Label calTotalLbl;

    @FXML
    private Tab changeDateTab;

    @FXML
    private DatePicker dateSelected;

    @FXML
    private Tab logReportTab;

    @FXML
    private BarChart<?, ?> foodGraph;

    @FXML
    private Tab deleteFoodItemTab;

    @FXML
    private TextField addRecipeNametf;

    @FXML
    private ComboBox<String> deleteLogEntrycb;

    @FXML
    private Button deleteLogEntrybtn;

    @FXML
    private Tab removeLogEntryTab;

    @FXML
    private Tab removeExerciseTab;

    @FXML
    private Label calsBurnedlbl;

    @FXML
    private Label netCalslbl;

    @FXML
    private Label currentWeightlbl;

    @FXML
    private Label totalFatlbl;

    @FXML
    private Label totalCarbslbl;

    @FXML
    private Label totalProteinlbl;

    @FXML
    private ListView<String> addRecipelv;

    @FXML
    private Label currentDatelbl;


    public ViewHandler(FoodController foodController, LogController logController, ExerciseController exerciseController){
        this.foodController = foodController;
        this.logController = logController;
        this.exerciseController = exerciseController;

    }

    //public void getCollections

    public void initialize(){
        radioViewHandler();
        tabHandler();
        logController.initDate();

    }

    public void tabHandler() {

        removeExerciseTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(removeExerciseTab.isSelected()){
                    System.out.println("Remove Exercise");
                    deleteExercisecb.getItems().clear();
                    for(String item: logController.getExerciseNames()) {
                        deleteExercisecb.getItems().add(item);
                    }
                }
            }
        });
//        removeLogEntryTab.setOnSelectionChanged(new EventHandler<Event>() {
//            @Override
//            public void handle(Event event) {
//                if(removeLogEntryTab.isSelected()){
//                    System.out.println("Remove log entry");
//                    deleteLogEntrycb.getItems().clear();
//                    for(String item: logController.getLogOnDate()){
//                        deleteLogEntrycb.getItems().add(item);
//                    }
//                }
//            }
//        });
        deleteFoodItemTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(deleteFoodItemTab.isSelected()){

                    System.out.println("Delete Food Item");

                    deleteFoodItemcb.getItems().clear();
                    //deleteFoodItemcb.valueProperty().set(null);
                    for(String item: logController.getFoods()) {
                        deleteFoodItemcb.getItems().add(item);

                    }

                }
            }
        });
        changeDateTab.setOnSelectionChanged(new EventHandler<Event>(){
            public void handle(Event t){
                if(changeDateTab.isSelected()){
                    System.out.println("GET DATE OBJECT");
                    currentDatelbl.setText(logController.calToString());
                    //logController.changeDate(2019,10,2);
                    //System.out.println(logController.calToString());
                }
            }
        });
        logReportTab.setOnSelectionChanged(new EventHandler<Event>(){
            public void handle(Event t){
                if(logReportTab.isSelected()){
                    System.out.println("Show report");

                    XYChart.Series series = new XYChart.Series();
                    series.setName("Food Values");
                    ArrayList<String> arr = logController.getLogOnDate();

                    double[] foodBreakdown = logController.getDailyLog().getFoodBreakdown();

                    double totalGrams = foodBreakdown[1] + foodBreakdown[2] + foodBreakdown[3];
                    System.out.println(foodBreakdown);
                    calTotalLbl.setText(String.valueOf(foodBreakdown[0]));
                    totalCarbslbl.setText(String.valueOf(Math.round(foodBreakdown[1] / totalGrams * 100)) + "%");
                    totalFatlbl.setText(String.valueOf(Math.round(foodBreakdown[2] / totalGrams * 100)) + "%");
                    totalProteinlbl.setText(String.valueOf(Math.round(foodBreakdown[3] / totalGrams * 100)) + "%");
                    calsBurnedlbl.setText(String.valueOf(logController.getDailyLog().getTotalCaloriesBurned()));
                    currentWeightlbl.setText(String.valueOf(logController.getDailyLog().getCurrentWeight()));
                    calGoalLbl.setText(String.valueOf(logController.getDailyLog().getCurrentCalorieGoal()));
                    netCalslbl.setText(String.valueOf(logController.getDailyLog().getNetCalories()));
                    for(String item: arr){
                        System.out.println(item);
                    }
                    foodGraph.getData().clear();

                    series.getData().add(new XYChart.Data("Carbs", foodBreakdown[1]));
                    series.getData().add(new XYChart.Data("Fat", foodBreakdown[2]));
                    series.getData().add(new XYChart.Data("Protein", foodBreakdown[3]));
                    foodGraph.getData().add(series);

                }
            }
        });
    }


    public void radioViewHandler(){
        addFoodItemGrp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (addFoodrb.isArmed()) {
                    addFoodPane.setVisible(true);
                    addRecipePane.setVisible(false);
                } else if (addReciperb.isArmed()) {
                    addRecipelv.getItems().clear();
                    addFoodPane.setVisible(false);
                    addRecipePane.setVisible(true);
                    System.out.println("Delete Food Item");

                    addRecipecb.getItems().clear();
                    for (String item : logController.getFoods()) {
                        addRecipecb.getItems().add(item);
                    }
                }
            }
        });

        logItemgrp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if(logFoodrb.isArmed()){
                    logItemcb.setVisible(true);
                    logItemcb.getItems().clear();
                    logAmountlbl.setVisible(true);
                    logAmountlbl.setText("Servings");
                    logAmounttf.setVisible(true);
                    logItemcb.getItems().clear();
                    for(String item: logController.getFoods()) {
                        Map<String, FoodInterface> foodMap = foodController.getFoods();
                        if (!foodMap.get(item).isRetired()) {
                            logItemcb.getItems().add(item);
                        }
                    }
                }
                else if(logWeightrb.isArmed()){
                    logItemcb.setVisible(false);
                    logAmountlbl.setVisible(true);
                    logAmounttf.setVisible(true);
                    logAmountlbl.setText("Current Weight");

                }
                else if(logCalrb.isArmed()){
                    logItemcb.setVisible(false);
                    logAmountlbl.setVisible(true);
                    logAmountlbl.setText("Calorie Goal");
                    logAmounttf.setVisible(true);
                }
                else if(logExerciserb.isArmed()){
                    logItemcb.getItems().clear();
                    logItemcb.setVisible(true);
                    logAmountlbl.setVisible(true);
                    logAmountlbl.setText("Duration");
                    logAmounttf.setVisible(true);
                    for(String item: logController.getExerciseNames()){
                        logItemcb.getItems().add(item);
                    }
                }
            }
        });
    }




    @FXML
    void deleteFoodItem(ActionEvent event) {
        String keyword = deleteFoodItemcb.getValue();
        String output =foodController.deleteFood(keyword);
        System.out.println(output);
        deleteFoodItemcb.getItems().clear();
        for(String item: logController.getFoods()) {
            deleteFoodItemcb.getItems().add(item);

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText("Basic Food deleted!");
        alert.setContentText(keyword + " has been successfully deleted from the system.");
        ButtonType buttonOk = new ButtonType("Ok");
        alert.showAndWait().ifPresent((btnType) -> {

        });
    }

//    @FXML
//    void deleteLogEntry(ActionEvent event) {
//        String keyword = deleteLogEntrycb.getValue();
//        logController.removeFromLog(Integer.parseInt(keyword));
////        System.out.println(output);
//        deleteLogEntrycb.getItems().clear();
//        for(String item: logController.getLogOnDate()) {
//            deleteLogEntrycb.getItems().add(item);
//
//        }
//    }

    @FXML
    void saveWithoutExit(ActionEvent event) {
        foodController.saveFoods();
        logController.saveLog();
        exerciseController.saveExerciseCollection();
    }


    @FXML
    void exitProgram(ActionEvent event) {
        foodController.saveFoods();
        logController.saveLog();
        exerciseController.saveExerciseCollection();
        System.exit(0);
    }

    @FXML
    void exitWithoutSaving(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void setDate(ActionEvent event) {
        LocalDate date = dateSelected.getValue();
        logController.changeDate(date.getYear(),date.getMonthValue(),date.getDayOfMonth());
        currentDatelbl.setText(logController.calToString());
    }

    @FXML
    ListView addFoodToRecipebtn(ActionEvent event) {
        String amount;
        String recipeName;
        String currentFood;

        boolean validate = true;
        validate = validateTf(addRecipeNametf, "String");
        validate = validateTf(addRecipeAmounttf, "Double");

        if (validate) {
            recipeName = addRecipeNametf.getText();
            amount = addRecipeAmounttf.getText();

              currentFood =  addRecipecb.getValue();
              if(addRecipeAmounttf.getText().equals(amount)) {
                  addRecipelv.getItems().add(currentFood + "," + amount);
              }
            System.out.printf("Added food to recipe ingredients: %s, %s %n", currentFood, amount);
        }
        return  addRecipelv;

    }

    @FXML
    void deleteExercise(ActionEvent event) {
        String keyword = deleteExercisecb.getValue();
        exerciseController.deleteExercise(keyword);
        for(String item: logController.getExerciseNames()) {
            deleteExercisecb.getItems().add(item);

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText("Exercise has been removed!");
        alert.setContentText(keyword + " has been successfully deleted from the system.");
        ButtonType buttonOk = new ButtonType("Ok");
        alert.showAndWait().ifPresent((btnType) -> {

        });

    }

    @FXML
    void submitExercise(ActionEvent event) {
        String exerciseName;
        String caloriesBurned;


        boolean isString = validateTf(addExerciseNametf, "String");
        boolean isDouble = validateTf(addExerciseCaltf, "Double");

        if (isString && isDouble) {
            exerciseName = addExerciseNametf.getText();
            caloriesBurned = addExerciseCaltf.getText();

            exerciseController.addExercise(exerciseName, Double.parseDouble(caloriesBurned));

            addExerciseNametf.setText("");
            addExerciseCaltf.setText("");

            System.out.printf("Info: %s, %s, %n", exerciseName, caloriesBurned);
        }

    }

    @FXML
    void submitFood(ActionEvent event) {


        String fn = "";
        String fc;
        String ff;
        String fcarb;
        String fp;


        boolean isString = validateTf(addFoodNametf, "String");
        //fn.replace(","," ");
        boolean calIsDouble = validateTf(addFoodCaltf, "Double");
        boolean fatIsDouble = validateTf(addFoodFattf, "Double");
        boolean carbIsDouble = validateTf(addFoodCarbtf, "Double");
        boolean proteinIsDouble = validateTf(addFoodProteintf, "Double");

        boolean validate = isString && calIsDouble && fatIsDouble && carbIsDouble && proteinIsDouble;
        if(validate){
            fn = addFoodNametf.getText();
            fc = addFoodCaltf.getText();
            ff = addFoodFattf.getText();
            fcarb = addFoodCarbtf.getText();
            fp = addFoodProteintf.getText();

            foodController.addBasicFood(fn, fc, ff, fcarb, fp);

            addFoodNametf.setText("");
            addFoodCaltf.setText("");
            addFoodCarbtf.setText("");
            addFoodFattf.setText("");
            addFoodProteintf.setText("");
            System.out.printf("Info: %s, %s, %s, %s, %s %n", fn, fc, fcarb, ff, fp);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText("Basic food added!");
        alert.setContentText(fn + " has been successfully added to the system.");
        ButtonType buttonOk = new ButtonType("Ok");
        alert.showAndWait().ifPresent((btnType) -> {

        });

    }



    @FXML
    void submitLogEntry(ActionEvent event) {
        if(logFoodrb.isSelected()){
            if(validateTf(logAmounttf, "Double")){
                logItemcb.getValue();
                System.out.println("GetValue: "+logItemcb.getValue());
                logController.addLogEntry(logItemcb.getValue(), Double.parseDouble(logAmounttf.getText()));
            }
        }
        else if(logCalrb.isSelected()){
            if(validateTf(logAmounttf, "Double")){
                logController.logCalorieLimit(logAmounttf.getText());
                logAmounttf.setText("");
            }
        }
        else if(logWeightrb.isSelected()){
            if(validateTf(logAmounttf, "Double")){
                logController.logWeight(logAmounttf.getText());
                logAmounttf.setText("");
            }
        }
        else if(logExerciserb.isSelected()){
            logItemcb.getValue();
            System.out.println("GetValue: "+logItemcb.getValue());
            if(validateTf(logAmounttf, "Double")) {
                logController.logExercise(logItemcb.getValue(), Double.parseDouble(logAmounttf.getText()));
            }
        }
    }

    @FXML
    void submitRecipe(ActionEvent event) {
        String ingredients = "";
        List<String> foodList = addRecipelv.getItems();
        for(String food: foodList) {

            ingredients += food + ',';
        }
        if(ingredients.equals("")) {
            System.out.println("There are no ingredients in the recipe, PLEASE ADD A FOOD TO MAKE A RECIPE!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setHeaderText("No Ingredients In The Recipe!");
            alert.setContentText("Please add a food to make a recipe.");
            ButtonType buttonOk = new ButtonType("Ok");
            alert.showAndWait().ifPresent((btnType) -> {

            });
        }
        else if(addRecipeNametf.getText().equals("")){
            System.out.println("INVALID OPERATION! PLEASE ENTER A RECIPE NAME!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setHeaderText("Recipe Cannot Have Empty Name!");
            alert.setContentText("Please enter a recipe name.");
            ButtonType buttonOk = new ButtonType("Ok");
            alert.showAndWait().ifPresent((btnType) -> {

            });
        }
        else{
            ingredients = ingredients.substring(0, ingredients.length() - 1);
            foodController.addRecipe(addRecipeNametf.getText(),ingredients);
            System.out.println("Recipe: " + addRecipeNametf.getText() + "," + ingredients);
            System.out.println("Added " + addRecipeNametf.getText() + " recipe to system");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Recipe added!");
            alert.setContentText(addRecipeNametf.getText() + " has been successfully added as a recipe to the system.");
            ButtonType buttonOk = new ButtonType("Ok");
            alert.showAndWait().ifPresent((btnType) -> {

            });
            addRecipelv.getItems().clear();
            addRecipeAmounttf.setText("");
            addRecipeNametf.setText("");
        }



    }

    boolean validateTf(TextField tf, String type){

//        String output;
        boolean validate = true;

        if(tf.getText().equals("")){
            tf.setStyle("-fx-border-color: red");
            validate = false;
        }
        else if(type.equals("String")){
            tf.setStyle("-fx-border-color: inherit");
            tf.setText(tf.getText().replace(","," "));
        }
        else if(type.equals("Integer")){
            if(!tf.getText().matches("\\d*")){
                tf.setText("");
                tf.setStyle("-fx-border-color: red");
                validate = false;
            }
            else{
                tf.setStyle("-fx-border-color: inherit");
            }

        }
        else if(type.equals("Double")){
            if(tf.getText().matches("\\d*\\.\\d*") ){

                tf.setStyle("-fx-border-color: inherit");
            }
            else{
                tf.setText("");
                tf.setStyle("-fx-border-color: red");
                validate = false;
            }
        }

//        output = tf.getText();
//        output.replace(","," ");
//        return output;
        return validate;
    }




}
