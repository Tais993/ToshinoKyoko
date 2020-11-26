package bot;

import bot.commands.CommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static bot.music.youtube.SearchYouTube.setYtApiKey;

public class ToshinoKyoko {
    public static JDA jda;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Discord bot\\token\\toshinokyoko.token")));

        String token = br.readLine();
        br.close();

        jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_MEMBERS).build().awaitReady();
        jda.addEventListener(new CommandHandler());

        setYtApiKey();
    }

    public static JDA getJda() {
        return jda;
    }
}
