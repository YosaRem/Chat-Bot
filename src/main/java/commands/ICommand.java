package commands;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import writers.IWriter;


public interface ICommand {
    void justDoIt(QuizLogic logic);
}
