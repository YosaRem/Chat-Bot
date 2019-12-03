package commands;


import chatBot.RequestKeyboard;
import chatBot.UserData;
import game.QuizLogic;
import writers.TelegramWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class HelpFriendCommand extends BaseCommand {
    private static final HelpFriendCommand command = new HelpFriendCommand();

    public HelpFriendCommand() {
        super("/resend");
    }

    @Override
    public String getDescription() {
        return getName() + " Запросить помощь друга.";
    }

    @Override
    public void justDoIt(CommandData data) {
        int userCount = 2;
        HashMap<String, QuizLogic> subscribers = data.tgBotLogic.getSubscribers();
        if (subscribers.size() == 1) {
            data.quizLogic.getWriter().printMsg("В данный момент нет других пользователей.");
            return;
        }
        ArrayList<String> otherUsers = new ArrayList<>();
        for (String subscriber : subscribers.keySet()) {
            if (subscriber.equals(data.chatId))
                continue;
            otherUsers.add(subscriber);
        }
        Collections.shuffle(otherUsers);

        int number = 0;
        Random rnd = new Random();
        if (otherUsers.size() > userCount) {
            number = rnd.nextInt(otherUsers.size() - userCount);
        }

        ArrayList<UserData> friends = new ArrayList<>();
        for (int i = number; i < otherUsers.size() && i < number + userCount; i++) {
            String tmpId = otherUsers.get(i);
            friends.add(new UserData(subscribers.get(tmpId).getPlayer().getName(), tmpId));
        }
        TelegramWriter writer = (TelegramWriter) data.quizLogic.getWriter();
        writer.setKeyboard(new RequestKeyboard(friends));
        writer.printMsg("Пользователи");
    }

    public static HelpFriendCommand getInstance() {
        return command;
    }
}
