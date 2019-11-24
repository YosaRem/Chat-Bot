package game;

import readers.IReader;
import writers.IWriter;

import java.io.IOException;

public class PlayerCreator {
    private IReader reader;
    private IWriter writer;

    public PlayerCreator(IReader reader, IWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public Player cratePlayerFromInput() {
        String name = "";
        writer.printMsg("Введите имя игрока");
        try {
            name = reader.read();
        } catch (IOException e) {
            writer.printMsg("Не могу считать имя игрока");
            writer.printMsg(e.getMessage());
            e.printStackTrace();
        }
        return new Player(name);
    }
}
