package chatBot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class UsersKeyboard implements IKeyboard {
    private final InlineKeyboardMarkup usersKeyboard;

    public UsersKeyboard(ArrayList<UserData> users) {
        this.usersKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (UserData user : users) {
            List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
            keyboardButtonsRow1.add(new InlineKeyboardButton().setText(user.getName()).setCallbackData("/resendTo_" + user.getId()));
            rowList.add(keyboardButtonsRow1);
        }
        usersKeyboard.setKeyboard(rowList);
    }

    @Override
    public ReplyKeyboard getKeyboard() {
        return usersKeyboard;
    }
}
