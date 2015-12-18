package drone;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import endPoints.EndPoint;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import pathToNavCommands.Command;
import pathToNavCommands.GoAheadCommand;

public class Drone implements ConfigurableRemoteIF, ControlableRemoteIF,Moveable {
	
	ArrayList<Command> commands;
	Producer<String,String> producer;

	public Drone() throws RemoteException{
		super();
		Properties props = new Properties();
		props.put("metadata.broker.list", "localhost:9093");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		//Partitionnement pas important pour l'instant
		//props.put("partitioner.class", "SimplePartitioner");
		props.put("request.required.acks", "1");
		ProducerConfig config = new ProducerConfig(props);

		producer = new Producer<String, String>(config);
	}

	public void loadPathCommands(ArrayList<Command> commands) throws RemoteException {
		this.commands = commands;
		System.out.println("Path has been loaded successfully ......");
	}

	public void goAhead(EndPoint point) {
		
		System.out.print("=====");
	}

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
