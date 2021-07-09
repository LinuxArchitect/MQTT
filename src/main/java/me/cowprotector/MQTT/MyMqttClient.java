package me.cowprotector.MQTT;

import org.eclipse.paho.client.mqttv3.*;

import java.util.logging.Logger;

public class MyMqttClient implements MqttCallback {

    private static MQTTPlugin plugin;
    org.eclipse.paho.client.mqttv3.MqttClient client;
    MqttConnectOptions connOpt;
    Logger logger;

    public MyMqttClient(MQTTPlugin instance) {
        plugin = instance;
        logger = plugin.getLogger();
    }

    public void setup(String brokerUrl, String clientId, String topic) throws MqttException {
        // Start connection
        connOpt = new MqttConnectOptions();
        connOpt.setCleanSession(true);
        connOpt.setKeepAliveInterval(30);

        client = new org.eclipse.paho.client.mqttv3.MqttClient(brokerUrl, clientId);
        client.setCallback(this);
        client.connect(connOpt);

        // Setup subscriber
        int subQoS = 0;
        client.subscribe(topic, subQoS);
    }

    public void send(String topic, String payload) throws MqttException {
        int pubQoS = 0;
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setQos(pubQoS);
        message.setRetained(false);

        if (client.isConnected()) {
            client.publish(topic, message);
        }
    }

    @Override
    public void connectionLost(Throwable thrwbl) {

        logger.severe("MQTT - Connection lost.");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mm) throws Exception {

        logger.info("Message arrived for topic " + topic + ": " + mm.toString());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {

        logger.info("Delivery complete!");
    }

}
