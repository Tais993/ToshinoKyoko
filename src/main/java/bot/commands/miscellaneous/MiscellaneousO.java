package bot.commands.miscellaneous;

public class MiscellaneousO {
    private final String responseBegin;
    private final String responseEnd;
    private boolean pingsUser = true;

    public MiscellaneousO(String responseBegin, String responseEnd) {
        this.responseBegin = responseBegin;
        this.responseEnd = responseEnd;
    }

    public MiscellaneousO(String responseBegin, String responseEnd, boolean pingsUser) {
        this.responseBegin = responseBegin;
        this.responseEnd = responseEnd;
        this.pingsUser = pingsUser;
    }

    public String getFullResponse(String userAsMention) {
        if (pingsUser) {
            return responseBegin + userAsMention + responseEnd;
        } else {
            return responseBegin + responseEnd;
        }
    }


}
