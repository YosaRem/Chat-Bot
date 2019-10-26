public class Player {
    private String name;
    private Level level;

    public Player(String name) {
        this.name = name;
        this.level = new Level();
    }
}
