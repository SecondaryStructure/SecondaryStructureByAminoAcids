package BioProj.Project;

import java.util.HashMap;

public class Interfaces {

	private HashMap<String, InterfaceRep> map;

	public HashMap<String, InterfaceRep> getMap() {
		return map;
	}

	public void setMap(HashMap<String, InterfaceRep> map) {
		this.map = map;
	}

	public Interfaces() {
		this.map = new HashMap<String, InterfaceRep>();
	}
	
	
	
	
}
