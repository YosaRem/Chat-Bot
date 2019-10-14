import tasks_extractor.QuizTasksExtractor;

public class Main {
    public static void main(String[] args) {
        QuizTasksExtractor extractor = new QuizTasksExtractor("resources/questions");
        IWriter writer = new ConsoleWriter();
        Game game = new Game(extractor, writer, "resources/help.txt");
        IReader reader = new ConsoleReader(game, System.in);
        game.startGame();
        while (true) {
            reader.read();
        }
    }
}
