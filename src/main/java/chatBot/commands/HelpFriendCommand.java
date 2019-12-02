package chatBot.commands;


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
        data.logic.getWriter().printMsg("Эта функция будет реализована в скорое время");
    }

    public static HelpFriendCommand getInstance() {
        return command;
    }
}
