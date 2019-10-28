import game.Game;
import game.Player;
import game.PlayerCreator;
import readers.*;
import tasks_extractor.QuizTasksExtractor;
import writers.ConsoleWriter;
import writers.IWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        QuizTasksExtractor extractor = new QuizTasksExtractor("resources/questions");
        IWriter writer = new ConsoleWriter();
        Game game = new Game(extractor, writer, "resources/help.txt");
        IReader reader = new ConsoleReader(game, System.in);
        Player player = new PlayerCreator(reader, writer).cratePlayerFromInput();
        game.startGame();
        while (true) {
            try {
                reader.read();
            } catch (IOException e) {
                writer.print(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
