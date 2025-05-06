package uniandes.dpoo.hamburguesas.tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	private Combo combo1;
	private ProductoMenu papasMed;
	private ProductoMenu gaseosa;
	private ArrayList<ProductoMenu> productosCombo1;
	
    @BeforeEach
    void setUp( ) throws Exception
    {
    	productosCombo1 = new ArrayList<ProductoMenu>();
    	papasMed = new ProductoMenu("papas medianas", 5500);
    	gaseosa = new ProductoMenu("gaseosa", 5000);
    	productosCombo1.add(papasMed);
    	productosCombo1.add(gaseosa);
    	combo1 = new Combo("combo corral", 0.1, productosCombo1);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }
    
    @Test
    void testGetNombre( )
    {
        assertEquals( "combo corral", combo1.getNombre(), "El nombre del combo no es el esperado." );
    }
    
    @Test
    void testGetPrecio( )
    {
        assertEquals( 9450, combo1.getPrecio(), "El precio del combo no es el esperado." ); // Precio combo: 10500 - 1050 (10% descuento) = 9450
    }
    
    @Test
    void testGenerarFactura( )
    {
    	String stringFactura = "";
    	stringFactura += "Combo combo corral\n";
        stringFactura += " Descuento: 0.1\n" ;
        stringFactura += "            9450\n";
    	
        assertEquals( stringFactura, combo1.generarTextoFactura(), "El texto de la factura no es el esperado" );
    }
}
