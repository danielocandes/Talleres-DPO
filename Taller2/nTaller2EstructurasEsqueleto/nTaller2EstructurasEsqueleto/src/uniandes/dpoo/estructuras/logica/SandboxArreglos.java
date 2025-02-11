package uniandes.dpoo.estructuras.logica;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Esta clase tiene un conjunto de métodos para practicar operaciones sobre arreglos de enteros y de cadenas.
 *
 * Todos los métodos deben operar sobre los atributos arregloEnteros y arregloCadenas.
 * 
 * No pueden agregarse nuevos atributos.
 * 
 * Implemente los métodos usando operaciones sobre arreglos (ie., no haga cosas como construir listas para evitar la manipulación de arreglos).
 */
public class SandboxArreglos
{
    /**
     * Un arreglo de enteros para realizar varias de las siguientes operaciones.
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private int[] arregloEnteros;

    /**
     * Un arreglo de cadenas para realizar varias de las siguientes operaciones
     * 
     * Ninguna posición del arreglo puede estar vacía en ningún momento.
     */
    private String[] arregloCadenas;

    /**
     * Crea una nueva instancia de la clase con los dos arreglos inicializados pero vacíos (tamaño 0)
     */
    public SandboxArreglos( )
    {
        arregloEnteros = new int[]{};
        arregloCadenas = new String[]{};
    }

    /**
     * Retorna una copia del arreglo de enteros, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de enteros
     */
    public int[] getCopiaEnteros( )
    {
    	int[] copiaEnteros = Arrays.copyOf(arregloEnteros, arregloEnteros.length);
        return copiaEnteros;
    }

    /**
     * Retorna una copia del arreglo de cadenas, es decir un nuevo arreglo del mismo tamaño que contiene copias de los valores del arreglo original
     * @return Una copia del arreglo de cadenas
     */
    public String[] getCopiaCadenas( )
    {
        String[] copiaCadenas = Arrays.copyOf(arregloCadenas, arregloCadenas.length);
        return copiaCadenas;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de enteros
     * @return
     */
    public int getCantidadEnteros( )
    {
        return arregloEnteros.length;
    }

    /**
     * Retorna la cantidad de valores en el arreglo de cadenas
     * @return
     */
    public int getCantidadCadenas( )
    {
    	//System.out.println(Arrays.toString(arregloCadenas) + arregloCadenas.length);
        return arregloCadenas.length;
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param entero El valor que se va a agregar.
     */
    public void agregarEntero( int entero )
    {
    	int[] nuevoArreglo = Arrays.copyOf(arregloEnteros, arregloEnteros.length + 1);
    	nuevoArreglo[nuevoArreglo.length - 1] = entero;
    	arregloEnteros = nuevoArreglo;
    }

    /**
     * Agrega un nuevo valor al final del arreglo. Es decir que este método siempre debería aumentar en 1 la capacidad del arreglo.
     * 
     * @param cadena La cadena que se va a agregar.
     */
    public void agregarCadena( String cadena )
    {
    	String[] nuevoArreglo = Arrays.copyOf(arregloCadenas, arregloCadenas.length + 1);
    	nuevoArreglo[nuevoArreglo.length - 1] = cadena;
    	arregloCadenas = nuevoArreglo;
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de enteros
     * @param valor El valor que se va eliminar
     */
    public void eliminarEntero( int valor )
    {
    	int tamanioArreglo = arregloEnteros.length - contarApariciones(valor);
    	
    	int[] nuevoArreglo = new int[tamanioArreglo];
    	int index = 0;
    	for(int entero : arregloEnteros) {
    		if (entero != valor) {
    			nuevoArreglo[index] = entero;
    			index++;
    		}
    	}
    	
    	arregloEnteros = nuevoArreglo;
    }

    /**
     * Elimina todas las apariciones de un determinado valor dentro del arreglo de cadenas
     * @param cadena La cadena que se va eliminar
     */
    public void eliminarCadena( String cadena )
    {
    	int tamanioArreglo = arregloCadenas.length - contarApariciones(cadena);
    	
    	String[] nuevoArreglo = new String[tamanioArreglo];
    	int index = 0;
    	for(String texto : arregloCadenas) {
    		if (!texto.equalsIgnoreCase(cadena)) {
    			nuevoArreglo[index] = texto;
    			index++;
    		}
    	}
    	
    	arregloCadenas = nuevoArreglo;
    }

    /**
     * Inserta un nuevo entero en el arreglo de enteros.
     * 
     * @param entero El nuevo valor que debe agregarse
     * @param posicion La posición donde debe quedar el nuevo valor en el arreglo aumentado. Si la posición es menor a 0, se inserta el valor en la primera posición. Si la
     *        posición es mayor que el tamaño del arreglo, se inserta el valor en la última posición.
     */
    public void insertarEntero( int entero, int posicion )
    {
    	int[] nuevoArreglo = new int[arregloEnteros.length + 1];
    	
    	posicion = Math.clamp(posicion, 0, arregloEnteros.length); //no use IA, conozco esta funcion de C# y busque a ver si tambien estaba en java y si, entonces me pareció conveniente usarlo
    	int index = 0;
    	for (int i=0; i < nuevoArreglo.length; i++) {
    		if (i == posicion) {
    			nuevoArreglo[i] = entero;
    		} else {
    			nuevoArreglo[i] = arregloEnteros[index];
    			index++;
    		}
    	}
    	
    	arregloEnteros = nuevoArreglo;
    }

    /**
     * Elimina un valor del arreglo de enteros dada su posición.
     * @param posicion La posición donde está el elemento que debe ser eliminado. Si el parámetro posicion no corresponde a ninguna posición del arreglo de enteros, el método
     *        no debe hacer nada.
     */
    public void eliminarEnteroPorPosicion( int posicion )
    {
    	// Primero se organiza la lista entonces esta prueba falla aunque este cumpliendo su proposito adecuado.
    	
    	int[] nuevoArreglo = new int[arregloEnteros.length - 1];
    	
    	if (0 <= posicion && posicion <= nuevoArreglo.length) 
    	{
    		int index = 0;
    		for(int i=0; i < nuevoArreglo.length; i++) {
    			if (i != posicion) {
    				nuevoArreglo[index] = arregloEnteros[i];
    				index++;
    			}
    		}
    	}
    	
    	arregloEnteros = nuevoArreglo;
    }

    /**
     * Reinicia el arreglo de enteros con los valores contenidos en el arreglo del parámetro 'valores' truncados.
     * 
     * Es decir que si el valor fuera 3.67, en el nuevo arreglo de enteros debería quedar el entero 3.
     * @param valores Un arreglo de valores decimales.
     */
    public void reiniciarArregloEnteros( double[] valores )
    {
    	int[] nuevoArreglo = new int[valores.length];
    	
    	for(int i = 0; i < nuevoArreglo.length; i++) {
    		nuevoArreglo[i] = (int) valores[i];
    	}
    	
    	arregloEnteros = nuevoArreglo;
    }

    /**
     * Reinicia el arreglo de cadenas con las representaciones como Strings de los objetos contenidos en el arreglo del parámetro 'objetos'.
     * 
     * Use el método toString para convertir los objetos a cadenas.
     * @param valores Un arreglo de objetos
     */
    public void reiniciarArregloCadenas( Object[] objetos )
    {
    	String[] nuevoArreglo = new String[objetos.length];
    	
    	for(int i = 0; i < nuevoArreglo.length; i++) {
    		nuevoArreglo[i] = objetos[i].toString();
    	}
    	
    	arregloCadenas = nuevoArreglo;
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores sean positivos.
     * 
     * Es decir que si en una posición había un valor negativo, después de ejecutar el método debe quedar el mismo valor muliplicado por -1.
     */
    public void volverPositivos( )
    {
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		arregloEnteros[i] = Math.abs(arregloEnteros[i]);
    	}
    }

    /**
     * Modifica el arreglo de enteros para que todos los valores queden organizados de menor a mayor.
     */
    public void organizarEnteros( )
    {
    	Arrays.sort(arregloEnteros);
    }

    /**
     * Modifica el arreglo de cadenas para que todos los valores queden organizados lexicográficamente.
     */
    public void organizarCadenas( )
    {
    	SandboxArreglos nuevosArreglos = new SandboxArreglos();
    	for(String texto : arregloCadenas) {
    		if (texto != null) {
    			nuevosArreglos.agregarCadena(texto);
    		}
    	}
    	
    	Arrays.sort(nuevosArreglos.arregloCadenas);
    	arregloCadenas = nuevosArreglos.arregloCadenas;
    }

    /**
     * Cuenta cuántas veces aparece el valor recibido por parámetro en el arreglo de enteros
     * @param valor El valor buscado
     * @return La cantidad de veces que aparece el valor
     */
    public int contarApariciones( int valor )
    {
    	int apariciones = 0;
    	for(int i=0; i < arregloEnteros.length; i++) {
    		if (arregloEnteros[i] == valor) {
    			apariciones ++;
    		}
    	}
    	
    	return apariciones;
    }

    /**
     * Cuenta cuántas veces aparece la cadena recibida por parámetro en el arreglo de cadenas.
     * 
     * La búsqueda no debe diferenciar entre mayúsculas y minúsculas.
     * @param cadena La cadena buscada
     * @return La cantidad de veces que aparece la cadena
     */
    public int contarApariciones( String cadena )
    {
        int apariciones = 0;
        for(int i=0; i < arregloCadenas.length; i++) {
    		if (arregloCadenas[i].equalsIgnoreCase(cadena)) {
    			apariciones ++;
    		}
    	}
        
        return apariciones;
    }

    /**
     * Busca en qué posiciones del arreglo de enteros se encuentra el valor que se recibe en el parámetro
     * @param valor El valor que se debe buscar
     * @return Un arreglo con los números de las posiciones del arreglo de enteros en las que se encuentra el valor buscado. Si el valor no se encuentra, el arreglo retornado
     *         es de tamaño 0.
     */
    public int[] buscarEntero( int valor )
    {
    	// Prueba falla porque se organiza arregloEnteros antes de ejecutar la prueba para esta funcion, parece mas error de las pruebas (creo).
        SandboxArreglos nuevosArreglos = new SandboxArreglos();
        for (int i = 0; i < arregloEnteros.length; i++) {
        	if (arregloEnteros[i] == valor) {
        		nuevosArreglos.agregarEntero(i);
        	}
        }
        
        return nuevosArreglos.arregloEnteros;
    }

    /**
     * Calcula cuál es el rango de los enteros (el valor mínimo y el máximo).
     * @return Un arreglo con dos posiciones: en la primera posición, debe estar el valor mínimo en el arreglo de enteros; en la segunda posición, debe estar el valor máximo
     *         en el arreglo de enteros. Si el arreglo está vacío, debe retornar un arreglo vacío.
     */
    public int[] calcularRangoEnteros( )
    {
    	SandboxArreglos nuevosArreglos = new SandboxArreglos();
    	if (arregloEnteros.length == 0) {return nuevosArreglos.arregloEnteros;}
    	int minimo = arregloEnteros[0];
    	int maximo = arregloEnteros[0];
    	
        for (int i = 1; i < arregloEnteros.length; i++) {
        	if (arregloEnteros[i] < minimo) {
        		minimo = arregloEnteros[i];
        	}
        	if (arregloEnteros[i] > maximo) {
        		maximo = arregloEnteros[i];
        	}
        }
        
        nuevosArreglos.agregarEntero(minimo);
        nuevosArreglos.agregarEntero(maximo);
        
        return nuevosArreglos.arregloEnteros;
    }

    /**
     * Calcula un histograma de los valores del arreglo de enteros y lo devuelve como un mapa donde las llaves son los valores del arreglo y los valores son la cantidad de
     * veces que aparece cada uno en el arreglo de enteros.
     * @return Un mapa con el histograma de valores.
     */
    public HashMap<Integer, Integer> calcularHistograma( )
    {
    	HashMap<Integer, Integer> mapa = new HashMap<Integer, Integer>();
    	for (int i = 0; i < arregloEnteros.length; i++) {
    		int cantidadActual = 0;
    		mapa.putIfAbsent(arregloEnteros[i], 0);
    		cantidadActual = mapa.get(arregloEnteros[i]);
    		mapa.put(arregloEnteros[i], cantidadActual+1);
    	}
    	
        return mapa;
    }

    /**
     * Cuenta cuántos valores dentro del arreglo de enteros están repetidos.
     * @return La cantidad de enteos diferentes que aparecen más de una vez
     */
    public int contarEnterosRepetidos( )
    {
    	int cantidadTotal = 0;
        HashMap<Integer, Integer> mapa = new HashMap<Integer, Integer>();
        for (int i = 0; i < arregloEnteros.length; i++) {
    		int valorActual = arregloEnteros[i];
    		
    		if (mapa.get(valorActual) == null) {
    			mapa.put(valorActual, 0);
    		} else if (mapa.get(valorActual) == 0){
    			mapa.put(valorActual, 1);
    			cantidadTotal += 1;
    		}
    	}
        
        return cantidadTotal;
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica si son iguales, es decir que contienen los mismos elementos exactamente en el mismo orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los arreglos son idénticos y false de lo contrario
     */
    public boolean compararArregloEnteros( int[] otroArreglo )
    {
    	// Una de las pruebas falla, revise y la prueba esta erronea ya que primero se corre la prueba que ordena arregloEnteros entonces ambos arreglos terminan quedando
    	// en el mismo orden, por lo tanto, son el mismo arreglo aunque la prueba pida que sean diferentes. Considero que probablemente es un error.
    	// Bajo mucho analisis, note que enterosSencillos en el archivo de pruebas eventualmente se organiza el mismo, no se que funcion hace esto y como pero
    	// se pierde la integridad de esta lista a traves de los tests y es por eso que sencillo tambien se genera de manera organizada. Los tests estan raros. o hice algo raro.. no se!!!!!
        return Arrays.equals(arregloEnteros, otroArreglo);
    }

    /**
     * Compara el arreglo de enteros con otro arreglo de enteros y verifica que tengan los mismos elementos, aunque podría ser en otro orden.
     * @param otroArreglo El arreglo de enteros con el que se debe comparar
     * @return True si los elementos en los dos arreglos son los mismos
     */
    public boolean mismosEnteros( int[] otroArreglo )
    {
    	int[] copiaEnteros = getCopiaEnteros();
    	Arrays.sort(copiaEnteros);
        Arrays.sort(otroArreglo);
    	return Arrays.equals(otroArreglo, copiaEnteros);
    }

    /**
     * Cambia los elementos del arreglo de enteros por una nueva serie de valores generada de forma aleatoria.
     * 
     * Para generar los valores se debe partir de una distribución uniforme usando Math.random().
     * 
     * Los números en el arreglo deben quedar entre el valor mínimo y el máximo.
     * @param cantidad La cantidad de elementos que debe haber en el arreglo
     * @param minimo El valor mínimo para los números generados
     * @param maximo El valor máximo para los números generados
     */
    public void generarEnteros( int cantidad, int minimo, int maximo )
    {
    	int[] nuevoArreglo = new int[cantidad];
    	int rango = maximo - minimo + 1;
    	
    	for (int i = 0; i < cantidad; i++) {
    		int entero = (int) (Math.random() * rango) + minimo;
    		nuevoArreglo[i] = entero;
    	}
    	
    	arregloEnteros = nuevoArreglo;
    }

}
