package drone;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import constants.MyConstants;
import endPoints.EndPoint;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.json.JSONObject;
import pathToNavCommands.Command;
import pathToNavCommands.GoAheadCommand;

public class Drone extends UnicastRemoteObject implements ConfigurableRemoteIF, ControlableRemoteIF,Moveable {

	ArrayList<Command> commands;
	Producer<String,String> producer;

	public Drone() throws RemoteException{
		super();
		Properties props = new Properties();
		props.put("metadata.broker.list", "localhost:9092");
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
		String msg = "x : "+point.getX() + ", y : "+point.getY()+", z : "+point.getZ();
		/*json.append("x",point.getX());
		json.append("y", point.getY());
		json.append("z", point.getZ());*/
		KeyedMessage<String, String> data = new KeyedMessage<String, String>(MyConstants.topic,msg);
		producer.send(data);
	}

	public void go() throws RemoteException {
		System.out.println("Drone is up and heading the destination....");
		System.out.print("[");
		/*Iterator<Command> ir = commands.iterator();
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
		}*/
		Iterator<Command> ir = commands.iterator();
		String msg;
		while(ir.hasNext()) {
			Command c = ir.next();
			((GoAheadCommand)c).setDrone(this);
			c.execute();
		}
		producer.close();

		System.out.println(">]");
		//A remplacer par l'envoi d'un message au MOM qui sera consomme par l'Event Manager
		//tracer.done();
		System.out.println("Drone has reached its destination .....");
	}

	public static void main(String [] args) throws RemoteException, MalformedURLException, InterruptedException {
		final Drone drone = new Drone();
		System.out.println("Drone is ready for a new delivery task .....");
		ArrayList<Command> mesCommandes = new ArrayList<>();
		mesCommandes.add(new GoAheadCommand(new EndPoint(2,3,4)));
		mesCommandes.add(new GoAheadCommand(new EndPoint(2,5,4)));
		mesCommandes.add(new GoAheadCommand(new EndPoint(1,3,4)));
		mesCommandes.add(new GoAheadCommand(new EndPoint(2,3,8)));
		drone.loadPathCommands(mesCommandes);
		drone.go();
	}


}
