package model;

import java.util.*;

public class FoodFactory {

    public FoodInterface createBasicFood(String name, String cals, String carbs, String protein, String fat) {
        double bfCalories = Double.parseDouble( cals );
        double bfCarbs = Double.parseDouble( carbs );
        double bfProtein = Double.parseDouble( protein );
        double bfFat = Double.parseDouble(fat);

        return new BasicFood(name, bfCalories, bfCarbs, bfProtein, bfFat, false);
    }

    public FoodInterface createRetiredBasicFood(String name) {
        double bfCalories = 0;
        double bfCarbs = 0;
        double bfProtein = 0;
        double bfFat = 0;

        return new BasicFood(name, bfCalories, bfCarbs, bfProtein, bfFat, true);
    }

    /**
     * Create a Recipe
     */
    public FoodInterface createRecipe(String name, Map<FoodInterface, Double> ingredients) {
        return new Recipe( name, ingredients );
    }
}
