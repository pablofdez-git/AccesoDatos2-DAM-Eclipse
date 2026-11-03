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
		
		Gson gson = new Gson();
		//Gson gsonFormateado = new GsonBuilder().setPrettyPrinting().create();
		
        String json = gson.toJson(p);
        //String jsonFormateado = gsonFormateado.toJson(p);

        System.out.println(json);
        System.out.println();
        //System.out.println(jsonFormateado);
        //System.out.println();
        
        
        /*
		 * 	DESERIALIZAR (Pasar de JSON a Java)
		 */
        String JSONcomoTexto = "{\"id\":2,\"nombre\":\"Martillo\",\"precio\":12.5,\"stock\":40}";
        
        Producto m = gson.fromJson(JSONcomoTexto, Producto.class);
        
        System.out.println(m);



	}

}
