package chatBot;

import java.util.Objects;

public class UserData {
    private final String name;
    private final String chatId;

    public UserData(String name, String chatId) {
        this.name = name;
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public String getChatId() {
        return chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return name.equals(userData.name)
                && chatId.equals(userData.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, chatId);
    }
}
