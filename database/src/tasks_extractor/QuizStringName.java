package tasks_extractor;

public enum QuizStringName {
    QUESTION (0),
    CORRECT (1);

    private final int index;

    QuizStringName(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
