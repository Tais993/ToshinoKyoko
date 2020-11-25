package bot.commands;

import bot.commands.general.Ping;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.PrivateChannel;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static bot.utils.Utils.getBotAsUser;
import static bot.utils.Utils.getCurrentColor;

public class CommandMap {
    static HashMap<String, ICommand> commands = new HashMap<>();

    static ArrayList<String> categories = new ArrayList<>(List.of("general", "anime"));

    public static void prepareCommands() {
        commands.put("ping", new Ping());
        commands.put("help", new Help());
    }

    public static boolean runCommand(CommandReceivedEvent e) {
        ICommand command = commands.get(e.getCommand());
        if (command != null) {
            command.command(e);
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCategory(String categoryName) {
        return categories.contains(categoryName);
    }

    public static boolean isCommand(String commandName) {
        return commands.containsKey(commandName);
    }

    public static void getHelpAllByCategory(CommandReceivedEvent e) {
        PrivateChannel privateChannel = e.getAuthor().openPrivateChannel().complete();

        categories.forEach(category -> {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(getCurrentColor());
            eb.setTitle("Help " + category);

            eb.setAuthor("Toshino Kyoko", "https://discord.gg/fsYrseYbpe", getBotAsUser().getAvatarUrl());
            eb.setTimestamp(Instant.now());

            commands.forEach((commandName, command) -> {
                if (command.getCategory().equalsIgnoreCase(category)) {
                    if (eb.length() > 1950) {
                        privateChannel.sendMessage(eb.build()).queue();
                        eb.setDescription("");
                    }
                    eb.appendDescription("**" + commandName + "** - " + command.getShortCommandDescription() + "\n");
                }
            });

            privateChannel.sendMessage(eb.build()).queue();
        });
    }


        public static EmbedBuilder getFullHelpCommand(String commandName) {
        ICommand command = commands.get(commandName);

        EmbedBuilder eb = new EmbedBuilder();

        eb.setAuthor("Toshino Kyoko", "https://discord.gg/fsYrseYbpe", getBotAsUser().getAvatarUrl());
        eb.setTimestamp(Instant.now());

        eb.setTitle("Help " + commandName);

        eb.addField("`." + command.getCommandExample() + "`",  command.getFullCommandDescription(), true);

        eb.setColor(getCurrentColor());
        return eb;
    }

    public static EmbedBuilder getErrorHelpCommand(String commandName) {
        ICommand command = commands.get(commandName);

        EmbedBuilder eb = new EmbedBuilder();

        eb.setAuthor("Toshino Kyoko", "https://discord.gg/fsYrseYbpe", getBotAsUser().getAvatarUrl());
        eb.setTimestamp(Instant.now());

        eb.setTitle("Error " + commandName);
        eb.addField("`." + command.getCommandExample() + "`",  command.getShortCommandDescription(), true);

        eb.setColor(getCurrentColor());
        return eb;
    }

    public static void getHelpByCategory(String category, CommandReceivedEvent e) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(getCurrentColor());

        eb.setTitle("Help " + category);
        eb.setAuthor("Toshino Kyoko", "https://discord.gg/fsYrseYbpe", getBotAsUser().getAvatarUrl());
        eb.setTimestamp(Instant.now());

        commands.forEach((commandName, command) -> {
            if (command.getCategory().equalsIgnoreCase(category)) {
                if (eb.length() > 1950) {
                    e.getChannel().sendMessage(eb.build()).queue();
                    eb.setDescription("");
                }
                eb.appendDescription("**" + commandName + "** - " + command.getShortCommandDescription() + "\n");
            }
        });

        e.getChannel().sendMessage(eb.build()).queue();
    }
}
