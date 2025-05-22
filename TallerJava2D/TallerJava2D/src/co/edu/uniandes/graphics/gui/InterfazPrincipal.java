package co.edu.uniandes.graphics.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class InterfazPrincipal extends JFrame{

	private PanelLienzo pLienzo;
	
	public InterfazPrincipal() {
		pLienzo = new PanelLienzo();
		setSize(1500, 800);
		add(pLienzo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new InterfazPrincipal();
	}
}
