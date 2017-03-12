package Models;

public class Ciclomotor extends Vehiculo {

	public Ciclomotor(String color, String matricula, String marca, String modelo, int velocidadMaxima,
			float velocidadActual, int telefonoDueño) {
		super(color, matricula, marca, modelo, velocidadMaxima, telefonoDueño);
	}
	public Ciclomotor(String color, String matricula, String marca, String modelo, int velocidadMaxima, int telefonoDueño,
			 String averia) {
			super(color, matricula, marca, modelo, velocidadMaxima, telefonoDueño, averia);
			
		}
	
}
