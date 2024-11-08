// CSC 422
// Wyatt Bechtle
// L00483047
// bechtlew@csp.edu
// Initial start data: 11/07/24
// Revisions and Notes
/*
 * 11/7/2024
 * Wyatt Bechtle
 * 
 */

import java.util.Scanner;

public class InputValidator {

    // Method for validating age
    public static boolean validateAge(int age) {

        return (0 < age) && (age < 21);
    }
    // Method used to validate add pet inputs
    public static boolean validateNameAgeEntry(String line) {

        // Extract strings
        String[] strings = line.split(" ");

        // Validate input
        if (strings.length != 2) {
            return false;
        }
        else {
            // Try to cast
            try {
                String name = (String) strings[0];
                int age = Integer.valueOf(strings[1]);
                return true;
            }
            catch (Exception exception) {
                return false;
            }
        }    
    }
    // Method used to validate index value
    public static boolean validateIndex(String index, int petCount) {

        boolean isValid = false;

        // Try to cast and validate
        try {

            int intIndex = Integer.valueOf(index);
            if (intIndex >= 0 && intIndex <= 4 && intIndex < petCount) {
                isValid = true;
            }
        }
        catch (Exception exception) {
            isValid = false;
        }
        return isValid;
    }
}