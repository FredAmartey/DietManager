package model;

import java.util.*;

/**
 * Provides functionality for collections to interact with the file manager.
 */
abstract class EntryCollection {

    private DataFileManager fileManager;
    private String fileName;

    /**
     * Creates the file manager and sets the filename
     */
    EntryCollection(String filename) {
        this.fileName = filename;
        createFileManager();
    }

    /**
     * Creates the file manager
     */
    private void createFileManager() {
        this.fileManager = new DataFileManager(this.fileName);
    }


    /**
     * Gets the data from the specified file to return to the subclass collection that will use it.
     * @return a list of string arrays. Each string array is a line in the csv file
     */
    List<String[]> getFileData() {
        return fileManager.readInFile();
    }
    

    /**
     * Saves the changes (processes additions, updates, and deletions) from the collections to the data file
     * @param changes a list of changes to process
     */
    private void saveChanges(ArrayList<String[]> changes) {
        fileManager.writeToFile(changes);
    }


    /**
     * Saves log entries to strings to write to the csv file.
     * @param logEntries the list of log entries
     */
    void saveLogContents(TreeMap<String, TreeMap<Integer, LogEntry>> logEntries) {
        if (fileManager == null) {
            createFileManager();
        }
        ArrayList<String[]> logStringEntries = new ArrayList<>();

        logEntries.forEach((date, dailyEntries) -> { // for each date

            String[] datevals = date.split("-");
            String year = datevals[0];
            String month = datevals[1];
            String day = datevals[2];

            dailyEntries.forEach((id, logEntry) -> { // for each entry in a day

                String type = String.valueOf(logEntry.getDataType());
                if (type.equals("r") || type.equals("b")) {
                    type = "f";
                }
                String value = logEntry.getValue();
                if (logEntry.getAmount() > 0) {
                    String amount = String.valueOf(logEntry.getAmount());
                    String[] entryCSV = {year, month, day, type, value, amount};
                    logStringEntries.add(entryCSV);
                }
                else {
                    String[] entryCSV = {year, month, day, type, value};
                    logStringEntries.add(entryCSV);
                }
            });
        });
        saveChanges(logStringEntries);
    }


    /**
     * Translates a map collection of the food library to strings that can be written to a CSV file.
     * @param foods the map of foods to translate
     */
    void saveFoodCollection(HashMap<String, FoodInterface> foods) {
        if (fileManager == null) {
            createFileManager();
        }

        ArrayList<String[]> changes = new ArrayList<>();

        foods.forEach((name, food) -> {
            // translate here
            if (food.getEntryType() == 'b') {
                String calories = Double.toString(food.getCalories());
                String fat = Double.toString(food.getFat());
                String carb = Double.toString(food.getCarbs());
                String protein = Double.toString(food.getProtein());
                String[] entry = {String.valueOf(food.getEntryType()), name, calories, fat, carb, protein};
                changes.add(entry);
            }
            else {
                Recipe recipe = (Recipe) food;
                Map<FoodInterface, Double> ingredients = recipe.getIngredients();
                ArrayList<String> recipeString = new ArrayList<>();
                recipeString.add(Character.toString(food.getEntryType()));
                recipeString.add(food.getName());
                ingredients.forEach((ingredient, amount) -> {
                    recipeString.add(ingredient.getName());
                    recipeString.add(Double.toString(amount));
                });
                String[] entry = new String[recipeString.size()];
                for (int i = 0; i < recipeString.size(); i++) {
                    entry[i] = recipeString.get(i);
                }
                changes.add(entry);
            }
        }); // end translation loop
        saveChanges(changes);
    }


    /**
     * Converts the exercise collection entries to strings that will be written to the file.
     * @param exercises the exercise collection
     */
    void saveExerciseCollection(HashMap<String, Exercise> exercises) {
        if (fileManager == null) {
            createFileManager();
        }

        ArrayList<String[]> entries = new ArrayList<>();

        exercises.forEach((name, exercise) -> {
            String[] entry = {String.valueOf(exercise.getEntryType()), name,
                    String.valueOf(exercise.getCaloricBurnRate())};
            entries.add(entry);
        });

        saveChanges(entries);
    }
}
