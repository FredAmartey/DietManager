package model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class DataFileManager {

    /**
     * the name of the file that is being managed
     */
    private String fileName;


    /**
     * the constructor for the file manager class
     * @param fileName the name of the file to use for reading and writing purposes
     */
    DataFileManager(String fileName) {
        this.fileName = fileName;
    }


    /**
     * Reads in the complete data file into an array of strings
     * @return a list of an array of strings, each being an individual entry in the data file
     */
    List<String[]> readInFile() {
        CSVReader fileReader;
        try {
            fileReader = new CSVReader(new FileReader(fileName));
            List<String[]> fileData = fileReader.readAll();
            fileReader.close();
            return fileData;
        }
        catch (IOException | CsvException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Writes the entries to the csv files
     * @param changes the list of String array entries that are in will be written to the file
     */
    void writeToFile(ArrayList<String[]> changes) {
        try {
            CSVWriter fileWriter = new CSVWriter(new FileWriter(fileName), CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            for (String[] entry: changes) {
                fileWriter.writeNext(entry);
            }
            fileWriter.close(); // commit changes to file
        }
        catch (IOException  e) {
            e.printStackTrace();
        }
    }
}
