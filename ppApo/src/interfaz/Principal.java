package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.swing.*;

import hilos.HiloPersonajeDos;
import hilos.HiloPersonajeUno;
import hilos.HiloSalto;
import modelo.Jugador;
import modelo.Partida;
import modelo.Personaje;

public class Principal extends JFrame {

//	public final static int ANCHO_ESC_UNO = 708;
//	public final static int ANCHO_ESC_DOS_TRES_CINCO_VTRES_VCUATRO_VCINCO_VSEIS_VSIETE_VOCHO_TSEIS_CUNO = 800;
//	public final static int ANCHO_ESC_CUATRO_NUEVE_DOCE_TCUATRO = 768;
//	public final static int ANCHO_ESC_SEIS_TRECE_CATORCE_QUINCE_DSEIS_DSIETE_VNUEVE_TREINTA_TUNO_TDOS_TTRES_TSIETE = 640;
//	public final static int ANCHO_ESC_SIETE_OCHO_DIEZ_ONCE_DOCHO_VEINTE_VUNO = 752;
//	public final static int ANCHO_ESC_DOCE = 768;
//	public final static int ANCHO_ESC_DNUEVE = 623;
//	public final static int ANCHO_ESC_VDOS = 1536;
//	public final static int ANCHO_ESC_TCINCO = 952;
//	public final static int ANCHO_ESC_TOCHO = 896;
//	public final static int ANCHO_ESC_TNUEVO = 650;
//	public final static int ANCHO_ESC_CUARENTA = 735;
//	
//	public final static int ALTO_ESC_CUARENTA = 240;
//	public final static int ALTO_ESC_TOCHO = 509;
//	public final static int ALTO_ESC_TCINCO = 416;
//	public final static int ALTO_ESC_TUNO_TTRES = 512;
//	public final static int ALTO_ESC_VDOS = 704;
//	public final static int ALTO_ESC_VUNO = 248;
//	public final static int ALTO_ESC_QUINCE = 322;
//	public final static int ALTO_ESC_CATORCE_DSEIS_VSIETE_TSIETE = 480;
//	public final static int ALTO_ESC_TRECE_DSIETE = 464;
//	public final static int ALTO_ESC_DOCE = 237;
//	public final static int ALTO_ESC_ONCE = 271;
//	public final static int ALTO_ESC_DOS_VTRES_VCUATRO_VCINCO_VSEIS_VSIETE_VOCHO_TSEIS_CUNO = 336;
//	public final static int ALTO_ESC_DIEZ = 360;
//	public final static int ALTO_ESC_UNO_SIETE = 256;
//	public final static int ALTO_ESC_TRES_CINCO = 366;
//	public final static int ALTO_ESC_CUATRO_TCUATRO = 368;
//	public final static int ALTO_ESC_SEIS = 407;
//	public final static int ALTO_ESC_OCHO_DOCHO_DNUEVE_VEINTE_VNUEVE_TNUEVO = 224;
//	public final static int ALTO_ESC_NUEVE_VOCHO_TREINTA_TDOS = 384;
	
	private Partida partida; 

	private JLabel fondo;
	
	private Etiqueta labPersonaje1; 
	private Etiqueta1 labPersonaje2;
	
	private URL urlLabel1;
	private URL urlLabel2;
	
	private Animacion animacion1 ; 
	private Animacion animacion2; 
	
	private Personaje personajeActual;
	private Personaje p1;
	private Personaje p2;
	
//	private boolean esprimero;
//	private boolean esSegundo;
	
	private HiloPersonajeUno hilo1;
	private HiloPersonajeDos hilo2;
	private HiloSalto hilo3;
	
	private int estadoKey1;
	private int estadoKey2;
	
	/**
	 * variable de control que ayuda a cambiar las imagenes de los personajes. 
	 * */
	private int controlImagen1;
	private int controlImagen2;	
	private int controlImagenSalto;
	private int controlImagenSaltoBajar;
	
//	private String derecha1;
//	private String izquierda1;
	
	private String[] keys1 = new String[4];
	private String[] keys2 = new String[4];
	
//	private String derecha2;
//	private String izquierda2;
	
	private String keyActual;
	
//	private ImageIcon icon1;
//	private ImageIcon icon2;
	
	
	private DialogoInicio inicio;
	
	public Principal() {
		super(" Samurai Jack .VS. Mad Jack ");
		
		partida = new Partida(); 
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//		setSize(708, 290);

		Random rdn = new Random();
		int numFondo = rdn.nextInt(7) + 1;
		System.out.println(numFondo);
	
		try {
						
			ImageIcon f = new ImageIcon("data/escenarios/"+numFondo+".gif");
			fondo = new JLabel();
			fondo.setIcon(f);
			
			urlLabel1 = new File("data/jackImg/blanco/derecha/1.png").toURI().toURL();
			labPersonaje1=new Etiqueta(urlLabel1);

			urlLabel2 = new File("data/jackImg/negro/izquierda/1.png").toURI().toURL();
			labPersonaje2=new Etiqueta1(urlLabel2);
		} 
	
		catch (MalformedURLException e) {
			e.printStackTrace();
		}

//		definirDimensionesFondo(numFondo);
		
		setSize(new Dimension(768 , 336));
		
		getContentPane().add(labPersonaje2);
        labPersonaje2.setBounds(partida.getPersonajes()[1].getPosX(),partida.getPersonajes()[1].getPosY(), 51, 106);
	
        getContentPane().add(labPersonaje1);
        labPersonaje1.setBounds(partida.getPersonajes()[0].getPosX(), partida.getPersonajes()[0].getPosY(), 51, 106);
        
        getContentPane().add(fondo);
        fondo.setBounds(0, 0, 768, 336);
		
		
		inicio = new DialogoInicio(this);
		controlImagen1 = 1;
		controlImagen2 = 2;
	
		keysPersonajeUno();
		keysPersonaje2();	
		asignarMovimientos (); 
	
	}
	
	public int darEstadoKey1()
	{
		return estadoKey1;
	}
	
	public int darEstadoKey2()
	{
		return estadoKey2;
	}
		
	public Personaje[] darPersonajesDeLaPartida() {
		return partida.getPersonajes(); 
	}
	
	public Partida getPartida() {
		return partida;
	}
	
//	public JLabel getLabPersonaje1() {
//		return labPersonaje1;
//	}

//	public JLabel getlabPersonaje2() {
//		return labPersonaje2;
//	}
//
	public DialogoInicio getDialogoInicio() {
		return inicio;
	}

	public String[] darKeys1()
	{
		return keys1;
	}
	
	public String[] darKeys2()
	{
		return keys2;
	}
//lol
//	public void definirDimensionesFondo(int numFondo) {
//		
//		if (numFondo == 1) {
//			setSize(new Dimension(ANCHO_ESC_UNO, ALTO_ESC_UNO_SIETE));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,150, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 150, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 708, 256);
//		}
//		else if (numFondo == 2 || numFondo == 23 || numFondo == 24 || numFondo == 25 || numFondo == 26 || numFondo == 27 || numFondo == 28 || numFondo == 36 || numFondo == 41) {
//			setSize(new Dimension(ANCHO_ESC_DOS_TRES_CINCO_VTRES_VCUATRO_VCINCO_VSEIS_VSIETE_VOCHO_TSEIS_CUNO, ALTO_ESC_DOS_VTRES_VCUATRO_VCINCO_VSEIS_VSIETE_VOCHO_TSEIS_CUNO));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(700,220, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(20, 220, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 336);
//		}
//		else if( numFondo == 3 || numFondo == 5) {
//			setSize(new Dimension(ANCHO_ESC_DOS_TRES_CINCO_VTRES_VCUATRO_VCINCO_VSEIS_VSIETE_VOCHO_TSEIS_CUNO, ALTO_ESC_TRES_CINCO));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,230, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 230, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);	
//		}
//		else if(numFondo == 4 || numFondo == 34) {
//			setSize(new Dimension(ANCHO_ESC_CUATRO_NUEVE_DOCE_TCUATRO, ALTO_ESC_CUATRO_TCUATRO));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,230, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 230, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 6) {
//			setSize(new Dimension(ANCHO_ESC_SEIS_TRECE_CATORCE_QUINCE_DSEIS_DSIETE_VNUEVE_TREINTA_TUNO_TDOS_TTRES_TSIETE, ALTO_ESC_SEIS));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(550,260, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(50, 260, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 7) {
//			setSize(new Dimension(ANCHO_ESC_SIETE_OCHO_DIEZ_ONCE_DOCHO_VEINTE_VUNO, ALTO_ESC_UNO_SIETE));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,130, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 130, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 8 || numFondo == 18 ||numFondo == 20 ) {
//			setSize(new Dimension( ANCHO_ESC_SIETE_OCHO_DIEZ_ONCE_DOCHO_VEINTE_VUNO, ALTO_ESC_OCHO_DOCHO_DNUEVE_VEINTE_VNUEVE_TNUEVO));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,100, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 100, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 9 ) {
//			setSize(new Dimension( ANCHO_ESC_CUATRO_NUEVE_DOCE_TCUATRO, ALTO_ESC_NUEVE_VOCHO_TREINTA_TDOS));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,270, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 270, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 10) {
//			setSize(new Dimension(ANCHO_ESC_SIETE_OCHO_DIEZ_ONCE_DOCHO_VEINTE_VUNO, ALTO_ESC_DIEZ));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,230, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 230, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 11) {
//			setSize(new Dimension(ANCHO_ESC_SIETE_OCHO_DIEZ_ONCE_DOCHO_VEINTE_VUNO, ALTO_ESC_ONCE));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,145, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 145, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);	
//		}
//		else if(numFondo == 12) {
//			setSize(new Dimension(ANCHO_ESC_CUATRO_NUEVE_DOCE_TCUATRO, ALTO_ESC_DOCE));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,130, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 130, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);	
//		}
//		else if(numFondo == 13 || numFondo == 17) {
//			setSize(new Dimension(ANCHO_ESC_SEIS_TRECE_CATORCE_QUINCE_DSEIS_DSIETE_VNUEVE_TREINTA_TUNO_TDOS_TTRES_TSIETE, ALTO_ESC_TRECE_DSIETE));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(580,320, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 320, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 14 || numFondo == 16 || numFondo == 37) {
//			setSize(new Dimension(ANCHO_ESC_SEIS_TRECE_CATORCE_QUINCE_DSEIS_DSIETE_VNUEVE_TREINTA_TUNO_TDOS_TTRES_TSIETE, ALTO_ESC_CATORCE_DSEIS_VSIETE_TSIETE));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(580,345, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 345, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 15) {
//			setSize(new Dimension(ANCHO_ESC_SEIS_TRECE_CATORCE_QUINCE_DSEIS_DSIETE_VNUEVE_TREINTA_TUNO_TDOS_TTRES_TSIETE, ALTO_ESC_QUINCE));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(480,170, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 170, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);	
//		}
//		else if(numFondo == 19) {
//			setSize(new Dimension(ANCHO_ESC_DNUEVE, ALTO_ESC_OCHO_DOCHO_DNUEVE_VEINTE_VNUEVE_TNUEVO));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(570,100, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 100, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);	
//		}
//		else if(numFondo == 21) {
//			setSize(new Dimension(ANCHO_ESC_SIETE_OCHO_DIEZ_ONCE_DOCHO_VEINTE_VUNO, ALTO_ESC_VUNO));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,140, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 140, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 22) {
//			setSize(new Dimension(ANCHO_ESC_VDOS, ALTO_ESC_VDOS));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(1000,600, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 600, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 29) {
//			setSize(new Dimension(ANCHO_ESC_SEIS_TRECE_CATORCE_QUINCE_DSEIS_DSIETE_VNUEVE_TREINTA_TUNO_TDOS_TTRES_TSIETE, ALTO_ESC_OCHO_DOCHO_DNUEVE_VEINTE_VNUEVE_TNUEVO));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(580,100, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 100, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);	
//		}
//		else if(numFondo == 30 || numFondo == 32) {
//			setSize(new Dimension(ANCHO_ESC_SEIS_TRECE_CATORCE_QUINCE_DSEIS_DSIETE_VNUEVE_TREINTA_TUNO_TDOS_TTRES_TSIETE, ALTO_ESC_NUEVE_VOCHO_TREINTA_TDOS));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(580,230, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 230, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 31 || numFondo == 33) {
//			setSize(new Dimension(ANCHO_ESC_SEIS_TRECE_CATORCE_QUINCE_DSEIS_DSIETE_VNUEVE_TREINTA_TUNO_TDOS_TTRES_TSIETE, ALTO_ESC_TUNO_TTRES));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(580,380, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 380, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 35) {
//			setSize(new Dimension(ANCHO_ESC_TCINCO, ALTO_ESC_TCINCO));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(800,260, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 260, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 38) {
//			setSize(new Dimension(ANCHO_ESC_TOCHO, ALTO_ESC_TOCHO));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(710,370, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 370, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		
//		else if(numFondo == 39) {
//			setSize(new Dimension(ANCHO_ESC_TNUEVO, ALTO_ESC_OCHO_DOCHO_DNUEVE_VEINTE_VNUEVE_TNUEVO));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(580,100, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 100, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//		else if(numFondo == 40) {
//			setSize(new Dimension(ANCHO_ESC_CUARENTA, ALTO_ESC_CUARENTA));
//			
//			getContentPane().add(labPersonaje2);
//	        labPersonaje2.setBounds(600,130, 51, 106);
//			
//	        getContentPane().add(labPersonaje1);
//	        labPersonaje1.setBounds(80, 130, 51, 106);
//	        
//	        getContentPane().add(fondo);
//	        fondo.setBounds(0, 0, 800, 366);
//		}
//	}
	
	/**
	 * este metodo define los movimientos de los personajes 
	 * */
	public void asignarMovimientos () {
		
		p1 = partida.getPersonajes()[0]; 
		p2 = partida.getPersonajes()[1];
		
		animacion1 = new Animacion(labPersonaje1, 24,this);
		animacion1.Accion(keysPersonajeUno()[0], -3,  0);
		animacion1.Accion(keysPersonajeUno()[1],  3,  0);
		animacion1.Accion(keysPersonajeUno()[2],  0, -3);
			
		animacion2 = new Animacion(labPersonaje2, 24,this);
		animacion2.Accion(keysPersonaje2()[0], -3,  0);
		animacion2.Accion(keysPersonaje2()[1],  3,  0);
		
	}
	
	public void determinarPersonaje(String key)
	{
		boolean terminar = false;
		
		keyActual = key;
		
		for(int i=0;i<darKeys1().length && !terminar;i++)
		{
			if(darKeys1()[i].equals(key))
			{
				personajeActual=p1;
				terminar = true;
			}			
		}
		
		if(terminar==false)
		{
			personajeActual=p2;
		}
	}
	
	public void asignarEstadoKey(int key, String k)
	{
		determinarPersonaje(k);
		
		if(personajeActual==p1)
		{
			estadoKey1=key;
		}
		else {
			estadoKey2=key;
		}
	}

	public int moverX(int deltaX, int deltaXdx) {
		int mover = 0;

		if (personajeActual == p1) 
			mover = partida.getPersonajes()[0].moverEnX(deltaX, deltaXdx);
		else 
			mover = partida.getPersonajes()[1].moverEnX(deltaX, deltaXdx);
		
		return mover ; 
				
	}
	
	public int moverY(int deltaY, int deltaYdy) {
		int mover = 0;

		if (personajeActual == p1) 
			mover = partida.getPersonajes()[0].moverEnY(deltaY, deltaYdy);
		else 
			mover = partida.getPersonajes()[1].moverEnY(deltaY, deltaYdy);
		
		return mover ; 
	}
	
//	public int moverX1(int deltaX, int deltaXdx) {
//		 return partida.getPersonajes()[0].moverEnX(deltaX, deltaXdx);
//	}
//		
//	public int moverY1(int deltaY, int deltaYdy) {
//		return partida.getPersonajes()[0].moverEnX(deltaY, deltaYdy);	
//	}
		
	public String[] keysPersonajeUno() {
		
		keys1[0] = "A";
		keys1[1] = "D";
		keys1[2] = "W";
		keys1[3] = "S";
		
		return keys1;
	}

	public String[] keysPersonaje2() {
		
		keys2[0] = "LEFT";
		keys2[1] = "RIGHT";
		keys2[2] = "UP";
		keys2[3] = "DOWN";
		
		return keys2;
	}
	
	public void moverPersonaje()
	{
		if(personajeActual==p1)
		{
			hilo1 = new HiloPersonajeUno(this);
			hilo1.start();
		}
		
		else
		{
			hilo2 = new HiloPersonajeDos(this);
			hilo2.start();
		}
	}
	
//	public boolean getEsPrimero() {
//		return esprimero;
//	}
//	
//	public boolean getEsSegundo() {
//		return esSegundo;
//	}
	
	public void validarMovimiento(int x, int y)
	{
		if(keyActual.equals(keysPersonajeUno()[2]))
		{
			hilo3 = new HiloSalto(this);
			hilo3.start();
		}
	}
	
	public void pintarImagenSalto()
	{
		try {
			urlLabel1= new File("data/jackImg/blanco/derecha/"+controlImagenSalto+".png").toURI().toURL();
			labPersonaje1.setUrl(urlLabel1);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		controlImagenSalto++; 
		
		if (controlImagenSalto > 3) {
			
			controlImagenSalto = 1; 
		}
	}
	
	
	public void pintarBajada()
	{
		try {
			urlLabel1= new File("data/jackImg/blanco/derecha/"+controlImagenSaltoBajar+".png").toURI().toURL();
			labPersonaje1.setUrl(urlLabel1);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		controlImagenSalto++; 
		
		if (controlImagenSalto > 6) {	
			controlImagenSalto = 4; 
		}
	}


//	public int saltar(int deltaY, int deltaYdx) {
//		
//		int movimiento = 0;
//		if (personajeActual == p1)
//			movimiento = getPartida().getPersonajes()[0].saltar(deltaY,deltaYdx);
//		else
//			movimiento = getPartida().getPersonajes()[1].saltar(deltaY, deltaYdx);
//		
//		return movimiento ;
//	}	

	
	public void pintarImagen() {
		
		if(personajeActual==p1)
		{		
			if(keyActual.equals(keysPersonajeUno()[1]))
			{
				try {
					urlLabel1= new File("data/jackImg/blanco/derecha/"+controlImagen1+".png").toURI().toURL();
					labPersonaje1.setUrl(urlLabel1);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				controlImagen1++; 
			
				if (controlImagen1 > 6) {
					
					controlImagen1 = 1; 
				}
				
			}
			else if(keyActual.equals(keysPersonajeUno()[0]))
			{	
				try {
					urlLabel1= new File("data/jackImg/blanco/izquierda/"+controlImagen1+".png").toURI().toURL();
					labPersonaje1.setUrl(urlLabel1);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				controlImagen1++; 
				if (controlImagen1 > 6) {
					controlImagen1 = 1; 
				}
			}
		}
		else
		{
			if(keyActual.equals(keysPersonaje2()[1]))
			{		
				try {
					urlLabel2= new File("data/jackImg/negro/derecha/"+controlImagen2+".png").toURI().toURL();
					labPersonaje2.setUrl(urlLabel2);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				controlImagen2++; 
				if (controlImagen2 > 6) {
					controlImagen2 = 1; 
				}
			}
			else if(keyActual.equals(keysPersonaje2()[0]))
			{
				try {
					urlLabel2= new File("data/jackImg/negro/izquierda/"+controlImagen2+".png").toURI().toURL();
					labPersonaje2.setUrl(urlLabel2);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}

				controlImagen2++; 
				if (controlImagen2 > 6) {
					controlImagen2 = 1; 
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		Principal p = new Principal();
		DialogoInicio comenzar = p.getDialogoInicio();
		
		comenzar.setVisible(true);
	
//		p.setVisible(true);
	}
	
}