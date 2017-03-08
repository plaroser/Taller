package Models;

public class Motocicleta extends Vehiculo {
	private String marcaEscape;

	public Motocicleta(String color, String matricula, String marca, String modelo, int velocidadMaxima,
			int telefonoDueño) {
		super(color, matricula, marca, modelo, velocidadMaxima, telefonoDueño);
		this.marcaEscape = "Escape de serie";
	}

	public Motocicleta(String color, String matricula, String marca, String modelo, int velocidadMaxima,
			int telefonoDueño, String marcaEscape) {
		this(color, matricula, marca, modelo, velocidadMaxima, telefonoDueño);
		this.marcaEscape = marcaEscape;
	}
	public void cambiarEscape(String nuevoEscape) {
		this.marcaEscape = nuevoEscape;
	}

	public String getMarcaEscape() {
		return marcaEscape;
	}
	
}
