package TestingPackage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONReader {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try {
            // Read the JSON file
            Object obj = parser.parse(new FileReader("data.json"));

            // Parse the JSON object
            JSONObject jsonObject = (JSONObject) obj;

            // Extract the values
            long no = (Long) jsonObject.get("no");
            double lat = (Double) jsonObject.get("lat");
            double lng = (Double) jsonObject.get("lng");

            // Print the values
            System.out.println("no: " + no);
            System.out.println("lat: " + lat);
            System.out.println("lng: " + lng);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
