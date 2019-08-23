package net.mrsistemas.healthy.facade;

import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.diozero.devices.LED;

import net.mrsistemas.healthy.sensor.Execute;

public class Main {

	public Main() {}

	public static void main(String[] args) throws InterruptedException {

		try (Scanner scan = new Scanner(System.in)) {
			menu();
			switch (scan.nextInt()) {
			case 1:
				ledsOperaction();
				break;
			case 2:
				runTemperature();
				break;
			default:
				System.exit(0);
				break;
			}
		}
	}

	@SuppressWarnings("static-access")
	private static void runTemperature() {
		System.out.println("Operaciones con el sensor de temperatura......");
		Execute run = new Execute();
		try (Scanner sc = new Scanner(System.in)) {
			temperatureMenu();
			switch (sc.nextInt()) {
			case 1:
				run.lanzarTemperatura("mqtt");
				break;
			case 2:
				run.lanzarTemperatura("rest");
				break;
			default:
				throw new Exception();
			}
		} catch (MqttException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Saliendo.....");
		}
	}

	private static void ledsMenu() {
		System.out.println("1: Encender.");
		System.out.println("2: Apagar");
		System.out.println("3: Salir");
		System.out.println("Ingrese la operacion que desea realizar:");
	}

	private static void temperatureMenu() {
		System.out.println("1: Enviar MQTT.");
		System.out.println("2: Enviar Rest");
		System.out.println("3: Salir");
		System.out.println("Ingrese la operacion que desea realizar:");
	}

	private static void menu() {
		System.out.println("1: Operaciones con facade.");
		System.out.println("2: Operaciones con Temperature");
		System.out.println("3: Salir");
		System.out.println("Ingrese la operacion que desea realizar:");
	}

	private static void ledsOperaction() {
		System.out.println("**************     Operaciones con facade     **********************");
		try (LED led = new LED(18); Scanner sc = new Scanner(System.in)) {
			while (true) {
				ledsMenu();
				int b = sc.nextInt();
				switch (b) {
				case 1:
					System.out.println("Encendiendo....*****************");
					led.on();
					break;
				case 2:
					System.out.println("Apagando.*****************");
					led.off();
					break;
				default:
					System.out.println("Opcion inv�lida saliendo.");

					throw new Exception();
				}
			}
		} catch (Exception e) {
			System.out.println("Saliendo......");
		}
	}

}
