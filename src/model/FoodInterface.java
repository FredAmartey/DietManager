package model;

/**
 * Provides methods that all food objects should implement. Extends LoggableItem since all food objects should be able
 * to be logged.
 */
public interface FoodInterface extends LoggableItem {

        /**
         * Gets the name of the food object
         * @return a string name
         */
        String getName();


        /**
         * Gets the calories in the food.
         * @return double amount (grams)
         */
        double getCalories();


        /**
         * Gets the carb count in the food.
         * @return double amount (grams)
         */
        double getCarbs();


        /**
         * Gets the protein count in the food.
         * @return double amount (grams)
         */
        double getProtein();


        /**
         * Gets the fat count in the food.
         * @return double amount (grams)
         */
        double getFat();


        /**
         * Gets whether or not the food has been retired.
         * @return boolean
         */
        boolean isRetired();

}
