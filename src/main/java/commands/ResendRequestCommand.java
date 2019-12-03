package commands;

import chatBot.RequestAnswerKeyboard;
import game.QuizLogic;
import taks_models.QuizTask;
import writers.TelegramWriter;

import java.util.ArrayList;
import java.util.HashMap;

public class ResendRequestCommand extends BaseCommand {
    private static final ResendRequestCommand resendRequestCommand = new ResendRequestCommand();

    public ResendRequestCommand() {
        super("/resendrequest");
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void justDoIt(CommandData data) {
        HashMap<String, QuizLogic> subscribers = data.tgBotLogic.getSubscribers();
        String to = data.commandText.split("_")[1];
        QuizLogic logicTo = subscribers.get(to);
        QuizLogic logicFrom = subscribers.get(data.chatId);
        TelegramWriter writer = (TelegramWriter) logicTo.getWriter();
        QuizTask task = logicFrom.getCurrentTask();
        writer.printMsg("Игрок " + logicFrom.getPlayer().getName() + " просит помочь с задачей");
        writer.setKeyboard(new RequestAnswerKeyboard(new ArrayList<>(task.getOptions().values()), data.chatId));
        writer.printMsg(task.getQuestion());
    }

    public static BaseCommand getInstance() {
        return resendRequestCommand;
    }
}
