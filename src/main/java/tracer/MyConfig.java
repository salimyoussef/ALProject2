package tracer;

import constants.MyConstants;
import kafka.admin.AdminUtils;
import kafka.utils.ZKStringSerializer$;
import org.I0Itec.zkclient.ZkClient;

import java.util.Properties;

public class MyConfig{
    public static void main(String[] args){
        // Create a ZooKeeper client
        // Create a ZooKeeper client
        int sessionTimeoutMs = 10000;
        int connectionTimeoutMs = 10000;
        ZkClient zkClient = new ZkClient("localhost:2181", sessionTimeoutMs, connectionTimeoutMs, ZKStringSerializer$.MODULE$);


        String topicName = MyConstants.topic;
        int numPartitions = 1;
        int replicationFactor = 1;
        Properties topicConfig = new Properties();
        AdminUtils.createTopic(zkClient, topicName, numPartitions, replicationFactor, topicConfig);
    }
}
