package bot.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TenorApi {

    static JSONParser parser = new JSONParser();
    static String urlStart = "https://api.tenor.com/v1/random?q=";
    static String urlEnd = "&key=R59P7YK0O3IA&limit=1";

    public static String getGifTypeAsUrl(String gifType) {
        return getRandomGif(urlStart + gifType + urlEnd);
    }

    private static String getRandomGif(String fullUrl) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(fullUrl).openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String str;
            while((str = reader.readLine())!= null){
                sb.append(str);
            }
            return getGifUrlFromJSON(sb.toString());
        } catch (IOException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        return null;
    }

    private static InputStream getGifOfUrl(String json) {
        String gifUrl = getGifUrlFromJSON(json);
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(gifUrl).openConnection();
            connection.setRequestMethod("GET");
            return connection.getInputStream();
        } catch (IOException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        return null;
    }

    private static String getGifUrlFromJSON(String toJson) {
        JSONObject json;
        try {
            json = (JSONObject) parser.parse(toJson);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error: parsing String to JSON failed";
        }

        JSONArray results = (JSONArray) json.get("results");
        JSONObject result = (JSONObject) results.get(0);
        JSONArray media = (JSONArray) result.get("media");
        JSONObject URLs = (JSONObject) media.get(0);
        JSONObject gif = (JSONObject) URLs.get("gif");

        return gif.get("url").toString();
    }
}
