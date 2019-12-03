package commands;

import chatBot.TelegramBotLogic;
import game.QuizLogic;

public class CommandData {
    protected QuizLogic quizLogic;
    protected TelegramBotLogic tgBotLogic;
    protected String chatId;
    protected String commandText;

    public CommandData(
            QuizLogic logic,
            TelegramBotLogic telegramBotLogic,
            String chatId,
            String commandText) {
        this.quizLogic = logic;
        this.tgBotLogic = telegramBotLogic;
        this.chatId = chatId;
        this.commandText = commandText;
    }

    public CommandData(QuizLogic logic) {
        this.quizLogic = logic;
        this.chatId = null;
        this.commandText = null;
        this.tgBotLogic = null;
    }
}
