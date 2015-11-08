package BioProj.Project;

import java.util.ArrayList;
import java.util.Collections;

public class InterfaceChainData {

	private String chainId;
	
	private ArrayList<Integer> acids;

	
	public int count(){
		return acids.size();
	}
	
	public InterfaceChainData() {
		
		this.chainId = "";
		this.acids = new ArrayList<Integer>();
	}

	public String getChainId() {
		return chainId;
	}

	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public ArrayList<Integer> getAcids() {
		return acids;
	}

	public void setAcids(ArrayList<Integer> acids) {
		this.acids = acids;
	}
	
	public void addAcid(Integer acidNumberInChain){
		this.acids.add(acidNumberInChain);
		Collections.sort(this.acids);
	}
	
	
	
}
