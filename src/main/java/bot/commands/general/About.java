package bot.commands.general;

import bot.commands.CommandReceivedEvent;
import bot.commands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

public class About implements ICommand {
    String command = "about";
    String category = "general";
    String commandExample = "about";
    String shortCommandDescription = "About the bot.";
    String fullCommandDescription = "About the bot";

    @Override
    public void command(CommandReceivedEvent event) {
        EmbedBuilder eb = getEmbed();

        eb.setThumbnail("https://steamuserimages-a.akamaihd.net/ugc/364029569323252827/DC188CC57168FC8447CC4AC9CC4E0834224D27DC/");
        eb.setTitle("About - Toshino, Kyoko");
        eb.addField("Description:", "Toshino Kyoko, is a custom Discord Bot made by Videos. Taken inspiration from the (totally legal) anime, Yuru Yuri. Toshino Kyoko is the core bot of the Discord Server, as a Utility, Fun, Moderation, & Anime-related bot.", false);
        eb.addField("How to get started?", "Simply type `.help` in <#781170159071133778>, and ya should be ready to go!", false);

        event.getChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getCommandExample() {
        return commandExample;
    }

    @Override
    public String getShortCommandDescription() {
        return shortCommandDescription;
    }

    @Override
    public String getFullCommandDescription() {
        return fullCommandDescription;
    }
}
