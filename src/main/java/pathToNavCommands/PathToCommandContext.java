package pathToNavCommands;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import drone.ConfigurableRemoteIF;
import pathFinder.Path;
import pathFinder.PathPlannerContext;

public class PathToCommandContext {
	private ConfigurableRemoteIF drone;
	private PathPlannerContext pathPlannerContext;
	private PathToComandStrategy converter;

	public PathToCommandContext(PathPlannerContext pathPlannerContext) throws MalformedURLException, RemoteException, NotBoundException {
		this.pathPlannerContext = pathPlannerContext;
		this.converter = new DummyPathConverter();
		this.drone = (ConfigurableRemoteIF)Naming.lookup("//localhost/drone");
	}

	public void loadCommands() throws RemoteException{
		Path path = this.pathPlannerContext.findPath();
		ArrayList<Command> commands = this.converter.convertPath(path);
		this.drone.loadPathCommands(commands);
	}
	

}
