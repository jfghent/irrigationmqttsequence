/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sstimeon;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author Jon
 */
public class SSTimeOn {

        private static MqttClient mqttClient;
        private final static String MQTT_BROKER       = "tcp://192.168.1.46:1885";
        private final static String MQTT_CLIENTID     = "mqtt-ss-Timer";
        private final static String topicM = "home/irrigation/ZoneCommand";
        private final static int Time = 5;//time in mins
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MqttException, InterruptedException {
        // TODO code application logic here
        
//        String b;
//        for(int i = 1; i<=8; i++){
//            b = Integer.toString(i)+";On";
//            mqttClient.publish("home/irrigation/ZoneCommand",b.getBytes(),2,true);
//            Thread.sleep(300000);
//        }

        //public String previous;

        /**
         * @param args the command line arguments
         */
            // TODO code application logic here
            System.out.println("Hello World!");
            
            try {
                ServerSocket s = new ServerSocket();
                s.bind(new InetSocketAddress("localhost",31102));
            }
            catch(IOException e) {
                System.out.println("ERROR: Program already running. Exiting program." );
                return;
            }
            
            try
            {
                    mqttCallback mqttRec = new mqttCallback();

                    mqttClient = new MqttClient(MQTT_BROKER, MQTT_CLIENTID); //, persistence);
                    mqttClient.setCallback(mqttRec);

            }
            catch(MqttException e){
                //logger.info("Exception initializing the mqtt client connection: " + e.toString());
                System.out.println("Exception initializing the mqtt client connection: " + e.toString());
            }

            mqttClientConnect();
            
            int T = Time*60000;
            
        mqttClient.publish(topicM,"1;On".getBytes(),2,true);
        
        Thread.sleep(T/2);
        
        mqttClient.publish(topicM,"2;On".getBytes(),2,true);
        
        Thread.sleep(T/2);
        
        mqttClient.publish(topicM,"3;On".getBytes(),2,true);
        
        Thread.sleep(T);
        
        mqttClient.publish(topicM,"4;On".getBytes(),2,true);
        
        Thread.sleep(T);
        
        mqttClient.publish(topicM,"5;On".getBytes(),2,true);
        
        Thread.sleep(T);
        
        mqttClient.publish(topicM,"6;On".getBytes(),2,true);
        
        Thread.sleep(T);
        
        mqttClient.publish(topicM,"7;On".getBytes(),2,true);
        
        Thread.sleep(T);
        
        mqttClient.publish(topicM,"8;On".getBytes(),2,true);
        
        Thread.sleep(T);
        
        mqttClient.publish(topicM,"8;Off".getBytes(),2,true);
        
        Thread.sleep(3*1000);
        
        System.exit(0);
    }

        private static void mqttClientConnect() {
            //Logger logger = LoggerFactory.getLogger(MqttControlledIO.class);

            if(mqttClient.isConnected()) return;

            try{
                MqttConnectOptions connOpts = new MqttConnectOptions();
                connOpts.setCleanSession(true);
                //logger.info("Connecting to broker: "+ MQTT_BROKER);
                System.out.println("Connecting to broker: "+ MQTT_BROKER);
                mqttClient.connect(connOpts);
                //logger.info("Connected");
                System.out.println("Connected");
                //logger.info("Subscribing to topic");
                System.out.println("Subscribing to topic");
                mqttClient.subscribe("home/irrigation/ZoneCommand");
                //mqttClient.subscribe("home/irrigation/ZoneState");
                //logger.info("Subscribed to topic");
                System.out.println("Subscribed to topic");
            } catch (MqttException e){
                //logger.info("Error connecting to MQTT Client: " + e.toString());
                System.out.println("Error connecting to MQTT Client: " + e.toString());
            }
        }

        static class mqttCallback implements MqttCallback {
            //Logger logger = LoggerFactory.getLogger(MqttControlledIO.class);

            @Override
            public void connectionLost(Throwable thrwbl) {
                mqttClientConnect();
            }

            @Override
            public void messageArrived(String topic, MqttMessage mm) throws Exception {

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken imdt) {
                //System.out.println("Delivery Complete.");
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        }
}
        
