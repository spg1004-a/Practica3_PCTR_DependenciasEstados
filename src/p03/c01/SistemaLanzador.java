package src.p03.c01;
public class SistemaLanzador {
	public static void main(String[] args) {
		
		IParque parque = new Parque(); // TODO
		char letra_puerta = 'A';
		
		System.out.println("�Parque abierto!");
		
		for (int i = 0; i < Integer.parseInt(args[0]); i++) {
			
			String puerta = ""+((char) (letra_puerta++));
			
			// Creaci�n de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread (entradas).start();
			new Thread (salidas).start();
			
			// 
			// TODO inicar hilos
			
			//
			
			
		}
	}	
}