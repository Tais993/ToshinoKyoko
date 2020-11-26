package bot.commands.miscellaneous;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class MiscellaneousHandler {
    private static final HashMap<String, MiscellaneousO> commands = new HashMap<>(Map.ofEntries(
            entry("uwu", new MiscellaneousO("", "-kun, you can't say that in here! Only owo's are permitted! :(")),
            entry("gm", new MiscellaneousO(":sunrise: Rise and shine, ", "-kun! :)")),
            entry("Good morning", new MiscellaneousO(":sunrise: Rise and shine, ", "-kun! :)")),
            entry("gn", new MiscellaneousO(":zzz: U-uhm, ", "-kun, I'm getting tired too! Goodnight!")),
            entry("good night", new MiscellaneousO(":zzz: U-uhm, ", "-kun, I'm getting tired too! Goodnight!")),
            entry("Rum Raisin", new MiscellaneousO("R-Rum Raisin?! ", "-kun, that's my favorite!!!!")),
            entry("Beatmap please", new MiscellaneousO("Here you go,  ", "-kun! https://osu.ppy.sh/beatmapsets")),
            entry("Videos' Profile", new MiscellaneousO("qt Videos' Profile: https://osu.ppy.sh/users/Videos", "", false)),
            entry("Videos Profile", new MiscellaneousO("qt Videos' Profile: https://osu.ppy.sh/users/Videos", "", false)),
            entry("poggers", new MiscellaneousO("P-poggers? Did I get that right, ", "-kun?"))
    ));

    public static void runMiscellaneousCommand(MessageReceivedEvent e) {
        MiscellaneousO commandRan = commands.get(e.getMessage().getContentRaw().toLowerCase());

        if (commandRan != null) {
            e.getChannel().sendMessage(commandRan.getFullResponse(e.getAuthor().getAsMention())).queue();
        }
    }

    public static String getAllMiscellaneousCommands() {
        StringBuilder sb = new StringBuilder();
        commands.forEach((key, value) -> sb.append(key).append("\n"));
        return sb.toString();
    }
}
