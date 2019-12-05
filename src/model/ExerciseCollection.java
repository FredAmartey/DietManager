package model;

import java.util.HashMap;
import java.util.List;

/**
 * Stores a collection of unique exercises to use
 */
public class ExerciseCollection extends EntryCollection {

    private HashMap<String, Exercise> exercises;

    public ExerciseCollection(String filename) {
        super(filename);
        exercises = new HashMap<>();
        loadExerciseCollection();
    }

    /**
     * Loads the collection from the csv file
     */
    public void loadExerciseCollection() {
        List<String[]> fileContents = super.getFileData();

        for (String[] entry : fileContents) {
            if (entry.length > 1) {
                Exercise newExercise = new Exercise(entry[1], Double.parseDouble(entry[2]));
                exercises.put(newExercise.getValue(), newExercise);
            }
        }
    }


    /**
     * Add an exercise to the collection
     * @param uniqueExerciseName the unique name to add
     * @param calorieBurnRate the burn rate per hour per 100lbs of body weight.
     */
    public void addExercise(String uniqueExerciseName, double calorieBurnRate) {
        Exercise newExercise = new Exercise(uniqueExerciseName, calorieBurnRate);
        exercises.put(newExercise.getValue(), newExercise);
    }


    /**
     * Delete an exercise from the collection
     * @param uniqueExerciseName the name to delete from the collection
     */
    public void deleteExercise(String uniqueExerciseName){
        exercises.remove(uniqueExerciseName);
    }


    /**
     * Edit an exercise name
     * @param existingExerciseName the existing exercise name
     * @param newExerciseName the new one to replace the old one
     */
    public void editExerciseName(String existingExerciseName, String newExerciseName) {
        Exercise value = exercises.get(existingExerciseName);
        exercises.remove(existingExerciseName);
        exercises.put(newExerciseName, value);
    }


    /**
     * Get the collection of exercises
     * @return the map
     */
    public HashMap<String, Exercise> getCollection() {
        return exercises;
    }


    /**
     * Gets an exercise
     * @param exerciseName the name of the exercise to get
     * @return the exercise object
     */
    public Exercise getExercise(String exerciseName) {
        return exercises.get(exerciseName);
    }


    /**
     * Save the collection of exercises. Call the parent's class translation method so that it writes to the file.
     */
    public void save() {
        super.saveExerciseCollection(exercises);
    }
}
