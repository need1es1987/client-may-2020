package service;

import java.io.OutputStream;
import java.io.PrintWriter;

public class MessageOutputService {

    private final PrintWriter writer;

    public MessageOutputService(OutputStream outputStream) {
        this.writer = new PrintWriter(outputStream);
    }

    public void writeMessage(String message) {
        writer.println(message);
        writer.flush();
    }

}
