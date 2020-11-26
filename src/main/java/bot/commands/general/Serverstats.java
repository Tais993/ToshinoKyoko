package bot.commands.general;

import bot.commands.CommandReceivedEvent;
import bot.commands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

public class Serverstats implements ICommand {
    String command = "serverstats";
    String category = "general";
    String commandExample = "serverstats";
    String shortCommandDescription = "Get the stats of the current server you're in.";
    String fullCommandDescription = "Get the stats of the current server you're in.";

    @Override
    public void command(CommandReceivedEvent event) {

        if (!event.isFromGuild()) {
            event.getChannel().sendMessage(getErrorHelp("Requires to be in a guild!")).queue();
            return;
        }

        EmbedBuilder eb = getEmbed();

        eb.setTitle("Discord Server Stats");

        eb.addField("Total # of Users on the Discord Server", "**"  + event.getGuild().getMemberCount() + "** total users! :D", false);
        eb.addField("Total # of Discord Supporters", "**"  + event.getGuild().getBoostCount() + "** supporter[s] so far!", false);

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
