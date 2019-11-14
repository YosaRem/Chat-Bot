package chatBote;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tasks_extractor.QuizTasksExtractor;

public class TelegramProgram {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        QuizTasksExtractor extractor = new QuizTasksExtractor("resources/questions");
        TelegramBot bot = new TelegramBot();
        TelegramBotLogic telegramBotLogic = new TelegramBotLogic(bot, extractor);
        bot.subscribe(telegramBotLogic);
        try {
            TelegramBotsApi botapi = new TelegramBotsApi();
            botapi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
