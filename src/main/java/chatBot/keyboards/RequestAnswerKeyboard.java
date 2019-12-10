package chatBot.keyboards;

import chatBot.UserData;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class RequestAnswerKeyboard implements IKeyboard {
    private final InlineKeyboardMarkup taskKeyboard;

    public RequestAnswerKeyboard(ArrayList<String> answers, UserData user) {
        this.taskKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            keyboardButtonsRow1.add(new InlineKeyboardButton()
                    .setText(answers.get(i))
                    .setCallbackData("/resendanswer_" + user.getName() + "_" + user.getChatId() + "_" + answers.get(i)));
            rowList.add(keyboardButtonsRow1);
        }
        taskKeyboard.setKeyboard(rowList);
    }

    @Override
    public ReplyKeyboard getKeyboard() {
        return taskKeyboard;
    }
}
