package tarea2_1;

import java.util.Scanner;

/**
 * Menú de consola. Solo muestra opciones, lee lo que escribe el usuario
 */
public class Menu {

	private static final int OPCION_CARGAR = 1;
	private static final int OPCION_BOLETINES = 2;
	private static final int OPCION_ESTADISTICAS = 3;
	private static final int OPCION_DESCARTADAS = 4;
	private static final int OPCION_SALIR = 5;
	private static final int OPCION_INVALIDA = -1;
	
	private final Scanner teclado = new Scanner(System.in);
	private final ImportadorNotas importador = new ImportadorNotas();
	private final GeneradorInformes generador = new GeneradorInformes();

	//Repite el menú hasta que el usuario elige salir
	public void ejecutar() {
		int opcion;
		do {
			mostrarMenu();
			opcion = leerOpcion();
			ejecutarOpcion(opcion);
		} while (opcion != OPCION_SALIR);
	}

	private void mostrarMenu() {
		System.out.println();
		System.out.println("===== GESTION DE NOTAS =====");
		System.out.println("1. Cargar y validar el fichero");
		System.out.println("2. Generar boletines");
		System.out.println("3. Generar estadisticas por asignatura");
		System.out.println("4. Generar informe de filas descartadas");
		System.out.println("5. Salir");
		System.out.print("Elige una opcion: ");
	}

	/**
	 * Lee la opción escrita. Devuelve OPCION_INVALIDA si no es un número,
	 * y OPCION_SALIR si ya no hay más entrada (para no quedarse en bucle infinito).
	 */
	private int leerOpcion() {
		int opcion = OPCION_INVALIDA;
		if (teclado.hasNextLine()) {
			try {
				opcion = Integer.parseInt(teclado.nextLine().trim());
			} catch (NumberFormatException e) {
				opcion = OPCION_INVALIDA;
			}
		} else {
			opcion = OPCION_SALIR;
		}
		return opcion;
	}

	// Decide qué hacer según la opción elegida
	private void ejecutarOpcion(int opcion) {
		switch (opcion) {
			case OPCION_CARGAR:
				cargarFichero();
				break;
			case OPCION_BOLETINES:
			case OPCION_ESTADISTICAS:
			case OPCION_DESCARTADAS:
				generarInforme(opcion);
				break;
			case OPCION_SALIR:
				System.out.println("Hasta pronto.");
				break;
			default:
				System.out.println("Opcion no valida. Elige un numero del 1 al 5.");
		}
	}

	// Opción 1: carga el fichero y muestra cuántas filas son válidas y cuántas descartadas
	private void cargarFichero() {
		if (importador.cargar()) {
			System.out.println("Filas validas: " + importador.getValidas().size());
			System.out.println("Filas descartadas: " + importador.getDescartadas().size());
		}
	}

	// Opciones 2 a 4: solo se pueden usar si antes se ha cargado el fichero
	private void generarInforme(int opcion) {
		if (!importador.isCargado()) {
			System.out.println("Primero debes cargar el fichero (opcion 1).");
		} else {
			switch (opcion) {
				case OPCION_BOLETINES:
					mostrarResultado(generador.generarBoletines(importador.getValidas()),
							"Boletines generados en la carpeta boletines/.");
					break;
				case OPCION_ESTADISTICAS:
					mostrarResultado(generador.generarEstadisticas(importador.getValidas()),
							"Fichero estadisticas.txt generado.");
					break;
				default:
					mostrarResultado(generador.generarDescartadas(importador.getDescartadas()),
							"Fichero descartadas.txt generado.");
			}
		}
	}

	// Si el informe se generó bien, lo confirma y si hay error ya lo muestra el generador
	private void mostrarResultado(boolean generado, String mensajeOk) {
		if (generado) {
			System.out.println(mensajeOk);
		}
	}
}