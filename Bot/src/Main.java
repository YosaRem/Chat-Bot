import game.QuizGame;
import game.QuizLogic;
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
        QuizGame game = new QuizGame(extractor);
        IWriter writer = new ConsoleWriter();
        ConsoleReader reader = new ConsoleReader(System.in);
        Player player = new PlayerCreator(reader, writer).cratePlayerFromInput();
        QuizLogic quizLogic = new QuizLogic(writer, player, game, "resources/help.txt");
        reader.subscribe(quizLogic);
        quizLogic.startGame();
        while (true) {
            reader.read();
        }
    }
}
