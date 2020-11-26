package bot.commands.general;

import bot.commands.CommandReceivedEvent;
import bot.commands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import static bot.utils.Time.getTextDateFromOffset;

public class Userinfo implements ICommand {
    String command = "userinfo";
    String category = "general";
    String commandExample = "userinfo";
    String shortCommandDescription = "Get basic info about a user.";
    String fullCommandDescription = "Get basic info about a user";

    @Override
    public void command(CommandReceivedEvent event) {
        EmbedBuilder eb = getEmbed();

        User author = event.getAuthor();
        Member member = event.getMember();

        eb.setAuthor(author.getAsTag(), author.getAvatarUrl(), author.getAvatarUrl());
        eb.setThumbnail(author.getAvatarUrl());

        if (event.isFromGuild()) {
            GuildVoiceState voiceState = member.getVoiceState();
            eb.addField("Nickname", event.isFromGuild() ? member.getEffectiveName() : "None", false);
            eb.addField("VC State", (voiceState.inVoiceChannel()) ? voiceState.getChannel().getName() : "None", false);
        } else {
            eb.addField("Nickname", "None", false);
            eb.addField("VC State", "None", false);
        }

        eb.addField("Status/Game", "None", false);
        eb.addField("Join date", getTextDateFromOffset(author.getTimeCreated()), false);

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
