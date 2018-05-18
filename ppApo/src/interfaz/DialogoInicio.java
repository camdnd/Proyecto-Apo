package interfaz;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DialogoInicio extends JDialog  implements ActionListener{

	public final static String MOSTRAR_LISTA_JUGADORES = "JUGADORES",  MOSTRAR_LISTA_PUNTAGES = "MEJOR PUNTAGE", COMENZAR = "COMENZAR"; 

	private JButton btnListJugodor , btnListPuntage, btnComenzar; 
	
	private JPanel panelOpciones;

	private DialogListaJugadores panelJugador;
	private DialogoPuntages puntages;
	
	
	private Principal principal;

	public DialogoInicio(Principal principal) {
		super();
		this.principal = principal;
	
		panelJugador = new DialogListaJugadores(this);
		puntages = new DialogoPuntages(this);
		
//		setDefaultCloseOperation( JDialog.EXIT_ON_CLOSE );
		setSize(608, 400);
		setLocationRelativeTo( null );	
		
		
		
		iniciarOpciones();
		

		
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		ImageIcon fondo = new ImageIcon("data/imgExt/portada2.jpg");
		
		g.drawImage(fondo.getImage(), 0, 0, null);
	
//		iniciarOpciones();
		
	}
	
	
	
	public void iniciarOpciones() {
		
		panelOpciones = new JPanel();
	
		btnComenzar = new JButton("COMENZAR");
		btnComenzar.setActionCommand(COMENZAR); 
		btnComenzar.addActionListener(this); 
		
//		btnComenzar.setLocation(new Point());
		
		btnListJugodor = new JButton("Mostrat Lista De jugadores");
		btnListJugodor.setActionCommand(MOSTRAR_LISTA_JUGADORES); 
		btnListJugodor.addActionListener(this); 
		
		btnListPuntage = new JButton("Mostras Mejores Puntages");
		btnListPuntage.setActionCommand(MOSTRAR_LISTA_PUNTAGES); 
		btnListPuntage.addActionListener(this); 
		
		panelOpciones.setLayout(null);
		btnComenzar.setBounds(10, 300, 120, 25);
		btnListJugodor.setBounds(10, 325, 250, 25);
		btnListPuntage.setBounds(10, 350, 250, 25);
		
//		panelOpciones.setFont();
		
		panelOpciones.add(btnComenzar);
		panelOpciones.add(btnListJugodor);
		panelOpciones.add(btnListPuntage);
		
		
		add(panelOpciones);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(COMENZAR.equals(e.getActionCommand())){
			
			principal.setVisible(true);
			
			dispose();
		
			
		}
		
		else if (MOSTRAR_LISTA_JUGADORES.equals(e.getActionCommand())) {
			
			panelJugador.setVisible(true);
			dispose();
		}
		
		else if(MOSTRAR_LISTA_PUNTAGES.equals(e.getActionCommand())) {
			puntages.setVisible(true);
			dispose();
		}
		
		
		
	}
	
	
	
	
	
}
