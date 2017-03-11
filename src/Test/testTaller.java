package Test;

import java.util.ArrayList;
import java.util.Scanner;
import Models.*;

public class testTaller {
	private static ArrayList<String> tiposVehiculos;
	private static ArrayList<String> acciones;
	private static ArrayList<String> reparaciones;
	private static Taller t = new Taller();

	private static void aniadirReparaciones() {
		reparaciones = new ArrayList<>();
		reparaciones.add("Acelerar");
		reparaciones.add("Frenar");
		reparaciones.add("Cambiar escape");
		reparaciones.add("Subir ventanillas");
		reparaciones.add("Bajar ventanillas");
		reparaciones.add("Marcar como reparado");
		reparaciones.add("Detener el proceso de reparación sin haber terminado");

	}

	private static void aniadirTiposVehiculos() {
		tiposVehiculos = new ArrayList<>();
		tiposVehiculos.add("Coche");
		tiposVehiculos.add("Ciclomotor");
		tiposVehiculos.add("Motocicleta");

	}

	private static void aniadirTipoAcciones() {
		acciones = new ArrayList<>();
		acciones.add("Salir");
		acciones.add("Añadir un vehiculo a reparar");
		acciones.add("Reparar un vehívulo");

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		aniadirTipoAcciones();
		aniadirReparaciones();
		boolean finalizado = false;
		System.out.println("-----BIENVENIDO-----");
		do {
			System.out.println("Lista de acciones disponibles:");
			mostrarLista(acciones);
			switch (leerOpcion(acciones.size())) {
			case 1:
				finalizado = true;
				break;
			case 2:
				do {
					aniadirVehiculo();
					System.out.println(
							"¿Quieres añadir mas vehiculos?\nIntrodudice \"s\" para añadir mas o \"n\" para volver.");
				} while (sioNo());
				break;
			case 3:
				System.out.print("Introduce la matricula del vehiculo a reparar: ");
				String matricula = sc.nextLine();
				Vehiculo v = t.buscarVehiculo(t.getListavehiculosAveriados(), matricula);
				if (v != null) {
					System.out.println("Lista de reparaciones disponibles: ");
					mostrarLista(reparaciones);
					switch (leerOpcion(reparaciones.size())) {
					case 1:
						System.out.println("Introduce la velocidad que quiere acelerar el vehiculo:");
						v.acelerar(leerDoubleMayorDe0());

						break;
					case 2:
						System.out.println("Introduce la velocidad que quiere frenar el vehiculo:");
						v.frenar(leerDoubleMayorDe0());

						break;
					}
				} else {
					System.out.println("[ERROR] Matricula no exixtente en los vehiculos averiados.");
				}

			}
		} while (!finalizado);

	}

	/**
	 * Pregunta el tipo de vehiculo tras esto pide todos sus atributos por
	 * teclado y depues lo añade a la lista de vehiculos averiados
	 */
	public static void aniadirVehiculo() {

		// Mostrar los tipos de vehiculos
		System.out.println(listarTiposVehiculo());
		// Crear un vehiculo a partir de la opción seleccionada

		switch (leerOpcion(tiposVehiculos.size())) {
		case 1:
		case 2:
			;
			t.aniadirVehiculoAveriado(crearVehiculo());
			break;
		case 3:
			t.aniadirVehiculoAveriado(crearMoto());
			break;

		}

	}

	public static void mostrarLista(ArrayList<String> lista) {
		if (lista.size() != 0) {

			for (int i = 0; i < lista.size(); i++) {
				System.out.println((i + 1) + ". " + lista.get(i));

			}

		} else {

			System.out.println("Lista vacia.");
		}
	}

	public static String listarTiposVehiculo() {
		aniadirTiposVehiculos();
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
		System.out.print("Introduce la opción deseada: ");
		do {
			try {
				aux = sc.next();
				numero = Integer.parseInt(aux);
				esCorrecto = true;
				if (numero > opcionesMaximas || numero < 1) {
					esCorrecto = false;
					System.out.println("[ERROR] ha introducido un numero fuera de rango");
					System.out.println("Introduce de nuevo el número: ");
				}
			} catch (Exception e) {
				esCorrecto = false;
				System.out.println("No ha introducido un numero o no es valido.");
				System.out.println("Introduce de nuevo el número: ");
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
		int telefonoDueño;
		System.out.println("Introduce el color del vehiculo:");
		color = sc.nextLine();
		System.out.println("Introduce la matricula del vehiculo:");
		matricula = sc.nextLine();
		System.out.println("Introduce la marca del vehiculo:");
		marca = sc.nextLine();
		System.out.println("Introduce el modelo del vehiculo:");
		modelo = sc.nextLine();
		System.out.println("¿Quieres introducir la averia?\nIntroduce \"s\" o \"n\" para responder.");
		if (sioNo()) {
			System.out.println("Introduce la averia del vehiculo:");
			averia = sc.nextLine();
		} else {
			averia = null;
		}
		System.out.println("Introduce la velocidad máxima del vehiculo:");
		velocidadMaxima = leerEnterosMayorDe0();
		System.out.println("Introduce el número de telefono del dueño: ");
		telefonoDueño = leerEnterosMayorDe0();
		Vehiculo v = new Vehiculo(color, matricula, marca, modelo, telefonoDueño, velocidadMaxima, averia) {
		};
		return v;
	}

	private static Vehiculo crearMoto() {
		Scanner sc = new Scanner(System.in);
		String escape;
		Vehiculo v = crearVehiculo();
		System.out.println("¿La moto lleva el escape de serie?");
		if (!sioNo()) {
			System.out.println("Introduce el escape de la moto: ");
			escape = sc.nextLine();
		} else {
			escape = null;
		}
		return new Motocicleta(v.getColor(), v.getMatricula(), v.getMarca(), v.getModelo(), v.getVelocidadMaxima(),
				v.getTelefonoDueño(), escape, v.getAveria());

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
			System.out.print("Introduce un número entero: ");
			try {
				aux = sc.next();
				numero = Integer.parseInt(aux);
				esCorrecto = true;
				if (numero < 0) {
					System.out.println("[ERROR] No se puede introducir un número menor de 0.");
					esCorrecto = false;
				}
			} catch (Exception e) {
				esCorrecto = false;
				System.out.println("No ha introducido un numero o no es valido.");
			}
		} while (esCorrecto == false);
		return numero;

	}

	public static Double leerDoubleMayorDe0() {
		Scanner sc = new Scanner(System.in);
		String aux;
		Double numero = (double) 0;
		boolean esCorrecto = false;

		do {
			System.out.print("Introduce un número entero: ");
			try {
				aux = sc.next();
				numero = Double.parseDouble(aux);
				esCorrecto = true;
				if (numero < 0) {
					System.out.println("[ERROR] No se puede introducir un número menor de 0.");
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
