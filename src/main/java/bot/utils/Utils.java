package bot.utils;

import net.dv8tion.jda.api.entities.User;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import static bot.ToshinoKyoko.getJda;

public class Utils {
    private static final Color yellow = new Color(252, 232, 3);
    private static final Color orange = new Color(219, 65, 5);

    public static Color getCurrentColor() {
        return Color.yellow;
    }

    public static User getBotAsUser() {
        return getJda().getSelfUser();
    }

    public static boolean isUrl(String possibleUrl) {
        try {
            URL url = new URL(possibleUrl);
            return true;
        } catch (MalformedURLException ignore) {
            return false;
        }
    }
}
