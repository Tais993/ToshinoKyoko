package bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static bot.commands.CommandMap.runCommand;
import static bot.commands.miscellaneous.MiscellaneousHandler.runMiscellaneousCommand;

public class CommandHandler extends ListenerAdapter {
    CommandReceivedEvent e;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        Thread thread = new Thread(() -> e = new CommandReceivedEvent(event));
        thread.start();

        if (event.getMessage().getContentRaw().startsWith(".")) {
            try {
                thread.join();
            } catch (InterruptedException ignore) {}
            if (runCommand(e)) {
                return;
            }
        }

        runMiscellaneousCommand(event);

        System.out.println("you fuckin failed LOL");
    }
}
