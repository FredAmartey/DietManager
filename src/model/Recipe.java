package model;

import java.util.*;

public class Recipe implements FoodInterface {

    private boolean isRetired; // True if object can be logged, false if food was deleted from food collection but
                                // recreated from log. Or if ingredient was deleted then the recipe will be retired.

    /**
     * Name of the recipe
     */
    private String rName;


    /**
     * The collection of ingredients that make up the recipe.
     * Key: The food object
     * Value: The amount (double)
     */
    private Map<FoodInterface, Double> ingredients;


    /**
     * Creates the recipe object
     * @param rName the recipe name
     * @param ingredients the ingredients
     */
    public Recipe(String rName, Map<FoodInterface, Double> ingredients) {
        this.rName = rName;
        this.ingredients = ingredients;
        this.isRetired = false;
        ingredients.forEach((food, amount) -> {
            if (food.isRetired()) {
                this.isRetired = true;
            }
        });
    }


    /**
     * Adds an ingredient to the recipe.
     * @param ingredient the ingredient to add
     * @param amount the amount of that ingredient
     */
    public void addIngredient(FoodInterface ingredient, Double amount) {
        ingredients.put(ingredient, amount);
    }


    /**
     * Get the collection of ingredients for this recipe.
     * @return the map collection
     */
    public Map<FoodInterface, Double> getIngredients() {
        return this.ingredients;
    }


    /**
     * Removes an ingredient from the recipe
     * @param ingredient the ingredient to remove
     */
    public void removeIngredient(FoodInterface ingredient) {
        ingredients.remove(ingredient);
    }

    @Override
    public String getName() {
        return rName;
    }

    @Override
    public double getCalories() {
        double calories = 0;
        for (Map.Entry<FoodInterface, Double> ingredient : ingredients.entrySet()) {
            calories += (ingredient.getKey().getCalories() * ingredient.getValue());
        }
        return calories;
    }

    @Override
    public double getCarbs() {
        double carbs = 0;
        for (Map.Entry<FoodInterface, Double> ingredient : ingredients.entrySet()) {
            carbs += (ingredient.getKey().getCarbs() * ingredient.getValue());
        }
        return carbs;
    }

    @Override
    public double getProtein() {
        double protein = 0;
        for (Map.Entry<FoodInterface, Double> ingredient : ingredients.entrySet()) {
            protein += (ingredient.getKey().getProtein() * ingredient.getValue());
        }
        return protein;
    }

    @Override
    public double getFat() {
        double fat = 0;
        for (Map.Entry<FoodInterface, Double> ingredient : ingredients.entrySet()) {
            fat += (ingredient.getKey().getFat() * ingredient.getValue());
        }
        return fat;
    }

    @Override
    public boolean isRetired() {
        return isRetired;
    }

    @Override
    public void addToLog(Log log, double amount) {
        log.addEntry(this, amount);
    }

    @Override
    public String getValue() {
        return getName();
    }

    @Override
    public char getEntryType() {
        return 'r';
    }


}
