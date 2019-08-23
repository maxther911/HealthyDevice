package net.mrsistemas.healthy.hardware;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.io.w1.W1Master;
import com.pi4j.temperature.TemperatureScale;

public class WaterTemperatureSensor {

	public double getWaterTemperature() {

		W1Master w1Master = new W1Master();

		System.out.println("W1 Master" + w1Master);
		// TODO No hay sensor de temperarura
		for (TemperatureSensor device : w1Master.getDevices(TemperatureSensor.class)) {
			System.out.printf("%-20s %3.1f�C %3.1f�F\n", device.getName(), device.getTemperature(),
					device.getTemperature(TemperatureScale.CELSIUS));
			if (device.getName().contains("28-0517005185ff")){
				System.out.println(device.getTemperature(TemperatureScale.CELSIUS));
				return device.getTemperature(TemperatureScale.CELSIUS);
			}
			System.out.println("device.getName(): "+ device.getName());
		}
		return 0;
	}

}
