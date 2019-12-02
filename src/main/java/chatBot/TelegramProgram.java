package chatBot;

import chatBot.commands.CommandConverter;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tasks_extractor.QuizTasksExtractor;


public class TelegramProgram {
    public static String questionPath = "src/main/resources/questions";

    public static void main(String[] args) {
        ApiContextInitializer.init();
        DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
        options.setProxyHost("66.110.216.221");
        options.setProxyPort(39603);
        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        QuizTasksExtractor extractor = new QuizTasksExtractor(questionPath);
        String botName = System.getenv("TelegramBotName");
        String botToken = System.getenv("TelegramBotToken");
        TelegramBot bot = new TelegramBot(botName, botToken, options);
        TelegramBotLogic telegramBotLogic = new TelegramBotLogic(bot, extractor);
        CommandConverter.defineCommands("Telegram");
        bot.subscribe(telegramBotLogic);

        try {
            TelegramBotsApi botapi = new TelegramBotsApi();
            botapi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
