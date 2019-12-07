package chatBot;

public class ResenderData {
    private final String name;
    private final String sendTo;

    public ResenderData(String name, String sendTo) {
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
