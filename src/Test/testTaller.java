package Test;

import java.util.ArrayList;
import java.util.Scanner;
import Models.*;

public class testTaller {
	private static ArrayList<String> tiposVehiculos;
	private static ArrayList<String> acciones;
	private static ArrayList<String> reparaciones;
	private static ArrayList<String> listados;
	private static Taller t = new Taller();
	private static String GUIONES="-------------------------------";

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

	private static void aniadirlistados() {
		listados = new ArrayList<>();
		listados.add("Listar todos los vehículos");
		listados.add("Listar vehículos averiados");
		listados.add("Listar vehículos reparados");
		listados.add("Volver al menú principal");

	}

	private static void aniadirTiposVehiculos() {
		tiposVehiculos = new ArrayList<>();
		tiposVehiculos.add("Coche");
		tiposVehiculos.add("Ciclomotor");
		tiposVehiculos.add("Motocicleta");

	}

	private static void aniadirTipoAcciones() {
		acciones = new ArrayList<>();
		acciones.add("Añadir un vehiculo a reparar");
		acciones.add("Reparar un vehículo");
		acciones.add("Listar vehiculos");
		acciones.add("Entregar vehículo al cliente");
		acciones.add("Salir");

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		aniadirTipoAcciones();
		aniadirReparaciones();
		boolean finalizado = false;
		String matricula;
		Vehiculo v;
		

		System.out.println("-----BIENVENIDO-----");
		do {
			System.out.println("Lista de acciones disponibles:");
			System.out.println(GUIONES);
			mostrarLista(acciones);
			switch (leerOpcion(acciones.size())) {

			case 1:
				do {
					aniadirVehiculo();
					System.out.println(
							"¿Quieres añadir mas vehiculos?\nIntrodudice \"s\" para añadir mas o \"n\" para volver.");
				} while (sioNo());
				break;
			case 2:
				System.out.print("Introduce la matricula del vehiculo a reparar: ");
				matricula = sc.nextLine();
				v = t.buscarVehiculo(t.getListavehiculosAveriados(), matricula);
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
					case 3:
						if (v instanceof Motocicleta) {
							String escape;
							System.out.print("Introduce el nuevo escape:");
							escape = sc.nextLine();
							((Motocicleta) v).cambiarEscape(escape);
							System.out.println("Escape: " + escape + " instalado.");
						} else {
							System.out.println(
									"El vehículo introducido no es una moto, no se le puede cambiar el escape.");
						}
						break;
					case 4:
						if (v instanceof Coche) {
							((Coche) v).subirVentanillas();
						} else {
							System.out.println("El vehiculo seleccionado no es un coche.");
						}
						break;

					case 5:
						if (v instanceof Coche) {
							((Coche) v).bajarVentanillas();
						} else {
							System.out.println("El vehiculo seleccionado no es un coche.");
						}
						break;
					case 6:
						t.cambiarAReparados(v);
						System.out.println("Vehiculo listo para entregar a su dueño.\nNumero de telefono para llamar: "
								+ v.getTelefonoDueño());
						break;

					}
				} else {
					System.out.println("[ERROR] Matricula no exixtente en los vehiculos averiados.");
				}
				break;
			// 3. Listar vehículos que a su vez mostrará el siguiente
			// submenú:
			case 3:
				aniadirlistados();
				mostrarLista(listados);
				switch (leerOpcion(listados.size())) {
				case 1:
					System.out.println("Vehiculos averiados: ");
					System.out.println(t.listarVehiculosAveriados());
					System.out.println("Vehiculos reparados:");
					System.out.println(t.getListaVehiculosReparados());
					break;
				case 2:
					System.out.println("Vehiculos averiados: ");
					System.out.println(t.listarVehiculosAveriados());

					break;
				case 3:
					System.out.println("Vehiculos reparados:");
					System.out.println(t.listarVehiculosReparados());
					break;
				default:
					break;
				}
				// 4. Entregar vehículo al cliente

				break;
			case 4:
				System.out.print("Introduce la matricula del vehiculo a entregar: ");
				matricula = sc.nextLine();
				v = t.buscarVehiculo(t.getListavehiculosAveriados(), matricula);
				if (v != null) {
					System.out.println("Vehículo aun sin reparar, por favor, vuelva mañana.");

				} else {
					v = t.buscarVehiculo(t.getListaVehiculosReparados(), matricula);
					if (v != null) {
						System.out.println("Puede llevarse el vehiculo. Gracias.");
						t.vehiculoEntregado(v);
					}
					System.out.println("Usted no tiene ningun vehivulo en este taller.\nPuede irse cuando quiera.");
				}
				break;
			case 5:
				finalizado = true;
				break;
			}

		} while (!finalizado);

	}

	/**
	 * Pregunta el tipo de vehiculo tras esto pide todos sus atributos por
	 * teclado y depues lo añade a la lista de vehiculos averiados
	 */
	public static void aniadirVehiculo() {
		Vehiculo v;
		// Mostrar los tipos de vehiculos
		System.out.println(listarTiposVehiculo());
		// Crear un vehiculo a partir de la opción seleccionada

		switch (leerOpcion(tiposVehiculos.size())) {
		case 1:
			t.aniadirVehiculoAveriado(crearCoche());
			break;
		case 2:
			t.aniadirVehiculoAveriado(crearCiclomotor());
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
		System.out.println(GUIONES);
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
		Vehiculo v = new Vehiculo(color, matricula, marca, modelo, velocidadMaxima, telefonoDueño, averia) {
		};
		return v;
	}

	private static Motocicleta crearMoto() {
		Scanner sc = new Scanner(System.in);
		String escape;
		Vehiculo v = crearVehiculo();
		System.out.println("¿La moto lleva el escape de serie?");
		System.out.print("Introduce s o n:");
		if (!sioNo()) {
			System.out.println("Introduce el escape de la moto: ");
			escape = sc.nextLine();
		} else {
			escape = null;
		}
		return new Motocicleta(v.getColor(), v.getMatricula(), v.getMarca(), v.getModelo(), v.getVelocidadMaxima(),
				v.getTelefonoDueño(), escape, v.getAveria());

	}

	private static Coche crearCoche() {
		Scanner sc = new Scanner(System.in);
		Vehiculo v = crearVehiculo();
		return new Coche(
				v.getColor(), 
				v.getMatricula(), 
				v.getMarca(), 
				v.getModelo(), 
				v.getVelocidadMaxima(),
				v.getTelefonoDueño(), 
				v.getAveria());

	}

	private static Ciclomotor crearCiclomotor() {
		Scanner sc = new Scanner(System.in);
		Vehiculo v = crearVehiculo();
		return new Ciclomotor(
				v.getColor(), 
				v.getMatricula(), 
				v.getMarca(), 
				v.getModelo(), 
				v.getVelocidadMaxima(),
				v.getTelefonoDueño(), 
				v.getAveria());

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
			System.out.print("Introduce un número: ");
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
