
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class inputPanel extends JPanel implements ActionListener {

	// UI declarations
	private JLabel gridSizeLabel, startLocationLabel, delayLabel;
	private JTextField gridSizeTextField, startLocationTextField, delayTextField;
	private JButton goButton;
	private JPanel InputPanel;

	// -----------------------------------------------------------------------

	public static JFrame f = new JFrame();

	public static int boardRows, boardCols;
	static int startRow, startCol;
	static int delay;

	static WarnsdorfsRule w;
	public static Timer timer;
	static Board b;

	Font plainFont = new Font("Serif", Font.BOLD, 12);

	public inputPanel() // Constructor Board(rows, cols)
	{
		// Initializes all necessary graphics

		System.out.println("Panel: initializing graphics");

		// gridSize
		gridSizeLabel = new JLabel("Grid Size");
		gridSizeTextField = new JTextField();

		// goButton
		goButton = new JButton("Go!");
		// takes current object and tells the action listener to call action
		// performed when go button is hit
		goButton.addActionListener(this);

		// startLocation
		startLocationLabel = new JLabel("Start Location");
		startLocationTextField = new JTextField();

		// delay
		delayLabel = new JLabel("Delay");
		delayTextField = new JTextField();

		// create a new panel
		InputPanel = new JPanel();

		// use the boxlayout for InputPanel
		InputPanel.setLayout(new BoxLayout(InputPanel, BoxLayout.X_AXIS));
		InputPanel.setPreferredSize(new Dimension(600, 40));

		// add the components to the InputPanel
		InputPanel.add(gridSizeLabel);
		InputPanel.add(gridSizeTextField);
		InputPanel.add(startLocationLabel);
		InputPanel.add(startLocationTextField);
		InputPanel.add(delayLabel);
		InputPanel.add(delayTextField);
		InputPanel.add(goButton);

		// set the grid layout for myself
		super.setLayout(new GridLayout(2, 1));

		// add the panels into the grid layout
		super.add(InputPanel);
		f.getContentPane().add(InputPanel);
		f.pack();
		f.setSize(20 * 35, 20 * 35);
		f.add(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setState(java.awt.Frame.NORMAL);
		f.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

		System.out.println("Panel: Done initializing graphics");
	}

	// This method is called under two conditions
	// 1- user presses "GO"
	// 2 - The timer goes off.
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == goButton) {
			// read from gridSizeTextField
			String gridSize = gridSizeTextField.getText();
			String result1[] = gridSize.split(" ");
			boardRows = Integer.parseInt(result1[0]);
			//limit board size to 30
			if(boardRows > 30){
				boardRows = 30;
				gridSizeTextField.setText("30");
			}
			boardCols = boardRows; // only square boards are allowed
			
			// read from startLocationTextField
			String startLocation = startLocationTextField.getText();
			String result2[] = startLocation.split(" ");
			startRow = Integer.parseInt(result2[0]);
			startCol = Integer.parseInt(result2[1]);
			//limit the start row and col to fit on the board
			if(startRow < 1){
				startRow = 1;
			}
			if(startCol < 1){
				startCol = 1;
			}
			if(startRow > boardRows){
				startRow = boardRows;
			}
			if(startCol > boardRows){
				startCol = boardRows;
			}
			startLocationTextField.setText(""+ startRow + " " + startCol);
			// read from delaytextFields
			String result3 = delayTextField.getText();
			delay = Integer.parseInt(result3);

			System.out.println(boardRows);
			System.out.println(boardCols);
			System.out.println(startRow);
			System.out.println(startCol);
			System.out.println(delay);
			// Starts tour
			TourProject.startTour();

		} else {
			// System.out.println("Timer Event."+ e.getSource());
			TourProject.MakeOneMove();// if it wasn't the go button then it's
										// the timer
		}
	}

	public int getStartRow() {
		return startRow;
	}

	public int getStartCol() {
		return startCol;
	}

	public static int getBoardRows() {
		return boardRows;
	}

	public static int getBoardCols() {
		return boardCols;
	}

	public int getDelay() {
		return delay;
	}

	public void paintComponent(Graphics g) {
		// System.out.println( "in paintComponent " + totalMoves);
		Font plainFont = new Font("Serif", Font.BOLD, 10);
		g.setFont(plainFont);
		//previousMove is the current number of knight moves
		//totalMoves is the count of the next move
		int testMove;
		int previousMove = Board.totalMoves - 1;

		for (int x = 3; x <= boardRows + 2; x++)// start 2 over because of border
		{
			for (int y = 3; y <= boardCols + 2; y++) {
				// g.clearRect(0, 0, 1000, 1000);
				if (Board.theDisplayBoard[x][y].equals("**"))
					g.setColor(Color.green);
				else if (Board.theDisplayBoard[x][y].equals("1"))// spot where it starts														
					g.setColor(Color.blue);
				else
					g.setColor(Color.red);
				//convert int to string and compares.If the board equals where the knight was last, draw knight
				if ((Board.theDisplayBoard[x][y].equals("" + previousMove)))
				{
					// System.out.println("Drawing knight" + Board.totalMoves +"
					// " + previousMove);
					g.drawImage(Board.knightImage[0], ((x - 2) * 30) - 10, ((y - 1) * 30) - 15, null);
				} else
					g.drawString(Board.theDisplayBoard[x][y], (x - 2) * 30, (y - 1) * 30);
				// -2 because the counter starts
				// at 3 to ignore the -1's but we are actually displaying the
				// first row or col
				if (!Board.theDisplayBoard[x][y].equals("**"))
				{
					testMove= Integer.parseInt(Board.theDisplayBoard[x][y]);
					testMove++;
					//if (testLoc == Board.totalMoves) 
						for (int x1 = 3; x1 <= boardRows + 2; x1++)
							for (int y1 = 3; y1 <= boardCols + 2; y1++) 
							{
								if (Board.theDisplayBoard[x1][y1].equals(""+ testMove))
								{
									g.drawLine  ((x - 2) * 30,
									(y - 1) * 30 ,
									(x1 - 2) * 30,
									(y1 - 1) * 30);

								}
							}
					}

			}
		}
		f.toFront();
	}

	// redraw method
	public static void redrawPanel() {
		f.repaint();
	}

	// test routine for printing board to console
	public void boardPrint(String s) {
		System.out.println(s);
		for (int x = 3; x <= boardRows + 2; x++) {
			System.out.println(" ");
			for (int y = 3; y <= boardCols + 2; y++) {
				System.out.print(Board.theBoard[x][y]);
			}

		}
		System.out.println(" ");
		System.out.println("----------------------------");
	}

}