package BioProj.Project;

import java.util.ArrayList;

public class ExtraAcids {

	private String chainId;
	private ArrayList<ChainAndNumber> aas;
	private ArrayList<SeconderyStructure> seconderyStructures;
	
	
	public ExtraAcids(String chainId) {
		this.chainId = chainId;
		this.aas = new ArrayList<ChainAndNumber>();
		this.seconderyStructures = new ArrayList<SeconderyStructure>();
	}
	
	
	public void addChain(ChainAndNumber chainAndNumber){
		if(chainAndNumber.getChain().equalsIgnoreCase(chainId)){
			
			aas.add(chainAndNumber);
			
			
			for(SeconderyStructure seconderyStructure: seconderyStructures){
				
				if(chainAndNumber.getOriginalNumber() - seconderyStructure.getStartEnd() == -1 || chainAndNumber.getOriginalNumber() - seconderyStructure.getTopEnd() == 1)
				{
					seconderyStructure.addAminoAcid(chainAndNumber);
					return;
					
				}
			}
			//if here then was not added to any chain
			
			SeconderyStructure seconderyStructure = new SeconderyStructure("Fake", chainAndNumber.getChain(), false);
			seconderyStructure.addAminoAcid(chainAndNumber);
			
			this.seconderyStructures.add(seconderyStructure);
			
		}
		
		
	}
	
	
	public String getChainId() {
		return chainId;
	}
	public void setChainId(String chainId) {
		this.chainId = chainId;
	}
	public ArrayList<ChainAndNumber> getAas() {
		return aas;
	}
	public void setAas(ArrayList<ChainAndNumber> aas) {
		this.aas = aas;
	}


	public ArrayList<SeconderyStructure> getSeconderyStructures() {
		return seconderyStructures;
	}


	public void setSeconderyStructures(
			ArrayList<SeconderyStructure> seconderyStructures) {
		this.seconderyStructures = seconderyStructures;
	}
	
	
	
	
}
