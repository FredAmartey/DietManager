package controller;

import model.*;

import java.util.*;

public class LogController {
    private Log log;
    private FoodController foodController;
    private ExerciseController ec;
    private DailyLog dailyLog;

    public LogController(FoodCollection f, Log l, ExerciseController e) {
        this.log = l;
        this.foodController = new FoodController( f );
        this.ec = e;

        this.dailyLog = new DailyLog(log);
    }

    public void saveLog(){
        log.save();
    }

    public DailyLog getDailyLog() {
        return dailyLog;
    }

    public void changeDate(int year, int month, int day){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, (month-1));
        c.set(Calendar.DAY_OF_MONTH, day);
        log.changeDate(c);
    }
    public void initDate(){
        Calendar c = Calendar.getInstance();
        c.get(Calendar.YEAR);
        System.out.printf("%s, %s, %s, %n", c.get(Calendar.YEAR), (c.get(Calendar.MONTH)+1), c.get(Calendar.DAY_OF_MONTH));
        //System.out.println(date);
        changeDate( c.get(Calendar.YEAR), (c.get(Calendar.MONTH)+1), c.get(Calendar.DAY_OF_MONTH) );
        System.out.println("INSIDE initDate");
    }

    public String calToString(){

        int month = log.getDate().get(Calendar.MONTH) + 1;
        String result = ""+log.getDate().get(Calendar.YEAR)+"-"+month+"-"+log.getDate().get(Calendar.DAY_OF_MONTH);
        return result;
    }



    // Converts the FoodComponent to a string so the log can use it
    public void updateLog(LoggableItem entry, String action) {

    }
    //Adds an item to the log


    public ArrayList<String> getLogOnDate() {
        TreeMap<Integer, LogEntry> dailyLog = log.getEntriesByDate(log.getDate());
        ArrayList<String> entries = new ArrayList<>();
        if (dailyLog == null) { // if no log entries are in there for the day
            return entries;
        }
        dailyLog.forEach((id, entry) -> {
            if (entry.getAmount() > 0) {
                entries.add(String.valueOf(entry.getDataType()) + ": " + entry.getValue() + ": " + String.valueOf(entry.getAmount()));
            }
            else {
                entries.add(String.valueOf(entry.getDataType()) + ": " + entry.getValue());
            }
        });
        return entries;
    }

    public void addLogEntry(String lFood, double lServings){


        Map<String, FoodInterface> foodMap = foodController.getFoods();

        if(foodMap.containsKey(lFood)){


            if(foodMap.get(lFood) instanceof BasicFood){
                BasicFood loggingFood = (BasicFood)foodMap.get(lFood);
                loggingFood.addToLog(log, lServings );
            }
            else if(foodMap.get(lFood) instanceof Recipe){
                Recipe loggingRecipe = (Recipe)foodMap.get(lFood);
                loggingRecipe.addToLog(log, lServings);
            }

            System.out.println("Added food to log");
        }
        else{
            System.out.println("Enter a food from the list...");
        }
    }

    //Removes item entry from the log
    public void removeFromLog(int UID){

    }

    // receives and exception from the view and handles it
    public void processLogException(Exception e) {

    }
    // receives an update of the viewDate and notifies the model
    public void changeViewDate(String date) {

    }
    // set a new weight or calorie goal and calls updateLog after converting
    // the value to conform to the LoggableItem standards
    public void setNewBenchmark(int benchmark) {

    }

    public List<String> getFoods(){
        Map<String, FoodInterface> foodInt = foodController.getFoods();
        ArrayList<FoodInterface> foodList = new ArrayList<>(((Map) foodInt).values());
        List<String> fName = new ArrayList<>();

        for(FoodInterface f : foodList){
             fName.add(f.getName());
        }
        return fName;
    }

    public String logFood(){
        return "";
    }

    public String logWeight(String weight){
         Weight w = new Weight(Double.parseDouble(weight));
        if( Double.parseDouble(weight) <= 0){
            return "Please enter a weight greater than zero";
        }
        w.addToLog(log, -1);
        return "";
    }

    public String logCalorieLimit(String calorie){
        CalorieTarget c = new CalorieTarget(Double.parseDouble(calorie));
        if( Double.parseDouble(calorie) <= 0){
            return "Please enter a weight greater than zero";
        }
        c.addToLog(log, -1);
        return "";
    }

    public List<String> getExerciseNames() {
        Map<String, Exercise> exercises = ec.getExercises();
        ArrayList<Exercise> exerciseList = new ArrayList<>(((Map) exercises).values());
        List<String> eName = new ArrayList<>();

        for (Exercise e : exerciseList) {
            eName.add(e.getValue());
        }
        return eName;

    }

    public void logExercise(String lExercise, double duration){
        Map<String, Exercise> exerciseMap = ec.getExercises();

        if(exerciseMap.containsKey(lExercise)){
            Exercise exerciseObj = exerciseMap.get(lExercise);
            exerciseObj.addToLog(log, duration);
            System.out.println("Add exercise to log");
        }
        else{
            System.out.println("Error adding exercise");
        }
    }

}
