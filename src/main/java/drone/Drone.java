package drone;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import endPoints.EndPoint;
import pathToNavCommands.Command;
import pathToNavCommands.GoAheadCommand;
import tracer.Notifiable;

public class Drone extends UnicastRemoteObject implements ConfigurableRemoteIF, ControlableRemoteIF, TracableRemoteIF,Moveable {
	
	ArrayList<Command> commands;
	//ArrayList<Notifiable> tracers = new ArrayList<Notifiable>();
	Notifiable tracer;

	public Drone() throws RemoteException{
		super();
	}

	@Override
	public void loadPathCommands(ArrayList<Command> commands) throws RemoteException {
		this.commands = commands;
		System.out.println("Path has been loaded successfully ......");
	}

	@Override
	public void registerForNotification(Notifiable n) throws RemoteException {
		//this.tracers.add(n);
		this.tracer = n;
		System.out.println("Tracer has been added successfully......");
	}

	@Override
	public void unregisterForNotification(Notifiable n) throws RemoteException {
		//this.tracers.remove(n);
		this.tracer=null;
	}
	
	@Override
	public void goAhead(EndPoint point) {
		try {
			this.tracer.notify(point.getX(), point.getY(), point.getZ());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("=====");
	}

	@Override
	public void go() throws RemoteException {
		System.out.println("Drone is up and heading the destination....");
		System.out.print("[");
		Iterator<Command> ir = commands.iterator();
		while(ir.hasNext()){
		    Command c = ir.next();
		    ((GoAheadCommand)c).setDrone(this);
		    c.execute();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(">]");
		tracer.done();
		System.out.println("Drone has reached its destination .....");
		
		
	}
	public static void main(String [] args) throws RemoteException, MalformedURLException{
		Drone drone = new Drone();
		Naming.rebind("drone", drone);
		System.out.println("Drone is ready for a new delivery task .....");
	}

	
}
