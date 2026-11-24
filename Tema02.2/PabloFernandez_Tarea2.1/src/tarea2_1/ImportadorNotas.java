package tarea2_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Lee notas_export.csv, valida cada fila y guarda el resultado en dos listas:
 * las notas válidas y las filas descartadas (con su motivo).
 */
public class ImportadorNotas {

	private static final String RUTA_CSV = "notas_modificaciones.csv";
	private static final int NUM_COLUMNAS = 6;

	private final List<Nota> validas = new ArrayList<>();
	private final List<FilaDescartada> descartadas = new ArrayList<>();
	private final Set<String> clavesVistas = new HashSet<>();			//detector duplicados
	private boolean cargado = false;

	/**
	 * Lee y valida el fichero. Devuelve false si no se ha podido leer sin que el programa se detenga.
	 */
	public boolean cargar() {
		boolean retorno = false;
		reiniciar();												//limpiar memoria historica
		// ISO-8859-1 para que las tildes y las eñes se lean bien
		try (BufferedReader lector = new BufferedReader(new FileReader(RUTA_CSV, StandardCharsets.ISO_8859_1))) {
			String linea;
			int numeroLinea = 0;
			while ((linea = lector.readLine()) != null) {
				numeroLinea++;
				// la cabecera (línea 1) no se procesa ni cuenta como descartada
				if (numeroLinea > 1) {
					procesarLinea(linea, numeroLinea);
				}
			}
			cargado = true;
			retorno = true;
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero " + RUTA_CSV + " en la raíz del proyecto.");
			reiniciar();
		} catch (IOException e) {
			System.out.println("Error al leer el fichero: " + e.getMessage());
			reiniciar();
		}
		return retorno;
	}

	// Vacía los resultados de una carga anterior
	private void reiniciar() {
		validas.clear();
		descartadas.clear();
		clavesVistas.clear();
		cargado = false;
	}

	// Valida una línea: si falla alguna regla la descarta, y si no, la guarda como nota válida
	private void procesarLinea(String linea, int numeroLinea) {
		String[] campos = linea.split(";", -1);							//le dices a java que procese hasta el ultimo caracter aunque este vacio
		MotivoDescarte motivo = detectarMotivo(linea, campos);

		if (motivo == null) {
			registrarValida(campos);
		} else {
			descartadas.add(new FilaDescartada(numeroLinea, motivo, linea));
		}
	}

	/**
	 * Aplica las reglas en el orden del enunciado. Al ser una cadena if / else if,
	 * solo se asigna el primer motivo que falle. Devuelve null si la fila es válida.
	 */
	private MotivoDescarte detectarMotivo(String linea, String[] campos) {
		MotivoDescarte motivo = null;

		if (linea.trim().isEmpty()) {
			motivo = MotivoDescarte.LINEA_VACIA;
		} else if (campos.length != NUM_COLUMNAS) {
			motivo = MotivoDescarte.COLUMNAS_INCORRECTAS;
		} else if (parsearId(campos[0]) < 1) {
			motivo = MotivoDescarte.ID_NO_VALIDO;
		} else if (parsearTrimestre(campos[4]) == -1) {
			motivo = MotivoDescarte.TRIMESTRE_NO_VALIDO;
		} else if (campos[5].trim().isEmpty()) {
			motivo = MotivoDescarte.NOTA_VACIA;
		} else if (parsearNota(campos[5]) == null) {
			motivo = MotivoDescarte.NOTA_NO_NUMERICA;
		} else if (!estaEnRango(parsearNota(campos[5]))) {
			motivo = MotivoDescarte.NOTA_FUERA_DE_RANGO;
		} else if (clavesVistas.contains(crearClave(campos))) {
			motivo = MotivoDescarte.FILA_DUPLICADA;
		}
		return motivo;
	}

	// Crea la Nota a partir de campos ya validados y anota su clave para detectar duplicados
	private void registrarValida(String[] campos) {
		validas.add(new Nota(
				parsearId(campos[0]),
				campos[1].trim(),
				campos[2].trim(),
				campos[3].trim(),
				parsearTrimestre(campos[4]),
				parsearNota(campos[5])));
		clavesVistas.add(crearClave(campos));
	}

	// Clave que identifica una nota: id, asignatura y trimestre
	private String crearClave(String[] campos) {
		return parsearId(campos[0]) + "|" + campos[3].trim() + "|" + parsearTrimestre(campos[4]);
	}

	// Devuelve el id si es un entero positivo; si no, -1
	private int parsearId(String texto) {
		int id = -1;
		String t = texto.trim();
		if (t.matches("\\d+")) {
			try {
				id = Integer.parseInt(t); // puede salir 0 (no válido, lo detecta el que llama)
			} catch (NumberFormatException e) {
				id = -1;
			}
		}
		return id;
	}

	// Devuelve 1, 2 o 3; si no es ninguno de ellos, -1
	private int parsearTrimestre(String texto) {
		int trimestre = -1;
		String t = texto.trim();
		if (t.equals("1") || t.equals("2") || t.equals("3")) {
			trimestre = Integer.parseInt(t);
		}
		return trimestre;
	}

	// Convierte "7,5" en BigDecimal. Devuelve null si no es un número
	private BigDecimal parsearNota(String texto) {
		BigDecimal nota = null;
		String t = texto.trim();
		if (t.matches("-?\\d+([.,]\\d+)?")) {
			nota = new BigDecimal(t.replace(',', '.'));
		}
		return nota;
	}

	// La nota debe estar entre 0 y 10, ambos incluidos
	private boolean estaEnRango(BigDecimal nota) {
		return nota.compareTo(BigDecimal.ZERO) >= 0 && nota.compareTo(BigDecimal.TEN) <= 0;
	}

	public List<Nota> getValidas() { return validas; }

	public List<FilaDescartada> getDescartadas() { return descartadas; }

	public boolean isCargado() { return cargado; }
}