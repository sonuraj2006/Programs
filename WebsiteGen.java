import java.io.*;
import java.util.*;
public class WebsiteGen {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Site name: ");
        String site = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("Do you want a folder for JavaScript? ");
        String js = sc.nextLine();
        System.out.print("Do you want a folder for CSS? ");
        String css = sc.nextLine();
        new File(site).mkdir();
        System.out.println("Created ./" + site);
        FileWriter f = new FileWriter(site + "/index.html");
        f.write("<title>" + site + "</title>\n");
        f.write("<meta name=\"author\" content=\"" + author + "\">");
        f.close();
        System.out.println("Created ./" + site + "/index.html");
        if (js.equalsIgnoreCase("y")) {
            new File(site + "/js").mkdir();
            System.out.println("Created ./" + site + "/js/");
        }
        if (css.equalsIgnoreCase("y")) {
            new File(site + "/css").mkdir();
            System.out.println("Created ./" + site + "/css/");
        }
        sc.close();
    }
}