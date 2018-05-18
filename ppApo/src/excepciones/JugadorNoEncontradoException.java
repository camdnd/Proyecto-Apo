package excepciones;

public class JugadorNoEncontradoException extends Exception{

	private String jugadorNoEncontrado;

	public JugadorNoEncontradoException(String jugadorNoEncontrado) {
		this.jugadorNoEncontrado = jugadorNoEncontrado;
		
	}
	
	
	
	
}
