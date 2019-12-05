package view;

import model.*;
import controller.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * view.CLI for DietManager.
 * @author TWNN
 */

public class CLI {
    private FoodCollection foodCollection;
    private ExerciseCollection exerciseCollection;
    private Log log;
    private FoodController foodController;
    private ExerciseController exerciseController;
    private LogController logController;



    public CLI(FoodCollection foodC, Log log, ExerciseCollection exerciseC) {
        this.foodCollection = foodC;
        this.exerciseCollection = exerciseC;
        this.log = log;
        
        this.foodController = new FoodController(foodCollection);
        this.exerciseController = new ExerciseController(exerciseCollection);
        this.logController = new LogController(foodCollection, log, exerciseController);

    }

    public  void readAvailableFoods(){
        System.out.println("Foods currently in system:");
        ArrayList<String> foods = (ArrayList<String>)logController.getFoods();
        for(String fName : foods){
            System.out.println("\t" + fName);
        }
    }

    public void run() {
        while (true) {
            System.out.println("\nCurrent date: " + logController.calToString());
            System.out.println("Please select an option:");
            System.out.println("1) Create a food");
            System.out.println("2) Delete a food");
            System.out.println("3) Create a recipe");
            System.out.println("4) Log a food");
            System.out.println("5) Log a calorie limit");
            System.out.println("6) Log a weight");
            System.out.println("7) View all logged entries");
            System.out.println("8) Read all foods");
            System.out.println("9) Date Options...");
            System.out.println("10) Save");
            System.out.println("11) Quit");


            Scanner scanner = new Scanner(System.in);
            int selection; // user selection
            try {
                selection = Integer.parseInt(scanner.nextLine().trim());
            }
            catch (NumberFormatException nfe) {
                System.out.println("Invalid selection. Try again...\n");
                continue;
            }

            // Create food
            if(selection == 1) {
                // Implement create food
                while(true){
                    System.out.print("Enter food name:");
                    String fName = scanner.nextLine().trim();

                    System.out.print("Enter food calories:");
                    String fCal = scanner.nextLine().trim();

                    System.out.print("Enter food Carbs:");
                    String fCarbs = scanner.nextLine().trim();

                    System.out.print("Enter food Protein:");
                    String fProtein = scanner.nextLine().trim();

                    System.out.print("Enter food Fat:");
                    String fFat = scanner.nextLine().trim();


                    foodController.addBasicFood(fName, fCal, fCarbs, fProtein, fFat);
                    System.out.print("Successfully added " + fName + "\n");
                    break;
                }
            }

            
            // Delete food
            else if(selection == 2) {
               // Implement delete food
                while(true){
                    System.out.print("Enter food name:");
                    String fName = scanner.nextLine().trim();

                    System.out.println(foodController.deleteFood(fName));
                    break;
                }
            }


            // Create a recipe
            else if(selection == 3) {
                // Implement create a recipe

                    String ingredients = "";
                    System.out.print("Here are all the foods you can create a recipe from: \n");
                    readAvailableFoods();

                    System.out.print("Enter recipe name:");
                    String rName = scanner.nextLine().trim();

                    while (true) {
                        System.out.print("Would you like to add another food to the recipe?: ('Y' or 'N')");
                        String userInput = scanner.nextLine().trim();
                        if (userInput.equals("Y") || userInput.equals("y")) {
                            while (true) {
                                System.out.print("Enter food name:");
                                String fName = scanner.nextLine().trim();
                                if (!fName.equals("")) {
                                    ingredients += fName + ",";
                                    break;
                                } else {
                                    System.out.println("ERROR! PLEASE ENTER VALID FOOD!");
                                }
                            }

                            while (true) {
                                System.out.print("How many servings?:");
                                String servings = scanner.nextLine().trim();
                                if (!servings.equals("")) {
                                    ingredients += servings + ",";
                                    break;
                                } else {
                                    System.out.println("ERROR! PLEASE ENTER VALID SERVINGS!");
                                }
                            }
                        }

                        else if (userInput.equals("N") || userInput.equals("n")) {
                            ingredients = ingredients.substring(0, ingredients.length() - 1);
                            foodController.addRecipe(rName, ingredients);
                            System.out.println("Successfully created " + rName + " recipe with the following ingredients: " + ingredients);
                            break;
                        } else {
                            System.out.println("ERROR! PLEASE ENTER VALID RESPONSE!");
                        }

                    }

//                foodController.addRecipe(rName, ingredients);

            }

            // Log food
            else if(selection == 4) {
                // Implement log a food
                

                readAvailableFoods();

                System.out.println("Pick a food from the list:");
                String lFood = scanner.nextLine().trim();

                System.out.println("How many servings?");

                try {

                    double lServings = Double.parseDouble(scanner.nextLine().trim());
                    logController.addLogEntry(lFood, lServings);

                }catch(NumberFormatException e){
                    System.out.println("PLease input your amount in decimal format '0.0'");
                }



            }

            // Log calorie limit
            else if(selection == 5) {
                // Implement log calorie limit

                System.out.print("Enter Calorie Limit: ");
                String calories = scanner.nextLine().trim();
                    logController.logCalorieLimit(calories);
                    System.out.println("Successfully logged calorie limit.");



            }

            // Log a weight
            else if(selection == 6) {
                // Implement log weight

                System.out.print("Enter a weight: ");
                String weight = scanner.nextLine().trim();
                    logController.logWeight(weight);
                    System.out.println("Successfully logged calorie limit.");
            }
            

            // View logged foods from a given date
            else if(selection == 7) {
                // Implement view logged foods
                for (String entry: logController.getLogOnDate()) {
                    System.out.println(entry);
                };
            }

            // Read all foods
            else if(selection == 8) {
                //Implement read all foods
                readAvailableFoods();
            }
            //Set the date
            else if(selection == 9){
                System.out.println("Change the date of the day? (Y or N)");
                String input = scanner.nextLine().trim();
                if(input.equals("Y") || input.equals("y")){
                    try {
                        System.out.println("Enter the year(yyyy):");
                        int year = Integer.parseInt(scanner.nextLine().trim());

                        System.out.println("Enter the month(mm):");
                        int month = Integer.parseInt(scanner.nextLine().trim()) - 1;

                        System.out.println("Enter the day(dd):");
                        int day = Integer.parseInt(scanner.nextLine().trim());

                        //logController.setCal(year, month, day);

                        System.out.println("Date is now "+year+"-"+(month+1)+"-"+day);
                    }
                    catch(NumberFormatException nfe){
                        System.out.println("Please input in the correct format");
                    }



                }

            }

            // Save
            else if(selection == 10) {
                foodCollection.save();
                log.save();
                System.out.println("Saved changes to files");
            }

            // Quit
            else if(selection == 11) {
                foodCollection.save();
                log.save();
                System.out.println("Diet Manager closed");
                System.exit(0);
            }
        }
    }
}
