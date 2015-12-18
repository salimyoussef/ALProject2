package drone;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tracer.Notifiable;

public interface TracableRemoteIF extends Remote{
	// called by tracer to register for drone callbacks
    public void registerForNotification(Notifiable n) throws RemoteException;
    // called by tracer to when they exit the tracing
    public void unregisterForNotification(Notifiable n) throws RemoteException;
    
}

