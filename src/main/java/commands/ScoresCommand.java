package commands;

import chatBot.TelegramMesData;
import chatBot.keyboards.StandardKeyboard;
import game.QuizLogic;
import writers.ITelegramWriterFactory;
import writers.IWriter;
import writers.WriterBuilder;

public class ScoresCommand extends BaseCommand {
    private QuizLogic logic;

    public ScoresCommand(QuizLogic logic) {
        super("/scores", "Получить-очки");
        this.logic = logic;
    }

    @Override
    public String getDescription() {
        return getName() + " Выводит ваш прогресс в игре.";
    }

    @Override
    public void justDoIt(TelegramMesData data, ITelegramWriterFactory writerFactory) {
        IWriter writer = writerFactory.compile(data.getChatId(), new StandardKeyboard());
        writer.printMsg("Игрок - " + data.getName() + "\nВаш уровень - "
                + logic.getLevel() + "\nВаши очки - " + logic.getScore());
    }
}
