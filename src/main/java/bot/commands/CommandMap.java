package bot.commands;

import bot.commands.anime.Hug;
import bot.commands.anime.Pat;
import bot.commands.anime.Wink;
import bot.commands.general.*;
import bot.commands.music.Play;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.PrivateChannel;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bot.utils.Utils.getBotAsUser;
import static bot.utils.Utils.getCurrentColor;
import static java.util.Map.entry;

public class CommandMap {
    static HashMap<String, ICommand> commands = new HashMap<>(Map.ofEntries(
            entry("ping", new Ping()),
            entry("help", new Help()),
            entry("pong", new Pong()),
            entry("about", new About()),
            entry("userinfo", new Userinfo()),
            entry("whois", new Userinfo()),
            entry("serverstats", new Serverstats()),
            entry("roles", new Roles()),
            entry("role", new Roles()),
            entry("regions", new Regions()),
            entry("region", new Regions()),
            entry("hug", new Hug()),
            entry("pat", new Pat()),
            entry("wink", new Wink()),
            entry("play", new Play())
    ));

    static ArrayList<String> categories = new ArrayList<>(List.of("General", "Anime", "Miscellaneous", "Music", "Staff"));

    public static boolean runCommand(CommandReceivedEvent e) {
        ICommand command = commands.get(e.getCommand().toLowerCase());
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

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(getCurrentColor());
        eb.setTitle("Kyoko's Command Handbook");

        eb.setAuthor("Toshino Kyoko", "https://discord.gg/fsYrseYbpe", getBotAsUser().getAvatarUrl());
        eb.setTimestamp(Instant.now());

        categories.forEach(category -> {
            StringBuilder commandsOfCategory = new StringBuilder();
            commands.forEach((commandName, command) -> {
                if (category.equalsIgnoreCase("Miscellaneous") && commandsOfCategory.isEmpty()) {
                    commandsOfCategory.append("uwu\ngood morning\ngoodnight\nRum Raisin\nBeatmap please\nVideos' Profile\nPoggers");
                } else if (command.getCategory().equalsIgnoreCase(category)) {
                    commandsOfCategory.append(commandName).append(" - ").append(command.getShortCommandDescription()).append("\n");
                }
            });

            eb.addField(category, commandsOfCategory.toString(), false);
//            if (eb.getFields().size() >= 2) {
//                privateChannel.sendMessage(eb.build()).queue();
//                eb.clearFields();
//            }
        });

        privateChannel.sendMessage(eb.build()).queue();
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
}
