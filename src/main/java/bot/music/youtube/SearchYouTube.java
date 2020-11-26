package bot.music.youtube;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URL;

public class SearchYouTube {

    static String ytApiKey;
    static JSONParser parser = new JSONParser();

    public static String searchYoutubeVideo(String input) {
        input = input.replaceAll(" ", "%20");

        String fullUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&q=" + input + "&key=" + ytApiKey;
        BufferedReader in;
        StringBuilder sb = new StringBuilder();
        try {
            in = new BufferedReader(new InputStreamReader(new URL(fullUrl).openConnection().getInputStream()));
            String str;
            while((str = in.readLine())!= null){
                sb.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String videoId = getVideoIdUsingString(sb.toString());

        if (videoId.isEmpty()) return "";
        if (videoId.startsWith("Error")) return "";

        return "https://www.youtube.com/watch?v=" + videoId;
    }

    public static String getVideoIdUsingString(String toJson) {
        JSONObject json;
        try {
            json = (JSONObject) parser.parse(toJson);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error: parsing String to JSON failed";
        }

        JSONArray jsonArray = (JSONArray) json.get("items");
        JSONObject firstItemArray = (JSONObject) jsonArray.get(0);
        JSONObject id = (JSONObject) firstItemArray.get("id");

        return (String) id.getOrDefault("videoId", "");
    }

    public static void setYtApiKey() {
        InputStream isYtApi;
        try {
            isYtApi = new FileInputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Discord bot\\token\\ytapi.key");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        BufferedReader brYtApi = new BufferedReader(new InputStreamReader(isYtApi));
        try {
            ytApiKey = brYtApi.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}