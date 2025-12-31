import java.io.*;
import java.net.*;
import java.util.*;

public class NewsApp {
    private static final String apikey = "const API_KEY = "XXXXXX_YOUR_API_KEY";
    private static final String baseurl = "https://newsapi.org/v2/top-headlines?";

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter category: (Ex: sports, technology, business)");
            String cat = sc.nextLine();

            String apiurl = baseurl + "category=" + cat + "&language=en&apikey=" + apikey;
            HttpURLConnection con = (HttpURLConnection) new URL(apiurl).openConnection();
            con.setRequestMethod("GET");

            BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line, json = "";
            while ((line = r.readLine()) != null) {
                json += line;
            }
            r.close();

            int index = 0;
            System.out.println("\nTop Headlines in " + cat + " category:\n");
            while ((index = json.indexOf("\"title\":\"", index)) != -1) {
                int endindex = json.indexOf("\"", index + 9);
                System.out.println("- " + json.substring(index + 9, endindex));
                index = endindex;
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
