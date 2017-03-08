package Models;

public class Motocicleta extends Vehiculo {
	private String marcaEscape;

	public Motocicleta(String color, String matricula, String marca, String modelo, int velocidadMaxima,
			float velocidadActual, int telefonoDue�o) {
		super(color, matricula, marca, modelo, velocidadMaxima, velocidadActual, telefonoDue�o);
		this.marcaEscape = "Escape de serie";
	}

	public void cambiarEscape(String nuevoEscape) {
		this.marcaEscape = nuevoEscape;
	}
}
