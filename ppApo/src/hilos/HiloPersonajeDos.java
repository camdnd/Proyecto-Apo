package hilos;

import javax.swing.ImageIcon;

import interfaz.Principal;

public class HiloPersonajeDos extends Thread{


	
	private Principal principal;

	private boolean mover = true;
	
	
	public HiloPersonajeDos(Principal principal) {
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
	
	
}
