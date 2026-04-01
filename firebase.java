import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class firebase {

    // 🔴 Replace with your Firebase URL
    static String FIREBASE_URL = "https://your-project-id-default-rtdb.firebaseio.com/notes.json";

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage:");
            System.out.println("java firebase new <note>");
            System.out.println("java firebase show");
            return;
        }

        String command = args[0];

        try {
            if (command.equals("new")) {

                String note = "";
                for (int i = 1; i < args.length; i++) {
                    note += args[i] + " ";
                }

                addNote(note.trim());

            } else if (command.equals("show")) {

                showNotes();

            } else {
                System.out.println("Invalid command");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 🔹 Add Note (POST)
    public static void addNote(String text) throws Exception {

        URL url = new URL(FIREBASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        String json = "{ \"date\": \"" + date + "\", \"text\": \"" + text + "\" }";

        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes());
        os.close();

        int responseCode = conn.getResponseCode();

        if (responseCode == 200) {
            System.out.println("✅ Your note was saved.");
        } else {
            System.out.println("❌ Failed to save note.");
        }
    }

    // 🔹 Show Notes (GET)
    public static void showNotes() throws Exception {

        URL url = new URL(FIREBASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        String line;
        String response = "";

        while ((line = br.readLine()) != null) {
            response += line;
        }

        br.close();

        if (response.equals("null")) {
            System.out.println("No notes found.");
            return;
        }

        // ⚠️ Basic parsing (not perfect but works for this project)
        response = response.replace("{", "")
                           .replace("}", "")
                           .replace("\"", "");

        String[] notes = response.split("text:");

        for (int i = 1; i < notes.length; i++) {
            String[] parts = notes[i].split(",");
            String text = parts[0];
            String date = parts[1].replace("date:", "");

            System.out.println(date + " - " + text);
        }
    }
}