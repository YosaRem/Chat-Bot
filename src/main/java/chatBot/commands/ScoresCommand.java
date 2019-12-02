package chatBot.commands;

import game.QuizLogic;

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
    public void justDoIt(CommandData data) {
        data.logic.getWriter().printMsg("Игрок - " + data.logic.getPlayer().getName() + "\nВаш уровень - "
                + data.logic.getGame().getLevel() + "\nВаши очки - " + data.logic.getPlayer().getScore());
    }

    public static BaseCommand getInstance() {
        return scoresCommand;
    }
}
