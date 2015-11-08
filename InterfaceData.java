package BioProj.Project;

import java.util.ArrayList;

public class InterfaceData {

	private ArrayList<InterfaceChainData> interfaces;

	
	
	
	public InterfaceData() {
		this.interfaces = new ArrayList<InterfaceChainData>();
	}

	public ArrayList<InterfaceChainData> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(ArrayList<InterfaceChainData> interfaces) {
		this.interfaces = interfaces;
	}
	
	
	
	public boolean isInInterface(String chainId, Integer number){
		for(InterfaceChainData interfaceChainData: interfaces){
			if(interfaceChainData.getChainId().equalsIgnoreCase(chainId)){
				if(interfaceChainData.getAcids().contains(number)){
					return true;
				}
			}
		}
		return false;
	}
	
	public int count(){
		int result = 0;
		for(InterfaceChainData interfaceChainData: interfaces){
			result = result + interfaceChainData.count();
		}
		return result;
	}
	
}
