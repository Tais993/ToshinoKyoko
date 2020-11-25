package bot.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;

public class CommandReceivedEvent {
    private final JDA jda;

    private final MessageChannel channel;

    private final User user;

    private Guild guild;
    private Member member;
    private TextChannel textChannel;

    private final Message message;
    private final String messageContentRaw;

    private String[] args;

    private final String prefix;
    private final String command;

    private final String messageWithoutCommand;

    boolean isFromGuild;
    boolean hasArgs;

    public CommandReceivedEvent(MessageReceivedEvent event) {
        jda = event.getJDA();

        channel = event.getChannel();

        user = event.getAuthor();

        message = event.getMessage();
        messageContentRaw = message.getContentRaw();

        args = messageContentRaw.split(" ");
        prefix = args[0].charAt(0) + "";
        command = args[0].replace(prefix, "");

        messageWithoutCommand = messageContentRaw.replace(command + " ", "");

        isFromGuild = event.isFromGuild();

        if (args.length >= 2) {
            hasArgs = true;
            args = Arrays.stream(args).skip(1).toArray(String[]::new);
        } else {
            hasArgs = false;
        }

        if (event.isFromGuild()) {
            guild = event.getGuild();
            member = event.getMember();
            textChannel = event.getTextChannel();
        }
    }

    public JDA getJDA() {
        return jda;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public User getAuthor() {
        return user;
    }

    public Guild getGuild() {
        return guild;
    }

    public Member getMember() {
        return member;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }

    public Message getMessage() {
        return message;
    }

    public String getMessageContentRaw() {
        return messageContentRaw;
    }

    public String[] getArgs() {
        return args;
    }

    public String getCommand() {
        return command;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getMessageWithoutCommand() {
        return messageWithoutCommand;
    }

    public boolean isFromGuild() {
        return isFromGuild;
    }

    public boolean hasArgs() {
        return hasArgs;
    }
}
