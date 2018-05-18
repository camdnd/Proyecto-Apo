package hilos;

import interfaz.Principal;

public class HiloSalto extends Thread{

	private Principal principal;

	private boolean mover = true;
	
	private int total;

	
	public HiloSalto(Principal principal)
	{
		this.principal=principal;

		total=6;
	}
	
	public void run() {

		
		
		
		
		
//		while(total>0&&!mover)
//		{
//			System.out.println(total);
//			try {
//				if(total>3) {
//					principal.pintarImagenSalto();
//					total--;
//				}
//				else {
//					principal.pintarBajada();
//					total--;
//				}
//				
//				Thread.sleep(59);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			
//		}
//		mover = false;
	}
	
}
