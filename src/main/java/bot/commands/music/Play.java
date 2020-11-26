package bot.commands.music;

import bot.commands.CommandReceivedEvent;
import bot.commands.ICommand;
import bot.music.PlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;

import static bot.music.youtube.SearchYouTube.searchYoutubeVideo;
import static bot.utils.MayPlayMusic.mayPlayMusic;
import static bot.utils.Utils.isUrl;

public class Play implements ICommand {
    String command = "about";
    String category = "general";
    String commandExample = "about";
    String shortCommandDescription = "About the bot.";
    String fullCommandDescription = "About the bot";

    String url;

    @Override
    public void command(CommandReceivedEvent event) {

        if (!mayPlayMusic(event, getCommand())) {
            return;
        }

        if (isUrl(event.getMessageWithoutCommand())) {
            url = event.getMessageWithoutCommand();
        } else {
            url = searchYoutubeVideo(event.getMessageWithoutCommand());
        }

        if (url.isEmpty()) {
            event.getChannel().sendMessage("Nothing has been found by " + url).queue();
            return;
        }

        PlayerManager manager = PlayerManager.getInstance();
        manager.loadAndPlay(event.getTextChannel(), url);
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
