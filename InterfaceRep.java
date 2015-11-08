package BioProj.Project;

import java.util.ArrayList;

public class InterfaceRep {

	//Cluster:#	2	Number of Members:	3	Representative:	3N2NAF	Members	3N2NAF	3N2NBD	3N2NCE
	
	private String interfaceName;
	private ArrayList<String> members;
	private int numOfMembers;
	
	
	public InterfaceRep() {
		this.interfaceName = "";;
		this.members = new ArrayList<String>();
		this.numOfMembers = 0;;
	}
	public void addMember(String id){
		this.members.add(id);
	}
	
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public ArrayList<String> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	public int getNumOfMembers() {
		return numOfMembers;
	}
	public void setNumOfMembers(int numOfMembers) {
		this.numOfMembers = numOfMembers;
	}
	public InterfaceRep(String interfaceName, ArrayList<String> members, int numOfMembers) {
		this.interfaceName = interfaceName;
		this.members = members;
		this.numOfMembers = numOfMembers;
	}
	
	
	
	
	
}
