package writers;

import chatBot.HelpKeyboard;
import chatBot.ITelegramBot;
import chatBot.TaskKeyboard;
import chatBot.TelegramBot;
import taks_models.QuizTask;

import java.util.ArrayList;

public class TelegramWriter implements IWriter {
    private ITelegramBot bot;
    private String userId;

    public TelegramWriter(ITelegramBot telegramBot, String userId) {
        bot = telegramBot;
        this.userId = userId;
    }

    @Override
    public void printMsg(String message) {
        bot.sendMsg(userId, message, new HelpKeyboard());
    }

    @Override
    public void printTask(QuizTask task) {
        bot.sendMsg(userId, task.getQuestion(), new TaskKeyboard(new ArrayList<>(task.getOptions().values())));
    }
}
