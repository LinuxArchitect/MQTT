package me.cowprotector.MQTT;

import me.cowprotector.MQTT.commands.MQTTCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.eclipse.paho.client.mqttv3.MqttException;

public final class MQTTPlugin extends JavaPlugin {

    public MyMqttClient myClient;

    private String BROKER_URL = "tcp://127.0.0.1:1883";
    private String TOPIC = "minecraft";
    private String CLIENT_ID = "Minecraft";

    public String getBROKER_URL() {
        return BROKER_URL;
    }

    public void setBROKER_URL(String BROKER_URL) {
        this.BROKER_URL = BROKER_URL;
    }

    public String getTOPIC() {
        return TOPIC;
    }

    public void setTOPIC(String TOPIC) {
        this.TOPIC = TOPIC;
    }

    public String getCLIENT_ID() {
        return CLIENT_ID;
    }

    public void setCLIENT_ID(String CLIENT_ID) {
        this.CLIENT_ID = CLIENT_ID;
    }

    @Override
    public void onEnable() {
        MQTTCommand commands = new MQTTCommand(this);
        getCommand("mqtt").setExecutor(commands);
        myClient = new MyMqttClient(this);
        try {
            myClient.setup(BROKER_URL, CLIENT_ID, TOPIC);
        } catch (MqttException e) {
            Bukkit.getServer().getLogger().severe("Failed setting up connnection with MQTT broker!");
        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
