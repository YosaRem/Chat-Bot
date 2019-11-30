package chatBot;

import commands.CommandConverter;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tasks_extractor.QuizTasksExtractor;


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
            /*DefaultBotOptions options = ApiContext.getInstance(DefaultBotOptions.class);
            options.
            options.setProxyHost("166.62.83.129");
            options.setProxyPort(20333);
            //Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
            options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);*/
            TelegramBotsApi botapi = new TelegramBotsApi();
            botapi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
