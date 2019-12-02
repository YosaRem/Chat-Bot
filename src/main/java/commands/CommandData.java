package commands;

import chatBot.TelegramBotLogic;
import game.QuizLogic;

public class CommandData {
    protected QuizLogic quizLogic;
    protected TelegramBotLogic tgBotLogic;
    protected String userId;
    protected String commandText;

    public CommandData(
            QuizLogic logic,
            TelegramBotLogic telegramBotLogic,
            String userId,
            String commandText) {
        this.quizLogic = logic;
        this.tgBotLogic = telegramBotLogic;
        this.userId = userId;
        this.commandText = commandText;
    }

    public CommandData(QuizLogic logic) {
        this.quizLogic = logic;
        this.userId = null;
        this.commandText = null;
        this.tgBotLogic = null;
    }
}
