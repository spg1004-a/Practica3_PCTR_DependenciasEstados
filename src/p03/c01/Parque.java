package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Parque implements IParque{

	// TODO 
	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	private static final int NUMPERSONASMAX = 50, 
							 NUMENTRADAS = 20;
	
	
	public Parque() {	// TODO
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		// TODO
	}


	@Override
	//si no hay adaptador falta algo que sirva como pa eso
	public synchronized void entrarAlParque(String puerta) {		// TODO
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		comprobarAntesDeEntrar(puerta);
		
		// TODO
			
		
		
		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;	
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
		
		// TODO
		checkInvariante();
		
		// TODO
		notify();
	}
	
	
	public synchronized void salirDelParque(String puerta)  {
		// Si no hay entradas por esa puerta, inicializamos				
		// TODO
		comprobarAntesDeSalir(puerta);
			
		// Aumentamos el contador total y el individual
		contadorPersonasTotales--;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
				
		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");
				
		// TODO
		checkInvariante();
		
		notify();
	}
	
	
	private void imprimirInfo (String puerta, String movimiento){
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); //+ " tiempo medio de estancia: "  + tmedio);
		
		// Iteramos por todas las puertas e imprimimos sus entradas
		for(String p: contadoresPersonasPuerta.keySet()){
			System.out.println("----> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}
	
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
			Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
			while (iterPuertas.hasMoreElements()) {
				sumaContadoresPuerta += iterPuertas.nextElement();
			}
		return sumaContadoresPuerta;
	}
	
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales : "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		// TODO 
		// TODO
//		Set<String> keys = contadoresPersonasPuerta.keySet();
//		Iterator<String> itr = keys.iterator();
//		while (itr.hasNext()){
//			String key = (String) itr.next();
//			sumarPersonasPuertas += contadoresPersonasPuerta.get(keys);
//		}
	}

	
	protected void comprobarAntesDeEntrar(String puerta) {	
		// assert variables de que haya hueco intuyo
		if( contadoresPersonasPuerta.get(puerta) > NUMENTRADAS || contadorPersonasTotales > NUMPERSONASMAX )
			try {
				wait();
			}
			catch(Exception e) {
				System.out.println("Intenta entrar al tope");
			}
		}

	protected void comprobarAntesDeSalir(String puerta) {	
		// assert variables de que haya 
		if(contadoresPersonasPuerta.get(puerta) <= 0  )
			try {
				wait();
			}
			catch(Exception e) {
				System.out.println("Intenta salir con 0");
			}
	}


}