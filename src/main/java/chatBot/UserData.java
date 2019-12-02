package chatBot;

public class UserData {
    private final String name;
    private final String sendTo;

    public UserData(String name, String sendTo) {
        this.name = name;
        this.sendTo = sendTo;
    }

    public String getName() {
        return name;
    }

    public String getSendTo() {
        return sendTo;
    }
}
