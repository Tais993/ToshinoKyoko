package bot.commands.general;

public class RoleO {
    private final long roleId;
    private final String roleEmote;

    public RoleO(long roleId, String roleEmote) {
        this.roleId = roleId;
        this.roleEmote = roleEmote;
    }

    public long getRoleId() {
        return roleId;
    }

    public String getRoleEmote() {
        return roleEmote;
    }
}
