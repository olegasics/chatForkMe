package ru.forksme.network;
import ru.forksme.client.sample.User;
import ru.forksme.client.sample.DatabaseHandler;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.sql.ResultSet;

public class TCPConnection {

    private final Socket socket;
    private final Thread rxThread;
    private final TCPConnectionListener eventListener;
    private final BufferedWriter out;
    private final BufferedReader in;

    public TCPConnection(TCPConnectionListener eventListener,String ipAddr, int port) throws IOException {
        this(eventListener, new Socket(ipAddr, port));


    }

    public TCPConnection(TCPConnectionListener eventListener, Socket socket) throws IOException {
        this.eventListener = eventListener;
        this.socket = socket;

       in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
       out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));

       rxThread = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   eventListener.onConnectionReady(TCPConnection.this);
                   while (!rxThread.isInterrupted()) {

                    eventListener.onReceiveString(TCPConnection.this, in.readLine());
                   }

               } catch (IOException e) {
                   eventListener.onException(TCPConnection.this, e);


               } finally {
                   eventListener.onDisconnect(TCPConnection.this);

               }

           }
       });
       rxThread.start();

    }


        public synchronized void sendString(String value) {
            try {
                out.write(value + "\r\n");
                out.flush();
            } catch (IOException e) {
                eventListener.onException(TCPConnection.this, e);
                disconnect();
            }
        }

        public synchronized void disconnect() {
            rxThread.isInterrupted();
            try {
                socket.close();
            } catch (IOException e) {
                eventListener.onException(TCPConnection.this, e);
            }
        }
    User user = new User();
    DatabaseHandler data = new DatabaseHandler();
    ResultSet resultSet = data.getUser(user);

    @Override
    public String toString() {
        return "TCPConnection " + resultSet.toString() + user.getUserName()  +  socket.getInetAddress() + ": " + socket.getPort();
    }
}
