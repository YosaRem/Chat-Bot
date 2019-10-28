package game;

public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void increaseScore(int value) {
        score += value;
    }

    public void resetToZero() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
