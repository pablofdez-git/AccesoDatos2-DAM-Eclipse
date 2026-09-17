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
		
		//for(int)

	}

}
