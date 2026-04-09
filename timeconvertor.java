import java.util.regex.*;
import java.util.Scanner;
 class TimeConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a sentence with time:");
        String input = sc.nextLine();
Pattern p = Pattern.compile("(\\d{1,2}):(\\d{2})\\s*(AM|PM|am|pm|a.m.|p.m.)");
        Matcher m = p.matcher(input);
        StringBuffer result = new StringBuffer();
        while (m.find()) {
            int hour = Integer.parseInt(m.group(1));
            String minute = m.group(2);
            String period = m.group(3).toLowerCase();
            if (period.contains("am")) {
                if (hour == 12) hour = 0;
            } else {
                if (hour != 12) hour += 12;
            }
            String newTime = String.format("%02d%s", hour, minute);
            m.appendReplacement(result, newTime);
        }
        m.appendTail(result);
        System.out.println("Converted text:");
        System.out.println(result);
        sc.close();
    }
}