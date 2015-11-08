package BioProj.Project;

import java.util.ArrayList;

import org.biojava.nbio.structure.AminoAcid;
import org.biojava.nbio.structure.Chain;
import org.biojava.nbio.structure.Group;

public class AllRelevantAcids {
	
	private ArrayList<SeconderyStructure> seconderyStructures;
	private ExtraAcidData extraAcids;
	
	
	public boolean isInStructAlready(String chainId, Integer originalNumber){
		
		for(SeconderyStructure secStruct : this.seconderyStructures){
        	if(secStruct.isInStructAlready(chainId, originalNumber)){
        		return true;
        	}
        }
		return false;
		
	}
	
	
	public AllRelevantAcids() {
		this.extraAcids = new ExtraAcidData();
		this.seconderyStructures = new ArrayList<SeconderyStructure>();
	}

	public ArrayList<SeconderyStructure> getSeconderyStructures() {
		return seconderyStructures;
	}

	public void setSeconderyStructures(
			ArrayList<SeconderyStructure> seconderyStructures) {
		this.seconderyStructures = seconderyStructures;
	}

	public ExtraAcidData getExtraAcids() {
		return extraAcids;
	}

	public void setExtraAcids(ExtraAcidData extraAcids) {
		this.extraAcids = extraAcids;
	}

	public AllRelevantAcids(ArrayList<SeconderyStructure> seconderyStructures,
			ExtraAcidData extraAcids) {
		this.seconderyStructures = seconderyStructures;
		this.extraAcids = extraAcids;
	}


	public void addNewStruct(Chain chain, Integer originalNumber, String structType) throws Exception {
		AminoAcidDataChain acidDataChain = new AminoAcidDataChain();
		SeconderyStructure seconderyStructure = new SeconderyStructure(structType, chain.getChainID(), true);
		
		//create Data chain
        Integer resNumInChain = 0;
		for (Group g: chain.getAtomGroups()){
            if ( g instanceof AminoAcid ){
            	AminoAcid aa = (AminoAcid)g;
            	resNumInChain++;
            	acidDataChain.addToEndOfChain(aa, resNumInChain);
            }
		}
		
		
		AminoAcidDataChainItem downItem = null;


		//crawl up data chain
		AminoAcidDataChainItem upCurrent = acidDataChain.getStart();
		boolean upHit = false;
		
		do { 
			if(!upHit){
				if(upCurrent.getCurrent().getResidueNumber().getSeqNum().compareTo(originalNumber) == 0){
					upHit = true;
					seconderyStructure.addAminoAcid(new ChainAndNumber(chain.getChainID(), upCurrent.getCurrentNumInChain(), upCurrent.getCurrent().getResidueNumber().getSeqNum()));
					downItem = upCurrent;
				}
			}
			else if(upHit){
				if(upCurrent.getCurrent().getSecStruc().size() > 0){
					String currentStructType = upCurrent.getCurrent().getSecStruc().get(App.SEC_STRUCT);
					if(currentStructType.equalsIgnoreCase(structType)){

						seconderyStructure.addAminoAcid(new ChainAndNumber(chain.getChainID(), upCurrent.getCurrentNumInChain(), upCurrent.getCurrent().getResidueNumber().getSeqNum()));
						
					}else{
						break;
					}
				}else{
					break;
				}

			}
			
		
			upCurrent = upCurrent.getNext();
		
			
		} while(upCurrent != null);

		
		if(downItem == null){
			throw new Exception("Item is null - BUG");
		}
		
		//crawl down data chain
		boolean downHit = false;
		while(downItem.hasPrev()){
			if(!downHit){
				if(downItem.getCurrent().getResidueNumber().getSeqNum().compareTo(originalNumber) == 0){
				
					downHit = true;
					
				}
			}else{
				if(downItem.getCurrent().getSecStruc().size() > 0)
				{
					String currentStructType = downItem.getCurrent().getSecStruc().get(App.SEC_STRUCT); //add if null
					if(currentStructType.equalsIgnoreCase(structType))
					{

						seconderyStructure.addAminoAcid(new ChainAndNumber(chain.getChainID(), downItem.getCurrentNumInChain(), downItem.getCurrent().getResidueNumber().getSeqNum()));
						
					}
					else
					{
						break;
					}
				}
				else
				{
					break;
				}	
			}
			downItem = downItem.getPrev();
		}

		
		this.seconderyStructures.add(seconderyStructure);

		
	}
	
	
	
	public int countSecondery(){
		int result = 0;
		for(SeconderyStructure seconderyStructure: this.seconderyStructures){
			result = result + seconderyStructure.count();
		}
		return result;
	}
	
	

	
	
	
	
	

}
