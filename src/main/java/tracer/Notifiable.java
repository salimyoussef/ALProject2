package tracer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Notifiable extends Remote {
    public void notify(int x , int y , int z) throws RemoteException;
    public void done() throws RemoteException;
}
