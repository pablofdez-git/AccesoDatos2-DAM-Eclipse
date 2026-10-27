package ejercicio2_25;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class _Principal {
	
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
	
	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\cuentas.dat";
		
		final int LONGITUD_NOMBRE = 25;
		final int TAMANO_REGISTRO = 4 + (LONGITUD_NOMBRE * 2) + 2 + 8 + 2;
		
		List<Cuenta> cuentas = new ArrayList<>();
		cuentas.add(new Cuenta(1001, "Ana López",       2500.75, 'C'));
		cuentas.add(new Cuenta(1002, "Carlos Martín",    850.00, 'A'));
		cuentas.add(new Cuenta(1003, "Lucía Fernández", 12300.50, 'A'));
		cuentas.add(new Cuenta(1004, "Javier Gómez",     -120.30, 'C'));
		cuentas.add(new Cuenta(1005, "Marta Sánchez",    4780.00, 'C'));
		cuentas.add(new Cuenta(1006, "Pedro Ruiz",        310.90, 'A'));
		cuentas.add(new Cuenta(1007, "Elena Díaz",       9650.25, 'A'));
		cuentas.add(new Cuenta(1008, "Sergio Moreno",    1999.99, 'C'));
		
		try(RandomAccessFile raf = new RandomAccessFile(ruta, "rw")){
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
