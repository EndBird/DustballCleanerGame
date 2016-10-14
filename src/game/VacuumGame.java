package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;
import game.Constants;
import game.ArrayGrid;

/**
 * A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {

    // a random number generator to move the DustBalls
    private Random random;
   
    
    // the grid
    private Grid<Sprite> grid;

    // the first player
    private Vacuum vacuum1;

    /// the second player
    private Vacuum vacuum2;

    // the dirt (both static dirt and mobile dust balls)
    private List<Dirt> dirts; 

    // the dumpsters
    private List<Dumpster> dumpsters;
    
    private String[] vacuum1_moves = {"a","d","w","s"};
    private String[] vacuum2_moves = {"j", "l", "i","k"};
   
    /**
     * Creates a new VacuumGame that corresponds to the given input text file.
     * Assumes that the input file has one or more lines of equal lengths, and
     * that each character in it (other than newline) is a character that 
     * represents one of the sprites in this game.
     * @param layoutFileName path to the input grid file
     */
    public VacuumGame(String layoutFileName) throws IOException {
        this.dirts = new ArrayList<Dirt>();
        this.dumpsters = new ArrayList<Dumpster>(); // Jen: may not need this
        this.random = new Random();

        // open the file, read the contents, and determine 
        // dimensions of the grid
        int[] dimensions = getDimensions(layoutFileName);
        this.grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

        // open the file again, read the contents, and store them in grid
        Scanner sc = new Scanner(new File(layoutFileName));

	// INITIALIZE THE GRID HERE
        String line = sc.nextLine();
        // i s for the row, j is for the column
        int i = 0; int j = 0; int k=dimensions[0]; int l = dimensions[1]; int m=0; 
        //checking what each character in a text is, and creating the right sprite.  
        // m is current index of the current line
        for (i=0; i<k;i++) {for (j=0;j<l;j++){char content = line.charAt(m);
        if (content == 'X') {this.grid.setCell(i,j, new Wall('X', i, j));}
        else if (content == 'o') {this.grid.setCell(i,j, 
        		new DustBall('o', i, j, Constants.DUST_BALL_SCORE));
        this.dirts.add((Dirt) this.grid.getCell(i,j));}
        else if (content == 'U') {this.grid.setCell(i,j, new Dumpster('U', i, j));
        this.dumpsters.add((Dumpster) this.grid.getCell(i,j));}
        else if (content == '.') {this.grid.setCell(i,j,
        		new Dirt('.', i, j, Constants.DIRT)); this.dirts.add((Dirt) this.grid.getCell(i,j));}
        else if (content == '1') {this.grid.setCell(i,j,
        		new Vacuum('1',i,j,Constants.CAPACITY));
        ((Vacuum) this.grid.getCell(i,j)).setUnder(new CleanHallway(' ', i,j));
        vacuum1 = (Vacuum) this.grid.getCell(i,j);}
        else if (content == '2') {this.grid.setCell(i,j,
        		new Vacuum('2',i,j,Constants.CAPACITY));
         ((Vacuum) this.grid.getCell(i,j)).setUnder(new CleanHallway(' ', i,j));
        vacuum2 = (Vacuum) this.grid.getCell(i,j);}
        else {this.grid.setCell(i,j, new CleanHallway(' ', i,j));}
        m = m + 1;    //k-i>0 means that it is not the last line.  //i++ to end the loop for when lastline
        if (m==l) {if (k-i>1) {m =0; line = sc.nextLine();} else{i++;}} //end of line.  get nextline
        }
        	
        	
        }
        
        
        
        
        
        sc.close();
    }


    /**
     * Returns the dimensions of the grid in the file named layoutFileName.
     * @param layoutFileName path of the input grid file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the grid that
     * corresponds to the given input grid file
     * @throws IOException
     */
    private int[] getDimensions(String layoutFileName) throws IOException {       

        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }
    /**Check to see if a certain cell row i column j has sprite dirt or hallway
     *
     * @param i
     * @param j
     * @return boolean 
     */
    private boolean is_dirt_or_hall(int i, int j)
    {if (this.grid.getCell(i,j) instanceof Dirt || this.grid.getCell(i,j) instanceof CleanHallway){return true;} 
    else {return false;}}
    
    
    /**Return a list of integer array where each array stores a row of a cell and a column cell.  This list contains all legal moves 
     * of DustBall db
     * 
     * @param DustBall db
     * @return List<int[]>
     */
    private List<int[]> DustBallLegalMoves(DustBall db) 
    {List<int[]> legal_moves = new ArrayList<int[]>(); int row = db.getRow(); int col = db.getColumn();
    int grid_col = this.grid.getNumColumns(); int grid_row = this.grid.getNumRows(); 
    if (grid_row==1 && grid_col==1){return legal_moves;} //no legal moves
    else if (grid_col==1){
    	if (row ==0){if (this.is_dirt_or_hall(row+1, col)) { int[] move ={row+1,col}; legal_moves.add(move);
    
                                                      //i can move up or down
    return legal_moves;} else {return legal_moves;}} 
    else if (row<grid_row-1) {if (this.is_dirt_or_hall(row+1, col)) { int[] move ={row+1,col}; legal_moves.add(move);} 
    if (this.is_dirt_or_hall(row-1, col)) { int[] move ={row-1,col}; legal_moves.add(move);} return legal_moves;}
    else {if (this.is_dirt_or_hall(row-1, col)) { int[] move ={row-1,col}; legal_moves.add(move);
    return legal_moves;} else {return legal_moves;}}}
    else if (grid_row==1){if (col ==0){if (this.is_dirt_or_hall(row, col+1)) { int[] move ={row,col+1}; legal_moves.add(move);   
    // this is when I can only move down. 
    return legal_moves;} else {return legal_moves;}} 
  //i can move left or right
    else if (col<grid_col-1) {if (this.is_dirt_or_hall(row, col+1)) { int[] move ={row,col+1}; legal_moves.add(move);} 
    if (this.is_dirt_or_hall(row, col-1)) { int[] move ={row,col-1}; legal_moves.add(move);} return legal_moves;}
    else {if (this.is_dirt_or_hall(row, col-1)) { int[] move ={row,col-1}; legal_moves.add(move);
    return legal_moves;} else {return legal_moves;}}}
    // this is when i can there is at least two rows and two columns
    else {return this.DustBallLegalMovesHelper(db,legal_moves,row,col,grid_col,grid_row);} }   
    
    /** A helper to DustBallLegalMoves.  Does exactly what DustBallLegalMoves does.  
     * 
     * @param db
     * @param legal_moves
     * @param row
     * @param col
     * @param grid_col
     * @param grid_row
     * @return List<int[]>
     */
    private List<int[]> DustBallLegalMovesHelper(DustBall db, List<int[]> legal_moves, int row, int col, int grid_col, int grid_row) {
    
    //the code checks for the possibilities of the dustball being at the four corners, being at the 4 sides, and somewhether inside the grid.  	
    if (row==0 && col==0) {if (this.is_dirt_or_hall(row, col+1)){int[] move ={row,col+1}; legal_moves.add(move);} 
    if (this.is_dirt_or_hall(row+1, col)){int[] move ={row+1,col}; legal_moves.add(move);} return legal_moves; }
    
    
    else if (row==0 && col==grid_col-1) {if (this.is_dirt_or_hall(row, col-1)){int[] move ={row,col-1}; legal_moves.add(move);}   
    if (this.is_dirt_or_hall(row+1, col)){int[] move ={row+1,col}; legal_moves.add(move);} return legal_moves;}
    else if (row==0) {if (this.is_dirt_or_hall(row, col+1)){int[] move ={row,col+1}; legal_moves.add(move);} 
    if (this.is_dirt_or_hall(row+1, col)){int[] move ={row+1,col}; legal_moves.add(move);} if 
    (this.is_dirt_or_hall(row, col-1)){int[] move ={row,col-1}; legal_moves.add(move);} return legal_moves;}   
    else if (col==0 && row==grid_row-1) {if (this.is_dirt_or_hall(row, col+1)){int[] move ={row,col+1}; legal_moves.add(move);} 
    if (this.is_dirt_or_hall(row-1, col)){int[] move ={row-1,col}; legal_moves.add(move);} return legal_moves;}
    else if (col==0) {if (this.is_dirt_or_hall(row, col+1)){int[] move ={row,col+1}; legal_moves.add(move);} 
    if (this.is_dirt_or_hall(row+1, col)){int[] move ={row+1,col}; legal_moves.add(move);} if 
    (this.is_dirt_or_hall(row-1, col)){int[] move ={row-1,col}; legal_moves.add(move);} return legal_moves;}
    else if (row==grid_row-1 && col==grid_col-1) {if (this.is_dirt_or_hall(row, col-1)){int[] move ={row,col-1}; legal_moves.add(move);} 
    if (this.is_dirt_or_hall(row-1, col)){int[] move ={row-1,col}; legal_moves.add(move);} return legal_moves;}   
    else if (row == grid_row-1) {if (this.is_dirt_or_hall(row, col+1)){int[] move ={row,col+1}; legal_moves.add(move);} 
    if (this.is_dirt_or_hall(row, col-1)){int[] move ={row,col-1}; legal_moves.add(move);} if 
    (this.is_dirt_or_hall(row-1, col)){int[] move ={row-1,col}; legal_moves.add(move);} return legal_moves;}
    else if (col == grid_col-1) {if (this.is_dirt_or_hall(row, col-1)){int[] move ={row,col-1}; legal_moves.add(move);} 
    if (this.is_dirt_or_hall(row+1, col)){int[] move ={row+1,col}; legal_moves.add(move);} if
    (this.is_dirt_or_hall(row-1, col)){int[] move ={row-1,col}; legal_moves.add(move);} return legal_moves;}
    else {if (this.is_dirt_or_hall(row, col+1)){int[] move ={row,col+1}; legal_moves.add(move);} if 
    	(this.is_dirt_or_hall(row, col-1)){int[] move ={row,col-1}; legal_moves.add(move);} 
    if (this.is_dirt_or_hall(row+1, col)){int[] move ={row+1,col}; legal_moves.add(move);} if 
    (this.is_dirt_or_hall(row-1, col)){int[] move ={row-1,col}; legal_moves.add(move);} return legal_moves;} 
    }
    /** Move all the DustBalls randomly
     * 
     */
    private void DustballMove() {if (this.grid.getNumRows()==1 && this.grid.getNumColumns()==1){return;} //can't move
    // the code also creates new dirt and add it to the dirt_list, which shares the same object as this.dirts. 
    // code checks to see if the dirt at a spot is already in the list before adding it.  
    List<Dirt> dirt_list = this.dirts; int dirt_list_size = this.dirts.size();  List<DustBall> db_list = 
    		new ArrayList<DustBall>();
    for (int i=0; i<dirt_list_size; i++) {if (dirt_list.get(i) instanceof DustBall) {db_list.add((DustBall) dirt_list.get(i));}}
    int db_list_size = db_list.size();   for (int i=0; i<db_list_size; i++) 
    {DustBall current_db =  db_list.get(i);
    List<int[]> current_db_legal_moves = DustBallLegalMoves(current_db); 
    int current_db_legal_moves_size = current_db_legal_moves.size();
    int ran_num = random.nextInt(current_db_legal_moves_size); int[] db_move = current_db_legal_moves.get(ran_num); 
    if (this.grid.getCell(db_move[0],db_move[1]) instanceof CleanHallway) {Dirt new_dirt = 
    		new Dirt('.', db_move[0],db_move[1],Constants.DIRT_SCORE); dirt_list.add( new_dirt);} 
    this.grid.setCell(db_move[0],db_move[1], current_db); Dirt new_dirt = 
    		new Dirt('.', current_db.getRow(),current_db.getColumn(),Constants.DIRT_SCORE); this.grid.setCell(current_db.getRow(),
    				current_db.getColumn(), new_dirt); 
    //contains uses the equals method of sprite to determine equality.  
    if (dirt_list.contains(new_dirt) == false) {dirt_list.add(new_dirt);} current_db.moveTo(db_move[0], db_move[1]); 
    
  
    }
    
    
    
    }
    /** check to see if a move nextMove for vacuum vac is legal, and if yes, then move vac accordingly.
     *  if no, then it does not move. 
     * @param vac
     * @param nextMove
     * @return boolean
     */
    private boolean vacuum_legal_move(Vacuum vac, char nextMove) {int vac_row = vac.getRow(); int vac_col = vac.getColumn();
    // the code also checks to see whats underneath the the vacuum and so as to leave it when the vac moves, and stores what is underneath
    // the vacuum after it moves.
    // this.dirts.remove uses equals method of sprite to determine equality. 
    if (nextMove =='w' || nextMove =='i'){Sprite up_sprite_tile = this.grid.getCell(vac_row-1,vac_col); if (up_sprite_tile instanceof Wall || 
    		up_sprite_tile instanceof Vacuum){return false;}
    else if (up_sprite_tile instanceof Dirt){Sprite under1 = vac.getUnder(); if (vac.clean(Constants.DIRT_SCORE)){this.dirts.remove(up_sprite_tile);
    this.grid.setCell(vac_row-1, vac_col, vac); vac.setUnder(new CleanHallway(' ',vac_row-1,vac_col));this.grid.setCell(vac_row, vac_col, under1);
    vac.moveTo(vac_row-1, vac_col);}
    else {this.grid.setCell(vac_row, vac_col, under1); this.grid.setCell(vac_row-1, vac_col, vac); vac.setUnder(up_sprite_tile); 
    vac.moveTo(vac_row-1, vac_col);} } else if (up_sprite_tile instanceof DustBall) {
    	Sprite under1 = vac.getUnder(); if (vac.clean(Constants.DUST_BALL_SCORE)){
    		this.dirts.remove(up_sprite_tile);this.grid.setCell(vac_row-1, vac_col, vac); vac.setUnder(new CleanHallway(' ',vac_row-1,vac_col));
    		this.grid.setCell(vac_row, vac_col, under1);vac.moveTo(vac_row-1, vac_col);}
        else {this.grid.setCell(vac_row, vac_col, under1); this.grid.setCell(vac_row-1, vac_col, vac); 
        vac.setUnder(up_sprite_tile); vac.moveTo(vac_row-1, vac_col);}
    } else if (up_sprite_tile instanceof CleanHallway) {Sprite under1 = vac.getUnder(); this.grid.setCell(vac_row, vac_col, under1); 
    this.grid.setCell(vac_row-1, vac_col, vac); vac.setUnder(up_sprite_tile); vac.moveTo(vac_row-1, vac_col);} 
    else{Sprite under1 = vac.getUnder(); this.grid.setCell(vac_row, vac_col, under1); this.grid.setCell(vac_row-1, vac_col, vac); 
    vac.setUnder(up_sprite_tile); vac.moveTo(vac_row-1, vac_col); vac.empty();} }
    
    else if (nextMove == 's' || nextMove =='k') {Sprite down_sprite_tile = this.grid.getCell(vac_row+1,vac_col); if (down_sprite_tile instanceof Wall || down_sprite_tile 
    		instanceof Vacuum){return false;}
    else if (down_sprite_tile instanceof Dirt){Sprite under1 = vac.getUnder(); if (vac.clean(Constants.DIRT_SCORE)){this.dirts.remove(down_sprite_tile);
    this.grid.setCell(vac_row+1,vac_col,vac); vac.setUnder(new CleanHallway(' ',vac_row+1,vac_col));this.grid.setCell(vac_row, vac_col, under1);vac.moveTo(vac_row+1, vac_col);}
    else {this.grid.setCell(vac_row, vac_col, under1); this.grid.setCell(vac_row+1,vac_col,vac); vac.setUnder(down_sprite_tile); vac.moveTo(vac_row+1, vac_col);} } 
    else if (down_sprite_tile instanceof DustBall) {
    	Sprite under1 = vac.getUnder(); if (vac.clean(Constants.DUST_BALL_SCORE)){this.dirts.remove(down_sprite_tile);this.grid.setCell(vac_row+1,vac_col,vac); 
    	vac.setUnder(new CleanHallway(' ',vac_row+1,vac_col));this.grid.setCell(vac_row, vac_col, under1);vac.moveTo(vac_row+1, vac_col);}
        else {this.grid.setCell(vac_row, vac_col, under1); this.grid.setCell(vac_row+1,vac_col,vac); vac.setUnder(down_sprite_tile); vac.moveTo(vac_row+1, vac_col);}
    } else if (down_sprite_tile instanceof CleanHallway) {Sprite under1 = vac.getUnder(); this.grid.setCell(vac_row, vac_col, under1); 
    this.grid.setCell(vac_row+1,vac_col,vac); vac.setUnder(down_sprite_tile); vac.moveTo(vac_row+1, vac_col);} else{Sprite under1 = vac.getUnder(); this.grid.setCell(vac_row, vac_col, under1); 
    this.grid.setCell(vac_row+1,vac_col,vac); vac.setUnder(down_sprite_tile); vac.moveTo(vac_row+1, vac_col); vac.empty();}}
    
    else if (nextMove == 'a' || nextMove =='j'){Sprite left_sprite_tile = this.grid.getCell(vac_row,vac_col-1); if (left_sprite_tile instanceof Wall || left_sprite_tile instanceof Vacuum){return false;}
    else if (left_sprite_tile instanceof Dirt){Sprite under1 = vac.getUnder(); if (vac.clean(Constants.DIRT_SCORE)){this.dirts.remove(left_sprite_tile);this.grid.setCell(vac_row, vac_col-1,vac); 
    vac.setUnder(new CleanHallway(' ',vac_row,vac_col-1));this.grid.setCell(vac_row, vac_col, under1);vac.moveTo(vac_row, vac_col-1);}
    else {this.grid.setCell(vac_row, vac_col, under1); this.grid.setCell(vac_row, vac_col-1,vac); vac.setUnder(left_sprite_tile); vac.moveTo(vac_row, vac_col-1);} } 
    else if (left_sprite_tile instanceof DustBall) {
    	Sprite under1 = vac.getUnder(); if (vac.clean(Constants.DUST_BALL_SCORE)){this.dirts.remove(left_sprite_tile);this.grid.setCell(vac_row, vac_col-1,vac); 
    	vac.setUnder(new CleanHallway(' ',vac_row,vac_col-1));this.grid.setCell(vac_row, vac_col, under1);vac.moveTo(vac_row, vac_col-1);}
        else {this.grid.setCell(vac_row, vac_col, under1); this.grid.setCell(vac_row, vac_col-1,vac); vac.setUnder(left_sprite_tile); vac.moveTo(vac_row, vac_col-1);}
    } else if (left_sprite_tile instanceof CleanHallway) {Sprite under1 = vac.getUnder(); this.grid.setCell(vac_row, vac_col, under1); 
    this.grid.setCell(vac_row, vac_col-1,vac); vac.setUnder(left_sprite_tile); vac.moveTo(vac_row, vac_col-1);} else{Sprite under1 = vac.getUnder(); this.grid.setCell(vac_row, vac_col, under1); 
    this.grid.setCell(vac_row, vac_col-1,vac); vac.setUnder(left_sprite_tile); vac.moveTo(vac_row, vac_col-1); vac.empty();}}
    
    else if (nextMove =='d' || nextMove =='l') {Sprite right_sprite_tile = this.grid.getCell(vac_row,vac_col+1); if (right_sprite_tile instanceof Wall || right_sprite_tile instanceof Vacuum){return false;}
    else if (right_sprite_tile instanceof Dirt){Sprite under1 = vac.getUnder(); if (vac.clean(Constants.DIRT_SCORE)){this.dirts.remove(right_sprite_tile);
    this.grid.setCell(vac_row,vac_col+1,vac); 
    vac.setUnder(new CleanHallway(' ',vac_row,vac_col+1));this.grid.setCell(vac_row,vac_col,under1);vac.moveTo(vac_row, vac_col+1);}
    else {this.grid.setCell(vac_row,vac_col,under1); this.grid.setCell(vac_row,vac_col+1,vac); vac.setUnder(right_sprite_tile); vac.moveTo(vac_row, vac_col+1);} } 
    else if (right_sprite_tile instanceof DustBall) {
    	Sprite under1 = vac.getUnder(); if (vac.clean(Constants.DUST_BALL_SCORE)){this.dirts.remove(right_sprite_tile);this.grid.setCell(vac_row,vac_col+1,vac); 
    	vac.setUnder(new CleanHallway(' ',vac_row,vac_col+1));this.grid.setCell(vac_row,vac_col,under1);vac.moveTo(vac_row, vac_col+1);}
        else {this.grid.setCell(vac_row,vac_col, under1); this.grid.setCell(vac_row,vac_col+1,vac); vac.setUnder(right_sprite_tile); vac.moveTo(vac_row, vac_col+1);}
    } else if (right_sprite_tile instanceof CleanHallway) {Sprite under1 = vac.getUnder(); this.grid.setCell(vac_row,vac_col,under1); 
    this.grid.setCell(vac_row,vac_col+1,vac); vac.setUnder(right_sprite_tile); vac.moveTo(vac_row, vac_col+1);} else{Sprite under1 = vac.getUnder(); 
    this.grid.setCell(vac_row,vac_col,under1); 
    this.grid.setCell(vac_row,vac_col+1,vac); vac.setUnder(right_sprite_tile); vac.moveTo(vac_row, vac_col+1); vac.empty();} }
    
   
    return true;} //only going to be called when the tile is in range of grid but perhaps the tile cannot be stepped on
    /**return grid of sprites of VacuumGame
     * 
     * @return Grid<Sprite>
     */
    public Grid<Sprite> getGrid() {return this.grid;} 
    /**return vacuum1
     * 
     * @return Vacuum
     */
    public Vacuum getVacuumOne() {return vacuum1;}
        
    
    
    /**
     * return vacuum2
     * @return Vacuum
     */
    public Vacuum getVacuumTwo() {return vacuum2;}
        
    
    
    /** return the number of rows of this game grid
     * 
     * @return int 
     */
    public int getNumRows() {return this.grid.getNumRows();}
    /**
     * return the number of columns of the game grid
     * @return int 
     */
    public int getNumColumns() {return this.grid.getNumColumns();}
    /**get the sprite living in the cell row i column j of grid
     * 
     * @param i
     * @param j
     * @return Sprite
     */
    public Sprite getSprite(int i, int j) {
    {return this.grid.getCell(i,j);}
    }
    /**return the ID of the winner of VacuumGame
     * 
     * @return int 
     */
    public int getWinner() {if (vacuum1.getScore() > vacuum2.getScore()){return 1;}
    else  {return 2;}}
    /** check to see if move nextMove is legal, and if so, move the respective vacuum with that move and move all the DustBalls.  If not, 
     * just move the DustBalls.  
     * 
     * @param nextMove
     * @return boolean
     */
    public boolean move(char nextMove) {if 
    	(Arrays.asList(vacuum1_moves).contains(Character.toString(nextMove))) {Vacuum vac_1 = this.getVacuumOne();  int vac_row = vac_1.getRow(); int grid_row = this.grid.getNumRows(); int grid_col = this.grid.getNumColumns();
    	// code check to see if this move is impossible based on where vacuum is.  
    	int vac_col = vac_1.getColumn(); if (nextMove == 'a'){if (vac_col == 0){return false;} else{boolean result = this.vacuum_legal_move(vac_1, nextMove); this.DustballMove(); return result;}} else if(nextMove=='d'){if(vac_col==grid_col-1){return false;} else{boolean result = this.vacuum_legal_move(vac_1, nextMove); this.DustballMove(); return result;}} else if(nextMove == 'w'){if (vac_row==0){return false;} else{boolean result = this.vacuum_legal_move(vac_1, nextMove); this.DustballMove(); return result;}} else{if (vac_row == grid_row-1){return false;} 
    	else{boolean result = this.vacuum_legal_move(vac_1, nextMove); this.DustballMove(); return result;}}}
    
    else if 
	(Arrays.asList(vacuum2_moves).contains(Character.toString(nextMove))) {Vacuum vac_2 = this.getVacuumTwo();  int vac_row = vac_2.getRow(); int grid_row = this.grid.getNumRows(); int grid_col = this.grid.getNumColumns();
	int vac_col = vac_2.getColumn(); if (nextMove == 'j'){if (vac_col == 0){return false;} else{boolean result = this.vacuum_legal_move(vac_2, nextMove); this.DustballMove(); return result;}} else if(nextMove=='l'){if(vac_col==grid_col-1){return false;} else{boolean result = this.vacuum_legal_move(vac_2, nextMove); this.DustballMove(); return result;}} else if(nextMove == 'i'){if (vac_row==0){return false;} else{boolean result = this.vacuum_legal_move(vac_2, nextMove); this.DustballMove(); return result;}} else{if (vac_row == grid_row-1){return false;} 
	else{boolean result = this.vacuum_legal_move(vac_2, nextMove); this.DustballMove(); return result;}}}  //vacuum_legal_move does all the work.  
    
    else {this.DustballMove(); return false; }
    }  
    
    /**check if game is over 
     * 
     * @return boolean
     */
    public boolean gameOver() {if (this.dirts.isEmpty()){return true;} else{return false;}}  
}
