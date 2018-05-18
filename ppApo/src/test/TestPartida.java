package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Partida;

class TestPartida {

	private Partida partida;
	
	void setupEscenario1() {
		partida = new Partida();
	}
	
	void setupEscenario2() {
		setupEscenario1();
		partida.agregar("jugador1");
		partida.agregar("jugador0");
	}
	
	void setupEscenario3() {
		setupEscenario1();
		partida.agregar("jugador1");
		partida.agregar("jugador0");
		partida.agregar("jugador5");
	}
	
	void setupEscenario4() {
		setupEscenario1();
		partida.agregar("jugador1");
		partida.agregar("jugador3");
		partida.agregar("jugador5");
		partida.agregar("jugador6");
		partida.agregar("jugador9");
		partida.agregar("jugador7");
		partida.agregar("jugador8");
		partida.agregar("jugador0");
		partida.agregar("jugador4");
		
	}
	
//	@Test
//	void testAgregar1() {
//		setupEscenario1();
//		assertNull(partida.getRaiz());
//		partida.agregar("1");
//		assertNotNull(partida.getRaiz());
//		
//	}
//
//	@Test
//	void testSAgregar2() {
//		setupEscenario1();
//		partida.agregar("1");
//		partida.agregar("0");	
//		assertEquals("0", partida.getRaiz().getIzquierda().getNickname());
//		
//	}
//	
//	@Test
//	void testAgregar3() {
//		setupEscenario1();
//		partida.agregar("1");
//		partida.agregar("0");
//		partida.agregar("5");
//		
////		 a = new ("5" , 0 , null);
////		 b = new ("0" , 0 , null);
////		System.out.println(a.compararPorNombre(b.getNickname()));
//
//		assertEquals("5", partida.getRaiz().getDerecha().getNickname());
//		assertEquals("0", partida.getRaiz().getIzquierda().getNickname());
//		assertEquals("1", partida.getRaiz().getNickname());
//		
//	}
	
//	@Test
//	void testAgregar4() {
//		setupEscenario3();
//		
//		assertTrue(partida.validarExistente("5"));
//		
//	}
	
	@Test
	void testAgregar5() {
		setupEscenario4();
		assertEquals("jugador1", partida.getRaiz().getNickname());
//		assertEquals("jugador0", partida.getRaiz().getIzquierda().getNickname());
		assertEquals("jugador3", partida.getRaiz().getDerecha().getNickname());
		assertEquals("jugador5", partida.getRaiz().getDerecha().getDerecha().getNickname());
		assertEquals("jugador4", partida.getRaiz().getDerecha().getDerecha().getIzquierda().getNickname());
		assertEquals("jugador6", partida.getRaiz().getDerecha().getDerecha().getDerecha().getNickname());
//		assertEquals("jugador7", partida.getRaiz().getDerecha().getDerecha().getDerecha().getDerecha().getIzquierda().getNickname());
//		assertEquals("jugador8", partida.getRaiz().getDerecha().getDerecha().getDerecha().getDerecha().getIzquierda().getDerecha().getNickname());
		assertEquals("jugador9", partida.getRaiz().getDerecha().getDerecha().getDerecha().getDerecha().getNickname());
		
		
//		
//		
//		
//		
//		
//		
//		
//		
	
		
	}
	
	
}
