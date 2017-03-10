package Models;

public class Coche extends Vehiculo {
	private String ventanillas;
	
	
	public Coche(String color, String matricula, String marca, String modelo, int velocidadMaxima,
			int telefonoDueño) {
		super(color, matricula, marca, modelo, velocidadMaxima, telefonoDueño);
		subirVentanillas();
	}
	public void subirVentanillas(){
		this.ventanillas="subidas";
	}
	public void bajarVentanillas(){
		this.ventanillas="bajadas";
	}
}
