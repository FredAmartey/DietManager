package model;

public class Weight implements LoggableItem {

    private double weightMeasurement;

    public Weight(double weight) {
        this.weightMeasurement = weight;
    }

    @Override
    public void addToLog(Log log, double amount) {
        log.addEntry(this, amount);
    }

    @Override
    public String getValue() {
        return String.valueOf(weightMeasurement);
    }

    @Override
    public char getEntryType() {
        return 'w';
    }
}
