import java.io.*;
import java.util.*;
class Inventory {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        FileWriter fw = new FileWriter("inventory.csv", true);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Serial Number: ");
            String serial = sc.nextLine();
            System.out.print("Enter Value: ");
            String value = sc.nextLine();
            fw.write(name + "," + serial + "," + value + "\n");
        }
        fw.close();
        System.out.println("\nData Saved Successfully!");
        BufferedReader br = new BufferedReader(new FileReader("inventory.csv"));
        String line;
        System.out.println("\nName | Serial Number | Value");
        System.out.println("----------------------------------");
        while ((line = br.readLine()) != null) {
            String data[] = line.split(",");
            System.out.println(data[0] + " | " + data[1] + " | " + data[2]);
        }
        br.close();
        BufferedReader br2 = new BufferedReader(new FileReader("inventory.csv"));
        FileWriter html = new FileWriter("inventory.html");
        html.write("<html><body><table border='1'>");
        html.write("<tr><th>Name</th><th>Serial</th><th>Value</th></tr>");
        while ((line = br2.readLine()) != null) {
            String data[] = line.split(",");
            html.write("<tr><td>" + data[0] + "</td><td>" + data[1] + "</td><td>" + data[2] + "</td></tr>");
        }
        html.write("</table></body></html>");
        html.close();
        br2.close();
        System.out.println("HTML file created!");
    }
}