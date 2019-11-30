package chatBot;

import commands.DeleteCommand;
import commands.ScoresCommand;
import commands.CommandConverter;
import commands.StartCommand;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import tasks_extractor.QuizTasksExtractor;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class TelegramProgram {
    public static String questionPath = "src/main/resources/questions";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        QuizTasksExtractor extractor = new QuizTasksExtractor(questionPath);
        String botName = System.getenv("TelegramBotName");
        String botToken = System.getenv("TelegramBotToken");
        TelegramBot bot = new TelegramBot(botName, botToken);
        TelegramBotLogic telegramBotLogic = new TelegramBotLogic(bot, extractor);
        CommandConverter.defineCommands();
        bot.subscribe(telegramBotLogic);
        try {
            TelegramBotsApi botapi = new TelegramBotsApi();
            botapi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
