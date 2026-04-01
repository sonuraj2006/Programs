import java.io.*;
import java.util.*;

public class productSearch {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<>();

        // ✅ Read JSON file
        try {
            BufferedReader br = new BufferedReader(new FileReader("products.json"));
            String line;

            while ((line = br.readLine()) != null) {
                data.add(line.trim());
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading file. Make sure products.json is in the same folder.");
            return; // stop program if file not found
        }

        // ✅ Search loop
        while (true) {
            System.out.print("What is the product name? (type 'exit' to quit): ");
            String search = sc.nextLine();

            // ✅ Exit condition
            if (search.equalsIgnoreCase("exit")) {
                System.out.println("Program ended.");
                break;
            }

            
            boolean found = false;

            for (int i = 0; i < data.size(); i++) {

                if (data.get(i).contains("\"name\"")) {

                    String name = data.get(i)
                            .split(":")[1]
                            .replace("\"", "")
                            .replace(",", "")
                            .trim();

                    if (name.equalsIgnoreCase(search)) {

                        String price = data.get(i + 1)
                                .split(":")[1]
                                .replace(",", "")
                                .trim();

                        String quantity = data.get(i + 2)
                                .split(":")[1]
                                .trim();

                        System.out.println("Name: " + name);
                        System.out.println("Price: $" + price);
                        System.out.println("Quantity on hand: " + quantity);

                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                System.out.println("Sorry, that product was not found in our inventory.");
            }
        }

        sc.close();
    }
}