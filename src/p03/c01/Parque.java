package src.p03.c01;
import java.util.Enumeration;
import java.util.Hashtable;
/**
 * Constructor del parque.
 * 
 * @author Martin Gonzalez y Sergio Pino
 * @version 1.0 
 * @see src.p03.c01.Parque
 */

public class Parque implements IParque{

	private int contadorPersonasTotales;
	private Hashtable<String, Integer> contadoresPersonasPuerta;
	private static final int NUMPERSONASMAX = 50, 
							 NUMENTRADAS = 20;
	
	
	public Parque() {	
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
		
	}


	@Override
	public synchronized void entrarAlParque(String puerta) {		
		
		if (contadoresPersonasPuerta.get(puerta) == null){
			contadoresPersonasPuerta.put(puerta, 0);
		}
		
		comprobarAntesDeEntrar(puerta);
		contadorPersonasTotales++;	
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)+1);
		
		imprimirInfo(puerta, "Entrada");
		checkInvariante();
		notify();
		
	}
	
	
	public synchronized void salirDelParque(String puerta)  {	
		comprobarAntesDeSalir(puerta);
		contadorPersonasTotales--;		
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta)-1);
		
		imprimirInfo(puerta, "Entrada");	
		checkInvariante();
		notify();
	}
	
	
	private void imprimirInfo (String puerta, String movimiento){	
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("--> Personas en el parque " + contadorPersonasTotales); 
		
		
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
		assert contadorPersonasTotales <= NUMPERSONASMAX: "INV: El conctador de personas en el parque deben ser menor que el máximo permitido";
		assert contadorPersonasTotales >= 0: "El contador de personas totales no puede ser menor que 0";
		
	}

	
	protected void comprobarAntesDeEntrar(String puerta) {	
		if( contadoresPersonasPuerta.get(puerta) > NUMENTRADAS || contadorPersonasTotales > NUMPERSONASMAX )
			try {
				wait();
			}
			catch(Exception e) {
				System.out.println("Intenta entrar al tope");
			}
		}
	

	protected void comprobarAntesDeSalir(String puerta) {	
		if(contadoresPersonasPuerta.get(puerta) <= 0  )
			try {
				wait();
			}
			catch(Exception e) {
				System.out.println("Intenta salir con 0");
			}
	}


}