package bot.commands.general;

import bot.commands.CommandReceivedEvent;
import bot.commands.ICommand;

public class Ping implements ICommand {
    String command = "ping";
    String category = "general";
    String commandExample = "ping";
    String shortCommandDescription = "Get the ping of the bot.";
    String fullCommandDescription = "Get the ping of the bot.";

    @Override
    public void command(CommandReceivedEvent event) {
        event.getChannel().sendMessage(event.getJDA().getGatewayPing() + "").queue();
        event.getChannel().sendMessage(getEmbed().setDescription("Helluu " + event.getJDA().getGatewayPing()).build()).queue();
    }

    public String getCommand() {
        return command;
    }

    public String getCategory() {
        return category;
    }

    public String getCommandExample() {
        return commandExample;
    }

    public String getShortCommandDescription() {
        return shortCommandDescription;
    }

    public String getFullCommandDescription() {
        return fullCommandDescription;
    }
}
