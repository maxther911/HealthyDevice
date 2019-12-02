package net.mrsistemas.healthy.device.controllers;

import java.util.Scanner;

import com.diozero.devices.LED;

public class DeviceControllers {
	
	private static final DeviceControllers INSTANCE = new DeviceControllers();
	private static final long SLEEP = 10000;
	private LED pin18 = null;
	private LED pin19 = null;
	private LED pin20 = null;
	private LED pin21 = null;
	private LED pin22 = null;
	
	private DeviceControllers() {
		pin18 = new LED(18);
		pin19 = new LED(19);
		pin20 = new LED(20);
		pin21 = new LED(21);
		pin22 = new LED(22);
		pin18.on();
	}
	
	public static DeviceControllers getInstance() {
		return INSTANCE;
	}

	public static void main(String[] args) throws InterruptedException {
		DeviceControllers device = INSTANCE;
		Scanner key = new Scanner(System.in);
		boolean status = true;
		do {
			System.out.print("Ingrese pin a cambiar: ");
			device.changeStatePin(Integer.valueOf(key.nextInt()), status);
			status = !status;
			Thread.sleep(SLEEP);
		} while (true);
	}

	/**
	 * Cambiar estado de un pin en la raspberry on - off
	 * @param pin
	 * @param status
	 * @return flag state change succesful
	 * @throws InterruptedException
	 */
	private boolean changeStatePin(int pin, boolean status) {
		switch (pin) {
		case 18:
			if(!pin18.isOn() && status)
				pin18.on();
			else 
				pin18.off();
			break;
		case 19:
			if(!pin18.isOn() && status)
				pin18.on();
			else 
				pin18.off();
			break;
		case 20:
			if(!pin18.isOn() && status)
				pin18.on();
			else 
				pin18.off();
			break;
		case 21:
			if(!pin18.isOn() && status)
				pin18.on();
			else 
				pin18.off();
			break;
		case 22:
			if(!pin18.isOn() && status)
				pin18.on();
			else 
				pin18.off();
			break;
			
		default:
			break;
		}
		return false;

	}

}
