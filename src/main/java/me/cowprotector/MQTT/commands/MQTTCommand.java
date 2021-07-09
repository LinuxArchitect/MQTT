package me.cowprotector.MQTT.commands;

import me.cowprotector.MQTT.MQTTPlugin;
import me.cowprotector.MQTT.MyMqttClient;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.eclipse.paho.client.mqttv3.*;

import java.util.StringJoiner;

@CommandInfo(name = "mqtt", requiresPlayer = false)
public class MQTTCommand extends PluginCommand {

    private static MQTTPlugin plugin;

    public MQTTCommand(MQTTPlugin instance) {
        plugin = instance;
    }

    @Override
    public void execute(Player p, String[] args) {

        if (args.length < 2) {
            p.sendMessage("The mqtt command needs a topic and a payload as parameters!");
            return;
        }

        try {
            StringJoiner joiner = new StringJoiner(" ");
            for (int i=1; i<args.length; i++) {
                joiner.add(args[i]);
            }
            plugin.myClient.send(args[0], joiner.toString());
        } catch (MqttException e) {
            p.sendMessage("The mqtt command failed, please check the server log");
            e.printStackTrace();
        }
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length < 2) {
            sender.sendMessage("The mqtt command needs a topic and a payload as parameters!");
            return;
        }

        try {
            StringJoiner joiner = new StringJoiner(" ");
            for (int i=1; i<args.length; i++) {
                joiner.add(args[i]);
            }
            plugin.myClient.send(args[0], joiner.toString());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
