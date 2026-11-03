package fases;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Producto;

public class Fase1 {

	public static void main(String[] args) {
		
		/*
		 * 	SERIALIZAR (Pasar de Java a JSON)
		 */
		Producto p = new Producto(1, "Taladro percutor", 89.90, 12);
		//Producto pError = new Producto(1, null, 89.90, 12);			//Omite los null: {"id":1,"precio":89.9,"stock":12}
		
		Gson gson = new Gson();
		Gson gsonFormateado = new GsonBuilder().setPrettyPrinting().create();		//para darle un formato "bonito"
		
        String json = gson.toJson(p);
        String jsonFormateado = gsonFormateado.toJson(p);

        System.out.println(json);
        System.out.println();
        System.out.println(jsonFormateado);
        System.out.println();
        
        
        /*
		 * 	DESERIALIZAR (Pasar de JSON a Java)
		*/
        String JSONcomoTexto = "{\"id\":2,\"nombre\":\"Martillo\",\"precio\":12.5,\"stock\":40}";
        //String JSONcomoTextoError = "{\"id\":2,\"nombre\":\"Martillo\",\"precio\":12.5,\"stok\":40}";	//Producto{id=2, nombre=Martillo, precio=12.5, stock=0}
        
        Producto m = gson.fromJson(JSONcomoTexto, Producto.class);		//Parametros: el texto y el tipo(la clase)
        
        System.out.println(m);
        System.out.println(m.getNombre());

	}

}
