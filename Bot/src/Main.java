
import taks_models.Task;
import tasks_extractor.QuizTasksExtractor;
import publisher_subscriber.ISubscriber;

public class Main {
    public static void main(String[] args) {
        QuizTasksExtractor extractor = new QuizTasksExtractor("resources/questions");
        IWriter writer = new ConsoleWriter();
        Game game = new Game(extractor, writer);
        game.startGame();
        /*while(true) {
            reader.read();
        }*/
    }
}
