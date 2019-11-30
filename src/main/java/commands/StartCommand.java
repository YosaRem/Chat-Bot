package commands;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import writers.IWriter;

public class StartCommand extends BaseCommand {
    private static final StartCommand startCommand = new StartCommand();

    public StartCommand() {
        super("/start");
    }

    @Override
    public String getDescription() {
        return getName() + " Запускает новую игру. Все достижения будут сброшены.";
    }

    @Override
    public void justDoIt(QuizLogic logic) {
        logic.startGame();
    }

    public static BaseCommand getInstance() {
        return startCommand;
    }
}
