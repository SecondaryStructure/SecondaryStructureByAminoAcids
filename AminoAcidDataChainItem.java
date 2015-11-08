package BioProj.Project;

import org.biojava.nbio.structure.AminoAcid;

public class AminoAcidDataChainItem {
	
	private AminoAcidDataChainItem prev;
	private AminoAcid current;
	private Integer currentNumInChain;
	private AminoAcidDataChainItem next;
	
	public boolean hasNext(){
		if(next != null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean hasPrev(){
		if(prev != null){
			return true;
		}else{
			return false;
		}
	}
	
	 public AminoAcidDataChainItem getLast(){
		 
		 if(!hasNext()){
			 return this;
		 }else{
			 return next.getLast();
		 }
	 }

	public Integer getCurrentNumInChain() {
		return currentNumInChain;
	}

	public void setCurrentNumInChain(Integer currentNumInChain) {
		this.currentNumInChain = currentNumInChain;
	}

	public AminoAcidDataChainItem getPrev() {
		return prev;
	}
	public void setPrev(AminoAcidDataChainItem prev) {
		this.prev = prev;
	}
	public AminoAcid getCurrent() {
		return current;
	}
	public void setCurrent(AminoAcid current) {
		this.current = current;
	}
	public AminoAcidDataChainItem getNext() {
		return next;
	}
	
	public void setNext(AminoAcidDataChainItem next) {
		this.next = next;
	}

	public AminoAcidDataChainItem(AminoAcidDataChainItem prev, AminoAcid current,
			Integer currentNumInChain, AminoAcidDataChainItem next) {
		this.prev = prev;
		this.current = current;
		this.currentNumInChain = currentNumInChain;
		this.next = next;
	}

	
	
	
	

}
