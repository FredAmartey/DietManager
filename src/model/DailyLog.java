package model;


import java.util.*;

/**
 * Provide a summary of data for the logged entries from the selected day of the log.
 */
public class DailyLog {

    private Log log;

    public DailyLog(Log log) {
        this.log = log;
    }


    /**
     * Gets the breakdown of the foods logged for the day in totals of calories, and grams of carbs/fats/protein.
     * @return a double array of the calories, carbs, fat, and protein
     */
    public double[] getFoodBreakdown() {
        double calories = 0;
        double carbs = 0;
        double fat = 0;
        double protein = 0;

        ArrayList<LogEntry> foods = log.getEntriesByType('b', this.log.getDate());
        if (log.getEntriesByType('r', this.log.getDate()) != null) {
            foods.addAll(log.getEntriesByType('r', this.log.getDate()));
        }
        if (foods != null) {
            for (LogEntry food: foods) {
                FoodInterface aFood = (FoodInterface) food.getEntry();
                calories += aFood.getCalories();
                carbs += aFood.getCarbs();
                fat += aFood.getCarbs();
                protein += aFood.getProtein();
            }
        }

        return new double[]{calories, carbs, fat, protein};
    }


    public double getCurrentWeight() {
        Set<String> set = log.getLog().keySet();
        for (String dateString : set) {
            TreeMap<Integer, LogEntry> dayEntries = log.getLog().get(dateString);
            Set<Integer> dailyEntrySet = dayEntries.keySet();
            List<Integer> list = new ArrayList(dailyEntrySet);
            Collections.sort(list, Collections.reverseOrder());
            for (Integer id : list) {
                LogEntry entry = dayEntries.get(id);
                if (entry.getDataType() == 'w') {
                    return Double.parseDouble(entry.getValue());
                }
            }
        }
        return 150.0;
    }


    public double getCurrentCalorieGoal() {
        Set<String> set = log.getLog().keySet();
        for (String dateString : set) {
            TreeMap<Integer, LogEntry> dayEntries = log.getLog().get(dateString);
            Set<Integer> dailyEntrySet = dayEntries.keySet();
            List<Integer> list = new ArrayList(dailyEntrySet);
            Collections.sort(list, Collections.reverseOrder());
            for (Integer id : list) {
                LogEntry entry = dayEntries.get(id);
                if (entry.getDataType() == 'c') {
                    return Double.parseDouble(entry.getValue());
                }
            }
        }
        return 2000.0;
    }


    public ArrayList<LogEntry> getLoggedFoods() {
        ArrayList<LogEntry> foodEntries = log.getEntriesByType('b', log.getDate());
        if (log.getEntriesByType('r', log.getDate()) != null) {
            foodEntries.addAll(log.getEntriesByType('r', log.getDate()));
        }
        return foodEntries;
    }


    /**
     * Gets a list of exercise entries for the selected date in the log then calculates the calories burned.
     * @return String 2D array [exercise index][exercise name, duration, calories burned]
     */
    public String[][] getExerciseSummary() {
        ArrayList<LogEntry> exerciseEntries = log.getEntriesByType('e', log.getDate());
        String[][] exerciseList = new String[exerciseEntries.size()][];
        int i = 0;
        for (LogEntry exercise : exerciseEntries) {
            if (exercise.getEntry() instanceof Exercise) {
                exerciseList[i][0] = exercise.getValue();
                exerciseList[i][1] = String.valueOf(exercise.getAmount());
                double caloriesBurned = calculateCaloriesBurned((Exercise) exercise.getEntry(), exercise.getAmount());
                exerciseList[i][2] = String.valueOf(caloriesBurned);
                i++;
            }
        }
        return exerciseList;
    }


    private boolean dayHasLoggedFoods() {
        return (getLoggedFoods() != null);
    }


    public double getTotalCaloriesBurned() {
        if (dayHasLoggedFoods()) {
            ArrayList<LogEntry> exerciseEntries = log.getEntriesByType('e', log.getDate());
            double caloriesBurned = 0;
            for (LogEntry exercise : exerciseEntries) {
                if (exercise.getEntry() instanceof Exercise) {
                    caloriesBurned += calculateCaloriesBurned((Exercise) exercise.getEntry(), exercise.getAmount());
                }
            }
            return caloriesBurned;
        }
        return 0;
    }


    public double getNetCalories() {
        return getFoodBreakdown()[0] - getTotalCaloriesBurned();
    }


    private double calculateCaloriesBurned(Exercise exercise, double duration) {
        double calBurnRate = exercise.getCaloricBurnRate();
        double weight = getCurrentWeight();
        double caloriesBurned = Math.round(calBurnRate * (weight / 100) * (duration / 60));
        return caloriesBurned;
    }


}
