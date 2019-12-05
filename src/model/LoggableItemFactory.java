package model;

class LoggableItemFactory {

    private FoodCollection foodCollection;
    private ExerciseCollection exerciseCollection;
    private FoodFactory foodFactory;

    LoggableItemFactory(FoodCollection fc, ExerciseCollection ec, FoodFactory foodFactory) {
        this.foodCollection = fc;
        this.exerciseCollection = ec;
        this.foodFactory = foodFactory;
    }

    LoggableItem createLoggableItem(String type, String value) {
        switch (type) {
            case "f":
                if (foodCollection.getFood(value) != null) {
                    return foodCollection.getFood(value);
                }
                else { // food was deleted from food collection but existed in the log
                    return foodFactory.createRetiredBasicFood(value);
                }
            case "w":
                return new Weight(Double.parseDouble(value));
            case "c":
                return new CalorieTarget(Double.parseDouble(value));
            case "e":
                if (exerciseCollection.getExercise(value) != null) {
                    return exerciseCollection.getExercise(value);
                }
                else { // exercise was deleted from collection but existed in log
                    return new Exercise(value);
                }
        }
        return null;
    }
}
