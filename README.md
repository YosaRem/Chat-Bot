Chat bot for OOP.
Задание:
1) Добавить другие подсказки:
	- если ты просишь помощь друга, то ему приходит данный вопрос, на который он отвечает и ответ отправляется попросившему помощь.
	- добавить перенаправление вопроса в гугл.
	
2) Добавить сущность команд.
3) Автоматически формировать Help.

Доп. Задания для телеграмм:

1) Добавить стартове меню.
2) Добавить inlineKeyboard.
3) Подсказки и помощь оставить в ReplyKeyboard.

Выполненные правки: (Показали на паре)
1) Все зависимости команды должны принимать через конструктор.
2) Начать игру и прочие тоже должны стать командами.
3) Выпилить статику. Либо фабрика списка команд, которая создаёт список принимая, к примеру, чат id.
4) Выпилить консоль.
5) Метод justDoIt не должен принимать больше, чем TelegramMesData.
6) Фабрика writers на основе chatId внутри ResendRequestCommand.
