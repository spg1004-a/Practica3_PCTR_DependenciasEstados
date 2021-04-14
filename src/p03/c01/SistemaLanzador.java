package src.p03.c01;
/**
 * Lanza hilos al parque.
 * 
 * @author Martin Gonzalez y Sergio Pino
 * @version 1.0 
 * @see src.p03.c01.SistemaLanzador
 */

public class SistemaLanzador {
	
	public static void main(String[] args) {	
		IParque parque = new Parque(); 
		char letra_puerta = 'A';
		System.out.println("!Parque abierto!");
		
		for (int i = 0; i < Integer.parseInt(args[0]); i++) {
			String puerta = ""+((char) (letra_puerta++));
			// Creación de hilos de entrada y salida
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread (entradas).start();
			new Thread (salidas).start();				
		}
	}	
}