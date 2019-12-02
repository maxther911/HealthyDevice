package net.mrsistemas.healthy.device.sensor;

import net.mrsistemas.healthy.device.hardware.WaterTemperatureSensor;
import org.eclipse.paho.client.mqttv3.MqttException;

import net.mrsistemas.healthy.device.mqtt.SendDataUsingMQTT;
import net.mrsistemas.healthy.device.restapi.RESTCall;

public class Execute {
	public static void lanzarTemperatura(String in) throws MqttException, InterruptedException {
		// TODO Auto-generated method stub

		WaterTemperatureSensor sensor = new WaterTemperatureSensor();
		RESTCall http = new RESTCall();
		SendDataUsingMQTT mqtt = new SendDataUsingMQTT();

		double temp;
		while (true) {
			temp = sensor.getWaterTemperature();
			if (temp != 0) {
				System.out.println("Temperature is: " + temp + " �C");
			} else
				System.out.println("Sensor not found ");
			// temp = Math.random() * 100;
			switch (in) {
			case "mqtt":
				System.out.println("Sending temp. data using MQTT. ");
				mqtt.publish("field1=" + temp);
				break;
			case "rest":
				System.out.println("Sending temp. data using REST. ");
				http.sendDataOverRest(temp);
				break;

			default:
				System.out.println("No interface is selected.");
				System.exit(0);
				break;
			}

			Thread.sleep(1 * 60 * 1000);
		}

	}

}
