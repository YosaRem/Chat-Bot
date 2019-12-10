package commands;

import chatBot.TelegramMesData;
import chatBot.UserData;
import chatBot.keyboards.RequestAnswerKeyboard;
import chatBot.keyboards.StandardKeyboard;
import game.QuizLogic;
import org.telegram.telegrambots.meta.api.objects.User;
import taks_models.QuizTask;
import writers.ITelegramWriterFactory;
import writers.IWriter;
import writers.TelegramWriter;
import writers.WriterBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class ResendRequestCommand extends BaseCommand {
    private final HashMap<UserData, QuizLogic> subscribers;

    public ResendRequestCommand(HashMap<UserData, QuizLogic> subscribers) {
        super("/resendrequest", "Переслать-вопрос");
        this.subscribers = subscribers;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void justDoIt(TelegramMesData data, ITelegramWriterFactory writerFactory) {
        String[] info = data.getText().split("_");
        UserData recipient = new UserData(info[1], info[2]);
        IWriter writer = writerFactory.compile(recipient.getChatId(), new StandardKeyboard());
        QuizLogic logicFrom = subscribers.get(data.getUser());
        QuizTask task = logicFrom.getCurrentTask();
        writer.printMsg("Игрок " + data.getName() + " просит помочь с задачей");
        writerFactory
                .compile(recipient.getChatId(), new RequestAnswerKeyboard(new ArrayList<>(task.getOptions().values()), data.getUser()))
                .printMsg(task.getQuestion());

    }
}
