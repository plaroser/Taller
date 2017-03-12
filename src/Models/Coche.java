package Models;

public class Coche extends Vehiculo {
	private String ventanillas;

	public Coche(String color, String matricula, String marca, String modelo, int velocidadMaxima, int telefonoDueño) {
		super(color, matricula, marca, modelo, velocidadMaxima, telefonoDueño);
		subirVentanillas();
	}

	public Coche(String color, String matricula, String marca, String modelo, int velocidadMaxima, int telefonoDueño,
		 String averia) {
		super(color, matricula, marca, modelo, velocidadMaxima, telefonoDueño, averia);
		subirVentanillas();
	}

	public void subirVentanillas() {
		this.ventanillas = "subidas";
		System.out.println(estadoVentanillas());
	}

	public void bajarVentanillas() {
		this.ventanillas = "bajadas";
		System.out.println(estadoVentanillas());
	}

	private String estadoVentanillas(){
		return "Las ventanillas estan: "+this.ventanillas;
	}
}
