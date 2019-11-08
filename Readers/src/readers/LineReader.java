package readers;

import java.io.*;
import java.util.ArrayList;

public class LineReader {
    private int count;
    private File file;
    private ArrayList<String> data;

    public LineReader(File file, int count) {
        this.count = count;
        this.file = file;
        this.data = new ArrayList<>();
    }

    public LineReader(File file) {
        this(file, Integer.MAX_VALUE);
    }

    public ArrayList<String> read() throws IOException {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while (bufferedReader.ready() && this.data.size() < this.count) {
            this.data.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        return (ArrayList<String>) data.clone();
    }
}
