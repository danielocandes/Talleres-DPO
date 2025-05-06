package uniandes.dpoo.hamburguesas.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;


public class RestauranteTest {
	
	private Restaurante restaurante1;
	private Restaurante restaurante2;
	private Restaurante restaurante3;
	private Restaurante restaurante4;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		restaurante1 = new Restaurante();
		restaurante2 = new Restaurante();
		restaurante3 = new Restaurante();
		restaurante4 = new Restaurante();
		
		restaurante2.iniciarPedido("Pepito", "Mi casa");
		
		new File("./facturas/").mkdirs(); // Las pruebas de factura pasan si se crea la carpeta de facturas antes pero creo que por enunciado de taller esto no se puede hacer, no se
		String carpeta = "./data/";
    	File ingredientesFile = new File(carpeta + "ingredientes.txt");
    	File menuFile = new File(carpeta + "menu.txt");
    	File combosFile = new File(carpeta + "combos.txt");
    	
    	try {
			restaurante3.cargarInformacionRestaurante(ingredientesFile, menuFile, combosFile);
		} catch (NumberFormatException | HamburguesaException | IOException e) {
			fail("Hubo un problema al cargar la info del restaurante. Mensaje de error: " + e.getMessage());
		}
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testIniciarPedido( )
    {
        try {
        	restaurante1.iniciarPedido("Seneca", "Cra. 1 #18a-12");
        	assertTrue(true); // Si no sale error, se sabe que se CREO un pedido, que es todo lo que se intenta probar aca. Luego veremos si el pedido se crea bien. 
        } catch (YaHayUnPedidoEnCursoException e) {
        	fail("Sale que ya hay un pedido, cosa que no deberia pasar ya que el restaurante se acaba de crear");
        }
        
        try {
        	restaurante2.iniciarPedido("Yo", "Si");
        	fail("Se creó un nuevo pedido cuando habia otro en curso.");
        } catch (YaHayUnPedidoEnCursoException e) {
        	assertTrue(true);
        }
        
    }
    
    @Test
    void testCerrarGuardarPedido()
    {
    	try {
    		restaurante1.cerrarYGuardarPedido();
    		fail("Se cerró un pedido que no existe.");
    	} catch (NoHayPedidoEnCursoException | IOException e) {
    		assertTrue(true);
    	}
    	
    	try {
    		String textoEsperado = restaurante2.getPedidoEnCurso().generarTextoFactura();
    		int idPedido = restaurante2.getPedidoEnCurso().getIdPedido();
    		restaurante2.cerrarYGuardarPedido();
    		assertEquals(restaurante2.getPedidoEnCurso(), null, "Aun hay un pedido en curso después de cerrarlo");
    		
    		String archivo = "./facturas/" + "factura_" + idPedido + ".txt";
    		BufferedReader reader = new BufferedReader( new java.io.FileReader( archivo ));
    		
    		String respuestaObtenida = "";
    		
    		String linea = reader.readLine( );
            while( linea != null )
            {
                respuestaObtenida += linea + "\n";
                linea = reader.readLine( );
            }
            reader.close();
            
            assertEquals(textoEsperado, respuestaObtenida, "La factura tiene un texto diferente al esperado");
    		
    	} catch (IOException | NoHayPedidoEnCursoException e) {
    		fail("Se arrojó una excepcion tipo " + e.getClass() + ". El mensaje del error es: " + e);
    	}
    }
    
    @Test
    void testGetPedidoEnCurso() 
    {
    	assertEquals(restaurante1.getPedidoEnCurso(), null, "El restaurante 1 no deberia tener un pedido activo");
    	
    	String nombrePedido = restaurante2.getPedidoEnCurso().getNombreCliente();
    	assertEquals("Pepito", nombrePedido, "El restaurante 2 no tiene el pedido correcto.");
    }
    
    @Test
    void testGetPedidos() {
    	try {
			for (int i = 0; i < 10; i++) {
				restaurante1.iniciarPedido(Integer.toString(i), Integer.toString(i));
				restaurante1.cerrarYGuardarPedido();
			}
			
			ArrayList<Pedido> listaPedidos = restaurante1.getPedidos();
			
			int i = 0;
			for (Pedido pedido : listaPedidos) {
	    		assertEquals(Integer.toString(i), pedido.getNombreCliente(), "El pedido " + i + " no es consistente con lo que se guardó en la lista de pedidos.");
	    	}
		
    	} catch (NoHayPedidoEnCursoException | IOException | YaHayUnPedidoEnCursoException e) {
			fail("Algo falló cuando se estaban arreglando los pedidos de prueba. Mensaje de error: " + e.getMessage());
		}
    	
    }
    
    @Test
    void testGetMenuBase() 
    {
    	File menuFile = new File("./data/menu.txt");
    	
    	try {
    	BufferedReader reader = new BufferedReader( new java.io.FileReader( menuFile ));
		
		ArrayList<ProductoMenu> productosMenu = new ArrayList<ProductoMenu>();
		
		String linea = reader.readLine( );
        while( linea != null )
        {
            String[] infoProducto = linea.split(";");
            ProductoMenu producto = new ProductoMenu(infoProducto[0], Integer.parseInt(infoProducto[1]));
        	productosMenu.add(producto);
            linea = reader.readLine( );
        }
        reader.close();
        
        ArrayList<ProductoMenu> productosRecibidos = restaurante3.getMenuBase();
        
        int i = 0;
        for (ProductoMenu productoEsperado : productosMenu) {
        	ProductoMenu productoRecibido = productosRecibidos.get(i);
        	assertEquals(productoEsperado.getNombre(), productoRecibido.getNombre(), "El restaurante no tiene los mismos nombres de productos que el menu");
        	assertEquals(productoEsperado.getPrecio(), productoRecibido.getPrecio(), "El restaurante no tiene los mismos precios de productos que el menu");
        	i++;
        }
        
    	} catch(Exception e) {
    		fail("hubo un problema con el archivo de menu");
    	}
    	
    }

    
    @Test
    void testGetMenuCombos() 
    {
    	File menuFile = new File("./data/combos.txt");
    	
    	try {
    	BufferedReader reader = new BufferedReader( new java.io.FileReader( menuFile ));
		
		ArrayList<Combo> menuCombosArchivo = new ArrayList<Combo>();
		
		String linea = reader.readLine( );
        while( linea != null )
        {
            String[] infoCombo = linea.split(";");
            ArrayList<ProductoMenu> productosMenu = new ArrayList<ProductoMenu>();
            for (int i = 2; i < infoCombo.length; i++) {
            	productosMenu.add(new ProductoMenu(infoCombo[i], 0)) ;
            }
            
            double descuento = Double.parseDouble(infoCombo[1].replace("%", ""));
            
            Combo combo = new Combo(infoCombo[0], descuento, productosMenu);
        	menuCombosArchivo.add(combo);
            linea = reader.readLine( );
        }
        reader.close();
        
        ArrayList<Combo> combosRecibidos = restaurante3.getMenuCombos();
        
        int i = 0;
        for (Combo comboEsperado : menuCombosArchivo) {
        	Combo comboRecibido = combosRecibidos.get(i);
        	assertEquals(comboEsperado.getNombre(), comboRecibido.getNombre(), "El restaurante no tiene los mismos nombres de combos que el menu");
        	i++;
        }
        
    	} catch(Exception e) {
    		fail("hubo un problema con el archivo de combos. Error: " + e.getMessage());
    	}
    	
    }
    
    
    @Test
    void testGetIngredientes() 
    {
    	File menuFile = new File("./data/ingredientes.txt");
    	
    	try {
    	BufferedReader reader = new BufferedReader( new java.io.FileReader( menuFile ));
		
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		
		String linea = reader.readLine( );
        while( linea != null )
        {
            String[] infoProducto = linea.split(";");
            Ingrediente ingrediente = new Ingrediente(infoProducto[0], Integer.parseInt(infoProducto[1]));
        	ingredientes.add(ingrediente);
            linea = reader.readLine( );
        }
        reader.close();
        
        ArrayList<Ingrediente> productosRecibidos = restaurante3.getIngredientes();
        
        int i = 0;
        for (Ingrediente productoEsperado : ingredientes) {
        	Ingrediente productoRecibido = productosRecibidos.get(i);
        	assertEquals(productoEsperado.getNombre(), productoRecibido.getNombre(), "El restaurante no tiene los mismos nombres de ingredientes que el menu");
        	assertEquals(productoEsperado.getCostoAdicional(), productoRecibido.getCostoAdicional(), "El restaurante no tiene los mismos precios de ingredientes que el menu");
        	i++;
        }
        
    	} catch(Exception e) {
    		fail("hubo un problema con el archivo de menu");
    	}
    	
    }
    
    @Test
    void testCargarInformacionRestaurante() 
    {
    	String carpeta = "./data/";
    	File ingredientesFile = new File(carpeta + "ingredientes.txt");
    	File menuFile = new File(carpeta + "menu.txt");
    	File combosFile = new File(carpeta + "combos.txt");
    	
    	File ingredientesRepetidosFile = new File(carpeta + "ingredientesRepetidos.txt");
    	File menuRepetidosFile = new File(carpeta + "menuRepetidos.txt");
    	File combosRepetidosFile = new File(carpeta + "combosRepetidos.txt");
    	File combosInvalidosFile = new File(carpeta + "combosInvalidos.txt");
    	
    	try {
    		restaurante4.cargarInformacionRestaurante(ingredientesRepetidosFile, menuFile, combosFile);
    		fail("Se cargó exitosamente un restaurante con un archivo de ingredientes con valores repetidos");
    	} catch(IngredienteRepetidoException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		fail("Sucedió un error inesperado al cargar 1era informacion del restaurante. Error: " + e.getMessage());
    	}
    	
    	restaurante4 = new Restaurante();
    	
    	try {
    		restaurante4.cargarInformacionRestaurante(ingredientesFile, menuRepetidosFile, combosFile);
    		fail("Se cargó exitosamente un restaurante con un archivo de menu con valores repetidos");
    	} catch(ProductoRepetidoException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		fail("Sucedió un error inesperado al cargar 2nda informacion del restaurante. Error: " + e.getMessage());
    	}
    	
    	restaurante4 = new Restaurante();
    	
    	try {
    		restaurante4.cargarInformacionRestaurante(ingredientesFile, menuFile, combosRepetidosFile);
    		fail("Se cargó exitosamente un restaurante con un archivo de combos con valores repetidos");
    	} catch(ProductoRepetidoException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		fail("Sucedió un error inesperado al cargar 3era informacion del restaurante. Error: " + e.getMessage());
    	}
    	
    	restaurante4 = new Restaurante();
    	
    	try {
    		restaurante4.cargarInformacionRestaurante(ingredientesFile, menuFile, combosInvalidosFile);
    		fail("Se cargó exitosamente un restaurante con un archivo de combos con productos invalidos");
    	} catch(ProductoFaltanteException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		fail("Sucedió un error inesperado al cargar 4ta informacion del restaurante. Error: " + e.getMessage());
    	}
    }
    
}
