package writers;

import chatBot.*;

import taks_models.QuizTask;
import java.util.ArrayList;

public class TelegramWriter implements IWriter {
    private ITelegramBot bot;
    private String userId;
    private IKeyboard currentKeyboard;

    public TelegramWriter(ITelegramBot telegramBot, String userId, IKeyboard keyboard) {
        bot = telegramBot;
        this.userId = userId;
        this.currentKeyboard = keyboard;
    }

    public void setKeyboard(IKeyboard keyboard) {
        this.currentKeyboard = keyboard;
    }

    @Override
    public void printMsg(String message) {
        bot.sendMsg(userId, message, this.currentKeyboard);
    }

    @Override
    public void printTask(QuizTask task) {
        bot.sendMsg(userId, task.getQuestion(), new TaskKeyboard(new ArrayList<>(task.getOptions().values())));
    }
}
