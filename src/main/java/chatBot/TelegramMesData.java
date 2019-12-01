package chatBot;

public class TelegramMesData {
    private String name;
    private String chatId;
    private String text;

    public TelegramMesData(String name, String chatId, String text) {
        this.name = name;
        this.chatId = chatId;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }
}
