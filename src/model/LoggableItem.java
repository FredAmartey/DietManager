package model;

public interface LoggableItem {
    /**
     * Enforces any object that should be logged have the ability to add itself to the log.
     * @param log the log to add the entry to
     * @param amount the OPTIONAL amount -- used for basic foods only. Otherwise put null
     */
    void addToLog(Log log, double amount);


    /**
     * @return a string of the value of the item being logged (ex. a weight, calgoal, foodName..)
     */
    String getValue();


    /**
     * @return the entry type discriminator -- w (weight), b (basic food), r (recipe), c (calorie goal)
     */
    char getEntryType();
}
