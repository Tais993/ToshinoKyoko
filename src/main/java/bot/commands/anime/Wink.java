package bot.commands.anime;

import bot.commands.CommandReceivedEvent;
import bot.commands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import static bot.utils.TenorApi.getGifTypeAsUrl;
import static bot.utils.Utils.getCurrentColor;

public class Wink implements ICommand {
    String command = "wink";
    String category = "anime";
    String commandExample = "wink (member ID/ member mention)\n";
    String shortCommandDescription = "Wink to a friend, or yourself.";
    String fullCommandDescription = "Wink to a friend, or yourself.\n" +
            "You can use a members user ID or mention him.";

    @Override
    public void command(CommandReceivedEvent event) {
        EmbedBuilder eb = new EmbedBuilder();

        Member mentionedMember;

        if (!event.hasArgs()) {
            mentionedMember = event.getMember();
        } else {
            mentionedMember = event.getFirstArgAsMember();
        }

        String mentionedMemberName = mentionedMember.getEffectiveName();
        String authorName = event.getMember().getEffectiveName();

        eb.setTitle(authorName + " winks to " + mentionedMemberName);
        eb.setImage(getGifTypeAsUrl("anime%20wink"));
        eb.setColor(getCurrentColor());

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
