package interfaz;

import java.awt.Dimension;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

/**
 * esta clase ayudara a definir el movimiento de los personajes con las acciones del teclado.
 * 
 * */

public class Animacion implements ActionListener {
	
	/**
	 * esta constante representa cuando la tecla deja de ser presionda.
	 * */
	private final static String RELEASED = "released "; 
	
	/**
	 * component : este atributo representa un componente grafico.
	 * */
	private JComponent component;
	
	/**
	 * timer : un hilo de java que ejecutara el movimiento de los personajes. 
	 * */
	private Timer timer;
	
	/**
	 * pressedkeys : un HashMap que resive como parametros un String que indica la tecla 
	 * que lo acciona y un objeto de tipo Poitn que indica hacia que punto del panel debe moverse 
	 * 
	 * */
	private Map<String, Point> pressedKeys = new HashMap<String, Point>();
	 
	private Principal principal;
	
	private String keyControl;
	
	/**
	 * constructor de la clase 
	 * @param component : JComponent - un objeto de tipo grafico 
	 * @param delay : int - un int que sirve para inicializar el Timer
	 * @param  
	 * */
	public Animacion (JComponent component, int delay,Principal principal )
	{
		this.component = component;
		timer = new Timer(24,this);
		timer.setInitialDelay( 0 );
		this.principal = principal;  
		 
	}

	public Principal getPrincipal() {
		return principal;
	}
		
	/**
	 * addAction : este m�todo se encarga de a�adir la accion de movimeinto
	 * @param keyStroker : String - que representa la tecla que debe presionar el usuario para que se active la acci�n 
	 * @praam deltaX : int - representa el avance en X del objeto
	 * @param deltaY : int - representa el avance en Y del objeto
	 * */
	public void Accion(String keyStroke, int deltaX, int deltaY)
	{

		/* OTENER EL "InputMap" Y EL  "ActionMap" DEL COMPONENTE GRAFICO (EL QUE SE TIENE COMO ATRIBUTO DE ESTA CLASE) */
		/* se crea un objeto InputMap con el InputMap del componete  */
		InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		/* se crea un objeto ActionMap con el action map del componente */
		ActionMap actionMap = component.getActionMap();
		
		/* crea la acci�n y a�ade *** para la pulsaci�n del boton  */
		
		/* crea un objeto Action de tipo AnimationAction  
		 * este objeto define hacia donde se va a mover el personaje (para esto se crea un odjeto tipo
		 * Point que define hacia donde se movera el personaje en pantalla) 
		 * al oprimir la tecla pasada por parametro (keyStroke)
		 * */
		Action pressedAction = new Accion(keyStroke, new Point(deltaX, deltaY),this);
		/* crea un objeto KeyStroker  */
		KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(keyStroke);
		inputMap.put(pressedKeyStroke, keyStroke);
		actionMap.put(keyStroke, pressedAction);

		//  Create Action and add binding for the released key
		
		/* el segundo odjeto de tipo accion defina que cuando el usuario deje de presionar
		 * la tecla el personaje deje de moverse en pantalla (objeto Point = null)  
		 * */
		Action releasedAction = new Accion(keyStroke, null, this);
		String releasedKey = RELEASED + keyStroke;
		KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke(releasedKey);
		inputMap.put(releasedKeyStroke, releasedKey);
		actionMap.put(releasedKey, releasedAction);

		
	}

	//  Invoked whenever a key is pressed or released

	/**
	 * handleKeyEvent - este metodo es llamado en la clase Accion cuando la tecla es 
	 * presionada por el usuario el personaje se mueve por el HashMap
	 * inicia el Timer cuando se esta presionando un tecla valida y
	 * lo para cuando deja de ser presionada.
	 * */
	public void handleKeyEvent(String key, Point moveDelta) 
	{	
		
		keyControl = key;
		//  Keep track of which keys are pressed
		
		/* si el onjeto Point es nulo quita el Key del HashMap (key == a la tecla presionada por el ususario)
		 * en caso contrario agrega el key y moveDelta (moveDelta == odjeto Point que define hacia donde se mueve el personaje)
		 */
		
		if (moveDelta == null) {
			pressedKeys.remove( key );
//			principal.parar();
		}
		else {
			pressedKeys.put(key, moveDelta);
		}

		//  Start the Timer when the first key is pressed

		//System.out.println(pressedKeys.size());
		/* si el boton esta presionado inicia el hilo (inicia el Timer)
		 * */
		
   		if (pressedKeys.size() == 1)
   		{
   			principal.asignarEstadoKey(1, key);
   			timer.start();
   		}		
   		
		//  Stop the Timer when all keys have been released

   		/* si deja de ser presionado para el hilo
   		 * */
   		
   		if (pressedKeys.size() == 0)
   		{
   			principal.asignarEstadoKey(0, key);
   			timer.stop();
   		}
   	}
	
	//  Invoked when the Timer fires

	public void actionPerformed(ActionEvent e)
	{
		moveComponent();
	}

	//  Move the component to its new location

	public void moveComponent()
	{

		int componentWidth = component.getSize().width;
		int componentHeight = component.getSize().height;

		Dimension parentSize = component.getParent().getSize();
		
		int parentWidth  = parentSize.width;
		int parentHeight = parentSize.height;

		//  Calculate new move

		int deltaX = 0;
		int deltaY = 0;
		
		for (Point delta : pressedKeys.values())
		{
//			if(keyControl == "LEFT" || keyControl == "RIGHT" || keyControl == "A" || keyControl == "D" ) {
			deltaX = principal.moverX(deltaX, delta.x);
			deltaY = principal.moverY(deltaY, delta.y);	
//			}
//			else if(keyControl == "W") {
//				deltaX = principal.saltar(deltaY,delta.y);
//			}
			
		}


		//  Determine next X position

		int nextX = Math.max(component.getLocation().x + deltaX, 0);

		if ( nextX + componentWidth > parentWidth)
		{
			nextX = parentWidth - componentWidth;
		}

		//  Determine next Y position

		int nextY = Math.max(component.getLocation().y + deltaY, 0);

		if ( nextY + componentHeight > parentHeight)
		{
			nextY = parentHeight - componentHeight;
		}

		System.out.println(keyControl);
		
		//  Move the componen
		component.setLocation(nextX, nextY);
		principal.determinarPersonaje(keyControl);
		principal.moverPersonaje();
		
	}


}