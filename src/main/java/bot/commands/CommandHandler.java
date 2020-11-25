package bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static bot.commands.CommandMap.runCommand;
import static net.dv8tion.jda.api.entities.MessageEmbed.TEXT_MAX_LENGTH;

public class CommandHandler extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        if (event.getMessage().getContentRaw().startsWith(".")) {
            CommandReceivedEvent e = new CommandReceivedEvent(event);
            if (runCommand(e)) {
                return;
            }
        }

        System.out.println("you fuckin failed LOL" + TEXT_MAX_LENGTH);
    }
}
