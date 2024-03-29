package objloader;

import java.util.Vector;

public class StructureOrganizer {
	private int numberofAtoms;

	public static Vector<float[]> AtomsData = new Vector<float[]>();
	//numbers per array in order: ID, type, number of bonds currently on atom, X location,
	//Y location, Z location 
	public static Vector<float[]> BondsData = new Vector<float[]>();
	//numbers per array in order: Begins, Ends, length, X beginning,
	//Y beginning, Z beginning, X end, Y end, Z end
	private boolean complete;
	
	public StructureOrganizer(){
		//called by JOGLRenderer to create structure for atoms

		//put all data in AtomsData and BondsData
		int ID = 0;
		int BondID = 0;
		
		//test inputs 
		float[] f = new float[]{1,1,0,0,0,0};
		AtomsData.add(f);
	
		float[] e = new float[]{2,1,0,0,0,0};
		AtomsData.add(e);
		
		float[] b = new float[]{1,2,3,0,0,0,0,0,0};
		BondsData.add(b);
		//end test inputs 
		
		
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
						getBondGeometry( (int) AtomsData.get(j)[0],
						(int) AtomsData.get(j)[2]); // get its bond geometry
						Unfinished = Unfinished + 1; 
					}
					j = j + 1;
				}
			
			
			
			for(int i = 0; i < BondsData.size(); i++){ //for all bonds
				if(BondsData.get(i)[6] != 0 && BondsData.get(i)[7] != 0
						&& BondsData.get(i)[8] != 0){// if the bond doesn't end on the 
					//origin (ie it has been placed)
					Place_Atom((int)BondsData.get(i)[1], i);
					//place the correct atom at the end of it. 
					Unfinished = Unfinished + 1; 
				}
				i = i + 1;
			}
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
		//TODO Julie write this please 
		//searches the xml to tell how many atoms the atom is bonded to 
		return data;
	}
	
	
	
	public void getBondGeometry(int ID, int numBonds){
		
		int[] bond = CalculateHybridization(ID);
		float dx = 0;
		float dy = 0;
		float dz = 0;
		if(bond == null){
			return;
		}
		for(int i = 0; i < bond.length; i++){ //set the start point of each bond
			//to be the location of the atom 
			if(BondsData.get(bond[i])[6] == AtomsData.get(ID)[3]&&
			   BondsData.get(bond[i])[7] == AtomsData.get(ID)[4]&&
			   BondsData.get(bond[i])[8] == AtomsData.get(ID)[5]){
				
			}else{
			BondsData.get(bond[i])[3] = AtomsData.get(ID)[3];
			BondsData.get(bond[i])[4] = AtomsData.get(ID)[4];
			BondsData.get(bond[i])[5] = AtomsData.get(ID)[5];
			}
		}
		if(numBonds == 0){ //if there are currently no bonds on the screen
			//set the first bond pointing in the X direction. 
			BondsData.get(bond[0])[6] = BondsData.get(bond[0])[3] +
					BondsData.get(bond[0])[2];
			BondsData.get(bond[0])[7] = BondsData.get(bond[0])[4];
			BondsData.get(bond[0])[8] = BondsData.get(bond[0])[5];

			bond[0] = -1;
			
		}else{ //if there is a bond already in place, set that index to -1
			for(int i = 0; i < bond.length; i++){
				if(BondsData.get(bond[i])[6] != 0 && BondsData.get(bond[i])[7] != 0
						&& BondsData.get(bond[i])[8] != 0){
				
					dx = -BondsData.get(bond[i])[6] + AtomsData.get(ID)[3];
					dy = -BondsData.get(bond[i])[7] + AtomsData.get(ID)[4];
					dz = -BondsData.get(bond[i])[8] + AtomsData.get(ID)[5];
					bond[i] = -1;
				}
			}
		}
		
		if(bond.length == 1){
			return;
		}
		if(bond.length == 2){ //set the un-established bond pointing in the other direction
			for(int i = 0; i < bond.length; i++){
				if(bond[i] != -1){
					BondsData.get(bond[i])[6] = dx+ AtomsData.get(ID)[3]; 
					BondsData.get(bond[i])[7] = dy + AtomsData.get(ID)[4];
					BondsData.get(bond[i])[8] = dz + AtomsData.get(ID)[5];
				}
			}
		}
		if(bond.length == 3){
			return;
		}
		if(bond.length == 4){
			return;
		}
		if(bond.length == 5){
			return;
		}
		if(bond.length == 6){
			return;
		}
	}
	
}
