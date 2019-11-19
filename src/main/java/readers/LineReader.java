package readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LineReader {
    private int count;
    private File file;

    public LineReader(File file, int count) {
        this.count = count;
        this.file = file;
    }

    public LineReader(File file) {
        this(file, Integer.MAX_VALUE);
    }

    public ArrayList<String> read() throws IOException {
        FileReader fileReader = new FileReader(this.file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> data = new ArrayList<String>();
        while (bufferedReader.ready() && data.size() < this.count) {
            data.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        return data;
    }
}
