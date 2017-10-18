import javax.swing.Timer;

public class MainTour {

	int currentRow = 0, currentCol = 0;
	Timer timer;
	public Board b;
	WarnsdorfsRule w;

	// This method gets called when the user had hit "GO"
	public void startTour(inputPanel p) {
		System.out.println("starting Tour....");
		b = new Board(); // create a board of the size specified by the user
		w = new WarnsdorfsRule(); // initialize the algorithm
		currentRow = p.getStartRow(); // get the user specified starting row
		currentCol = p.getStartCol(); // get the user specified starting col
		b.placeKnight(currentRow, currentCol); // Manually place the knight at
												// the the starting location.
		timer = new Timer(p.getDelay(), p); // The knight will move every timer tick 
											// Timer will call Make One Move()
		timer.start();
	}

	public void MakeOneMove(inputPanel p) { // called by timer
		// Check to see if a move is possible using Warnsdorfs rule
		// if a move is possible, place the knight there.
		if (w.GetNextMove(b, currentRow, currentCol) == true) {
			currentRow = w.GetNextRow();
			currentCol = w.GetNextColumn();
			b.placeKnight(currentRow, currentCol);
			b.displayStuff();
		} else {
			// Tour is at the last location. But GetNextMove will not allow the
			// knight to be placed there
			// because there are no more valid moves. So, we put the knight down
			// manually at the last location.
			currentRow = w.GetNextRow();
			currentCol = w.GetNextColumn();
			b.placeKnight(currentRow, currentCol);
			b.displayStuff();
			timer.stop();
		}
	}
}
