package commands;

import chatBot.TelegramMesData;
import chatBot.UserData;
import game.QuizLogic;
import writers.TelegramWriter;
import writers.WriterBuilder;

import java.util.HashMap;

public class SendHelpAnswerCommand extends BaseCommand {
    private final HashMap<UserData, QuizLogic> subscribers;

    public SendHelpAnswerCommand(HashMap<UserData, QuizLogic> subscribers) {
        super("/resendanswer");
        this.subscribers = subscribers;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void justDoIt(TelegramMesData data) {
        String[] info = data.getText().split("_");
        new WriterBuilder(info[2]).compile().printMsg("Вам советуют ответить: " + info[3]);
    }
}
