package bot.commands.general;

import bot.commands.CommandReceivedEvent;
import bot.commands.ICommand;

public class Pong implements ICommand {
    String command = "pong";
    String category = "general";
    String commandExample = "pong";
    String shortCommandDescription = "The fake version of ping.";
    String fullCommandDescription = "The fake version of ping";

    @Override
    public void command(CommandReceivedEvent event) {
        event.getChannel().sendMessage("B-baka! " + event.getAuthor().getAsMention() + "-kun, it's `.ping` not `.pong`! :(").queue();
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
