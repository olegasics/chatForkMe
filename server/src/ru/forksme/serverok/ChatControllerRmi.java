package ru.forksme.serverok;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ChatControllerRmi extends Remote {

    boolean notifyAllClients(String username, String message) throws RemoteException;
    ArrayList<User> getAll() throws RemoteException, SQLException;
    User get(String username) throws RemoteException, SQLException;
    boolean addChatObserver(ChatObServer chatObserver)throws RemoteException;
    boolean removeChatObserver(ChatObServer chatObserver)throws RemoteException;
    boolean isReserved(String username) throws RemoteException;
    boolean checkCredentials(String username, String password) throws RemoteException, SQLException;
    boolean updateClientList() throws RemoteException;
    boolean addNewUser(User user) throws RemoteException, SQLException;
}
