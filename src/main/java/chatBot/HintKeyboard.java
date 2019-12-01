package chatBot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class HintKeyboard implements IKeyboard {
    private final InlineKeyboardMarkup hintKeyboard;

    public HintKeyboard(String search) {
        hintKeyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow1.add(new InlineKeyboardButton()
                .setText("Посмотреть ответ в google")
                .setCallbackData("совершен запрос")
                .setUrl("www.google.com/search?q=" + search));
        keyboardButtonsRow2.add(new InlineKeyboardButton()
                .setText("50 на 50")
                .setCallbackData("/del"));
        keyboardButtonsRow3.add(new InlineKeyboardButton()
                .setText("Помощь друга")
                .setCallbackData("/resend"));
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow3);
        hintKeyboard.setKeyboard(rowList);
    }

    @Override
    public ReplyKeyboard getKeyboard() {
        return hintKeyboard;
    }
}
