package commands;

import chatBot.TelegramBotLogic;

public interface IHint {
    String getName();

    String detDescription();

    void assist(TelegramBotLogic botLogic);
}
