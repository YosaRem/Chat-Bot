package commands;

import chatBot.TelegramBotLogic;
import game.QuizLogic;

import java.util.ArrayList;

public class HelpFriendCommand extends BaseCommand {
    private static final HelpFriendCommand command = new HelpFriendCommand();

    public HelpFriendCommand() {
        super("/resend");
    }

    @Override
    public String getDescription() {
        return getName() + " Запросить помощь друга.";
    }

    @Override
    public void justDoIt(QuizLogic logic) {
        logic.getWriter().printMsg("Эта функция будет реализована в скорое время");
    }

    public static HelpFriendCommand getInstance() {
        return command;
    }
}
