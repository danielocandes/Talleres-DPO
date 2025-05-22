package co.edu.uniandes.graphics.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.math.*;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PanelLienzo extends JPanel{
	public PanelLienzo() {
		setSize(1500, 800);
		setBorder(new TitledBorder("Panel Lienzo"));
	}
	
	public void paint (Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
	
		// Humano
		g2d.setColor(Color.GRAY);
		g2d.fill(new Rectangle2D.Double(190, 20, 400, 550));
		
		g2d.setColor(Color.BLACK);
		g2d.fill(new Ellipse2D.Double(350, 30, 100, 100));
		
		g2d.fill(new Rectangle2D.Double(390, 130, 20, 40));

		g2d.fill(new RoundRectangle2D.Double(320, 170, 25, 180, 20, 20));
		
		g2d.fill(new RoundRectangle2D.Double(455, 170, 25, 180, 20, 20));
		
		g2d.fill(new Rectangle2D.Double(330, 170, 140, 40));
		
		g2d.setColor(Color.GRAY);
		g2d.fill(new RoundRectangle2D.Double(345, 200, 15, 40, 20, 20));
		
		g2d.fill(new RoundRectangle2D.Double(440, 200, 15, 40, 20, 20));
		
		g2d.setColor(Color.BLACK);
		g2d.fill(new Rectangle2D.Double(360, 170, 80, 180));
		
		g2d.fill(new RoundRectangle2D.Double(360, 330, 35, 180, 20, 20));

		g2d.fill(new RoundRectangle2D.Double(405, 330, 35, 180, 20, 20));
		
		int[] arrX1 = {230, 250, 550, 570};
		int[] arrY1 = {550, 500, 500, 550};
		
		g2d.fill(new Polygon(arrX1, arrY1, 4));
		
		// Figura nueva
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fill(new Ellipse2D.Double(650, 30, 300, 300));
	
		int[] arrX2 = {655, 800, 945, 887, 713};
		int[] arrY2 = {140, 30, 140, 300, 300};
		g2d.setColor(Color.GRAY);
		g2d.fill(new Polygon(arrX2, arrY2, 5));
		
		g2d.setColor(Color.DARK_GRAY);
		g2d.fill(new Rectangle2D.Double(704, 104, 192, 170));
		
		g2d.setColor(Color.BLACK);
		g2d.fill(new RoundRectangle2D.Double(704, 120, 192, 20, 20, 20));
		g2d.fill(new RoundRectangle2D.Double(704, 160, 192, 20, 20, 20));
		g2d.fill(new RoundRectangle2D.Double(704, 200, 192, 20, 20, 20));
		g2d.fill(new RoundRectangle2D.Double(704, 240, 192, 20, 20, 20));

		g2d.fill(new RoundRectangle2D.Double(724, 104, 20, 170, 20, 20));
		g2d.fill(new RoundRectangle2D.Double(764, 104, 20, 170, 20, 20));
		g2d.fill(new RoundRectangle2D.Double(804, 104, 20, 170, 20, 20));
		g2d.fill(new RoundRectangle2D.Double(844, 104, 20, 170, 20, 20));
		
		//Figura rellena con lineas
		for (int i = 0; i < 300; i++) {
			g2d.drawLine(400, 550, 250+i, 750);
		}
		
		//Figura estrella
		g2d.setColor(Color.YELLOW);
		for (int i = 0; i < 20; i++) {
			// origin : 900, 550
			g2d.drawLine(900-i*5, 550, 900, 450+i*5);
			g2d.drawLine(900+i*5, 550, 900, 450+i*5);
			g2d.drawLine(900-i*5, 550, 900, 650-i*5);
			g2d.drawLine(900+i*5, 550, 900, 650-i*5);
			
			g2d.drawLine(800, 550, 1000, 550);
		}
		
		// Figura adicional que hice para experimentar y sufrir un poquito. 
		g2d.setColor(Color.BLACK);
		
		// Establezco vectores normalizados que formen el cuadrado inicial y los guardo en una matriz
		double[] v1 = {1, 1};
		double[] v2 = {-1, 1};
		double[] v3 = {-1, -1};
		double[] v4 = {1, -1};
		double[][] normalizedVectors = {v1, v2, v3, v4};
		
		// Establezco el origen del cuadrado, el tamaño inicial que tendrá y la matriz de rotación inicial - la matriz identidad
		int[] origin = {1200, 400};
		double size = 200;
		double[][] rotationMatrix = {{1, 0}, {0, 1}};
		
		// Se harán 20 iteraciones, pero se pueden hacer cuantos cuadrados se quieran hacer
		for (int i = 0; i < 20; i++) {
			
			// Establezco los vectores que pintarán el cuadrado real. Este no se ha calculado aún. Los guardo en otra matriz.
			double[] u1 = {0, 0};
			double[] u2 = {0, 0};
			double[] u3 = {0, 0};
			double[] u4 = {0, 0};
			double[][] realVectors = {u1, u2, u3, u4};
			
			// Modifico la matriz de rotación para que cambie a un ángulo proporcional a i
			rotationMatrix[0][0] = Math.cos(0+i*(Math.PI/12));
			rotationMatrix[0][1] = -Math.sin(0+i*(Math.PI/12));
			rotationMatrix[1][0] = Math.sin(0+i*(Math.PI/12));
			rotationMatrix[1][1] = Math.cos(0+i*(Math.PI/12));
			
			// Calculo cada punto del poligono como vector, usando la matriz de rotación como transformación lineal
			// y luego multiplico esto por el tamaño del cuadrado en esta iteración.
			int j = 0;
			for (double[] vector : normalizedVectors) {
				realVectors[j][0] = origin[0] + size*( rotationMatrix[0][0] * vector[0] + rotationMatrix[0][1] * vector[1]);
				realVectors[j][1] = origin[1] + size*( rotationMatrix[1][0] * vector[0] + rotationMatrix[1][1] * vector[1]);
				j++;
			}
			
			// Ya guardo los vectores en un formato que Polygon pueda leer, también pasandolos a int de una vez.
			int[] vx = {(int) u1[0],(int) u2[0],(int) u3[0],(int) u4[0]};
			int[] vy = {(int) u1[1],(int) u2[1],(int) u3[1],(int) u4[1]};
			
			g2d.draw(new Polygon(vx, vy, 4)); // Dibujo el poligono cuadrado con los vectores calculados anteriormente
			
			size = size/1.23; // Reduzco el tamaño por un valor que saqué por prueba y error para el próximo cuadrado.
		}
	}
	
	
}
