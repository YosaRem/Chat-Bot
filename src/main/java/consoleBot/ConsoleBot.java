package consoleBot;

import chatBot.commands.CommandConverter;
import game.QuizGame;
import game.QuizLogic;
import game.Player;
import game.PlayerCreator;
import readers.*;
import tasks_extractor.QuizTasksExtractor;
import writers.ConsoleWriter;
import writers.IWriter;


public class ConsoleBot {
    public static void main(String[] args) {
        QuizTasksExtractor extractor = new QuizTasksExtractor("src/main/resources/questions");
        QuizGame game = new QuizGame(extractor);
        IWriter writer = new ConsoleWriter();
        ConsoleReader reader = new ConsoleReader(System.in);
        Player player = new PlayerCreator(reader, writer).cratePlayerFromInput();
        QuizLogic quizLogic = new QuizLogic(writer, player, game, "src/main/resources/help.txt");
        reader.subscribe(quizLogic);
        CommandConverter.defineCommands("Console");
        quizLogic.startGame();
        while (true) {
            reader.read();
        }
    }
}