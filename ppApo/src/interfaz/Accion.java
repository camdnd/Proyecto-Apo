package interfaz;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * esta clase ayuda a la clase animacion a definir los movimientos de los personajes 
 * (mas informacion en la clase animacion apartir de la linea 88) 
 * */
public class Accion extends AbstractAction implements ActionListener
{

	/**
	 * odjeto de la clase Point 
	 * */
	private Point moveDelta;
	
	
	/**
	 * relacion con la clase animacion.
	 * */
	private Animacion animacion;
	
	
	private int controlImagen;

	public Accion(String key, Point moveDelta, Animacion animacion )
	{
		super(key);

		this.moveDelta = moveDelta;
		this.animacion = animacion;
		
	}

	public void actionPerformed(ActionEvent e)
	{
		
		animacion.handleKeyEvent((String)getValue(NAME), moveDelta);
		
	}
}
