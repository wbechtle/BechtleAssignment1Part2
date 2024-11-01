// CSC 422
// Wyatt Bechtle
// L00483047
// bechtlew@csp.edu
// Initial start data: 10/31/24

import java.util.Scanner;

public class PetDatabase {

    // Attributes
    private int petArrayCapacity = 20;
    private Pet[] petArray = new Pet[petArrayCapacity];
    private int petsInArray = 0;

    // Method used to display menu
    public void displayMenu() {

        System.out.print("What would you like to do?\n" + 
                        "1) View all pets\n" + 
                        "2) Add more pets\n" + 
                        "3) Exit program\n" +
                        "Your choice:");
    }
    // Method used to append a pet to the petArray
    public void appendPet(Pet pet) {

        // If petArray at max capacity, double the size
        if (this.petsInArray == petArray.length) {

            // Create temp array of double size
            Pet[] tempArray = new Pet[this.petArrayCapacity * 2];

            // Update capacity
            this.petArrayCapacity *= 2;

            // Copy elements of array
            for (int i = 0; i < this.petArray.length; i++) {
                tempArray[i] = this.petArray[i];
            }
            // Update petArray reference to tempArray address
            this.petArray = tempArray;
        }
        // Add pet to array
        this.petArray[petsInArray] = pet;

        // Increment petInArray
        this.petsInArray += 1;
    }
    // Method used to add Pet objects to the PetDatabase
    public void addPets() {

        // Scanner object for input extraction
        Scanner myScanner = new Scanner(System.in);
        
        // Variable to indicate when user is done inputing data
        boolean goAgain = true;

        // While goAgain prompt user for pet data
        while (goAgain) {

            // Prompt user for input
            System.out.print("add pet (name, age): ");

            // Extract name
            String petName = myScanner.next();

            // If user does not enter done, finish extracting data, create a new object, and 
            // append it to the petArray
            if (!petName.equalsIgnoreCase("done")) {

                // Extract age
                int petAge = myScanner.nextInt();

                // Extract user input and instantiate a Pet object.
                Pet pet = new Pet(petName, petAge);

                // Call appendPet
                appendPet(pet);
            }
            else {
                goAgain = false;
            }
        }
    }
    // Method used to view all pets
    public void displayAllPets() {

        // Display column titles
        System.out.println("+----------------------+\n" +
                           "| ID | NAME      | AGE |\n" +
                           "+----------------------+");
        // Display rows one at a time
        for (int i = 0; i < this.petsInArray; i++) {
            System.out.printf("|%3d | %-10s|%4d |\n", i, this.petArray[i].getName(), this.petArray[i].getAge());
        } 
        // Display footer and row count
        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", this.petsInArray);
    }
    public static void main(String[] args) {

        // Instantiate a new PetDatabase object
        PetDatabase myPetDatabase = new PetDatabase();

        // Create scanner object for extracting user input
        Scanner myScanner = new Scanner(System.in);

        // Variable to indicate if program should iterate
        boolean goAgain = true;

        // For testing purposes
        String[] petNames = new String[] {"Kitty", "Bruno", "Boomer", "Boomer", "Fiesty"};
        int[] petAges = new int[] {8, 7, 8, 3, 3};
        for (int i = 0; i < petNames.length; i++) {
            myPetDatabase.petArray[i] = new Pet(petNames[i], petAges[i]);
            myPetDatabase.petsInArray += 1;
        }

        // Iterates the program until user select quit option
        do {
            // Display menu
            myPetDatabase.displayMenu();

            // Get user input
            String userChoice = myScanner.nextLine();

            // Handles user input
            switch (userChoice) {

                // User selects to display all pets within the database
                case "1":
                    myPetDatabase.displayAllPets();
                    break;
                
                // User select to add more pets to the database
                case "2":

                    myPetDatabase.addPets();
                    break;

                // User selects to exit database
                case "3":

                    goAgain = false;
                    break;
            
                default:

                    System.out.println("Error: invalid input. Please enter 1-3");
                    break;
            }
        } while (goAgain);
    }
}