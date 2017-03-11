package Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SingleSelectionModel;

import Models.Motocicleta;
import Models.Vehiculo;

public class testTaller {
	private static ArrayList<String> tiposVehiculos;

	private static void a�adirTiposVehiculos() {
		tiposVehiculos = new ArrayList<>();
		tiposVehiculos.add("Coche");
		tiposVehiculos.add("Motocicleta");
		tiposVehiculos.add("Ciclomotor");
	}

	public static void main(String[] args) {

		System.out.println("-----BIENVENIDO-----");
		System.out.println(listarTiposVehiculo());
		leerOpcion(tiposVehiculos.size());
		leerOpcion(tiposVehiculos.size());

		Vehiculo v = crearVehiculo();
		System.out.println(v);
		v=crearMoto();

	}

	public static void mostrarLista(ArrayList<String> lista) {
		if (lista.size() != 0) {
			System.out.println("Lista de vehiculos averiados:\n ");
			for (int i = 0; i < lista.size(); i++) {
				System.out.println((i + 1) + ". " + lista.get(i));

			}

		} else {

			System.out.println("Lista vacia.");
		}
	}

	public static String listarTiposVehiculo() {
		a�adirTiposVehiculos();
		String aux = "";
		System.out.println("Tipos de vehiculos disponibles:");
		for (int i = 0; i < tiposVehiculos.size(); i++) {
			aux += ((i + 1) + ". " + tiposVehiculos.get(i)) + "\n";
		}
		return aux;
	}

	/**
	 * Lee por teclado un numero de opciones dentro del rango introducido
	 * Empezando siempre por 1
	 * 
	 * @param opcionesMaximas
	 *            a elegir
	 * @return la opcion elegida empezando a partir de 1
	 */
	private static int leerOpcion(int opcionesMaximas) {
		Scanner sc = new Scanner(System.in);
		String aux;
		int numero = 0;
		boolean esCorrecto = false;

		do {
			try {
				aux = sc.next();
				numero = Integer.parseInt(aux);
				esCorrecto = true;
				if (numero > opcionesMaximas || numero < 1) {
					esCorrecto = false;
					System.out.println("[ERROR] ha introducido un numero fuera de rango");
					System.out.println("Introduce de nuevo el n�mero: ");
				}
			} catch (Exception e) {
				esCorrecto = false;
				System.out.println("No ha introducido un numero o no es valido.");
				System.out.println("Introduce de nuevo el n�mero: ");
			}
		} while (esCorrecto == false);
		return numero;

	}

	private static Vehiculo crearVehiculo() {
		Scanner sc = new Scanner(System.in);
		String color;
		String matricula;
		String marca;
		String modelo;
		String averia;
		int velocidadMaxima;
		int telefonoDue�o;
		System.out.println("Introduce el color del vehiculo:");
		color = sc.nextLine();
		System.out.println("Introduce la matricula del vehiculo:");
		matricula = sc.nextLine();
		System.out.println("Introduce la marca del vehiculo:");
		marca = sc.nextLine();
		System.out.println("Introduce el modelo del vehiculo:");
		modelo = sc.nextLine();
		System.out.println("�Quieres introducir la averia?\nIntroduce \"s\" o \"n\" para responder.");
		if (sioNo()) {
			System.out.println("Introduce la averia del vehiculo:");
			averia = sc.nextLine();
		} else {
			averia = null;
		}
		System.out.println("Introduce la velocidad m�xima del vehiculo:");
		velocidadMaxima = leerEnterosMayorDe0();
		System.out.println("Introduce el n�mero de telefono del due�o: ");
		telefonoDue�o = leerEnterosMayorDe0();
		Vehiculo v = new Vehiculo(color, matricula, marca, modelo, telefonoDue�o, velocidadMaxima, averia) {
		};
		return v;
	}

	private static Vehiculo crearMoto() {
		Scanner sc = new Scanner(System.in);
		String escape;
		Vehiculo v = crearVehiculo();
		System.out.println("�La moto lleva el escape de serie?");
		if (!sioNo()) {
			System.out.println("Introduce el escape de la moto: ");
			escape = sc.nextLine();
		} else {
			escape = null;
		}
		return new Motocicleta(v.getColor(), v.getMatricula(), v.getMarca(), v.getModelo(),
				v.getVelocidadMaxima(), v.getTelefonoDue�o(), escape, v.getAveria());
		
	}

	/**
	 * Pregunta que si quiere hacer algo
	 * 
	 * @return la respuesta.
	 */
	private static boolean sioNo() {
		Scanner sc = new Scanner(System.in);
		boolean esCorrecto = false;
		boolean continuar = false;
		do {
			String aux = sc.next();
			aux = aux.toLowerCase();
			if (aux.equals("s") || aux.equals("n")) {
				if (aux.equals("n")) {
					return false;
				}

				esCorrecto = true;
			} else {
				System.out.println("ERROR: No ha introducido una opcion valida.");
				esCorrecto = false;
			}
		} while (!esCorrecto);
		return true;
	}

	public static int leerEnterosMayorDe0() {
		Scanner sc = new Scanner(System.in);
		String aux;
		int numero = 0;
		boolean esCorrecto = false;

		do {
			System.out.print("Introduce un n�mero entero: ");
			try {
				aux = sc.next();
				numero = Integer.parseInt(aux);
				esCorrecto = true;
				if (numero < 0) {
					System.out.println("[ERROR] No se puede introducir un n�mero menor de 0.");
					esCorrecto = false;
				}
			} catch (Exception e) {
				esCorrecto = false;
				System.out.println("No ha introducido un numero o no es valido.");
			}
		} while (esCorrecto == false);
		return numero;

	}
}
