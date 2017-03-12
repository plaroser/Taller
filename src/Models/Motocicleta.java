package Models;

public class Motocicleta extends Vehiculo {
	private String marcaEscape;

	
	
	public Motocicleta(
			String color, 
			String matricula, 
			String marca, 
			String modelo, 
			int velocidadMaxima,
			int telefonoDue�o, 
			String marcaEscape, 
			String averia) {
		super(color, matricula, marca, modelo, velocidadMaxima, telefonoDue�o, averia);
		this.marcaEscape = marcaEscape;
	}

	public void cambiarEscape(String nuevoEscape) {
		this.marcaEscape = nuevoEscape;
	}

	public String getMarcaEscape() {
		return marcaEscape;
	}
	
}
