package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Taller {
	private ArrayList<Vehiculo> ListavehiculosAveriados;
	private ArrayList<Vehiculo> ListaVehiculosReparados;
	private ArrayList<String> listaReparaciones;

	public Taller() {
		this.ListavehiculosAveriados = new ArrayList<>();
		this.ListaVehiculosReparados = new ArrayList<>();
		this.listaReparaciones = new ArrayList<>();
		this.listaReparaciones.add("Cambiar aceite");
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

	public String listarVehiculosReparados() {
		String lista = "";
		for (Vehiculo v : this.ListaVehiculosReparados) {
			lista += v + " Telefno dueño: " + Integer.toString(v.getTelefonoDueño()) + "\n";
		}
		return lista;
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
		for (int i = 0; i < listarMotocicletas().size(); i++) {
			System.out.println((i + 1) + ". " + listarMotocicletas().get(i));
			matriculas.add(listarMotocicletas().get(i).getMatricula());
		}
		opcion = (leerOpcion(listarMotocicletas().size())) - 1;

		Vehiculo v = buscarVehiculo(listarMotocicletas(),
				((Motocicleta) listarMotocicletas().get(opcion)).getMatricula());
		if (v != null) {
			System.out.println("Introduce el nuevo escape: ");
			String escape = sc.nextLine();
			((Motocicleta) v).cambiarEscape(escape);
		}
	}

	public ArrayList<Vehiculo> getListavehiculosAveriados() {
		return this.ListavehiculosAveriados;
	}

	public ArrayList<Vehiculo> getListaVehiculosReparados() {
		return ListaVehiculosReparados;
	}

	private Vehiculo buscarVehiculo(ArrayList<Vehiculo> lista, String matricula) {
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getMatricula().equals(matricula)) {
				return lista.get(i);
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
	 * Muestra los vehiculos averiado Pide que selecciones uno, tras esto
	 * muestra una lista de posibles reparaciones. Pide que selecciones una
	 * reparacion.
	 */
	public void repararVehiculo() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> matriculas = new ArrayList<>();
		int opcion = 0;
		int opcion2;
		Vehiculo v = null;
		boolean reparado = false;
		do {
			boolean esCorrecto;

			if (ListavehiculosAveriados.size() != 0) {
				System.out.println("Selecciona un vehiculo de la lista para reparar: ");
				for (int i = 0; i < this.ListavehiculosAveriados.size(); i++) {
					System.out.println((i + 1) + ". " + this.ListavehiculosAveriados.get(i));
					matriculas.add(listarMotocicletas().get(i).getMatricula());
				}
				opcion = leerOpcion(this.ListavehiculosAveriados.size()) - 1;
				v = buscarVehiculo(ListavehiculosAveriados,
						((Vehiculo) ListavehiculosAveriados.get(opcion)).getMatricula());
			} else {
				v = null;
			}
			if (v != null) {

				do {
					int i;
					String reparacionAuxiliar;
					System.out.println("Selecciona una reparacion: ");
					for (i= 0; i < this.listaReparaciones.size(); i++) {
						System.out.println((i + 1) + ". " + this.listaReparaciones.get(i));

					}
					System.out.println((i + 1) + ". " + "Introducir otra reparación manualmente.");
					opcion2 = leerOpcion(this.listaReparaciones.size()+1) - 1;
					if(opcion2<=this.listaReparaciones.size()-1){
					System.out.println(listaReparaciones.get(opcion2) + " realizado.");
					}else{
						System.out.println("Introduce la reparacion a realizar: ");
						reparacionAuxiliar=sc.nextLine();
						System.out.println(reparacionAuxiliar + " realizado.");
					}
					this.ListaVehiculosReparados.add(ListavehiculosAveriados.get(opcion));
					this.ListavehiculosAveriados.remove(opcion);
					String aux;
					System.out.println("Quiere realizar mas reparaciones?(s para continuar n para salir");
					aux = sc.next();
					aux = aux.toLowerCase();
					if (aux.equals("s") || aux.equals("n")) {
						if (aux.equals("n")) {
							reparado = false;
						}
						esCorrecto = true;
					} else {
						System.out.println("ERROR: No ha introducido una opcion valida.");
						esCorrecto = false;
					}
				} while (!esCorrecto);

			} else {
				System.out.println("No hay vehiculos averiados en el taller.");
				reparado = true;
			}
		} while (!reparado);
	}
}
