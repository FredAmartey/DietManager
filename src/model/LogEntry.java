package model;

import java.util.Date;

/**
 * This class defines the objects that the log will hold.
 */
public class LogEntry {
    private Date date;          // the date of the entry
    private Double amount;      // the amount of the entry (servings of a food)
    private int id;             // the unique id of the entry (to discern entries with the same value)
    private LoggableItem entry; // the entries' loggable item (food, recipe, weight, etc.)

    /**
     * The LogEntry constructor
     * @param id unique id, by day
     * @param date the date of the new entry
     * @param entry the entry value to log such as a food or weight
     * @param amount the amount, if necessary. Otherwise -1
     */
    public LogEntry(int id, Date date, LoggableItem entry, double amount) {
        this.id = id;
        this.date = date;
        this.entry = entry;
        this.amount = amount;
    }


    /**
     * Gets the date
     * @return the date of the entry
     */
    public Date getDate() {
        return this.date;
    }


    /**
     * @return the data type of the entry (r, b, w, e, c)
     */
    public char getDataType() {
        return this.entry.getEntryType();
    }


    /**
     * @return the value of the entry (name of food or the measurement)
     */
    public String getValue() {
        return this.entry.getValue();
    }


    /**
     * @return gets the amount
     */
    public Double getAmount() {
        return this.amount;
    }


    /**
     * @return gets the unique ID
     */
    public int getId() {
        return this.id;
    }


    /**
     * @return the item that was entered into the log
     */
    public LoggableItem getEntry() {
        return this.entry;
    }


    /**
     * Sets the new amount of the entry
     * @param newAmount the new amount to change
     */
    public void setNewAmount(Double newAmount) {
        this.amount = newAmount;
    }
}
