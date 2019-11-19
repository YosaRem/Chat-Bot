package logic;

import writers.IWriter;

public class FakeWriter implements IWriter {
    public String output;

    @Override
    public void print(String message) {
        output = message;
    }

    public String getOutput() {
        return output;
    }
}
