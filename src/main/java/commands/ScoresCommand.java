package commands;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import writers.IWriter;

public class ScoresCommand extends BaseCommand {
    private static final ScoresCommand scoresCommand = new ScoresCommand();

    public ScoresCommand() {
        super("/scores");
    }

    @Override
    public String getDescription() {
        return getName() + " Выводит ваш прогресс в игре.";
    }

    @Override
    public void justDoIt(QuizLogic logic) {
        logic.getWriter().printMsg("Игрок - " + logic.getPlayer().getName() + "\nВаш уровень - "
                + logic.getGame().getLevel() + "\nВаши очки - " + logic.getPlayer().getScore());
    }

    public static BaseCommand getInstance() {
        return scoresCommand;
    }
}
