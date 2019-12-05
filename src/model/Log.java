package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Log extends EntryCollection {

    // the data structure that will hold the log entries
    // Key is a date string, value is a log of that day of type TreeMap. Key is the unique id and value is the entry obj
    private TreeMap<String, TreeMap<Integer, LogEntry>> log;
    private Date currentDate;


    /**
     * Create a log object to being logging entries in the system's model.
     */
    public Log(String filename, FoodCollection fc, ExerciseCollection ec, FoodFactory ff) {
        super(filename);
        this.log = new TreeMap<>(Collections.reverseOrder()); // reverse order so the most recent day is first
        this.currentDate = Calendar.getInstance().getTime();
        loadLogFromFile(fc, ec, ff);
    }


    public TreeMap<String, TreeMap<Integer, LogEntry>> getLog() {
        return this.log;
    }


    /**
     * Loads log contents from the csv file.
     */
    private void loadLogFromFile(FoodCollection fc, ExerciseCollection ec, FoodFactory ff) {
        // get raw file data
        List<String[]> fileContents = super.getFileData();
        // create the factory to create log entries
        LoggableItemFactory loggableItemFactory = new LoggableItemFactory(fc, ec, ff);

        for (String[] entry: fileContents) {
            if (entry.length > 1) {
                String year = entry[0];
                String month = entry[1];
                String day = entry[2];

                Calendar calendar = Calendar.getInstance();
                calendar.clear();

                calendar.set(Calendar.YEAR, Integer.parseInt(year));
                calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
                calendar.set(Calendar.DATE, Integer.parseInt(day));
                Date date = calendar.getTime();
                if (entry.length == 6) { // is a food with an amount
                    LoggableItem loggableItem = loggableItemFactory.createLoggableItem(entry[3], entry[4]);
                    addEntry(loggableItem, Double.parseDouble(entry[5]), date);
                }
                else if (entry.length == 5) { // is any other type of entry
                    LoggableItem loggableItem = loggableItemFactory.createLoggableItem(entry[3], entry[4]);
                    addEntry(loggableItem, -1.0, date);
                }
            }
        }
    }


    /**
    *   Gets all entry/entries from the given date
    *   @param calendar the date to get log entries from
    *   @return an editable collection object for logHistory
    */
    public TreeMap<Integer, LogEntry> getEntriesByDate(Calendar calendar) {
        String date = dateToString(calendar.getTime());
        return log.get(date);
    }


    /**
     *
     * @param date the date to convert to a string
     * @return the string date formatted in yyyy-MM-dd
     */
    private String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }


    /**
     *  adds a new entry to the model's log and assigns it a unique identifier
     *  @param entry the weight, food, or caloric goal to add to the log
     */
    void addEntry(LoggableItem entry, double amount) {
        String date = dateToString(this.currentDate);
        if (log.get(date) == null){ // nothing has been logged on this date
            TreeMap<Integer, LogEntry> dailyEntries = new TreeMap<>();
            dailyEntries.put(0, new LogEntry(0, this.currentDate, entry, amount));
            log.put(date, dailyEntries);
        }
        else { // there is already something logged on this date, add to this daily log
            int id = log.get(date).lastKey() + 1;
            log.get(date).put(id, new LogEntry(id, this.currentDate, entry, amount));
        }
    }

    /**
     *  adds a new entry to the model's log and assigns it a unique identifier
     *  @param entry the weight, food, or caloric goal to add to the log
     *  @param date the date to specifically use
     */
    private void addEntry(LoggableItem entry, double amount, Date date) {
        String stringDate = dateToString(date);
        if (log.get(stringDate) == null){ // nothing has been logged on this date
            TreeMap<Integer, LogEntry> dailyEntries = new TreeMap<>();
            dailyEntries.put(0, new LogEntry(0, date, entry, amount));
            log.put(stringDate, dailyEntries);
        }
        else { // there is already something logged on this date, add to this daily log
            int id = log.get(stringDate).lastKey() + 1;
            log.get(stringDate).put(id, new LogEntry(id, date, entry, amount));
        }
    }


    /**
     * Removes an entry from the log.
     * @param entry the entry to remove
     * @return boolean if the remove succeeded or not
     */
    public boolean removeEntry(LogEntry entry) {
        return log.get(dateToString(this.getDate().getTime())).remove(entry.getId()) != null;
    }


    /**
     * Gets a list of entries of a given type.
     * @param entryType the type of entry (r, b, c, e, w)
     * @param date the date of the entries to look through
     * @return a list of LogEntry objects
     */
    ArrayList<LogEntry> getEntriesByType(char entryType, Calendar date) {
        TreeMap<Integer, LogEntry> dailyLog = getEntriesByDate(date);
        ArrayList<LogEntry> entryList = new ArrayList<>();
        if (dailyLog == null) {
            return null;
        }
        dailyLog.forEach((id, entry) -> {
            if (entry.getDataType() == entryType) {
                entryList.add(entry);
            }
        });
        return entryList;
    }


    /**
     * Changes the working date of the model (log).
     * @param date the date as a calendar object
     */
    public void changeDate(Calendar date) {
        this.currentDate = date.getTime();
    }


    /**
     * Gets the date that the model is set to.
     * @return A date formatted in a Calendar object
     */
    public Calendar getDate() {
        System.out.println(this.currentDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.currentDate);
        return calendar;
    }

    /**
     * Saves all changes that were made to the model's log to the data file.
     * Uses the super class method to do this.
     */
    public void save() {
        super.saveLogContents(log);
    }
}
