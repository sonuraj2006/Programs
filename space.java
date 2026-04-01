import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
class space {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://api.open-notify.org/astros.json");
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
            JSONObject data = new JSONObject(response.toString());
            int total = data.getInt("number");
            JSONArray people = data.getJSONArray("people");
            System.out.println("Number of astronauts in space: " + total);
            System.out.println("\n-------------------------------------------");
            System.out.printf("%-25s %-15s\n", "Name", "Spacecraft");
            System.out.println("-------------------------------------------");
            for (int i = 0; i < people.length(); i++) {
                JSONObject person = people.getJSONObject(i);
                String name = person.getString("name");
                String craft = person.getString("craft");
                System.out.printf("%-25s %-15s\n", name, craft);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}