package ejercicio7;

import java.util.*;

public class _Principal {

	public static void main(String[] args) {
		
		List<Plaza> parking = new ArrayList<>();
		Random aleat = new Random();
		
		for(int i = 0; i<10;i++) {
			int aux  = aleat.nextInt(2);
			boolean esElectrico;
			if(aux == 0) {
				esElectrico = true;
			}else {
				esElectrico = false;
			}
			Plaza p = new Plaza(i,esElectrico);
			parking.add(p);
		}
		
		List<Vehiculo> coches = new ArrayList<>();
		coches.add(new Vehiculo("4521-KLM", false));
		coches.add(new Vehiculo("7789-XPT",true));
		coches.add(new Vehiculo("3312-BBQ", false));
		coches.add(new Vehiculo("9087-ZZT",false));
		coches.add(new Vehiculo("1234-AAA", true));
		coches.add(new Vehiculo("5566-CCV",false));
		coches.add(new Vehiculo("8899-DDR", false));
		coches.add(new Vehiculo("2231-EFG",false));
		coches.add(new Vehiculo("6677-HIJ", true));
		coches.add(new Vehiculo("4409-KLL",false));
		
		List<Vehiculo> fuera = new ArrayList<>();
		boolean bandera = false;
		
		for(int i = 0; i< coches.size(); i++) {
			Vehiculo coche = coches.get(i);
			boolean aparcado =false;
			
			for(int j = 0; j < parking.size() && !aparcado; j++) {
				Plaza plaza = parking.get(j);
				
				if(!plaza.isOcupada()) {
					if(coche.isEsElectrico() || !plaza.isElectricos()) {
						plaza.setOcupada(true);
						plaza.setVehiculo(coche);
						aparcado  =true;
					}
				}
			}
			if(!aparcado) {
				fuera.add(coche);
			}
		}
		
		System.out.println("=== ESTADO DEL APARCAMIENTO ===");
		for (int i = 0; i < parking.size(); i++) {
			Plaza plaza = parking.get(i);
			String tipoPlaza = plaza.isElectricos() ? "[ELÉCTRICA]" : "[NORMAL]   ";
			
			if (plaza.isOcupada()) {
				System.out.println("Plaza " + plaza.getNum() + " " + tipoPlaza + " -> Ocupada por: " + plaza.getVehiculo().getMatricula());
			} else {
				System.out.println("Plaza " + plaza.getNum() + " " + tipoPlaza + " -> LIBRE");
			}
		}
		
		System.out.println("\n=== COCHES QUE SE QUEDARON FUERA ===");
		if (fuera.isEmpty()) {
		} else {
			System.out.println("Ninguno, todos han conseguido aparcar (de milagro).");
			for (int i = 0; i < fuera.size(); i++) {
				Vehiculo v = fuera.get(i);
				String tipoCoche = v.isEsElectrico() ? "(Eléctrico)" : "(No eléctrico)";
				System.out.println("- Matrícula: " + v.getMatricula() + " " + tipoCoche);
			}
		}


	}

}
