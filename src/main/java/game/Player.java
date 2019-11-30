package game;

public class Player {
    private String name;
    private int score;
    private boolean isOnlyTwoUsed;

    public Player(String name) {
        isOnlyTwoUsed = true;
        this.name = name;
        this.score = 0;
    }

    public boolean useOnlyTwoAnswer() {
        if (isOnlyTwoUsed) {
            isOnlyTwoUsed = false;
            return true;
        } else { return false; }
    }

    public void increaseScore(int value) {
        score += value;
    }

    public void resetScore() {
        score = 0;
        isOnlyTwoUsed = true;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
