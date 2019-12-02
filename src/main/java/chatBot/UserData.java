package chatBot;

public class UserData {
    private final String name;
    private final String id;

    public UserData(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
