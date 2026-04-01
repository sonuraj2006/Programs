import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Employee {
    String last;
    String first;
    String salary;

    Employee(String last, String first, String salary) {
        this.last = last;
        this.first = first;
        this.salary = salary;
    }
}

public class Datafile {
    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("employees.csv"));

            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {

                // Skip header
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");

                employees.add(new Employee(parts[0], parts[1], parts[2]));
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Print Table
        System.out.printf("%-12s%-12s%10s\n", "Last", "First", "Salary");
        System.out.println("--------------------------------------");

        for (Employee emp : employees) {
            System.out.printf("%-12s%-12s%10s\n",
                    emp.last, emp.first, emp.salary);
        }
    }
}