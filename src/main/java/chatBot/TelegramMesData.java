package chatBot;

public class TelegramMesData {
    private UserData user;
    private String text;

    public TelegramMesData(String name, String chatId, String text) {
        this.user = new UserData(name, chatId);
        this.text = text;
    }

    public String getName() {
        return user.getName();
    }

    public String getChatId() {
        return user.getChatId();
    }

    public String getText() {
        return text;
    }

    public UserData getUser() {
        return user;
    }
}
