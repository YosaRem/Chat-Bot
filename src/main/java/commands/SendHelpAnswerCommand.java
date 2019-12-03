package commands;

import game.QuizLogic;
import writers.TelegramWriter;

import java.util.HashMap;

public class SendHelpAnswerCommand extends BaseCommand {
    private static final SendHelpAnswerCommand sendHelpAnswerCommand = new SendHelpAnswerCommand();

    public SendHelpAnswerCommand() { super("/resendanswer"); }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void justDoIt(CommandData data) {
        HashMap<String, QuizLogic> subscribers = data.tgBotLogic.getSubscribers();
        String[] words = data.commandText.split("_");
        QuizLogic logicTo = subscribers.get(words[1]);
        TelegramWriter writer = (TelegramWriter) logicTo.getWriter();
        writer.printMsg("Вам советуют ответить: " + words[2]);
    }

    public static BaseCommand getInstance() { return sendHelpAnswerCommand; }
}
