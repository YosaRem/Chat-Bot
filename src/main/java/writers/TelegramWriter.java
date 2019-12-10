package writers;

import chatBot.*;

import chatBot.keyboards.IKeyboard;
import chatBot.keyboards.TaskKeyboard;
import taks_models.QuizTask;

import java.util.ArrayList;

public class TelegramWriter implements IWriter {
    private ITelegramBot bot;
    private String userId;
    private IKeyboard keyboard;

    public TelegramWriter(ITelegramBot telegramBot, String userId, IKeyboard keyboard) {
        bot = telegramBot;
        this.userId = userId;
        this.keyboard = keyboard;
    }

    @Override
    public void printMsg(String message) {
        bot.sendMsg(userId, message, keyboard);
    }

    @Override
    public void printTask(QuizTask task) {
        bot.sendMsg(userId, task.getQuestion(), new TaskKeyboard(new ArrayList<>(task.getOptions().values())));
    }
}
