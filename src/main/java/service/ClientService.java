package service;

import lombok.SneakyThrows;
import utils.MessageSource;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Locale;
import java.util.ResourceBundle;

public class ClientService {
    public static final String HOST = "localhost";
    public static final int PORT = 0001;
    private final MessageInputService consoleInputService = new MessageInputService(System.in);


    @SneakyThrows
    public void start() {

        Socket socket = new Socket(HOST, PORT);
        if (socket.isConnected()) {
            System.out.println(MessageSource.getMessage("clientConnected"));

            new Thread(new SocketRunnable(socket)).start();

            MessageOutputService messageOutputService = new MessageOutputService(socket.getOutputStream());

            System.out.println(MessageSource.getMessage("hiDear"));
            System.out.println(MessageSource.getMessage("enterYourLogin"));
            String login = consoleInputService.readMessage();
            System.out.println(MessageSource.getMessage("enterYourPassword"));
            String password = consoleInputService.readMessage();
            System.out.println(MessageSource.getMessage("registration"));
            System.out.println(MessageSource.getMessage("authorisation"));
            String autoOrReg = consoleInputService.readMessage();
            String auto = "1";
            String reg = "2";
            String message = null;
            if (autoOrReg.equals(auto)) {
                message = "!@#$auto" + login + ":" + password;
            } else if (autoOrReg.equals(reg)) {
                message = "!@#$reg" + login + ":" + password;
            }
            messageOutputService.writeMessage(message);

            String consoleInput;
            while ((consoleInput = consoleInputService.readMessage()) != null) {
                messageOutputService.writeMessage(consoleInput);
            }
        }
    }
}
