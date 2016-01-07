package tracer;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import org.json.JSONObject;

public class ConsumerTest implements Runnable {
    private KafkaStream<byte[],byte[]> m_stream;
    private int m_threadNumber;

    public ConsumerTest(KafkaStream<byte[],byte[]> a_stream, int a_threadNumber) {
        m_threadNumber = a_threadNumber;
        m_stream = a_stream;
    }

    public void run() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext()){
           // String msg = new String(it.next().message());
           // JSONObject json = new JSONObject(msg);
            StringBuilder str = new StringBuilder();
            String msg = new String(it.next().message());
            System.out.println("*** Drone moved to these coordinates "+msg);
                   // "("+json.getString("x")+","+json.getString("y")+","+json.getString("z")+") ***");
        }
        System.out.println("Shutting down Thread: " + m_threadNumber);
    }
}