package BioProj.Project;

import java.util.ArrayList;
import java.util.Collections;

public class SeconderyStructure {

	private String type;
	private String chainId;
	private ArrayList<ChainAndNumber> aminoAcidNumbers;
	
	private boolean isRealOrFromFree;
	

	private ArrayList<Integer> simpleNumbersOfOriginalAminoAcids;
	
	
	
	public String toCode(){
		
		
		String res = chainId + ":" + simpleNumbersOfOriginalAminoAcids.get(0);
		if(simpleNumbersOfOriginalAminoAcids.size() > 1){
			int last = simpleNumbersOfOriginalAminoAcids.size() - 1;
			res = res + "-" + simpleNumbersOfOriginalAminoAcids.get(last);
		}
		
		return res;
	}
	
	
	public int getTopEnd(){
		Collections.sort(simpleNumbersOfOriginalAminoAcids);
		
		int last = simpleNumbersOfOriginalAminoAcids.size() - 1;
		return simpleNumbersOfOriginalAminoAcids.get(last);
	}
	
	public int getStartEnd(){
		Collections.sort(simpleNumbersOfOriginalAminoAcids);
		return simpleNumbersOfOriginalAminoAcids.get(0);
	}
	
	
	public void addAminoAcid(ChainAndNumber chainAndNumber){
		if(!isInStructAlready(chainAndNumber.getChain(), chainAndNumber.getOriginalNumber())){
			
			aminoAcidNumbers.add(chainAndNumber);
			Collections.sort(aminoAcidNumbers);
			
			simpleNumbersOfOriginalAminoAcids.add(chainAndNumber.getOriginalNumber());
			Collections.sort(simpleNumbersOfOriginalAminoAcids);
			
			
		}
	}
	
	public boolean isInStructAlready(String chainId, Integer originalNumber){
		if(this.chainId.equalsIgnoreCase(chainId)){
			for(ChainAndNumber chainAndNumber : aminoAcidNumbers){
				if(chainAndNumber.getOriginalNumber().compareTo(originalNumber) == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	public SeconderyStructure(String type, String chainId, boolean isRealOrFromFree) {
		this.isRealOrFromFree = isRealOrFromFree;
		this.chainId = chainId;
		this.type = type;
		this.aminoAcidNumbers = new ArrayList<ChainAndNumber>();
		this.simpleNumbersOfOriginalAminoAcids = new ArrayList<Integer>();
	}



	public String getChainId() {
		return chainId;
	}



	public void setChainId(String chainId) {
		this.chainId = chainId;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<ChainAndNumber> getAminoAcidNumbers() {
		return aminoAcidNumbers;
	}
	public void setAminoAcidNumbers(ArrayList<ChainAndNumber> aminoAcidNumbers) {
		this.aminoAcidNumbers = aminoAcidNumbers;
	}
	public SeconderyStructure(String type, ArrayList<ChainAndNumber> aminoAcidNumbers) {
		this.type = type;
		this.aminoAcidNumbers = aminoAcidNumbers;
	}

	public int count() {
		return simpleNumbersOfOriginalAminoAcids.size();
	}
	
	
	
	
}
