import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AnimalProcessor {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        HashMap<String, Integer> speciesCount = new HashMap<>();

        try {
            // Read from arrivingAnimals.txt
            BufferedReader reader = new BufferedReader(new FileReader("arrivingAnimals.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                int age = Integer.parseInt(parts[1].trim());
                String species = parts[2].trim();

                // Create Animal instances based on species
                Animal animal;
                switch (species) {
                    case "Hyena":
                        animal = new Hyena(name, age);
                        break;
                    case "Lion":
                        animal = new Lion(name, age);
                        break;
                    case "Tiger":
                        animal = new Tiger(name, age);
                        break;
                    case "Bear":
                        animal = new Bear(name, age);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown species: " + species);
                }

                // Add animal to the list
                animals.add(animal);

                // Update species count
                speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Output report to newAnimals.txt
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("newAnimals.txt"));

            for (Animal animal : animals) {
                // Write animal details to the report
                writer.write(animal.getSpecies() + ": " + animal.getName() + ", " + animal.getAge());
                writer.newLine();
            }

            // Write species count to the report
            for (String species : speciesCount.keySet()) {
                writer.write("Total " + species + "s: " + speciesCount.get(species));
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
