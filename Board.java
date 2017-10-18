import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//This class creates the board
//The board is internally represented like this:
//
//	-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
//	-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
//	-1 -1  0  0  0  0  0  0  0  0 -1 -1
//	-1 -1  0  0  0  0  0  0  0  0 -1 -1
//	-1 -1  0  0  0  0  0  0  0  0 -1 -1 
//	-1 -1  0  0  0  0  0  0  0  0 -1 -1
//	-1 -1  0  0  0  0  0  0  0  0 -1 -1 
//	-1 -1  0  0  0  0  0  0  0  0 -1 -1
//	-1 -1  0  0  0  0  0  0  0  0 -1 -1 
//	-1 -1  0  0  0  0  0  0  0  0 -1 -1
//	-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
//	-1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
//
//- Locations with -1 are not valid moves
//- Locations with 0 are valid moves
//- if a location has a number > 0, then the knight has visited it.


public class Board {
	public static int theBoard[][] = new int[100][100];
	public static String theDisplayBoard[][] = new String[100][100];
	public static int totalMoves = 0;
	public static int boardRows, boardCols;
	public static Image knightImage[];

	// constructor
	public Board() {
		int x;
		int y;

		boardRows = inputPanel.getBoardRows();
		boardCols = inputPanel.getBoardCols();
		System.out.println("Board: initialization creating board " + boardRows + " x " + boardCols);

		totalMoves = 1;// re initialize to 1 so when you rerun the program it
						// starts counting at 1
		knightImage = new Image[1];
		// create the boarders where you can't go (-1)
		for (x = 1; x <= boardRows + 4; x++)
			for (y = 1; y <= boardCols + 4; y++) {
				theBoard[x][y] = -1;
			}
		// fill the center of the board with 0s where you can go
		for (x = 3; x <= boardRows + 2; x++)
			for (y = 3; y <= boardCols + 2; y++) {
				theBoard[x][y] = 0;
			}
		displayStuff();
		try {
			// open image file
			System.out.println("Opening image file");
			knightImage[0] = ImageIO.read(new File("P:\\cs219\\TheKnightsTourGUI\\src\\Knight.jpg"));

		} catch (IOException e) {
			System.out.println("Can't open image.  ");
			e.printStackTrace();
		}
	}

	public boolean placeKnight(int row, int col) {
		// places a knight at this location
		// Returns FALSE if placement failed
		// Increments number of locations visited so far
		if (SpotisOpen(row, col) == true) {
			// System.out.println("Move " + totalMoves + " at (" + row + "," +
			// col + ")");
			theBoard[row + 2][col + 2] = totalMoves++;
			return true;
		} else {
			return false;
		}
	}

	public boolean SpotisOpen(int row, int col) {
		// returns true if the spot has not been visited and isn’t off the board
		if (theBoard[row + 2][col + 2] == 0)
			return true;
		else {
			// System.out.println("spot not open at" + row + " " + col);
			return false;
		}
	}

	// takes the board and converts the int values to string values so they can
	// be displayed
	public static void displayStuff() {
		System.out.println("in Display stuff " + totalMoves);
		for (int y = 3; y <= boardRows + 2; y++) {
			for (int x = 3; x <= boardCols + 2; x++) {
				if (theBoard[x][y] == 0) {
					theDisplayBoard[x][y] = "**";
				} else {
					theDisplayBoard[x][y] = "" + theBoard[x][y];// change ints
																// to strings so
																// you can
																// display it in
																// the graphics
																// window
				}
			}
		}
		inputPanel.redrawPanel();
	}
}