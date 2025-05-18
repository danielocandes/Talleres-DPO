package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;

@SuppressWarnings("serial")
public class VentanaAgregarRestaurante extends JFrame
{
    /**
     * El panel donde se editan los detalles del restaurante
     */
    private PanelEditarRestaurante panelDetalles;

    /**
     * El panel con los botones para agregar un restaurante o cerrar la ventana
     */
    private PanelBotonesAgregar panelBotones;

    /**
     * El panel para marcar la ubicación del restaurante
     */
    private PanelMapaAgregar panelMapa;

    /**
     * La ventana principal de la aplicación
     */
    private VentanaPrincipal ventanaPrincipal;

    public VentanaAgregarRestaurante( VentanaPrincipal principal )
    {
        this.ventanaPrincipal = principal;
        setLayout( new BorderLayout( ) );

        // Agrega el panel donde va a estar el mapa
        // reviewTODO completar
        panelMapa = new PanelMapaAgregar();

        // Agrega en el sur un panel para los detalles del restaurante y para los botones
        // reviewTODO completar
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        panelBotones = new PanelBotonesAgregar(this);
        panelDetalles = new PanelEditarRestaurante();
        southPanel.add(panelBotones, BorderLayout.SOUTH);
        southPanel.add(panelDetalles, BorderLayout.CENTER);

        add(panelMapa, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        
        // Termina de configurar la ventana
        setTitle( "Agregar Restaurante" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 480, 650 );
        setResizable( false );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setLocationRelativeTo( null );
        setVisible(true);
    }

    /**
     * Le pide al panelDetalles los datos del nuevo restaurante y se los envía a la ventana principal para que cree el nuevo restaurante, y luego cierra la ventana.
     */
    public void agregarRestaurante( )
    {
        // TODO completar
    	String nombre;
    	int calificacion;
    	int[] coordenadas;
    	boolean visitado;
    	
    	nombre = panelDetalles.getNombre();
    	calificacion = panelDetalles.getCalificacion();
    	coordenadas = panelMapa.getCoordenadas();
    	visitado = panelDetalles.getVisitado();
    	ventanaPrincipal.agregarRestaurante(nombre, calificacion, coordenadas[0], coordenadas[1], visitado);
    	
    	dispose( );
    }

    /**
     * Cierra la ventana sin crear un nuevo restaurante
     */
    public void cerrarVentana( )
    {
        dispose( );
    }

}
