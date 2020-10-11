package service;
import lombok.SneakyThrows;

import java.net.Socket;

public class SocketRunnable implements Runnable {
    private final MessageInputService messageInputService;
    private Socket socket;

    @SneakyThrows
    public SocketRunnable(Socket socket) {
        this.messageInputService = new MessageInputService(socket.getInputStream());
    }

    @SneakyThrows
    @Override
    public void run() {
        String messageFromServer;

        while ((messageFromServer = messageInputService.readMessage()) != null) {
            if (messageFromServer.equals("401")) {
                socket.close();
                System.exit(-1);
            }
            System.out.println(messageFromServer);
        }
    }
}
