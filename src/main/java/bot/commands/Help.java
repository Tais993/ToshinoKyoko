package bot.commands;

import static bot.commands.CommandMap.*;

public class Help implements ICommand {
    String command = "help";
    String category = "general";
    String commandExample = "help";
    String shortCommandDescription = "Get help for a command, category or help all.";
    String fullCommandDescription = "Get help for a command, category or help all.";

    @Override
    public void command(CommandReceivedEvent e) {

        if (!e.hasArgs()) {
            e.getChannel().sendMessage("Bad, no args for you!").queue();
            return;
        }

        String[] args = e.getArgs();

        if (isCategory(args[0])) {
            getHelpByCategory(args[0], e);
        } else if (isCommand(args[0])) {
            e.getChannel().sendMessage(getFullHelpCommand(args[0]).build()).queue();
        } else {
            getHelpAllByCategory(e);
        }
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
