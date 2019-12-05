package model;

public class BasicFood implements FoodInterface {

    private String name;
    private double calories;
    private double carbs;
    private double protein;
    private double fat;
    private boolean isRetired; // True if object can be logged, false if food was deleted from food collection but
                                // recreated from log.


    /**
     * Creates a basic food object
     * @param name the name of the food
     * @param calories the calorie count of the food
     * @param carbs the amount of carbs in grams
     * @param protein the amount of protein in grams
     * @param fat the amount of fat in grams
     */
    BasicFood(String name, double calories, double carbs, double protein, double fat, boolean isRetired) {
        this.name = name;
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.isRetired = isRetired;
    }


    /**
     * Sets the calorie content of the food
     * @param calories the calorie count
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }


    /**
     * Sets the carbs content of the food
     * @param carbs the carbs count in grams
     */
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }


    /**
     * Sets the protein content of the food
     * @param protein the fat count in grams
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }


    /**
     * Sets the fat content of the food
     * @param fat the fat count in grams
     */
    public void setFat(double fat) {
        this.fat = fat;
    }


    /**
     * Returns the unique name of the food
     * @return string food name
     */
    @Override
    public String getName() {
        return name;
    }


    /**
     * Gets the type of food. 'b' is for basic food
     * @return a char
     */
    @Override
    public char getEntryType() {
        return 'b';
    }


    /**
     * Gets the calorie count
     * @return a double
     */
    @Override
    public double getCalories() {
        return calories;
    }


    /**
     * Gets the carbs count in grams
     * @return a double
     */
    @Override
    public double getCarbs() {
        return carbs;
    }


    /**
     * Gets the protein count in grams
     * @return a double
     */
    @Override
    public double getProtein() {
        return protein;
    }


    /**
     * Gets the fat count in grams
     * @return a double
     */
    @Override
    public double getFat() {
        return fat;
    }

    /**
     * Calls the specified log's addEntry method
     * @param log the log to add this to
     * @param amount the amount (servings)
     */
    @Override
    public void addToLog(Log log, double amount) {
        log.addEntry(this, amount);
    }


    /**
     * Returns the unique name of the food
     * @return string food name
     */
    @Override
    public String getValue() {
        return getName();
    }


    /**
     * Checks to see if this object is retired or not.
     * @return boolean
     */
    @Override
    public boolean isRetired() {
        return isRetired;
    }

}
