package tracer;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import constants.MyConstants;
import drone.ControlableRemoteIF;
import drone.TracableRemoteIF;
import endPoints.EndPoint;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import pathFinder.PathPlannerContext;
import pathToNavCommands.PathToCommandContext;

public class Tracer {
	PathPlannerContext plannerContext;
	PathToCommandContext commandctx;
	private final ConsumerConnector consumer;
	private final String topic;
	private ExecutorService executor;
	
	public Tracer(String a_zookeeper, String a_groupId, String a_topic) throws Exception{
		super();
		/*this.plannerContext = new PathPlannerContext(new EndPoint(10,19,10));
		this.commandctx = new PathToCommandContext(this.plannerContext);
		this.commandctx.loadCommands();*/
		//this.drone = (TracableRemoteIF) Naming.lookup("//localhost/drone");
		//((ControlableRemoteIF)(this.drone)).go();
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
				createConsumerConfig(a_zookeeper, a_groupId));
		this.topic = a_topic;
	}
	
	public void done() throws RemoteException {
		System.out.println("Drone has reached its destination .....");
		
	}

	public void run(int a_numThreads) {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, a_numThreads);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

		// now launch all the threads
		//
		executor = Executors.newFixedThreadPool(a_numThreads);

		// now create an object to consume the messages
		//
		int threadNumber = 0;
		for (final KafkaStream stream : streams) {
			executor.submit(new ConsumerTest(stream, threadNumber));
			threadNumber++;
		}
	}

	private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId) {
		Properties props = new Properties();
		props.put("zookeeper.connect", a_zookeeper);
		props.put("group.id", a_groupId);
		props.put("zookeeper.session.timeout.ms", "400");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");

		return new ConsumerConfig(props);
	}


	public static void main(String[] args) throws Exception  {
		String zooKeeper = "localhost:2181"; //args[0];
		String groupId = "group1"; //args[1];
		String topic = MyConstants.topic; //args[2];
		int threads = 1; //Integer.parseInt(args[3]);
		Tracer tracer = new Tracer(zooKeeper,groupId,topic);
		tracer.run(threads);
	}

	
}
