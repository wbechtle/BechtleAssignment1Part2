// bechtlew@csp.edu
// Initial start data: 10/31/24
// Revisions and Notes
/*
 * 11/3/2024
 * Wyatt Bechtle
 * Added setters to the Pet class.
 * The setters allow for Pets to be updated in the PetDatabase.
 */
public class Pet {

    // Attributes
    private String name;
    private int age;

    // Constructor
    public Pet(String name, int age) {

        this.name = name;
        this.age = age;
    }
    // Getters
    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
