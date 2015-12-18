package drone;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import pathToNavCommands.Command;

public interface ConfigurableRemoteIF extends Remote{
  public void loadPathCommands(ArrayList<Command> commands) throws RemoteException;
}

