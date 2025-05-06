package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

public class PedidoTest {
	
	private Pedido pedido1;
	private Pedido pedido2;
	private Pedido pedido3;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		ProductoMenu producto1 = new ProductoMenu("corral", 14000);
    	ProductoMenu producto2 = new ProductoMenu("wrap de pollo", 15000);
		pedido1 = new Pedido("Seneca", "Cra 1 #18A-12");
		pedido2 = new Pedido("Fernando de Magallanes", "Cl 152A #16A-85");
		pedido3 = new Pedido("Maluma", "Cra 45 #22B-56");
		
		pedido1.agregarProducto(producto1);
		pedido1.agregarProducto(producto2);
		
		pedido2.agregarProducto(producto1);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetID( )
    {
    	int idInicial = pedido1.getIdPedido(); // El ID es privado entonces no se puede probar si el primer ID es correcto, pero podemos saber si los otros dos son consistentes
    	
        assertEquals( idInicial + 1, pedido2.getIdPedido(), "El ID del pedido 2 no es el esperado" );
        assertEquals( idInicial + 2, pedido3.getIdPedido(), "El ID del pedido 3 no es el esperado" );
    }
    
    @Test
    void testGetNombreCliente( )
    {
        assertEquals( "Seneca", pedido1.getNombreCliente(), "El nombre del cliente del pedido 1 no es el esperado" );
        assertEquals( "Fernando de Magallanes", pedido2.getNombreCliente(), "El nombre del cliente del pedido 2 no es el esperado" );
        assertEquals( "Maluma", pedido3.getNombreCliente(), "El nombre del cliente del pedido 3 no es el esperado" );
    }
    
    @Test
    void testGetPrecio( )	// Este tambien sirve para probar agregarProducto(), ya que Pedido no tiene un getter para la lista de sus productos
    {
        assertEquals( 16660, pedido2.getPrecioTotalPedido(), "El precio del pedido 2 no es el correcto" );;
        assertEquals( 34510, pedido1.getPrecioTotalPedido(), "El precio del pedido 1 no es el correcto" );
    }
    
    private String textoFacturaEsperada() {
    	String respuestaEsperada = "";
    	
    	respuestaEsperada += ( "Cliente: Seneca\n" );
        respuestaEsperada += ( "Dirección: Cra 1 #18A-12\n" );
        respuestaEsperada += ( "----------------\n" );

        respuestaEsperada += "corral\n";
        respuestaEsperada += "            14000\n";
        
        respuestaEsperada += "wrap de pollo\n";
        respuestaEsperada += "            15000\n";
        

        respuestaEsperada += ( "----------------\n" );
        respuestaEsperada += ( "Precio Neto:  29000\n" );
        respuestaEsperada += ( "IVA:          5510\n" );
        respuestaEsperada += ( "Precio Total: 34510\n" );
        
        return respuestaEsperada;
    }
    
    @Test
    void testGenerarTextoFactura( )
    {
    	String respuestaEsperada = textoFacturaEsperada();
        assertEquals( respuestaEsperada, pedido1.generarTextoFactura(), "El texto de la factura generado no es correcto" );
    }
    
    @Test
    void testGuardarFactura( ) throws NoHayPedidoEnCursoException, IOException
    {
    	Restaurante restaurante = new Restaurante();
    	try {
			restaurante.iniciarPedido("Seneca", "Cra 1 #18A-12");
		} catch (YaHayUnPedidoEnCursoException e) {
			fail("hubo un error en la prueba");
		}
    	
    	Pedido estePedido = restaurante.getPedidoEnCurso();
    	ProductoMenu producto1 = new ProductoMenu("corral", 14000);
    	ProductoMenu producto2 = new ProductoMenu("wrap de pollo", 15000);
    	estePedido.agregarProducto(producto1);
    	estePedido.agregarProducto(producto2);
    	
    	try {
    	restaurante.cerrarYGuardarPedido();
    	} catch (FileNotFoundException e) {
    		fail("El archivo no se creó exitosamente");
    	}
    	
    	String CARPETA_FACTURAS = "./facturas/";
    	String PREFIJO_FACTURAS = "factura_";
	
    	String nombreArchivo = CARPETA_FACTURAS + PREFIJO_FACTURAS + estePedido.getIdPedido( ) + ".txt";
    	
    	String respuestaEsperada = textoFacturaEsperada();
    	String respuestaObtenida = "";
    	
    	try {
			BufferedReader reader = new BufferedReader( new java.io.FileReader( nombreArchivo ) );
			try
	        {
	            String linea = reader.readLine( );
	            while( linea != null )
	            {
	                respuestaObtenida += linea + "\n";
	                linea = reader.readLine( );
	            }
	        }
	        catch( Exception e )
	        {
	        	fail("Hubo un error al leer el archivo: " + nombreArchivo);
	        }
	        finally
	        {
	            try {
					reader.close( );
				} catch (IOException e) {
					fail("Hubo un error al leer el archivo: " + nombreArchivo);
				}
	        }
			
			
		} catch (FileNotFoundException e) {
			fail("No se pudo encontrar el archivo: " + nombreArchivo);
		}
    	
    	assertEquals( respuestaEsperada, respuestaObtenida, "La factura no se guardó correctamente." );
    	
    }
}
