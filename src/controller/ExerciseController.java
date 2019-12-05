package controller;

import model.Exercise;
import model.ExerciseCollection;
import model.FoodInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseController {

    private ExerciseCollection ec;

    public ExerciseController(ExerciseCollection exerciseCollection) {
        this.ec = exerciseCollection;
    }

    public void addExercise(String name, double calorieBurnRate) {
        ec.addExercise(name, calorieBurnRate);
    }

    public void deleteExercise(String name) {
        ec.deleteExercise(name);
    }

    public void editExerciseName(String existingName, String newName) {
        ec.editExerciseName(existingName, newName);
    }

    public void saveExerciseCollection() {
        ec.save();
    }

    public HashMap<String, Exercise> getExercises() {
        return ec.getCollection();
    }

}
