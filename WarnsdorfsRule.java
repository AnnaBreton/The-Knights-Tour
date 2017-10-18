
public class WarnsdorfsRule {
	private int nextRow;
	private int nextCol;

	// constructor
	public WarnsdorfsRule() {

	}

	// method
	public boolean GetNextMove(Board b, int CurrentRow, int CurrentCol) {
		// -We are at (CurrentRow, CurrentCol) and we are looking for the next
		// location from which there are the fewest moves.
		// -Uses the private lookAhead method to find out what the next move is
		// -Returns true if there is a valid move
		// -Returns false if the game is over

		nextRow = CurrentRow;
		nextCol = CurrentCol;
		int[] LookAheadSpots = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		// b.boardPrint("in GetNextMove" + CurrentRow +" " + CurrentCol);

		if (b.SpotisOpen(CurrentRow - 1, CurrentCol - 2))// 1
		{
			nextRow = CurrentRow - 1;// set up nextRow and nextCol to ignore
										// LookAhead on the last move
			nextCol = CurrentCol - 2;
			LookAheadSpots[1] = LookAhead(b, CurrentRow - 1, CurrentCol - 2);
		}

		if (b.SpotisOpen(CurrentRow + 1, CurrentCol - 2))// 2
		{
			nextRow = CurrentRow + 1;
			nextCol = CurrentCol - 2;
			LookAheadSpots[2] = LookAhead(b, CurrentRow + 1, CurrentCol - 2);
		}

		if (b.SpotisOpen(CurrentRow + 2, CurrentCol - 1))// 3
		{
			nextRow = CurrentRow + 2;
			nextCol = CurrentCol - 1;
			LookAheadSpots[3] = LookAhead(b, CurrentRow + 2, CurrentCol - 1);
		}

		if (b.SpotisOpen(CurrentRow + 2, CurrentCol + 1))// 4
		{
			nextRow = CurrentRow + 2;
			nextCol = CurrentCol + 1;
			LookAheadSpots[4] = LookAhead(b, CurrentRow + 2, CurrentCol + 1);
		}

		if (b.SpotisOpen(CurrentRow + 1, CurrentCol + 2))// 5
		{
			nextRow = CurrentRow + 1;
			nextCol = CurrentCol + 2;
			LookAheadSpots[5] = LookAhead(b, CurrentRow + 1, CurrentCol + 2);
		}

		if (b.SpotisOpen(CurrentRow - 1, CurrentCol + 2))// 6
		{
			nextRow = CurrentRow - 1;
			nextCol = CurrentCol + 2;
			LookAheadSpots[6] = LookAhead(b, CurrentRow - 1, CurrentCol + 2);
		}

		if (b.SpotisOpen(CurrentRow - 2, CurrentCol + 1))// 7
		{
			nextRow = CurrentRow - 2;
			nextCol = CurrentCol + 1;
			LookAheadSpots[7] = LookAhead(b, CurrentRow - 2, CurrentCol + 1);
		}

		if (b.SpotisOpen(CurrentRow - 2, CurrentCol - 1))// 8
		{
			nextRow = CurrentRow - 2;
			nextCol = CurrentCol - 1;
			LookAheadSpots[8] = LookAhead(b, CurrentRow - 2, CurrentCol - 1);
		}

		// System.out.println("LookAhead returned");
		// for (int x = 1; x <= 8; x++)
		// {
		// System.out.print(LookAheadSpots[x]);// prints lookAhead
		// }
		// System.out.println(" ");
		int sumLookAheadSpots = 0;
		// see if there are any possible moves ahead
		// loop through the array of all 8 lookAhead positions and
		// see if there's at least one possible position
		for (int x = 1; x <= 8; x++) {
			sumLookAheadSpots = sumLookAheadSpots + LookAheadSpots[x];
		}
		if (sumLookAheadSpots == 0) {
			return false; // there are no valid moves
		}

		// find which index has the smallest number
		int smallestLookAheadIndex = 0;
		int smallestNumber = 8;

		for (int x = 1; x <= 8; x++) {
			// store the smallest non 0 number
			// loop through all 8 possible positions to find the smallest number
			if (LookAheadSpots[x] < smallestNumber && LookAheadSpots[x] > 0) {
				smallestLookAheadIndex = x;
				smallestNumber = LookAheadSpots[x];
			}
		}
		// System.out.println("Smallest is at index" + smallestLookAheadIndex);

		// ----------------------------------------------------------------------------------
		// set smallest number of look ahead positions to the case
		switch (smallestLookAheadIndex) {
		case 1:
			nextRow = CurrentRow - 1;
			nextCol = CurrentCol - 2;
			break;
		case 2:
			nextRow = CurrentRow + 1;
			nextCol = CurrentCol - 2;
			break;
		case 3:
			nextRow = CurrentRow + 2;
			nextCol = CurrentCol - 1;
			break;
		case 4:
			nextRow = CurrentRow + 2;
			nextCol = CurrentCol + 1;
			break;
		case 5:
			nextRow = CurrentRow + 1;
			nextCol = CurrentCol + 2;
			break;
		case 6:
			nextRow = CurrentRow - 1;
			nextCol = CurrentCol + 2;
			break;
		case 7:
			nextRow = CurrentRow - 2;
			nextCol = CurrentCol + 1;
			break;
		case 8:
			nextRow = CurrentRow - 2;
			nextCol = CurrentCol - 1;
			break;
		}

		return true;

	}

	private int LookAhead(Board b, int testRow, int testCol) {
		int numberOfOpenSpots = 0;
		// From each of 8 potential positions that the knight can move to,
		// figure out how many of the 8 possible moves are valid from there
		if (b.SpotisOpen(testRow - 1, testCol - 2))// 1
		{
			numberOfOpenSpots++;
		}

		if (b.SpotisOpen(testRow + 1, testCol - 2))// 2
		{
			numberOfOpenSpots++;
		}

		if (b.SpotisOpen(testRow + 2, testCol - 1))// 3
		{
			numberOfOpenSpots++;
		}

		if (b.SpotisOpen(testRow + 2, testCol + 1))// 4
		{
			numberOfOpenSpots++;
		}

		if (b.SpotisOpen(testRow + 1, testCol + 2))// 5
		{
			numberOfOpenSpots++;
		}

		if (b.SpotisOpen(testRow - 1, testCol + 2))// 6
		{
			numberOfOpenSpots++;
		}

		if (b.SpotisOpen(testRow - 2, testCol + 1))// 7
		{
			numberOfOpenSpots++;
		}

		if (b.SpotisOpen(testRow - 2, testCol - 1))// 8
		{
			numberOfOpenSpots++;
		}

		return numberOfOpenSpots;
	}

	public int GetNextRow() {
		// if GetNextMove returns true, this will give you the next row you can
		// move to

		return nextRow;
	}

	public int GetNextColumn() {
		// if GetNextMove returns true, this will give you the next column you
		// can move to

		return nextCol;
	}

}
