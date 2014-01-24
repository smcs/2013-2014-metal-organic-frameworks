package objloader;

public class StructureOrganizer {
	private int numberofAtoms;
	private JOGLRenderer MyRenderer;
	
	public StructureOrganizer(JOGLRenderer M){
		//called by JOGLRenderer to create structure for atoms
		MyRenderer = M;
	}
	public void Place_Atom(int atomNumber){
		//places an atom
		
		//if there is nothing else, place at origin 
		if(numberofAtoms == 0){
			MyRenderer.place(atomNumber, 0, 0, 0);
		}else{
			//otherwise, place at the end of correct bond 
		}
	}
	public void Place_Bonds(){
		//places bonds based on geometry 
	}
	
	public void CalculateHybridization(int IDnumber){
		//searches the xml to tell how many atoms the atom is bonded to 
	}
	
	public void getBondGeometry(){
		//calculates the bond geometry 
	}
	
}
