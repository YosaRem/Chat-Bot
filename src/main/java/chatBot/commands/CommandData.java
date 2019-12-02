package chatBot.commands;

import game.QuizLogic;

public class CommandData {
    protected QuizLogic logic;
    protected String userId;

    public CommandData(QuizLogic logic, String userId) {
        this.logic = logic;
        this.userId = userId;
    }

    public CommandData(QuizLogic logic) {
        this.logic = logic;
        this.userId = null;
    }
}
