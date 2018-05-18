package interfaz;

import java.awt.Graphics;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Etiqueta1 extends JLabel{
	
	private ImageIcon icono;
	
	public Etiqueta1(URL direccion)
	{
		
		icono = new ImageIcon(direccion);
		
	}
	
	public void setUrl(URL nueva)
	{
		icono = new ImageIcon(nueva);
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);	
		g.drawImage(icono.getImage(),0,0,51,106,null);
	}

}
