package tracer;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import drone.ControlableRemoteIF;
import drone.TracableRemoteIF;
import endPoints.EndPoint;
import pathFinder.PathPlannerContext;
import pathToNavCommands.PathToCommandContext;

public class Tracer extends UnicastRemoteObject implements Notifiable {
	TracableRemoteIF drone;
	PathPlannerContext plannerContext;
	PathToCommandContext commandctx;
	
	public Tracer() throws Exception{
		super();
		this.plannerContext = new PathPlannerContext(new EndPoint(10,19,10));
		this.commandctx = new PathToCommandContext(this.plannerContext);
		this.commandctx.loadCommands();
		this.drone = (TracableRemoteIF) Naming.lookup("//localhost/drone");
		drone.registerForNotification(this);
		((ControlableRemoteIF)(this.drone)).go();
		
		
	}

	@Override
	public void notify(int x, int y, int z) throws RemoteException {
		System.out.println("*** Drone moved to these coordinates ("+x+","+y+","+z+") ***");

	}
	
	@Override
	public void done() throws RemoteException {
		System.out.println("Drone has reached its destination .....");
		
	}

	public static void main(String[] args) throws Exception  {
		Tracer tracer = new Tracer();
		Naming.rebind("tracer", tracer);
		
	}

	
}
