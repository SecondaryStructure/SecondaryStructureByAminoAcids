package BioProj.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

import org.biojava.nbio.structure.AminoAcid;
import org.biojava.nbio.structure.Chain;
import org.biojava.nbio.structure.Group;
import org.biojava.nbio.structure.ResidueNumber;
import org.biojava.nbio.structure.Structure;
import org.biojava.nbio.structure.io.FileParsingParameters;
import org.biojava.nbio.structure.io.LocalPDBDirectory.FetchBehavior;
import org.biojava.nbio.structure.io.PDBFileReader;

public class SecondaryStructureFromPdb {

	
	private final static String USER_AGENT = "Mozilla/5.0";
	
	/**
	 * The pdb String for Secondary Structures, if others are found in some pdb, then should be added aswell.
	 */
	public final static String SEC_STRUCT = "PDB_AUTHOR_ASSIGNMENT";
	
	
	/**
	 * The threshold of size of the secondary structures.
	 */
	public final static int THRESHOLD_OF_SECONDERY_STRUCTURES = 2;
	
	
	/**
	 *A path to the folder where the files are.
	 *Each file should have a row for each chain 
	 *Each row should start with the chain id and then followed by the AminoAcids Ids
	 *Here is an example of such a file:
	 *	G 56 63 65 66 67 68 69 24 25 26 27 48 28 7 9 8 75 74 73 72 71 70 10 12 59 58 55 54 57 30 53
	 *	H 51 50 62 64 65 66 81 48 49 46 47 44 45 42 43 40 41 7 9 8 39 11 13 38 33 37 36 34 
	 * 
	 */
	public final static String FOLDER_PATH = "c:\\proteins";
	
	

	
	
	public static void main(String args[]) throws Exception{
		
		
		final File folder = new File(FOLDER_PATH);
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	System.out.println(fileEntry.getPath());
	        	continue;
	        	}
	        else {
	            String pdbId = fileEntry.getName().substring(0, 4);
	            System.out.println("======= STARTING " + pdbId +" =======");

	    		InterfaceData interfaceData = new InterfaceData();
	    		
	            //get amino acid numbers
	    		Scanner input = new Scanner(fileEntry);
	    		while(input.hasNext()) {
	    		    String nextToken = input.nextLine();
	    		    System.out.println(nextToken);
	    		    
	    		    String[] splited = nextToken.split("\\s+");
	    		    ArrayList<String> strArray = new ArrayList<String>(Arrays.asList(splited));
	    		    
	    		    InterfaceChainData interfaceChainData = new InterfaceChainData(); 
	    		    
	    		    
	    		    int i = 0;
	    		    for(String s: strArray){
	    		    	if(i==0){
	    		    		interfaceChainData.setChainId(s);
	    		    	}else{
	    		    		interfaceChainData.addAcid(Integer.parseInt(s));
	    		    	}
	    		    	
	    		    	i++;

	    		    }
	    		    		
	    		    interfaceData.getInterfaces().add(interfaceChainData);
    		    
	    		}
	    		
	    		input.close();
	    		
	    		AllRelevantAcids allRelevantAcids = new AllRelevantAcids();
	    		
	    		Structure structure = bringStructure(pdbId);
	    		int numOfItemsWithSecStruc = 0;
		        for (Chain c: structure.getChains()){
		        	 
		            //System.out.println("Atom ligands: " + c.getAtomLigands());
		            //System.out.println(c.getSeqResSequence());
		            Integer resNumInChain = 0;
		            for (Group g: c.getAtomGroups()){
	                    if ( g instanceof AminoAcid ){
	                    	resNumInChain ++;
	                        AminoAcid aa = (AminoAcid)g;
	                        ResidueNumber residueNumber = g.getResidueNumber();
	                        Integer resNumber = residueNumber.getSeqNum();
	                        

	                        if(interfaceData.isInInterface(c.getChainID(), resNumber)){
	                        	
	                        	Map<String,String> sec = aa.getSecStruc();
	                        	int secSize = sec.size();
	                        	
	                        	//if not part of struct
	                        	if(secSize == 0)
	                        	{
	                        		
	                        		allRelevantAcids.getExtraAcids().add(new ChainAndNumber(c.getChainID(), resNumInChain, resNumber));
	                        	}
	                        	else if(secSize == 1)
	                        	{
	                        		numOfItemsWithSecStruc++;
	                        		//if already in structure then good
	                        		if(allRelevantAcids.isInStructAlready(c.getChainID(), resNumber)){
	                        			continue;
	                        		}
	                        		//need to add to existing or new struct
	                        		else
	                        		{
	                        			
	                        			String currentStructType = sec.get(App.SEC_STRUCT);
	                        			if(currentStructType == null){
	                        				throw new Exception("Struct type not fetched with : " + App.SEC_STRUCT);
	                        			}
	                        			allRelevantAcids.addNewStruct(c, resNumber, currentStructType);
	                        		}
	                        		
	                        		
	                        		
	                        	}
	                        	else
	                        	{
	                        		throw new Exception("secSize > 1.  Should not happen. ");
	                        	}
	                        	
	                        }
	       
	                    }
	                }  
	         }	    		
	            System.out.println("======= " + pdbId +" =======");
	            
	            System.out.println("Found " + allRelevantAcids.getExtraAcids().count() + " extra acids");
	            System.out.println("Found " + allRelevantAcids.getSeconderyStructures().size() + " secondery structurs");
	            int a = numOfItemsWithSecStruc + allRelevantAcids.getExtraAcids().count();
            	System.out.println("numOfItemsWithSecStruc: " + numOfItemsWithSecStruc + ", num of free: " + allRelevantAcids.getExtraAcids().count() + ", Total: " + a + ". Should be: " + interfaceData.count());
            	int b = allRelevantAcids.countSecondery() - numOfItemsWithSecStruc;
            	System.out.println("New AminoAcids from seconderyStructures: " + b );
	            for(SeconderyStructure se : allRelevantAcids.getSeconderyStructures()){
	            	se.getChainId();
	            	se.getType();
	            	int last = se.getAminoAcidNumbers().size() - 1;
	            	se.getAminoAcidNumbers().get(0);
	            	se.getAminoAcidNumbers().get(last);
	            	
	            	System.out.println("In Chain: " + se.getChainId() + " Found structure of type: " + se.getType());
	            	System.out.println("Starting from: " + se.getAminoAcidNumbers().get(0).getOriginalNumber() + " to: " + se.getAminoAcidNumbers().get(last).getOriginalNumber());
	            }
	            	            System.out.println("======= FINISHED " + pdbId +" =======");

	        }
	    }

		
	}

		
		
	public static Structure bringStructure(String pdbId){
	      try {
	 
	         PDBFileReader reader = new PDBFileReader();
	 
	         // the path to the local PDB installation
	         reader.setPath("c:\\pdbs");
	 
	         // are all files in one directory, or are the files split,
	         // as on the PDB ftp servers?
	         //reader.setPdbDirectorySplit(true);
	 
	         // should a missing PDB id be fetched automatically from the FTP servers?
	         reader.setFetchBehavior(FetchBehavior.FETCH_FILES);//setAutoFetch(true);
	 
	 
	         // configure the parameters of file parsing
	 
	         FileParsingParameters params = new FileParsingParameters();
	 
	         // should the ATOM and SEQRES residues be aligned when creating the internal data model?
	         params.setAlignSeqRes(true);
	 
	         // should secondary structure get parsed from the file
	         params.setParseSecStruc(true);
	 
	         params.setLoadChemCompInfo(true);
	 
	         reader.setFileParsingParameters(params);
	 
	         Structure structure = reader.getStructureById(pdbId);
	         
	         return structure;
	 
	 
	      } catch (Exception e){
	         e.printStackTrace();
	         
	         return null;
	      }
	 
	 }
	

	
	
}
