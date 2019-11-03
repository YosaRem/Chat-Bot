package chatBote;

import game.Player;
import game.PlayerCreator;
import game.QuizGame;
import game.QuizLogic;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import game.QuizGame;
import game.QuizLogic;
import game.Player;
import game.PlayerCreator;
import readers.*;
import tasks_extractor.QuizTasksExtractor;
import writers.ConsoleWriter;
import writers.IWriter;
import writers.TelegramWriter;

public class TelegramProgram {
    public static void main(String[] args) {
        ApiContextInitializer.init(); // Инициализируем апи
        TelegramBotsApi botapi = new TelegramBotsApi();
        QuizTasksExtractor extractor = new QuizTasksExtractor("resources/questions");
        QuizGame game = new QuizGame(extractor);
        try {
            TelegramBot bot = new TelegramBot();
            botapi.registerBot(bot);
            IWriter writer = new TelegramWriter(bot,"531962828");
            //ConsoleReader reader = new ConsoleReader(System.in);
            Player player = new Player("Danil");
            QuizLogic quizLogic = new QuizLogic(writer, player, game, "resources/help.txt");
            bot.subscribe(quizLogic);
            quizLogic.startGame();

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
