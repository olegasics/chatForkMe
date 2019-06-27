package ru.forksme.serverok;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatObServer extends Remote {
    boolean update(String username,String message) throws RemoteException;
    ArrayList<User> getOnlineUsers() throws RemoteException;
    String getUsername() throws RemoteException;
    boolean updateUI(ArrayList<String> clientList) throws RemoteException;
}
