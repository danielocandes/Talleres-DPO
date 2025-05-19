package uniandes.dpoo.swing.interfaz.agregar;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelEditarRestaurante extends JPanel
{
    /**
     * El campo para que el usuario ingrese el nombre del restaurante
     */
    private JTextField txtNombre;

    /**
     * Un selector (JComboBox) para que el usuario seleccione la calificación (1 a 5) del restaurante
     */
    private JComboBox<String> cbbCalificacion;

    /**
     * Un selector (JComboBox) para que el usuario indique si ya visitó el restaurante o no
     */
    private JComboBox<String> cbbVisitado;

    public PanelEditarRestaurante( )
    {
    	setLayout(new BorderLayout());
        // Crea el campo para el nombre con una etiqueta al frente
        // reviewTODO completar
    	JPanel panelNombre = new JPanel();
    	panelNombre.setLayout(new BorderLayout());
    	JLabel lblNombre = new JLabel("Nombre: ");
    	txtNombre = new JTextField();
    	panelNombre.add(lblNombre, BorderLayout.WEST);
    	panelNombre.add(txtNombre, BorderLayout.CENTER);

        // Crea el selector para la calificación con una etiqueta al frente
        // reviewTODO completar
    	JPanel panelCalificacion = new JPanel();
    	panelCalificacion.setLayout(new BorderLayout());
    	JLabel lblCalificacion = new JLabel("Calificacion: ");
    	cbbCalificacion = new JComboBox<String>();
    	panelCalificacion.add(lblCalificacion, BorderLayout.WEST);
    	panelCalificacion.add(cbbCalificacion, BorderLayout.CENTER);
    	for (int i = 1; i <= 5; i++) {
    		cbbCalificacion.addItem(String.valueOf(i));
    	}
    	
    	
        // Crea el selector para indicar si ya ha sido visitado, con una etiqueta al frente
        // reviewTODO completar
    	JPanel panelVisitado = new JPanel();
    	panelVisitado.setLayout(new BorderLayout());
    	JLabel lblVisitado = new JLabel("Visitado: ");
    	cbbVisitado= new JComboBox<String>();
    	panelVisitado.add(lblVisitado, BorderLayout.WEST);
    	panelVisitado.add(cbbVisitado, BorderLayout.CENTER);
    	
    	cbbVisitado.addItem("Si");
    	cbbVisitado.addItem("No");

        // Agregar todos los elementos al panel
        // reviewTODO completar
    	add(panelNombre, BorderLayout.NORTH);
    	add(panelCalificacion, BorderLayout.CENTER);
    	add(panelVisitado, BorderLayout.SOUTH);
    	
    }

    /**
     * Indica si en el selector se seleccionó la opción que dice que el restaurante fue visitado
     * @return
     */
    public boolean getVisitado( )
    {
        // reviewTODO completar
    	if (cbbVisitado.getSelectedItem().equals("Si")) {
    		return true;
    	}	else {
        return false;
    	}
    }

    /**
     * Indica la calificación marcada en el selector
     * @return
     */
    public int getCalificacion( )
    {
        String calif = ( String )cbbCalificacion.getSelectedItem( );
        return Integer.parseInt( calif );
    }

    /**
     * Indica el nombre digitado para el restaurante
     * @return
     */
    public String getNombre( )
    {
        // reviewTODO completar
    	return txtNombre.getText();
    }
}
