package bot.commands.general;

import bot.commands.CommandReceivedEvent;
import bot.commands.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class Regions implements ICommand {
    String command = "regions";
    String category = "general";
    String commandExample = "regions (region name)\n";
    String shortCommandDescription = "Get a region role.";
    String fullCommandDescription = """
            Get a region role, possible roles to use are:
            NA, EU, AU
        """;

    HashMap<String, RoleO> roles = new HashMap<>(Map.ofEntries(
            entry("NA", new RoleO(781257495913168947L, ":flag_us:")),
            entry("EU", new RoleO(781257614414970890L, ":flag_eu:")),
            entry("AU", new RoleO(781257632274972743L, ":flag_au:"))
    ));

    @Override
    public void command(CommandReceivedEvent event) {
        if (!event.isFromGuild()) {
            event.getChannel().sendMessage(getErrorHelp("Requires to be in a guild!")).queue();
            return;
        }

        if (!event.hasArgs()) {
            event.getChannel().sendMessage(getIAErrorHelp("Possible roles: NA, EU, AU")).queue();
            return;
        }

        String[] args = event.getArgs();

        RoleO roleO = roles.get(args[0]);

        if (roleO == null) {
            event.getChannel().sendMessage(getIAErrorHelp("Possible roles: NA, EU, AU")).queue();
            return;
        }

        Role role = event.getGuild().getRoleById(roleO.getRoleId());
        List<Role> roles = event.getMember().getRoles();

        EmbedBuilder eb = new EmbedBuilder();

        if (roles.contains(role)) {
            event.getGuild().removeRoleFromMember(event.getAuthor().getId(), role).queue();

            eb.setColor(Color.RED);
            eb.setTitle("Role Removed!");
            eb.setDescription("You no longer have the **" + role.getName() + "** Role!");
        } else {
            
            event.getGuild().addRoleToMember(event.getAuthor().getId(), role).queue();

            eb.setColor(Color.GREEN);
            eb.setTitle("Role Granted!");

            eb.setDescription("You have been granted the **" + role.getName() + "** Role! (" + roleO.getRoleEmote() + ")");
        }

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
