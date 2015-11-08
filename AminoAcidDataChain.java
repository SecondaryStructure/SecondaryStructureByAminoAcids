package BioProj.Project;

import org.biojava.nbio.structure.AminoAcid;

public class AminoAcidDataChain {

	
	private AminoAcidDataChainItem start;
	
	public void addToEndOfChain(AminoAcid aminoAcid, Integer currentNumInChain){
		if(this.start == null){
			this.start = new AminoAcidDataChainItem(null, aminoAcid, currentNumInChain, null);
		}else{
			start.getLast().setNext(new AminoAcidDataChainItem(start.getLast(), aminoAcid, currentNumInChain, null));
		}
		
	}

	public AminoAcidDataChainItem getStart() {
		return start;
	}

	public void setStart(AminoAcidDataChainItem start) {
		this.start = start;
	}

	public AminoAcidDataChain() {
		this.start = null;
	}
	
	
}
