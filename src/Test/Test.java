package Test;

import Models.*;

public class Test {

	public static void main(String[] args) {
		Vehiculo v1 = new Motocicleta("Rojo", "1234asd", "Marca", "Modelo", 200, 666666666);
		Taller t1 = new Taller();
		t1.aniadirVehiculoAveriado((Vehiculo)v1);
		(t1).cambiarEscape();
	}

}
