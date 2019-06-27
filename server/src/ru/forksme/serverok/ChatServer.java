package ru.forksme.serverok;

import ru.forksme.network.TCPConnection;
import ru.forksme.network.TCPConnectionListener;
import ru.forksme.client.sample.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer implements TCPConnectionListener {

    public static void main(String[] args) {
        new ChatServer();

    }

    public final List<TCPConnection> connections = new ArrayList<>();

    public ChatServer() {
        System.out.println("Server running...");
        try(ServerSocket serverSocket = new ServerSocket(8189)) {
            while(true) {
                try {
                    new TCPConnection(this, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("TCPConnection exception " + e);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
            connections.add(tcpConnection);
            sendToAllConnections("New client connected : " + tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        sendToAllConnections(value);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
            connections.remove(tcpConnection);
        sendToAllConnections("client disconnected : " + tcpConnection);

    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception " + e);

    }

    public void sendToAllConnections(String value) {
        System.out.println(value);
        final int sizeConnectiond = connections.size();
        for (int i = 0; i < sizeConnectiond; i++) connections.get(i).sendString(value);
    }

    public ChatServer(String value) {
        System.out.println(value);
        final int sizeConnectiond = connections.size();
        for (int i = 0; i < sizeConnectiond; i++) connections.get(i).sendString(value);
    }


}


