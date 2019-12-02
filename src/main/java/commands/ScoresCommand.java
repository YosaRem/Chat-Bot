package commands;

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
        data.quizLogic.getWriter().printMsg("Игрок - " + data.quizLogic.getPlayer().getName() + "\nВаш уровень - "
                + data.quizLogic.getGame().getLevel() + "\nВаши очки - " + data.quizLogic.getPlayer().getScore());
    }

    public static BaseCommand getInstance() {
        return scoresCommand;
    }
}
