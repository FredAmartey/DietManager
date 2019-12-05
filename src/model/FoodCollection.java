package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores a collection of food objects
 */
public class FoodCollection extends EntryCollection {

    /**
     * Stores the food objects. Key is the unique food name, value is the food object.
     */
    private HashMap<String, FoodInterface> foods;


    /**
     * Creates the food collection object
     * @param filename the file to use to store the collection locally when the program isn't running.
     */
    public FoodCollection(String filename) {
        super(filename);
        this.foods = new HashMap<>();
        loadFoodCollection();
    }


    /**
     * Loads the food collection from the locally stored file.
     */
    private void loadFoodCollection() {
        List<String[]> fileContents = super.getFileData();
        FoodFactory foodFactory = new FoodFactory();
        ArrayList<String[]> recipes = new ArrayList<>();
        for (String[] entry: fileContents) {
            if (entry.length > 1) { // don't read in blank csv lines
                if (entry[0].equals("b")) { // load a basic food
                    FoodInterface food = foodFactory.createBasicFood(entry[1], entry[2], entry[3], entry[4], entry[5]);
                    addFood(food);
                }
                else {
                    recipes.add(entry); // save recipes to be loaded after all basic foods have been created
                }
            }
        }
        for (String[] recipe: recipes) { // load a recipe
            Map<FoodInterface, Double> ingredients = new HashMap<>();
            int len = recipe.length;
            for (int i = 2; i < len; i+=2) {
                FoodInterface food = getFood(recipe[i]);
                if (food == null) { // the food has been deleted but a recipe still depends on it
                    food = foodFactory.createRetiredBasicFood(recipe[i]); // create a retired food
                }
                ingredients.put(food, Double.parseDouble(recipe[i+1]));
            }
            addFood(foodFactory.createRecipe(recipe[1], ingredients));
        }
    }


    /**
     * Adds or updates a food in the collection. Can be either basic food or a recipe.
     * @param basicFood the food
     */
    public void addFood(FoodInterface basicFood) {
        foods.put( basicFood.getName(), basicFood );
    }


    /**
     * Deletes a food from the collection.
     * @param name the unique name
     */
    public void deleteFood(String name) {
        foods.remove(name);
    }


    /**
     * Gets a food in the collection
     * @param name the name of the food to get
     * @return the food
     */
    public FoodInterface getFood(String name) {
        return foods.get( name );
    }


    /**
     * Gets the entire food collection
     * @return the collection Map object
     */
    public Map<String, FoodInterface> getCollection() {
        return foods;
    }


    /**
     * Saves the food collection to the file.
     */
    public void save() {
        super.saveFoodCollection(foods);
    }
}
