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

public class Roles implements ICommand {
    String command = "roles";
    String category = "general";
    String commandExample = "roles (role name)\n";
    String shortCommandDescription = "Get a role.";
    String fullCommandDescription = """
            Get a role, possible roles to use are:
            standard, taiko, ctb, mania
        """;

    HashMap<String, RoleO> roles = new HashMap<>(Map.ofEntries(
            entry("standard", new RoleO(781219059751845901L, ":red_circle:")),
            entry("taiko", new RoleO(781219057910415420L, ":drum:")),
            entry("ctb", new RoleO(781219195530641409L, ":apple:")),
            entry("mania", new RoleO(781219217386242089L, ":musical_keyboard:"))
    ));

    @Override
    public void command(CommandReceivedEvent event) {
        if (!event.isFromGuild()) {
            event.getChannel().sendMessage(getErrorHelp("Requires to be in a guild!")).queue();
            return;
        }

        if (!event.hasArgs()) {
            event.getChannel().sendMessage(getIAErrorHelp("Possible roles: standard, taiko, ctb, mania")).queue();
            return;
        }

        String[] args = event.getArgs();

        RoleO roleO = roles.get(args[0]);

        if (roleO == null) {
            event.getChannel().sendMessage(getIAErrorHelp("Possible roles: standard, taiko, ctb, mania")).queue();
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
