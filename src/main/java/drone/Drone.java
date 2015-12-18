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

public class Drone implements ConfigurableRemoteIF, ControlableRemoteIF,Moveable {
	
	ArrayList<Command> commands;

	public Drone() throws RemoteException{
		super();
	}

	@Override
	public void loadPathCommands(ArrayList<Command> commands) throws RemoteException {
		this.commands = commands;
		System.out.println("Path has been loaded successfully ......");
	}
	
	@Override
	public void goAhead(EndPoint point) {
		//A remplacer cette notif par un message envoye au MOM qui sera consomme par le drone
		//this.tracer.notify(point.getX(), point.getY(), point.getZ());
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
		//A remplacer par l'envoi d'un message au MOM qui sera consomme par l'Event Manager
		//tracer.done();
		System.out.println("Drone has reached its destination .....");
	}

	public static void main(String [] args) throws RemoteException, MalformedURLException{
		Drone drone = new Drone();
		Naming.rebind("drone", drone);
		System.out.println("Drone is ready for a new delivery task .....");
	}

	
}
