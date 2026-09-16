package ejercicio6;

import java.util.*;

public class _Principal {

	public static void main(String[] args) {
		
		String[] catalogo = {"Leche", "Pan", "Huevos", "Manzanas", "Pollo", 
                "Arroz", "Pasta", "Tomates", "Queso", "Café"};
		
		List<Producto> cesta = new ArrayList<>();
		Random aleat = new Random();
		
		double importeTotal = 0;
		Producto masCaro = null;
		
		for(int i = 0; i<catalogo.length;i++) {
			String prodAleat = catalogo[aleat.nextInt(catalogo.length)];
			
			double precioAleat = 1 + (aleat.nextDouble() * 19);
			
			int cantAleat = aleat.nextInt(5) + 1;
			
			Producto p = new Producto(prodAleat, precioAleat, cantAleat);
			cesta.add(p);
			
			System.out.println(p.toString());
			
			importeTotal += p.total();
			
			if(masCaro == null || p.getPrecio() > masCaro.getPrecio()) {
				masCaro = p;
			}
		}
		
		
		System.out.println(" TICKET COMPRS ");
		
		System.out.println("Total incial "+importeTotal);
		if(importeTotal > 50) {
			double descuento = importeTotal * 0.10;
			importeTotal -= descuento;
			System.out.println("Descuento aplicado");
			System.out.println("Total a pagar: "+importeTotal);
		}else {
			System.out.println("Total a pagar "+Math.round(importeTotal * 100.0) / 100.0 + "€");
		}
		
		System.out.println("El producto más caro es: " + masCaro.getNombre() + " a " + Math.round(masCaro.getPrecio() * 100.0) / 100.0 + "€/ud");
	}

}
