package hilos;

import javax.swing.ImageIcon;

import interfaz.Principal;
import modelo.Personaje;

public class HiloPersonajeUno extends Thread{

	
	private Principal principal;

	private boolean mover = true;
	
	
	public HiloPersonajeUno(Principal principal) {
		this.principal = principal;
		
	}
	
	public void run () {

		while(mover)
		{
			try {
				principal.pintarImagen();
				Thread.sleep(59);
				mover = false ;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public synchronized void parar() {
//		System.out.println("algo");
		mover = false ;
	}
	
	
}
