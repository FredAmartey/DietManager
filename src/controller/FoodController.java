package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class FoodController {
    private FoodCollection foodCollection;
    private FoodFactory foodFactory;

    public FoodController(FoodCollection f) {
        this.foodCollection = f;
        this.foodFactory = new FoodFactory();
    }

    public void saveFoods(){
        foodCollection.save();
    }

    // takes a food and an action and tells the model library what change to do
    public void modifyFoodLibrary(FoodInterface food, String action) {

    }

    // receives and exception from the view and handles it
    public void processFoodException(Exception e) {

    }
    //add a basic food to the foodCollection
    public String addBasicFood(String fName, String fCal, String fCarbs, String fProtein, String fFat){
        try {
            FoodInterface newFood = foodFactory.createBasicFood(fName, fCal, fCarbs, fProtein, fFat);
            foodCollection.addFood(newFood);
            return "";
        }
        catch(NumberFormatException nfe){
            return "";
        }
    }

    public FoodInterface getSpecificFood(String name) {
        FoodInterface specificFood = foodCollection.getFood(name);

        return specificFood;
    }

    //add a recipe to the foodCollection
    public String addRecipe(String rName, String ingredients ){
        String[] listOfIngredients = ingredients.split(",");
        Map<FoodInterface, Double> ingredientsServing = new HashMap<>();
        for (int i = 0; i < listOfIngredients.length; i += 2) {
            try {
                if (foodCollection.getFood(listOfIngredients[i]) == null) {
                    return listOfIngredients[i] + " is not a valid foood. Please add it to the collection by creating it first.";
                }

                // Add to map
                ingredientsServing.put(foodCollection.getFood(listOfIngredients[i]), Double.parseDouble(listOfIngredients[i + 1])
                );

            }
            catch(ArrayIndexOutOfBoundsException e){
                return "Please enter as information as: \"f1name,f1count,f2name, f2count,...,fNname,fNcount\"";
            }
        }

        FoodInterface newRecipe = foodFactory.createRecipe(rName, ingredientsServing);
        foodCollection.addFood(newRecipe);
        return "";
    }

    public Map<String, FoodInterface> getFoods(){
        Map<String, FoodInterface> foodHash = foodCollection.getCollection();
        return foodHash;
    }

    public String deleteFood(String keyword){
        try{
            foodCollection.deleteFood(keyword);
            return "Successfully deleted "+ keyword;
        }catch(Exception e){
            return "Something went wrong";
        }
    }



}
