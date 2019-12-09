package commands;


import chatBot.TelegramMesData;
import game.QuizLogic;
import writers.ITelegramWriterFactory;
import writers.IWriter;

public class StartCommand extends BaseCommand {
    private QuizLogic logic;

    public StartCommand(QuizLogic logic) {
        super("/start");
        this.logic = logic;
    }

    @Override
    public String getDescription() {
        return getName() + " Запускает новую игру. Все достижения будут сброшены.";
    }

    @Override
    public void justDoIt(TelegramMesData data, ITelegramWriterFactory writerFactory) {
        logic.startGame();
    }
}
