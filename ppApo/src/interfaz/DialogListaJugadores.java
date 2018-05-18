package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

public class DialogListaJugadores extends JDialog implements ActionListener{

	public final static String ORDENAR = "ORDENAR";
	public final static String BUSCAR = "BUSCAR";
	public final static String REGRESAR = "REGRESAR";
	
	private JButton btnOrdenar;
	private JButton btnBuscar;
	private JButton btnRegresar;
	
	private DefaultListModel<String> model;
	private JList listaJugadores;
	private JScrollPane scroll; 
	
	private JPanel panelLista;
	
	private DialogoInicio dialogo;

	public DialogListaJugadores(DialogoInicio dialogo) {
		this.dialogo = dialogo;
		
		panelLista = new JPanel();
		
		
		panelLista.setBorder(new TitledBorder(" Jugadores "));

		panelLista.setLayout(new GridLayout(2, 1));

		btnOrdenar = new JButton("ORDENAR");
		btnBuscar = new JButton("BUSCAR");
		btnRegresar = new JButton("REGRESAR");
		
		
		model = new DefaultListModel<String>();
		listaJugadores = new JList(model);
		scroll = new JScrollPane(listaJugadores);
		
		btnOrdenar.setActionCommand(ORDENAR);
		btnBuscar.setActionCommand(BUSCAR);
		btnRegresar.setActionCommand(REGRESAR);
		
		btnOrdenar.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnRegresar.addActionListener(this);
		
		JPanel auxpanelOpcion = new JPanel();
		auxpanelOpcion.add(btnOrdenar);
		auxpanelOpcion.add(btnBuscar);
		auxpanelOpcion.add(btnRegresar);
		
		JPanel auxPanelLista = new JPanel();
		listaJugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		auxPanelLista.add(scroll);
		
		setLocationRelativeTo( null );
		setSize(600, 600);
		
		add(auxPanelLista, BorderLayout.NORTH);
		add(auxpanelOpcion, BorderLayout.SOUTH);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(REGRESAR.equals(e.getActionCommand())) {
			dialogo.setVisible(true);
			dispose();
		}
		
	}
	
}
