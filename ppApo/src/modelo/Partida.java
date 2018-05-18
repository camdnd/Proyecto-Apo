package modelo;

//import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * claase que representa una partida del juego donde se enfrentaran dos jugadores.
 * */
public class Partida {

	/**
	 * constante que representa la cantidad de personajes que hay en el arreglo. 
	 * */
	public final static int CANT_PERSONAJES = 2; 
	
	/**
	 * ArrayList donde se encuentran todos los jugadores que ingresen al juego.
	 * */
//	private ArrayList<Jugador> jugadores ; 
	/**
	 * arreglo donde se encuentra los dos personajes del juego. 
	 * */
	private Personaje[] personajes; 
	
	
	private Jugador raiz;
//	private Jugador ultimo;
	
	private int numJugadores;
	 
	/**
	 * constructor de la clase inicializa una partida.
	 * */
	public Partida() {
		
		
//		jugadores = new ArrayList<Jugador>();
		this.personajes = new Personaje[CANT_PERSONAJES]; 
		crearPersonajes(); 
	}

	public Personaje[] getPersonajes() {
		return personajes;
	}

	public Jugador getRaiz() {
		return raiz;
	}

	public void setraiz(Jugador raiz) {
		this.raiz = raiz;
	}

	/**
	 * este m�todo se encarga de inicializar a los personajes del juego 
	 * este m�todo quiza no este definido del todo se esta creando para 
	 * para probar si finciona el movimiento de los personajes 
	 * 
	 * */
	public void crearPersonajes() {
		personajes[0] = new Personaje(1000, true, 80 , 220, 50, 50, Personaje.PRIMER_PERSONAJE); 
		personajes[1] = new Personaje(1000, true, 600, 220, 50, 50, Personaje.SEGUNDO_PERSONAJE); 	
	}
	
	public Jugador buscarMayorAntesDe(Jugador menor) {
		
		Jugador encontrado = raiz;
		boolean encontro = false ;

		while(!encontro ) {
			
			if(menor.compareTo(encontrado) < 0) {
				encontro = true;
			} 
			else {
				encontrado = encontrado.getDerecha();
			}
		}
		return encontrado;
	}
	
	public Jugador buscarMenorAntesDe(Jugador menor) {
		Jugador encontrado = raiz;
		boolean encontro = false ;

		while(!encontro) {
			if(menor.compareTo(encontrado) > 0) {
				encontro = true;
			} 
			else {
				encontrado = encontrado.getIzquierda();
			}
		}
		return encontrado;
	}


	
	
	
	public void agregar(String nickName) {
		Jugador nuevo = new Jugador (nickName, 0, "");

		if (raiz == null)
			raiz = nuevo;
		else {
			agregarNuevoJugador(nuevo);
		} 	
			
	}

	/*
	 * validarJugadorExistente(nickName : String ) : boolean 
	 * 
	 * el metodo valida si un jagador existe o no en la lista de jugadores 
	 * 
	 * pre = la lsta de jugadores no esta vacia (lista != null, o por lo menos tiene un jugador)
	 * 
	 * @param nickName : String = el nombre del jugador a buscar en la lista.
	 * @return existe : boolean = true = si el jugador existe en la lista 
	 * 				 			= false = si el jugador no existe en la lista
	 * */
	
	public boolean validarJugadorExistente(String nickName) {
		
		Jugador nuevo = new Jugador(nickName, 0, "");
		Jugador auxprimero = raiz;
		boolean existe = false ;
		int contador = 0;
		
		while(!existe && (contador < numJugadores+1 )) {
			if(nuevo.compareTo(auxprimero) == 0) {
				existe = true;
				nuevo.setPuntaje(auxprimero.getPuntaje());
			}
			else {
				auxprimero = auxprimero.getDerecha();
				contador++;
			}
		}
		
		return existe;
	}
	
	/*
	 * aniadirNuevoJuagdor(nickName : String ): void 
	 * el metodo agrega un nuevo jugador de mayor menor
	 * 
	 * pre = el nuevo jugador no existe dentro de la lista (su nombre no es igual a 
	 * uno que ya existe)
	 * 
	 * pos = el nuevo jugador fue agregado
	 * 
	 * @param = nickname : String - representa el nombre del nuevo jugador a agregar 
	 *  
	 * */
	
	public void verificarMayor(Jugador nuevo) {
		
		Jugador auxRaiz = raiz;
		boolean agrego = false ;
		
		while(!agrego /*&& nuevo.compareTo(auxRaiz) > 0?*/) {

			if (auxRaiz.getDerecha() == null && nuevo.compareTo(auxRaiz) > 0) {
				auxRaiz.setDerecha(nuevo);
				agrego = true;
			}
			else if (nuevo.compareTo(auxRaiz) < 0)
				verificarMenor(nuevo);
			else 
				auxRaiz = auxRaiz.getDerecha();
			
			
		}// FIN DEL SICLO
		
	}
	
	public void verificarMenor(Jugador nuevo) {
	
		Jugador auxRaiz = raiz;
		boolean agrego = false ;
		
		while(!agrego /*&& nuevo.compareTo(auxRaiz) < 0*/) {
			
			if (auxRaiz.getIzquierda() == null && nuevo.compareTo(auxRaiz) < 0) {
				auxRaiz.setIzquierda(nuevo);
				agrego = true;
			}
			else if (nuevo.compareTo(auxRaiz) > 0)
				verificarMayor(nuevo);
			else
				auxRaiz = auxRaiz.getIzquierda();
		}
	}
	
	public void agregarNuevoJugador(Jugador nuevo) {
		
//		Jugador nuevo = new Jugador(nickName, 0 , "");
		Jugador auxRaiz = raiz;
		boolean agrego = false ;
		
		
		
		//VALIDA SI VA A LA DERECHA
			
			while(!agrego && nuevo.compareTo(auxRaiz) > 0) {
//			verificarMayor(nuevo);

			
				if (auxRaiz.getDerecha() == null && nuevo.compareTo(auxRaiz) > 0) {
					auxRaiz.setDerecha(nuevo);
					agrego = true;
				}
				else {
					auxRaiz = auxRaiz.getDerecha();
					
				}
				
		}// FIN DEL SICLO
	
//		else if (nuevo.compareTo(auxRaiz) > 0) {
//			verificarMenor(nuevo);
//		}	
		
//		}//FIN DE LA VALIDACION 
		
			while(!agrego && nuevo.compareTo(auxRaiz) < 0) {
				
				if (auxRaiz.getIzquierda() == null && nuevo.compareTo(auxRaiz) < 0) {
					auxRaiz.setIzquierda(nuevo);
					agrego = true;
				} else {
					auxRaiz = auxRaiz.getIzquierda();
				}
			}// FIN DEL SICLO
		
	}// FIN DEL METODO
	
	//FIN DA LA VIDA MISMA 
	
	
	
	
	
	
//	public void agregarNuevoJuagdor(Jugador nuevo, Jugador auxRaiz ) {
//		boolean agregado = false ;
//		
//		//VALIDA SI LA LISTA ESTA VACIA
//		if(auxRaiz == null) {
//			auxRaiz  = nuevo;
//			numJugadores++;
//			agregado = true;
//			return;
//		}
//		
//		//AGREGA 
//		else {
//			
//			//VALIDA SI ES MAYOR QUE EL PRIMERO
//			if(nuevo.compareTo(auxRaiz) > 0) {
//				agregarNuevoJuagdor(nuevo, buscarMayorAntesDe(nuevo));
////				return;
//			}
//			//VALIDA SI ES MENOR QUE EL ULTIMO 
//			else if (nuevo.compareTo(auxRaiz) < 0) {
//				agregarNuevoJuagdor(nuevo, buscarMenorAntesDe(nuevo));
////				return;
//			}
//			
//		}//FIN VALIDACION EXISTE
//	
//	}// FIN DEL METODO

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	public void agregarderecha(String nickName , Jugador auxRaiz) {
//		
//		Jugador nuevo = new Jugador(nickName, 0, "");
////		Jugador auxRaiz = raiz;
//		boolean agrego = false ;
//		while(!agrego){
//
//			if(auxRaiz.getDerecha().compararPorNombre(nuevo.getNickname()) < 0) {
//				auxRaiz.setDerecha(nuevo);
//				agrego = true;
//			}else
//				auxRaiz = auxRaiz.getDerecha();
//		}
//		if(!agrego)
//			agregarIzquierda(nickName, auxRaiz);
//	}
//
//	public void agregarIzquierda(String nickName, Jugador auxRaiz) {
//		
//		Jugador nuevo = new Jugador(nickName, 0, "");
////		Jugador auxRaiz = raiz;
//		
//		boolean agrego = false ;
//		while(!agrego){
//		
//			if(auxRaiz.getIzquierda() ==  null)  
//				auxRaiz.setIzquierda(nuevo);
//			else
//				auxRaiz = auxRaiz.getIzquierda();
//		}
//		
//		if(!agrego)
//			agregarderecha(nickName, auxRaiz);
//		
//	}
//
//	public void agregarNuevoJugador(String nickName) {
//		
//		Jugador nuevo = new Jugador(nickName, 0, "");
//		Jugador auxRaiz = raiz;
//		
//		if (raiz == null) {
//			raiz = nuevo;
//			
//		} else {
//			
//			while(raiz != null) {
//
//				if (auxRaiz.compararPorNombre(nickName) < 0) 
//					agregarderecha(nickName, auxRaiz);
//				else 
//					agregarIzquierda(nickName, auxRaiz);
//			}
//			
//		}
//		
//	}
	
	/*
	 * serializarArchivos() : void
	 * este metodo serializa a los jugadores 
	 * @throws I
	 * */
	
	public void serializarArchivos() throws IOException , FileNotFoundException{
		
		FileOutputStream fos = new FileOutputStream("./data/serializacion/jugadores.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		try {
			oos.writeObject(raiz);
		}catch (FileNotFoundException e) {
			e.getMessage();
		}catch (IOException e) {
			e.getMessage();
		}
		
		finally {
			
			try {
				if (fos != null) {
					fos.close();
				}
				if(oos != null) {
					oos.close();
				}
				
			}catch (IOException e) {
				e.getMessage();
			}	
		}
	}
	
	
	public void recuperarInformacion() throws FileNotFoundException, IOException {
		
		FileInputStream fis = new FileInputStream("./data/serializacion/jugadores.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Jugador nuevaRaiz = (Jugador)ois.readObject();
			raiz = nuevaRaiz;
			
			
		}catch (Exception e) {
			e.getMessage();
		}
		finally {
			if(fis != null) {
				fis.close();
			}
			if(ois != null) {
				ois.close();
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * validarJugadorExistente(nickName : String ) : boolean 
	 * 
	 * el metodo valida si un jagador existe o no en la lista de jugadores 
	 * 
	 * pre = la lsta de jugadores no esta vacia (lista != null, o por lo menos tiene un jugador)
	 * 
	 * @param nickName : String = el nombre del jugador a buscar en la lista.
	 * @return existe : boolean = true = si el jugador existe en la lista 
	 * 				 			= false = si el jugador no existe en la lista
	 * */
//	
//	public boolean validarJugadorExistente(String nickName) {
//		
//		Jugador nuevo = new Jugador(nickName, 0, "");
//		Jugador auxprimero = raiz;
//		boolean existe = false ;
//		int contador = 0;
//		
//		while(!existe && (contador < numJugadores+1 )) {
//			if(nuevo.compararPorNombre(auxprimero.getNickname()) == 0) {
//				existe = true;
//				nuevo.setPuntaje(auxprimero.getPuntaje());
//			}
//			else {
//				auxprimero = auxprimero.getSiguiente();
//				contador++;
//			}
//		}
//		
//		return existe;
//	}
//	
	/*
	 * aniadirNuevoJuagdor(nickName : String ): void 
	 * el metodo agrega un nuevo jugador de mayor menor
	 * 
	 * pre = el nuevo jugador no existe dentro de la lista (su nombre no es igual a 
	 * uno que ya existe)
	 * 
	 * pos = el nuevo jugador fue agregado
	 * 
	 * @param = nickname : String - representa el nombre del nuevo jugador a agregar 
	 *  
	 * */
	
//	public void aniadirNuevoJuagdor(String nickName) {
//		
//		Jugador nuevo = new Jugador(nickName, 0, "");
//		Jugador auxprimero = raiz;
//		boolean agregado = false ;
//		
//		//VALIDA SI LA LISTA ESTA VACIA
//		if(primero == null) {
//			primero = nuevo;
//			primero.setAnterior(primero);
//			primero.setSiguiente(primero);
//			ultimo = primero;
//			numJugadores++;
//			agregado = true;
//			
//		}
//		
//		//AGREGA 
//		else {
//			while(!validarJugadorExistente(nickName) && !agregado) {
//			
//			//VALIDA SI ES MAYOR QUE EL PRIMERO
//			if(nuevo.compararPorNombre(primero.getNickname()) > 0) {
//				nuevo.setAnterior(ultimo);
//				nuevo.setSiguiente(primero);
//				setPrimero(nuevo);
//				numJugadores++;
//				agregado = true;
//			}
//			//VALIDA SI ES MENOR QUE EL ULTIMO 
//			else if (nuevo.compararPorNombre(ultimo.getNickname()) < 0) {
//				nuevo.setAnterior(ultimo);
//				nuevo.setSiguiente(primero);
//				setUltimo(nuevo);
//				numJugadores++;
//				agregado = true;
//			}
//			//EL NUEVO JUGADOR ESTARA EN MEDIO DE LA LISTA 
//			else if (nuevo.compararPorNombre(auxprimero.getNickname()) < 0) {
//				nuevo.setAnterior(auxprimero);
//				nuevo.setSiguiente(auxprimero.getSiguiente());
//				numJugadores++;
//				agregado = true;
//			}
//			//SI NO ES MENOR QUE auxPrimero LO COMPARA CON EL SIGUIENTE
//			else 
//				auxprimero = auxprimero.getSiguiente();
//			}
//			
//		}//FIN VALIDACION EXISTE
//	
//	}// FIN DEL METODO
	
	
	
	
	
	
	
	
	
	
	
	
}
