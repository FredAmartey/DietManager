package model;

public class Exercise implements LoggableItem {

    private String name; // the unique name of the exercise
    private double caloricBurnRate; // the amount of calories burned per hour per 100lbs of body weight
    private boolean isRetired;


    public Exercise(String name, double caloricBurnRate) {
        this.name = name;
        this.caloricBurnRate = caloricBurnRate;
        this.isRetired = false;
    }


    public Exercise(String name) {
        this.name = name;
        this.isRetired = true;
    }


    @Override
    public void addToLog(Log log, double duration) {
        log.addEntry(this, duration);
    }


    @Override
    public String getValue() {
        return this.name;
    }


    @Override
    public char getEntryType() {
        return 'e';
    }


    /**
     * @return the caloric burn rate of this exercise to be used for calculations
     */
    public double getCaloricBurnRate() {
        return this.caloricBurnRate;
    }


    /**
     * Updates the caloric burn rate to a new value
     * @param newRate the new rate to update this exercise with
     */
    public void setCaloricBurnRate(double newRate) {
        this.caloricBurnRate = newRate;
    }


    /**
     * Checks whether this object is retired.
     * @return boolean
     */
    public boolean isRetired() {
        return isRetired;
    }
}
