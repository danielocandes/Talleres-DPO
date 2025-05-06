package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	
	// ESTA CLASE NO LLEGA A COBERTURA DEL 100% PORQUE NO HAY MANERA DE AGREGAR INGREDIENTES A LAS LISTAS DE AGREGADOS/ELIMINADOS EN PRODUCTOAJUSTADO
	
	private ProductoAjustado productoAjustado1;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		ProductoMenu productoMenu = new ProductoMenu("corral", 14000);
		productoAjustado1 = new ProductoAjustado(productoMenu);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetNombre( )
    {
        assertEquals( "corral", productoAjustado1.getNombre(), "El nombre del producto ajustado no es el esperado." );
    }
    
    @Test
    void testGetPrecio( )
    {
        assertEquals( 14000, productoAjustado1.getPrecio(), "El precio del producto ajustado no es el esperado." ); // No hay funcion para agregar ingredientes. Entonces este precio sera el precio del corral normal.
    }
    
    @Test
    void testGenerarFactura( )
    {
    	String stringFactura = "";
    	stringFactura += "corral\n";
        stringFactura += "            14000\n";
    	
        assertEquals( stringFactura, productoAjustado1.generarTextoFactura(), "El texto de la factura no es el esperado" );
    }
}
