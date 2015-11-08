package BioProj.Project;

public class ChainAndNumber implements Comparable<ChainAndNumber>{

	private String chain;
	private Integer numberInChain;
	
	private Integer originalNumber;

	public String getChain() {
		return chain;
	}

	public void setChain(String chain) {
		this.chain = chain;
	}

	public Integer getNumberInChain() {
		return numberInChain;
	}

	public void setNumberInChain(Integer numberInChain) {
		this.numberInChain = numberInChain;
	}

	public Integer getOriginalNumber() {
		return originalNumber;
	}

	public void setOriginalNumber(Integer originalNumber) {
		this.originalNumber = originalNumber;
	}

	public ChainAndNumber(String chain, Integer numberInChain,
			Integer originalNumber) {
		this.chain = chain;
		this.numberInChain = numberInChain;
		this.originalNumber = originalNumber;
	}

	public int compareTo(ChainAndNumber o) {
		if(this.chain.compareTo(o.getChain()) == 0){
			return this.originalNumber.compareTo(o.getOriginalNumber());
		}
		else{
			return this.chain.compareTo(o.getChain());
		}
		
	}
	
	
	
	


	
	
	
	
}
