import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class movie {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(Sy  stem.in);

        try {
            System.out.print("Enter the name of a movie: ");
            String movieName = scanner.nextLine().replace(" ", "+");

            String apiKey = "YOUR_API_KEY"; // put your real key

            String urlString = "http://www.omdbapi.com/?t=" + movieName + "&apikey=" + apiKey;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String data = response.toString();

            if (data.contains("\"Response\":\"False\"")) {
                System.out.println("Movie not found!");
                return;
            }

            // Extract required fields
            String title = extract(data, "Title");
            String year = extract(data, "Year");
            String rated = extract(data, "Rated"); // PG-13 etc
            String runtime = extract(data, "Runtime");
            String plot = extract(data, "Plot");
            String rating = extract(data, "imdbRating");

            // Output format (as you asked)
            System.out.println("Title: " + title);
            System.out.println("Year: " + year);
            System.out.println("Rating: " + rated);
            System.out.println("Running Time: " + runtime);
            System.out.println("Description: " + plot);

            // Recommendation logic
            double score = 0;
            if (!rating.equals("N/A") && !rating.equals("")) {
                score = Double.parseDouble(rating) * 10;
            }

            if (score > 80) {
                System.out.println("You should watch this movie right now!");
            } else if (score < 50) {
                System.out.println("You should avoid this movie.");
            } else {
                System.out.println("This movie is average, you may watch it.");
            }

        } catch (Exception e) {
            System.out.println("Error occurred!");
        }
    }

    // Helper method to extract JSON values
    public static String extract(String json, String key) {
        try {
            String search = "\"" + key + "\":\"";
            int start = json.indexOf(search) + search.length();
            int end = json.indexOf("\"", start);
            return json.substring(start, end);
        } catch (Exception e) {
            return "N/A";
        }
    }
}