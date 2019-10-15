import publisher_subscriber.ISubscriber;
import taks_models.QuizTask;
import taks_models.QuizTaskValue;
import taks_models.Task;
import tasks_extractor.QuizTasksExtractor;

import java.io.*;

public class Game implements ISubscriber {
    private QuizTasksExtractor extractor;
    private IWriter writer;
    private QuizTask currentTask;
    private String path;

    public Game(QuizTasksExtractor extractor, IWriter writer, String instructionsPath) {
        this.extractor = extractor;
        this.writer = writer;
        this.path = instructionsPath;
    }

    public void startGame() {
        printHelp();
        try {
            currentTask = extractor.getRandomTask();
            printTask();
        } catch (FileNotFoundException e) {
            writer.print("Не получается считать задание из файла " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void continueGame(String input) {
        switch (input) {
            case "/help": {
                printHelp();
                break;
            }
            default: {
                try {
                    if (currentTask.checkAnswer(input)) {
                        writer.print("Правильно!");
                    } else {
                        writer.print("Увы, но это не так.");
                        writer.print("Правильный ответ - " + currentTask.getRightAnswer());
                    }
                } catch (NumberFormatException e) {
                    writer.print("Неправильный ответ!");
                    writer.print("Правильный ответ - " + currentTask.getRightAnswer());
                }
                try {
                    currentTask = extractor.getRandomTask();
                    printTask();
                } catch (FileNotFoundException e) {
                    writer.print("Не получается считать задание из файла " + e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printHelp() {
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader lineReader = new BufferedReader(fileReader);
            String line = lineReader.readLine();
            while (line != null) {
                writer.print(line);
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printTask() {
        writer.print(new QuizTaskValue(currentTask).getDescription());
    }

    @Override
    public void objectModified(String data) {
        continueGame(data);
    }
}