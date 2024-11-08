// CSC 422
// Wyatt Bechtle
// L00483047
// bechtlew@csp.edu
// Initial start data: 11/07/24
// Revisions and Notes
/*
 * 11/7/2024
 * Wyatt Bechtle
 * The FileManager class performs all operation involving files.
 * The functionalities provided include saving and loading a PetDatabase from a file.
 * The static methods allow for acces without an instance.
 * The constant is the data file name with extension.
 */

import java.io.File;
import java.io.FileInputStream;
import java.lang.Exception;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {
    
    // Attribute
    static final String DATA_FILE = "PetDatabase.txt";

    // Method used to save PetDatabase object to a file
    public static void save(PetDatabase petDatabase) {

        // Used to catch exceptions
        try {
            // Instanstantiate a File object
            File dataFile = new File(DATA_FILE);

            // Check if file exists
            if (dataFile.exists()) {

                // Delete current file - Later, logs could be made from this
                dataFile.delete();
            }
            // Create data file
            dataFile.createNewFile();

            // Instantiate FileOutputStream object for writing to file
            FileOutputStream fileOutputStream = new FileOutputStream(DATA_FILE);

            // Instantiate ObjectOutputStream object for writing obect to file
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Write object to file
            objectOutputStream.writeObject(petDatabase);

            objectOutputStream.close();
        }
        catch (Exception exception) {

            System.out.println("\nERROR: Save Unsuccessful");
        }
        
    }
    // Method used to load a PetDatabase from a file
    public static PetDatabase load() {

        // File object used to check if file exists
        File dataFile = new File(DATA_FILE);

        // If no save file, return new PetDatabase
        if (!dataFile.exists()) {
            return new PetDatabase();
        };
        // Used to catch exceptions
        try {
            // Instantiate FileInputStream object to read data from files
            FileInputStream fileInputStream = new FileInputStream(DATA_FILE);

            // Instantiate ObjectInputStream object to read objects from files
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Read the PetDatabase object store in the file
            PetDatabase myPetDatabase = (PetDatabase) objectInputStream.readObject();

            objectInputStream.close();

            return myPetDatabase;
        } 
        catch (Exception exception) {
            
            System.out.println("\nERROR: Data Loaded Unsuccessfully");
            return null;
        }
    }
}
