package commands;


import chatBot.TelegramMesData;
import chatBot.UserData;
import chatBot.keyboards.RequestKeyboard;
import game.QuizLogic;
import writers.ITelegramWriterFactory;
import writers.IWriter;
import writers.WriterBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class HelpFriendCommand extends BaseCommand {
    private HashMap<UserData, QuizLogic> subscribers;

    public HelpFriendCommand(HashMap<UserData, QuizLogic> subscribers) {
        super("/resend");
        this.subscribers = subscribers;
    }

    @Override
    public String getDescription() {
        return getName() + " Запросить помощь друга.";
    }

    @Override
    public void justDoIt(TelegramMesData data, ITelegramWriterFactory writerFactory) {
        IWriter writer = writerFactory.compile(data.getChatId());
        int userCount = 2;
        if (this.subscribers.size() == 1) {
            writer.printMsg("В данный момент нет других пользователей.");
            return;
        }
        ArrayList<UserData> otherUsers = new ArrayList<>();
        for (UserData subscriber : this.subscribers.keySet()) {
            if (subscriber.equals(data.getUser()))
                continue;
            otherUsers.add(subscriber);
        }
        Collections.shuffle(otherUsers);

        ArrayList<UserData> friends = new ArrayList<>();
        for (int i = 0; i < otherUsers.size() && i < userCount; i++) {
            UserData user = otherUsers.get(i);
            friends.add(user);
        }
        writer = writerFactory.setMsgKeyboard(new RequestKeyboard(friends)).compile(data.getChatId());
        writer.printMsg("Пользователи");
    }
}
