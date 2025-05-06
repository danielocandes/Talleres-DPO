package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	private ProductoMenu productoMenu;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		productoMenu = new ProductoMenu("corral", 14000);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetNombre( )
    {
        assertEquals( "corral", productoMenu.getNombre(), "El nombre del producto ajustado no es el esperado." );
    }
    
    @Test
    void testGetPrecio( )
    {
        assertEquals( 14000, productoMenu.getPrecio(), "El precio del producto ajustado no es el esperado." );
    }
    
    @Test
    void testGenerarFactura( )
    {
    	String stringFactura = "";
    	stringFactura += "corral\n";
        stringFactura += "            14000\n";
    	
        assertEquals( stringFactura, productoMenu.generarTextoFactura(), "El texto de la factura no es el esperado" );
    }
}
