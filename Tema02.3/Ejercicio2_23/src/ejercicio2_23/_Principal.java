package ejercicio2_23;

import java.io.*;
import java.util.*;

public class _Principal {

	public static void main(String[] args) {
		//Añadir funciones para simplificar el codigo
		Scanner sc = new Scanner(System.in);
		
		int opcion = 0;
		
		String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\Clientes.dat";
		File fichero = new File(ruta);
		
		List<Cliente> listaClientes = new ArrayList<>();
		listaClientes.add(new Cliente("CL-01", "Pablo", "123456789"));
		listaClientes.add(new Cliente("CL-02", "Maria", "987654321"));
		listaClientes.add(new Cliente("CL-03", "Jorge", "456789123"));
		listaClientes.add(new Cliente("CL-04", "Ana", "789123456"));
		
		if(fichero.exists()) {
			try(ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(fichero))){
				listaClientes = (List<Cliente>) entrada.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
				
		do {
			System.out.println("1. Añadir nuevo cliente");
			System.out.println("2. Modificar datos");
			System.out.println("3. Dar de baja cliente");
			System.out.println("4. Listar los clientes");
			System.out.println("5. Salir");
			System.out.print("Selección: ");
			opcion = sc.nextInt();
			
			switch(opcion) {
				case 1:
					System.out.print("Introduce el codigo: ");
					String codigo = sc.next();
					System.out.print("Introduce el nombre: ");
					String nombre = sc.next();
					System.out.print("Introduce el telefono: ");
					String telefono = sc.next();
					listaClientes.add(new Cliente(codigo, nombre, telefono));
					break;
				case 2:
					System.out.print("Introduce el identificador del cliente a buscar: ");
					String idModificar = sc.next();
					boolean encontrado = false;
					for(Cliente c : listaClientes) {
						if(c.getId().equalsIgnoreCase(idModificar)) {
							System.out.print("Introduce el nombre a modificar: ");
							String nombreNuevo = sc.next();
							System.out.print("Introduce el telefono a modificar: ");
							String telefonoNuevo = sc.next();
							c.setNombre(nombreNuevo);
							c.setTelefono(telefonoNuevo);
							System.out.println("Cliente modificado");
							encontrado = true;
						}
					}
					if(!encontrado) {
						System.out.println("No existe el id");
					}
					break;
				case 3:
					System.out.print("Introduce el identificador del cliente a buscar: ");
					String idBorrar = sc.next();
					boolean borrado = false;
					for(Cliente c : listaClientes) {
						if(c.getId().equalsIgnoreCase(idBorrar)) {
							//listaClientes.remove(c);		--> no se pueden modificar datos en un for-each
							borrado = true;
						}
					}
					if(!borrado) {
						System.out.println("No se ha borrado porque no existe");
					}
					break;
				case 4:
					for(Cliente c : listaClientes) {
						System.out.println(c);
					}
					break;
				case 5:
					try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {
						salida.writeObject(listaClientes);
					} catch (IOException e) {
						System.out.println(e);
					}
					break;
			}
				
		}while(opcion != 5);
		
		sc.close();
	}

}
