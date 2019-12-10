package chatBot.keyboards;

import chatBot.UserData;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class RequestKeyboard implements IKeyboard {
    private final InlineKeyboardMarkup requestKeyboard;

    public RequestKeyboard(ArrayList<UserData> users) {
        this.requestKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (UserData user : users) {
            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            keyboardButtonsRow1.add(new InlineKeyboardButton()
                    .setText(user.getName())
                    .setCallbackData("/resendrequest_" + user.getName() + "_" + user.getChatId()));
            rowList.add(keyboardButtonsRow1);
        }
        requestKeyboard.setKeyboard(rowList);
    }

    @Override
    public ReplyKeyboard getKeyboard() {
        return requestKeyboard;
    }
}
