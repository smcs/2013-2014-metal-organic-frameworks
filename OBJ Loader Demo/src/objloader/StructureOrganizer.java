package objloader;

import java.util.LinkedList;
import java.util.Vector;

public class StructureOrganizer {
	private int numberofAtoms;
	private JOGLRenderer MyRenderer;
	private Vector<float[]> AtomsData;
	//numbers per array in order: ID, type, number of bonds currently on atom, X location,
	//Y location, Z location 
	private Vector<float[]> BondsData;
	//numbers per array in order: Begins, Ends, length, X beginning,
	//Y beginning, Z beginning, X end, Y end, Z end
	private boolean complete;
	
	public StructureOrganizer(JOGLRenderer M){
		//called by JOGLRenderer to create structure for atoms
		MyRenderer = M;
		//put all data in AtomsData and BondsData
		int ID = 0;
		int BondID = 0;
		
		int Count = 0;
		while(complete == false){ //while there is still unfilled data 
			
			int Unfinished = 0;
			
			if(Count == 0){ //if this is the first atom 
			Place_Atom(ID, BondID); //place an atom
			getBondGeometry(ID, (int) AtomsData.get(ID)[2]); 
			//get the bond geometry for that atom
			}else{
				int j = 0;
				while(AtomsData.get(j) != null){ //for all atoms
					if(AtomsData.get(j)[3] != 0 && AtomsData.get(j)[4] != 0 
					&& AtomsData.get(j)[5] != 0){ //if the atom isn't at the origin
						getBondGeometry( (int) AtomsData.get(ID)[1],
						(int) AtomsData.get(ID)[2]); // get its bond geometry
						Unfinished = Unfinished + 1; 
					}
					j = j + 1;
				}
			}
			
			int i = 0;
			while(BondsData.get(i) != null){ //for all bonds
				if(BondsData.get(i)[6] != 0 && BondsData.get(i)[7] != 0
						&& BondsData.get(i)[8] != 0){// if the bond doesn't end on the 
					//origin (ie it has been placed)
					Place_Atom((int)BondsData.get(i)[1], i);
					//place the correct atom at the end of it. 
					Unfinished = Unfinished + 1; 
				}
				i = i + 1;
			}
			
			Count = Count + 1; 
			if(Unfinished == 0){
				complete = true;
			}
		}
	}
	public void Place_Atom(int ID, int BondID){
		//places an atom
		
		//if there is nothing else, place at origin 
		if(numberofAtoms == 0){
			AtomsData.get(ID)[3] = 0;
			AtomsData.get(ID)[4] = 0;
			AtomsData.get(ID)[5] = 0;
			numberofAtoms = numberofAtoms +1;
		}else{
			//if there is other stuff, place this atom at the end of the bond
			//it is associated with 
			AtomsData.get(ID)[3] = BondsData.get(BondID)[6];
			AtomsData.get(ID)[4] = BondsData.get(BondID)[7];
			AtomsData.get(ID)[5] = BondsData.get(BondID)[8];
			numberofAtoms = numberofAtoms +1;
		}
	}

	
	public int[] CalculateHybridization(int IDnumber){
		int[] data = null;
		//searches the xml to tell how many atoms the atom is bonded to 
		return data;
	}
	
	public void getBondGeometry(int ID, int numBonds){
		int[] bond = CalculateHybridization(ID);

		
		for(int i = 0; i < bond.length; i++){ //set the start point of each bond
			//to be the location of the atom 
			BondsData.get(bond[i])[3] = AtomsData.get(ID)[3];
			BondsData.get(bond[i])[4] = AtomsData.get(ID)[4];
			BondsData.get(bond[i])[5] = AtomsData.get(ID)[5];
		}
		if(numBonds == 0){ //if there are currently no bonds on the screen
			//set the first bond pointing in the X direction. 
			BondsData.get(bond[0])[6] = BondsData.get(bond[0])[3] +
					BondsData.get(bond[0])[2];
			BondsData.get(bond[0])[7] = BondsData.get(bond[0])[4];
			BondsData.get(bond[0])[8] = BondsData.get(bond[0])[5];
		}else{
			for(int i = 0; i < bond.length; i++){
				if(BondsData.get(bond[0])[6] != 0 && BondsData.get(bond[0])[7] != 0
						&& BondsData.get(bond[0])[8] != 0){
					bond[i] = -1;
					
				}
			}
		}
		//lots of math here
		if(bond.length == 1){
			
		}
		if(bond.length == 2){
			
		}
		if(bond.length == 3){
			
		}
		if(bond.length == 4){
			
		}
		if(bond.length == 5){
			
		}
		if(bond.length == 6){
			
		}
	}
	
}
