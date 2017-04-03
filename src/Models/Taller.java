package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Taller {
	private LinkedList<Vehiculo> ListavehiculosAveriados;
	private LinkedList<Vehiculo> ListaVehiculosReparados;
	private LinkedList<String> listaReparaciones;
	// True es FIFO
	// False es LIFO
	private boolean politicaReparacion;

	public Taller() {
		this.ListavehiculosAveriados = new LinkedList<>();
		this.ListaVehiculosReparados = new LinkedList<>();
		this.listaReparaciones = new LinkedList<>();
		this.listaReparaciones.add("Cambiar aceite");
		this.politicaReparacion = true;
	}

	/**
	 * Inserta un vehiculo en la lista de vehiculosaveriados
	 * 
	 * @param Vehiculo
	 *            a insertar
	 */
	public void aniadirVehiculoAveriado(Vehiculo v) {
		this.ListavehiculosAveriados.add(v);
	}

	public String listarVehiculosAveriados() {
		String lista = "";
		for (Vehiculo v : this.ListavehiculosAveriados) {
			lista += v + "\n";
		}
		return lista;
	}

	public void cambiarPoliticaReparacion() {
		this.politicaReparacion = !this.politicaReparacion;
		System.out.println((politicaReparacion) ? "Politica de reparacion del taller actualizada a FIFO."
				: "Politica de reparacion del taller actualizada a LIFO.");
	}

	public String listarVehiculosReparados() {
		String lista = "";
		for (Vehiculo v : this.ListaVehiculosReparados) {
			lista += v + " Telefno dueño: " + Integer.toString(v.getTelefonoDueño()) + "\n";
		}
		return lista;
	}

	/**
	 * crea una lista con las motocicletas averiadas disponibles en el taller
	 * 
	 * @return Lista de motocicletas
	 */
	public LinkedList<Vehiculo> listarMotocicletasAveriadas() {
		LinkedList<Vehiculo> listaMotos = new LinkedList();
		for (Vehiculo v : ListavehiculosAveriados) {
			if (v instanceof Motocicleta)
				listaMotos.add((Motocicleta) v);
		}

		return listaMotos;

	}

	/**
	 * crea una lista con las motocicletas disponibles en el taller
	 * 
	 * @return Lista de motocicletas
	 */
	public ArrayList<Vehiculo> listarMotocicletas() {
		ArrayList<Vehiculo> listaMotos = new ArrayList<>();
		for (Vehiculo v : ListavehiculosAveriados) {
			if (v instanceof Motocicleta)
				listaMotos.add((Motocicleta) v);
		}
		for (Vehiculo v : ListaVehiculosReparados) {
			if (v instanceof Motocicleta)
				listaMotos.add((Motocicleta) v);
		}
		return listaMotos;

	}

	/**
	 * Muestra un menu con las motocicletas disponibles. Tras esto pide
	 * seleccionar una e introducir el nuevo escape
	 */
	public void cambiarEscape() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> matriculas = new ArrayList<>();
		int opcion;
		boolean esCorrecto = false;
		for (int i = 0; i < listarMotocicletasAveriadas().size(); i++) {
			System.out.println((i + 1) + ". " + listarMotocicletasAveriadas().get(i));
			matriculas.add(listarMotocicletasAveriadas().get(i).getMatricula());
		}
		opcion = (leerOpcion(listarMotocicletasAveriadas().size())) - 1;

		Vehiculo v = buscarVehiculo(listarMotocicletasAveriadas(),
				((Motocicleta) listarMotocicletasAveriadas().get(opcion)).getMatricula());
		if (v != null) {
			System.out.println("Introduce el nuevo escape: ");
			String escape = sc.nextLine();
			((Motocicleta) v).cambiarEscape(escape);
		}
	}

	public LinkedList<Vehiculo> getListavehiculosAveriados() {
		return this.ListavehiculosAveriados;
	}

	public LinkedList<Vehiculo> getListaVehiculosReparados() {
		return ListaVehiculosReparados;
	}

	public Vehiculo buscarVehiculo(LinkedList<Vehiculo> linkedList, String matricula) {
		for (int i = 0; i < linkedList.size(); i++) {
			if (linkedList.get(i).getMatricula().equals(matricula)) {
				return linkedList.get(i);
			}
		}
		return null;
	}

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

	/**
	 * Repara un vehiculo segun la politica de reparacion seleccionada
	 */
	public void repararVehiculo() {
		/*
		 * Scanner sc = new Scanner(System.in); ArrayList<String> matriculas =
		 * new ArrayList<>(); int opcion = 0; int opcion2; Vehiculo v = null;
		 * boolean reparado = false; do { boolean esCorrecto;
		 * 
		 * if (ListavehiculosAveriados.size() != 0) { System.out.
		 * println("Lista de vehiculos averiados:\n " ); for (int i = 0; i <
		 * this.ListavehiculosAveriados.size(); i++) { System.out.println((i +
		 * 1) + ". " + this.ListavehiculosAveriados.get(i)); matriculas.add(
		 * listarMotocicletasAveriadas().get(i). getMatricula()); } System.out.
		 * print("Selecciona un vehiculo de la lista para reparar:\n " ); opcion
		 * = leerOpcion(this.ListavehiculosAveriados. size()) - 1; v =
		 * buscarVehiculo(ListavehiculosAveriados, ((Vehiculo)
		 * ListavehiculosAveriados.get(opcion)). getMatricula()); } else { v =
		 * null; } if (v != null) {
		 * 
		 * int i; String reparacionAuxiliar; System.out.
		 * println("Lista de reparaciones: "); for (i = 0; i <
		 * this.listaReparaciones.size(); i++) { System.out.println((i + 1) +
		 * ". " + this.listaReparaciones.get(i));
		 * 
		 * } System.out.println((i + 1) + ". " +
		 * "Introducir otra reparación manualmente." ); if (v.getAveria() !=
		 * null) { System.out. println("se le debe de realizar: " +
		 * v.getAveria()); } System.out. print("Selecciona una reparacion: ");
		 * opcion2 = leerOpcion(this.listaReparaciones.size() + 1) - 1; if
		 * (opcion2 <= this.listaReparaciones.size() - 1) {
		 * System.out.println(listaReparaciones.get( opcion2) + " realizado.");
		 * } else { System.out. println("Introduce la reparacion a realizar: "
		 * ); reparacionAuxiliar = sc.nextLine();
		 * System.out.println(reparacionAuxiliar + " realizado."); }
		 * this.ListaVehiculosReparados.add(
		 * ListavehiculosAveriados.get(opcion));
		 * this.ListavehiculosAveriados.remove( opcion); String aux; do {
		 * System.out.
		 * println("Quiere realizar mas reparaciones?(s para continuar n para salir"
		 * ); aux = sc.next(); aux = aux.toLowerCase(); if (aux.equals("s") ||
		 * aux.equals("n")) { if (aux.equals("n")) { reparado = true; }
		 * esCorrecto = true; } else { System.out.
		 * println("ERROR: No ha introducido una opcion valida." ); esCorrecto =
		 * false; } } while (!esCorrecto);
		 * 
		 * } else { System.out.
		 * println("No hay vehiculos averiados en el taller." ); reparado =
		 * true; } } while (!reparado);
		 */
		if (politicaReparacion) {
			Vehiculo aux = ListavehiculosAveriados.getFirst();
			ListavehiculosAveriados.removeFirst();
			System.out.println("Vehiculo: " + aux + " Reparado");
			ListaVehiculosReparados.addLast(aux);
		} else {
			Vehiculo aux = ListavehiculosAveriados.getLast();
			ListavehiculosAveriados.removeLast();
			System.out.println("Vehiculo: " + aux + " Reparado");
			ListaVehiculosReparados.addLast(aux);
		}
	}

	public void cambiarAReparados(Vehiculo v) {
		for (int i = 0; i < ListavehiculosAveriados.size(); i++) {
			if (v.getMatricula().equals(ListavehiculosAveriados.get(i))) {
				ListaVehiculosReparados.add(ListavehiculosAveriados.get(i));
				ListavehiculosAveriados.remove(i);
				break;
			}
		}
	}

	public void vehiculoEntregado(Vehiculo v) {
		for (int i = 0; i < ListavehiculosAveriados.size(); i++) {
			if (v.getMatricula().equals(ListaVehiculosReparados.get(i))) {
				ListaVehiculosReparados.remove(i);
				break;
			}
		}
	}

	public void clienterecogeVehiculo() {
		System.out.println("Selecciona un vehiculo de la lista para entregar: ");
		for (int i = 0; i < this.ListaVehiculosReparados.size(); i++) {
			System.out.println((i + 1) + ". " + this.ListaVehiculosReparados.get(i));

		}
		int opcion = leerOpcion(this.ListaVehiculosReparados.size()) - 1;

		System.out.println("Vehiculo " + this.ListaVehiculosReparados.get(opcion) + " entregado");
		if (this.ListaVehiculosReparados.get(opcion).getAveria() != null) {
			System.out.println("Se le ha realizado: " + this.ListaVehiculosReparados.get(opcion).getAveria() + ".");
		}
		boolean pagadoCompleto = this.ListaVehiculosReparados.get(opcion)
				.getTotalPagado() >= this.ListaVehiculosReparados.get(opcion).getPrecioReparacion();
		if (pagadoCompleto) {
			this.ListaVehiculosReparados.remove(opcion);
		} else {
			System.out.println("[ERROR]: El vehiculo no se puede retirar porque no esta pagado completo.");
			System.out.println(
					"Aun le queda por pagar: " + (this.ListaVehiculosReparados.get(opcion).getPrecioReparacion()
							- this.ListaVehiculosReparados.get(opcion).getTotalPagado()) + " Eur.");
		}
	}
}
