package chatBot.commands;

import game.QuizLogic;

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
    public void justDoIt(CommandData data) {
        data.logic.startGame();
    }

    public static BaseCommand getInstance() {
        return startCommand;
    }
}
