package model;

public class CalorieTarget implements LoggableItem {

    private double calTarget;

    public CalorieTarget(double calTarget) {
        this.calTarget = calTarget;
    }

    @Override
    public void addToLog(Log log, double amount) {
        log.addEntry(this, amount);
    }

    @Override
    public String getValue() {
        return String.valueOf(calTarget);
    }

    @Override
    public char getEntryType() {
        return 'c';
    }
}
