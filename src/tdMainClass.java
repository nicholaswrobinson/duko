// Nicholas Robinson 20157886


public class tdMainClass {
	
	public static int 		duko_x_dimensions 	= 9;
	public static int 		duko_y_dimensions 	= 9;
	public static int 		duko_numRange 		= 9;
	public static int 		duko_numSectors 	= 9;
		
	public static char[][] 	duko_columns 		= new char[duko_y_dimensions][duko_x_dimensions];
	public static char[][] 	duko_rows 			= new char[duko_x_dimensions][duko_y_dimensions];
	public static char[][] 	duko_sectors 		= new char[duko_numSectors][duko_numRange];
	
	
	public static int x_sector_length = (int)Math.sqrt(duko_x_dimensions);
	public static int y_sector_length = (int)Math.sqrt(duko_y_dimensions);
	
	public static void main(String[] args) {
		
		
		
		duko_rows[4][4] = '9';
		
		
//do_printSuduko();
		
	     
		
		if(det_valid(4, 4, '9')){
			System.out.println("it is valid");
		} else {
			System.out.println("it is not valid");
		}

		
//		System.out.println(det_sector(4,3));
	
	   
	    
	   }
	
	public static void do_printSuduko(){
		// method outputs all table data to console, used for testing purposes
		
		
		// print columns
		System.out.println("\ncolumns data:");
		for (int i = 0; i < duko_y_dimensions; i++) {
	    	String toPrint = "";
			for (int j = 0; j < duko_x_dimensions; j++) {
				toPrint += duko_columns[j][i] + " ";			
			}
			
			System.out.println(toPrint);
		}
		
		// print rows
		System.out.println("\nrows data:");
		for (int i = 0; i < duko_y_dimensions; i++) {
	    	String toPrint = "";
			for (int j = 0; j < duko_x_dimensions; j++) {
				toPrint += duko_rows[i][j] + " ";			
			}
			
			System.out.println(toPrint);
		}
		
		// print sectors
		System.out.println("\nsectors data:");
		for (int i = 0; i < duko_numSectors; i++) {
			String toPrint = "sector " + (i+1) + ": ";
			for (int j = 0; j < duko_numRange; j++) {
				toPrint += duko_sectors[i][j] + " ";
			}
			
			System.out.println(toPrint);
		}
		
		
	}
	
	

	
	public static void do_assignToGrid(int x_coord, int y_coord, char value){
		int sector = det_sector(x_coord, y_coord);
		
		duko_columns[x_coord][y_coord] = value;
		duko_rows[y_coord][x_coord] = value;
		//duko_sectors[sector]
		
	}
	
	public static int det_sector(int x_coord, int y_coord){
		// var x_coord		-The x sudoko grid reference
		// var y_coord		-The y sudoko grid reference
		// var sector		-The subgrid a coordinate is assigned to
		
		
		//	[][][] [][][] [][][]
		//	[][][] [][][] [][][]
		//	[][][] [][][] [][][]
		//                       _
		//	[][][] [][][] [][][]  \
		//	[][][] [][][] [][][]   >-- this is a sector length (equal to square root of grid side length)
		//	[][][] [][][] [][][] _/
		//
		//	[][][] [][][] [][][]
		//	[][][] [][][] [][][]
		//	[][][] [][][] [][][]
		
		
		//	Sector number assignment:
		//
		//		 +0    +1    +2
		//		 ___   ___   ___
		//	0*3	| 0 | | 1 | | 2 |
		//		|___| |___| |___|
		//		 ___   ___   ___
		//	1*3	| 3 | | 4 | | 5 |
		//		|___| |___| |___|
		//		 ___   ___   ___
		//	2*3	| 6 | | 7 | | 8 |
		//		|___| |___| |___|
		//	
		//	example: sector 7 = 2*3+1
			
		
		// determine x sub-sector
				
		int x_sector = (int)((x_coord) / x_sector_length);		
		
		// determine y sub-sector
		
		int y_sector = (int)((y_coord) / y_sector_length);	
		
		// determine sector to be assigned to
		int sector = (y_sector * y_sector_length) + x_sector; 
		
		return sector;
	}
	
	
	
	
	public static boolean det_valid(int x_coord, int y_coord, char value){
		
		// var x_coord		-The x sudoko grid reference
		// var y_coord		-The y sudoko grid reference
		// var sector		-The sudoku grid sector reference
		// var value		-The input value of block
		// section			-Refers to a ( column / row / sector ) 
		
		int sector = det_sector(x_coord, y_coord);

		boolean valid = true;		
		// search loops end when: all values of a section have been checked / a match has been found (illegal move)
		
		
		// checks if coordinate is occupied
		if (duko_rows[y_coord][x_coord] != 0){
			valid = false;
		}
		
		// checks if specified column contains specified value
		for (int count_forColumn = 0; count_forColumn < duko_y_dimensions && valid; count_forColumn++){			
			if (value == duko_columns[x_coord][count_forColumn]){
				valid = false;
			}
		}
		
	
		// checks if specified row contains specified value		
		for (int count_forRow = 0; count_forRow < duko_x_dimensions && valid; count_forRow++){			
			if (value == duko_rows[y_coord][count_forRow]){
				valid = false;
			}
		}
		

		// checks if specified sector contains specified value		
		for (int count_forSector = 0; count_forSector < duko_numRange && valid; count_forSector++){
			if (value == duko_sectors[sector][count_forSector]){
				valid = false;
			}
			
		}
			
		return valid;
		// returns: true if move is legal / false if move is illegal
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}