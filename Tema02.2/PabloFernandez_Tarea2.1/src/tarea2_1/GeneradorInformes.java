package tarea2_1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Genera los tres informes de la aplicación (boletines, estadísticas y filas descartadas)
 * Cada método público devuelve true si el informe se ha generado correctamente.
 */
public class GeneradorInformes {

	private static final String CARPETA_BOLETINES = "boletines";
	private static final String FICHERO_ESTADISTICAS = "estadisticas.txt";
	private static final String FICHERO_DESCARTADAS = "descartadas.txt";

	private static final BigDecimal NOTA_APROBADO = new BigDecimal("5");
	private static final int NUM_TRIMESTRES = 3;
	private static final int LONGITUD_SEPARADOR = 42;
	// Las medias se calculan con 10 decimales (casi sin redondear) y se redondean a 2 solo al escribir
	private static final int DECIMALES_CALCULO = 10;

	// ===================== BOLETINES =====================

	// Vacía la carpeta boletines
	public boolean generarBoletines(List<Nota> notas) {
		boolean retorno = false;
		prepararCarpetaBoletines();
		Map<Integer, List<Nota>> porAlumno = agruparPorAlumno(notas);
		try {
			for (List<Nota> notasAlumno : porAlumno.values()) {
				escribirBoletin(notasAlumno);
			}
			retorno = true;
		} catch (IOException e) {
			System.out.println("Error al generar los boletines: " + e.getMessage());
		}
		return retorno;
	}

	// Crea la carpeta si no existe y borra los ficheros que tuviera de ejecuciones anteriores
	private void prepararCarpetaBoletines() {
		File carpeta = new File(CARPETA_BOLETINES);
		if (!carpeta.exists()) {
			carpeta.mkdir();
		}
		File[] ficheros = carpeta.listFiles();
		if (ficheros != null) {
			for (File fichero : ficheros) {
				if (fichero.isFile()) {
					fichero.delete();
				}
			}
		}
	}

	// Agrupa las notas por id de alumno. Con TreeMap se mantienen ordenadas por id
	private Map<Integer, List<Nota>> agruparPorAlumno(List<Nota> notas) {
		Map<Integer, List<Nota>> porAlumno = new TreeMap<>();
		for (Nota nota : notas) {
			if (!porAlumno.containsKey(nota.getId())) {
				porAlumno.put(nota.getId(), new ArrayList<>());
			}
			porAlumno.get(nota.getId()).add(nota);
		}
		return porAlumno;
	}

	// Escribe el boletín de un alumno
	private void escribirBoletin(List<Nota> notasAlumno) throws IOException {
		Nota primera = notasAlumno.get(0);
		File fichero = new File(CARPETA_BOLETINES, primera.getId() + ".txt");
		
		Map<String, BigDecimal[]> tabla = agruparPorAsignatura(notasAlumno);
				
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(fichero, StandardCharsets.UTF_8))) {
			escribirCabeceraBoletin(escritor, primera);
			List<BigDecimal> medias = escribirAsignaturas(escritor, tabla);
			escribirPieBoletin(escritor, medias);

			// Aviso por consola si la media global es estrictamente menor que 5
			BigDecimal mediaGlobal = media(medias);
			if (mediaGlobal.compareTo(NOTA_APROBADO) < 0) {
				System.out.println("Alumno suspenso: " + primera.getApellidos() + ", " + primera.getNombre()
						+ " (ID " + primera.getId() + ") - Media: " + formatear(mediaGlobal));
			}
		}
	}

	// Escribe las 4 primeras líneas: título, alumno, separador y cabecera de columnas
	private void escribirCabeceraBoletin(BufferedWriter escritor, Nota alumno) throws IOException {
		escribirLinea(escritor, "BOLETIN DE NOTAS");
		escribirLinea(escritor, "Alumno: " + alumno.getApellidos() + ", " + alumno.getNombre()
				+ " (ID " + alumno.getId() + ")");
		escribirLinea(escritor, "-".repeat(LONGITUD_SEPARADOR));
		escribirLinea(escritor, "Asignatura;T1;T2;T3;Media");
	}

	// Escribe una línea por asignatura y devuelve las medias para la media global
	private List<BigDecimal> escribirAsignaturas(BufferedWriter escritor, Map<String, BigDecimal[]> tabla)
			throws IOException {
		List<BigDecimal> medias = new ArrayList<>();
		for (Map.Entry<String, BigDecimal[]> asignatura : tabla.entrySet()) {
			BigDecimal media = media(valoresConNota(asignatura.getValue()));
			medias.add(media);
			escribirLinea(escritor, crearLineaAsignatura(asignatura.getKey(), asignatura.getValue(), media));
		}
		return medias;
	}

	// Escribe el separador final y la media global
	private void escribirPieBoletin(BufferedWriter escritor, List<BigDecimal> medias) throws IOException {
		escribirLinea(escritor, "-".repeat(LONGITUD_SEPARADOR));
		escribirLinea(escritor, "Media global: " + formatear(media(medias)));
	}

	/**
	 * Agrupa las notas de un alumno por asignatura
	 * Cada asignatura tiene un array de 3 posiciones, una por trimestre; null si no hay nota
	 * 
	 */
	private Map<String, BigDecimal[]> agruparPorAsignatura(List<Nota> notasAlumno) {
		Map<String, BigDecimal[]> tabla = new TreeMap<>();
		for (Nota nota : notasAlumno) {
			if (!tabla.containsKey(nota.getAsignatura())) {
				tabla.put(nota.getAsignatura(), new BigDecimal[NUM_TRIMESTRES]);
			}
			tabla.get(nota.getAsignatura())[nota.getTrimestre() - 1] = nota.getNota();
		}
		return tabla;
	}

	
	private String crearLineaAsignatura(String asignatura, BigDecimal[] trimestres, BigDecimal media) {
		StringBuilder linea = new StringBuilder(asignatura);
		for (BigDecimal nota : trimestres) {
			linea.append(';').append(nota == null ? "-" : formatear(nota));
		}
		linea.append(';').append(formatear(media));
		return linea.toString();
	}

	// Devuelve solo los trimestres que tienen nota
	private List<BigDecimal> valoresConNota(BigDecimal[] trimestres) {
		List<BigDecimal> valores = new ArrayList<>();
		for (BigDecimal nota : trimestres) {
			if (nota != null) {
				valores.add(nota);
			}
		}
		return valores;
	}

	// ===================== ESTADISTICAS =====================

	// Crea estadisticas.txt con una línea por asignatura (orden alfabético)
	public boolean generarEstadisticas(List<Nota> notas) {
		boolean retorno = false;
		Map<String, List<BigDecimal>> porAsignatura = agruparNotasPorAsignatura(notas);
		try (BufferedWriter escritor = new BufferedWriter(
				new FileWriter(FICHERO_ESTADISTICAS, StandardCharsets.UTF_8))) {
			escribirLinea(escritor, "Asignatura;Notas;Media;Aprobadas;Suspensas;Maxima;Minima;PorcentajeAprobados");
			for (Map.Entry<String, List<BigDecimal>> asignatura : porAsignatura.entrySet()) {
				escribirLinea(escritor, crearLineaEstadistica(asignatura.getKey(), asignatura.getValue()));
			}
			retorno = true;
		} catch (IOException e) {
			System.out.println("Error al generar las estadísticas: " + e.getMessage());
		}
		return retorno;
	}

	// Agrupa todas las notas válidas por asignatura
	private Map<String, List<BigDecimal>> agruparNotasPorAsignatura(List<Nota> notas) {
		Map<String, List<BigDecimal>> porAsignatura = new TreeMap<>();
		for (Nota nota : notas) {
			if (!porAsignatura.containsKey(nota.getAsignatura())) {
				porAsignatura.put(nota.getAsignatura(), new ArrayList<>());
			}
			porAsignatura.get(nota.getAsignatura()).add(nota.getNota());
		}
		return porAsignatura;
	}

	private String crearLineaEstadistica(String asignatura, List<BigDecimal> valores) {
		int aprobadas = contarAprobadas(valores);
		int suspensas = valores.size() - aprobadas;
		return asignatura + ";" + valores.size()
				+ ";" + formatear(media(valores))
				+ ";" + aprobadas
				+ ";" + suspensas
				+ ";" + formatear(Collections.max(valores))
				+ ";" + formatear(Collections.min(valores));
				//+ ";" +formatear;
	}

	// Una nota está aprobada si es mayor o igual que 5
	private int contarAprobadas(List<BigDecimal> valores) {
		int aprobadas = 0;
		for (BigDecimal valor : valores) {
			if (valor.compareTo(NOTA_APROBADO) >= 0) {
				aprobadas++;
			}
		}
		return aprobadas;
	}

	// ===================== DESCARTADAS =====================

	// Crea descartadas.txt con una línea por fila descartada
	public boolean generarDescartadas(List<FilaDescartada> descartadas) {
		boolean retorno = false;
		try (BufferedWriter escritor = new BufferedWriter(
				new FileWriter(FICHERO_DESCARTADAS, StandardCharsets.UTF_8))) {
			for (FilaDescartada fila : descartadas) {
				escribirLinea(escritor, fila.toString());
			}
			retorno = true;
		} catch (IOException e) {
			System.out.println("Error al generar el informe de filas descartadas: " + e.getMessage());
		}
		return retorno;
	}

	// ===================== UTILIDADES COMUNES =====================

	// Media de una lista no vacía
	private BigDecimal media(List<BigDecimal> valores) {
		BigDecimal suma = BigDecimal.ZERO;
		for (BigDecimal valor : valores) {
			suma = suma.add(valor);
		}
		return suma.divide(BigDecimal.valueOf(valores.size()), DECIMALES_CALCULO, RoundingMode.HALF_UP);
	}

	// Dos decimales, redondeo hacia arriba desde el .5 y coma decimal
	private String formatear(BigDecimal numero) {
		return numero.setScale(2, RoundingMode.HALF_UP).toString().replace('.', ',');
	}

	// Escribe una línea y su salto de línea
	private void escribirLinea(BufferedWriter escritor, String texto) throws IOException {
		escritor.write(texto);
		escritor.newLine();
	}
	/*
	private BigDecimal porcentaje(List<Nota> notas) {
		
	}*/
}