package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumerativo con los distintos equipos que se utilizan
 * durante un ejercicio.
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public enum Equipment {
	
	MANCUERNA ("MANCUERNA"),
	BARRA_OLIMPICA ("BARRA OLIMPICA"),
	KETTLEBELL("KETTLEBELL"),
	SOGA ("SOGA"),
	MEDICINE_BALL("MEDICINE BALL"),
	SOGA_ONDULACIONES ("SOGA ONDULACIONES"),
	CINTA("CINTA");
	
	
	private final String name;
	
	private Equipment(String name) {
		this.name = name;
	}
	
	public final String getName(){
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	public final List<Equipment> getAll (){
		List<Equipment> equipments = new ArrayList<Equipment>();
		Equipment[] values = Equipment.values();
		for (Equipment equipment : values) {
			equipments.add(equipment);
		}
		return equipments;
	}

}
