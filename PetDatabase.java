// CSC 422
// Wyatt Bechtle
// L00483047
// bechtlew@csp.edu
// Initial start data: 10/31/24
// Revisions and Notes
/*
 * 11/2/2024
 * Wyatt Bechtle
 * Added searching feature to pet database. 
 * Allows for search by name or age. 
 * Added small improvements to output formatting.
 */
/*
 * 11/3/2024
 * Wyatt Bechtle
 * Added update and delete functionality.
 * Added small improvements to output formatting.
 */

import java.util.Scanner;

public class PetDatabase {

    // Attributes
    private int petArrayCapacity = 20;
    private Pet[] petArray = new Pet[petArrayCapacity];
    private int petsInArray = 0;

    // Method used to display menu
    public void displayMenu() {

        System.out.print("\nWhat would you like to do?\n" + 
                        "1) View all pets\n" + 
                        "2) Add more pets\n" + 
                        "3) Update an existing pet\n" +
                        "4) Remove an existing pet\n" +
                        "5) Search pets by name\n" +  
                        "6) Search pets by age\n" +
                        "7) Exit program\n" +
                        "\nYour choice:");
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
            System.out.print("\nadd pet (name, age): ");

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
        System.out.println("\n+----------------------+\n" +
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
    // Method used to search pets by name
    public void searchPetsByName(String name) {

        // Display column titles
        System.out.println("\n+----------------------+\n" +
                           "| ID | NAME      | AGE |\n" +
                           "+----------------------+");
        // Create a count
        int rows = 0;
        // Display rows one at a time
        for (int i = 0; i < this.petsInArray; i++) {

            // If name matches, display row
            if (name.equalsIgnoreCase(this.petArray[i].getName())) {
                System.out.printf("|%3d | %-10s|%4d |\n", i, this.petArray[i].getName(), this.petArray[i].getAge());
                rows += 1;
            }
        } 
        // Display footer and row count
        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", rows);
    }
    // Method used to search pets by age
    public void searchPetsByAge(int age) {

        // Display column titles
        System.out.println("\n+----------------------+\n" +
                           "| ID | NAME      | AGE |\n" +
                           "+----------------------+");
        // Create a count
        int rows = 0;
        // Display rows one at a time
        for (int i = 0; i < this.petsInArray; i++) {

            // If age matches, display row
            if (age == this.petArray[i].getAge()) {
                System.out.printf("|%3d | %-10s|%4d |\n", i, this.petArray[i].getName(), this.petArray[i].getAge());
                rows += 1;
            }
        } 
        // Display footer and row count
        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", rows);
    }
    // Method used to update an existing pet
    public void updatePet(int index, String name, int age) {

        Pet pet = this.petArray[index];
        System.out.printf("\n%s %d changed to %s %d.\n", pet.getName(), pet.getAge(), name, age);
        pet.setAge(age);
        pet.setName(name);
    }
    // Method used to delete an existing pet
    public void deletePet(int index) {

        String nameToDelete = this.petArray[index].getName();
        int ageToDelete = this.petArray[index].getAge();

         // Shift elements to the left, starting from the index
        for (int i = index; i < this.petsInArray - 1; i++) {
            this.petArray[i] = this.petArray[i + 1];
        }
        // Set the last element to null after shifting
        this.petArray[this.petsInArray - 1] = null;

        // Update the count of pets
        this.petsInArray--;

        System.out.printf("\n%s %d succesfully deleted.\n", nameToDelete, ageToDelete);
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

                // User select to update an existing pet
                case "3":
                    myPetDatabase.displayAllPets();

                    // Prompt user for input
                    System.out.print("\nEnter the pet ID you want to update: ");
                    int index = myScanner.nextInt();
                    System.out.print("\nEnter new name and age: ");
                    String newName = myScanner.next();
                    int newAge = myScanner.nextInt();
                    myScanner.nextLine();

                    // Call updatePet method
                    myPetDatabase.updatePet(index, newName, newAge);
                    break;

                // User select to remove an existing pet
                case "4":
                    myPetDatabase.displayAllPets();

                    // Prompt user for input
                    System.out.print("\nEnter the pet ID you want to delete: ");
                    int indexToDelete = myScanner.nextInt();
                    myScanner.nextLine();
                    myPetDatabase.deletePet(indexToDelete);

                    break;

                // User select to search pets by name
                case "5":
                    System.out.print("\nEnter a name to search: ");
                    String name = myScanner.next();
                    myScanner.nextLine();
                    myPetDatabase.searchPetsByName(name);
                    break;

                // User select to search pets by age
                case "6":
                    System.out.print("\nEnter an age to search: ");
                    int age = myScanner.nextInt();
                    myScanner.nextLine();
                    myPetDatabase.searchPetsByAge(age);
                    break;

                // User selects to exit database
                case "7":

                    System.out.println("\nGood-Bye...");
                    goAgain = false;
                    break;
            
                default:

                    System.out.println("\nError: invalid input. Please enter 1-5");
                    break;
            }
        } while (goAgain);
    }
}