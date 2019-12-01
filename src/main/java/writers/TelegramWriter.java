package writers;

import chatBot.*;

import taks_models.QuizTask;

import java.util.ArrayList;

public class TelegramWriter implements IWriter {
    private ITelegramBot bot;
    private String userId;
    private IKeyboard standardKeyboard;
    private IKeyboard currentKeyboard;
    private boolean isUpdateKeyboard;

    public TelegramWriter(ITelegramBot telegramBot, String userId, IKeyboard keyboard) {
        bot = telegramBot;
        this.userId = userId;
        this.standardKeyboard = keyboard;
        this.isUpdateKeyboard = false;
    }

    public void setKeyboard(IKeyboard keyboard) {
        this.currentKeyboard = keyboard;
        this.isUpdateKeyboard = true;
    }

    @Override
    public void printMsg(String message) {
        System.out.println(message + " " + isUpdateKeyboard);
        if (this.isUpdateKeyboard) {
            bot.sendMsg(userId, message, currentKeyboard);
            this.isUpdateKeyboard = false;
        } else {
            bot.sendMsg(userId, message, standardKeyboard);
        }
    }

    @Override
    public void printTask(QuizTask task) {
        bot.sendMsg(userId, task.getQuestion(), new TaskKeyboard(new ArrayList<>(task.getOptions().values())));
    }
}
