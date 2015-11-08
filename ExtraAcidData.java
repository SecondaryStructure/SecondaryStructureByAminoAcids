package BioProj.Project;

import java.util.ArrayList;

public class ExtraAcidData {

	ArrayList<ExtraAcids> extraBuckets;

	public ArrayList<ExtraAcids> getExtraBuckets() {
		return extraBuckets;
	}

	public void setExtraBuckets(ArrayList<ExtraAcids> extraBuckets) {
		this.extraBuckets = extraBuckets;
	}

	public ExtraAcidData() {
		this.extraBuckets = new ArrayList<ExtraAcids>();
	}
	
	
	public void add(ChainAndNumber chainAndNumber){
		boolean found = false;
		for(ExtraAcids extraAcids :extraBuckets){
			if(extraAcids.getChainId().equalsIgnoreCase(chainAndNumber.getChain())){
				extraAcids.addChain(chainAndNumber);
				found = true;
			}
		}
		if(!found){
			ExtraAcids extraAcid = new ExtraAcids(chainAndNumber.getChain());
			extraAcid.addChain(chainAndNumber);
			extraBuckets.add(extraAcid);
		}
	}
	
	
	public int count(){
		int res = 0;
		for(ExtraAcids extraAcids :extraBuckets){
			res = res + extraAcids.getAas().size();
		}
		 return res;
	}
	
}
