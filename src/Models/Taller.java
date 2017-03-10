package Models;

import java.util.ArrayList;
import java.util.Scanner;

public class Taller {
	private ArrayList<Vehiculo> ListavehiculosAveriados;
	private ArrayList<Vehiculo> ListaVehiculosReparados;

	public Taller() {
		this.ListavehiculosAveriados = new ArrayList<>();
		this.ListaVehiculosReparados = new ArrayList<>();
	}

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

	public void cambiarEscape() {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> matriculas = new ArrayList<>();
		int opcion;
		boolean esCorrecto = false;
		for (int i = 0; i < listarMotocicletas().size(); i++) {
			System.out.println((i + 1) + ". " + listarMotocicletas().get(i));
			matriculas.add(listarMotocicletas().get(i).getMatricula());
		}
		opcion = (leerOpcion(listarMotocicletas().size()))-1;
		
		Vehiculo v = buscarVehiculo(listarMotocicletas(), ((Motocicleta)listarMotocicletas().get(opcion)).getMatricula());
		if(v != null){
			System.out.println("Introduce el nuevo escape: ");
			String escape = sc.nextLine();
			((Motocicleta) v).cambiarEscape(escape);
		}
	}

	public ArrayList<Vehiculo> getListavehiculosAveriados() {
		return ListavehiculosAveriados;
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
				}
			} catch (Exception e) {
				esCorrecto = false;
				System.out.println("No ha introducido un numero o no es valido.");
			}
		} while (esCorrecto == false);
		return numero;

	}
}
