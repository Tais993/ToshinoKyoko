package bot.utils;

import bot.commands.CommandReceivedEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import static bot.commands.CommandMap.getErrorHelpCommand;

public class MayPlayMusic {
    public static boolean mayPlayMusic(CommandReceivedEvent e, String commandName) {
        if (!e.isFromGuild()) {
            e.getChannel().sendMessage("You can only run this command in a Discord server/guild!").queue();
            return false;
        }

        AudioManager audioManager = e.getGuild().getAudioManager();
        GuildVoiceState memberVoiceState = e.getMember().getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            e.getChannel().sendMessage(getErrorHelpCommand(commandName).setDescription("Error: Join a voice channel before running this command.").build()).queue();
            return false;
        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();

        if (audioManager.isConnected() && audioManager.getConnectedChannel().getId().equals(voiceChannel.getId())) {
            e.getChannel().sendMessage(getErrorHelpCommand(commandName).setDescription("Error: Join the same channel as the bot.").build()).queue();
            return false;
        }

        Member selfMember = e.getGuild().getSelfMember();

        if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
            e.getChannel().sendMessage(getErrorHelpCommand(commandName).setDescription("Error: Missing VOICE_CONNECT permission").build()).queue();
            return false;
        }

        audioManager.openAudioConnection(voiceChannel);
        return true;
    }
}
