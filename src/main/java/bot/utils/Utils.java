package bot.utils;

import net.dv8tion.jda.api.entities.User;

import java.awt.*;

import static bot.ToshinoKyoko.getJda;

public class Utils {
    private static final Color yellow = new Color(252, 232, 3);
    private static final Color orange = new Color(219, 65, 5);

    public static Color getCurrentColor() {
        return yellow;
    }

    public static User getBotAsUser() {
        return getJda().getSelfUser();
    }
}
