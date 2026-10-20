package ejercicio17;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class _Principal {

    public static void main(String[] args) {

        String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\temperaturas.dat";
        
        String meses [] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        
        ObjectInputStream flujoEntrada = null;
        
        try {
			flujoEntrada = new ObjectInputStream (new FileInputStream(ruta));
			double[] temperaturas = (double[]) flujoEntrada.readObject();
			for(int i = 0; i<temperaturas.length;i++) {
				System.out.println(meses[i]+": "+temperaturas[i]+"ºC");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
}

