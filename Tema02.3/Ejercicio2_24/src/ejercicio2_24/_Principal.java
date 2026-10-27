package ejercicio2_24;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

	/*	
		Long: 8 bytes
		Integer: 4 bytes
		Short: 2 bytes
		Byte: 1 byte
		Double: 8 bytes
		Float: 4 bytes
		Boolean: 1 byte
		Char: 2 bytes
		String: 2 bytes por cada caracter
	*/

public class _Principal {

	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\empleados.dat";
		final int LONGITUD_NOMBRE = 10;
		final int TAMANO_REGISTRO = 4 + (LONGITUD_NOMBRE *2) + 4 + 8 + 2 + 1;
		
		Empleado[] empleados = {
				new Empleado(1, "Ana López",       1850.50f, 120.0, 'G', true),
				new Empleado(2, "Carlos Martín",   1600.00f,  80.5, 'A', false),
				new Empleado(3, "Lucía Fernández", 2100.75f, 200.0, 'R', true),
				new Empleado(4, "Javier Gómez",    1450.00f,  50.0, 'A', false),
				new Empleado(5, "Marta Sánchez",   1980.25f, 150.0, 'G', true),
				new Empleado(6, "Pedro Ruiz",      1700.00f,  95.0, 'R', false),
				new Empleado(7, "Elena Díaz",      2300.00f, 250.0, 'G', true),
				new Empleado(8, "Sergio Moreno",   1550.40f,  60.0, 'A', false)
		};
		
		try(RandomAccessFile raf = new RandomAccessFile(ruta, "rw")){
			raf.setLength(0);
			
			for(Empleado e : empleados) {
				raf.writeInt(e.getCodigo());
				raf.writeChars(String.format("%-" + LONGITUD_NOMBRE + "." + LONGITUD_NOMBRE + "s", e.getNombre()));
				raf.writeFloat(e.getSalario());
				raf.writeDouble(e.getComision());
				raf.writeChar(e.getDepartamento());
				raf.writeBoolean(e.isTitulacion());
				
			}
			System.out.println("Fichero creado con " + empleados.length + " empleados");
			
			int total = (int) (raf.length() / TAMANO_REGISTRO);							//Cuantos empleados hay
			int posicion = new Random().nextInt(total);									//Posicion random
			raf.seek((long) posicion * TAMANO_REGISTRO);								//ir al registro del ganador
			
			int codigo = raf.readInt();	
			
			StringBuilder nombreBuilder = new StringBuilder();		
            for (int i = 0; i < LONGITUD_NOMBRE; i++) {
                nombreBuilder.append(raf.readChar());
            }
            String nombre = nombreBuilder.toString().trim();
			
            float salario = raf.readFloat();
			double comision = raf.readDouble();
			char departamento = raf.readChar();
			boolean titulacion = raf.readBoolean();
			
			Empleado ganador = new Empleado(codigo, nombre, salario, comision, departamento, titulacion);

			System.out.println("GANADOR DEL SORTEO (registro " + (posicion + 1) + " de " + total+")");
			System.out.println(ganador);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
