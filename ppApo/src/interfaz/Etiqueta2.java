package interfaz;

import java.awt.Graphics;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Etiqueta2 extends JLabel{
	
	private ImageIcon icono;
	
	public Etiqueta2(URL direccion)
	{
		
		icono = new ImageIcon(direccion);
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(icono.getImage(),0,0,null);
	}

}
