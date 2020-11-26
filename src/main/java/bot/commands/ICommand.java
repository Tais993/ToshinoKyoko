package bot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.time.Instant;

import static bot.commands.CommandMap.*;
import static bot.utils.Utils.getBotAsUser;
import static bot.utils.Utils.getCurrentColor;

public interface ICommand {
    String command = "example";
    String category = "example";
    String commandExample = "example";
    String shortCommandDescription = "This is a example command without any functionality.";
    String fullCommandDescription = "This is a example command without any functionality";

    void command(CommandReceivedEvent event);

    default EmbedBuilder getEmbed() {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(getCurrentColor());
//        eb.setAuthor("Toshino Kyoko", "https://discord.gg/fsYrseYbpe", getBotAsUser().getAvatarUrl());
        eb.setTimestamp(Instant.now());

        return eb;
    }

    String getCommand();

    String getCategory();

    String getCommandExample();

    String getShortCommandDescription();

    String getFullCommandDescription();

    default MessageEmbed getFullHelp(String error) {
        if (error.isEmpty()) {
            return getFullHelpCommand(getCommand()).build();
        }
        return getFullHelpCommand(getCommand()).setDescription(error).build();
    }

    default MessageEmbed getErrorHelp(String error) {
        return getErrorHelpCommand(getCommand()).setDescription("**" + error + "**").build();
    }

    default MessageEmbed getIAErrorHelp(String IAFix) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setAuthor("Toshino Kyoko", "https://discord.gg/fsYrseYbpe", getBotAsUser().getAvatarUrl());
        eb.setTimestamp(Instant.now());

        eb.setTitle("Invalid arguments!");
        eb.addField("`." + getCommandExample() + "`", IAFix, true);

        eb.setColor(getCurrentColor());
        return eb.build();
    }
}
