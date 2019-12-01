package commands;

import game.QuizLogic;

public class HelpFriendCommand extends BaseCommand {
    public static final HelpFriendCommand command = new HelpFriendCommand();

    public HelpFriendCommand() {
        super("/resend");
    }

    @Override
    public String getDescription() {
        return "Запросить помощь друга";
    }

    @Override
    public void justDoIt(QuizLogic logic) {
        logic.getWriter().printMsg("Эта функция будет реализована в скорое время");
    }

    public static HelpFriendCommand getInstance() {
        return command;
    }
}
