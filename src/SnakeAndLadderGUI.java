import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeAndLadderGUI {
    private JFrame frame;
    private JPanel boardPanel;
    private JLabel player1Label, player2Label;
    private JButton rollDiceButton;
    private JTextArea messageArea;
    private SnakeAndLadder game;
    private Player player1, player2;
    private JLabel[][] boardLabels;

    // Constructor to initialize the game and set up the GUI
    public SnakeAndLadderGUI() {
        game = new SnakeAndLadder(100); // Creates a new SnakeAndLadder game with 100 squares
        game.initiateGame(); // Initializes the game, setting up snakes, ladders, and other necessary settings
        setupPlayers(); // Prompts the user to enter player names and sets up the players
        setupGUI(); // Sets up the graphical user interface
    }

    // Method to set up players by asking for their names
    private void setupPlayers() {
        String firstPlayer = JOptionPane.showInputDialog("Enter first player name:"); // Prompt for first player's name
        String secondPlayer = JOptionPane.showInputDialog("Enter second player name:"); // Prompt for second player's name

        player1 = new Player(firstPlayer); // Creates a new Player object for the first player
        player2 = new Player(secondPlayer); // Creates a new Player object for the second player

        game.addPlayer(player1); // Adds the first player to the game
        game.addPlayer(player2); // Adds the second player to the game
    }

    // Method to set up the graphical user interface
    private void setupGUI() {
        frame = new JFrame("Snake and Ladder Game"); // Creates the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets default close operation
        frame.setSize(800, 600); // Sets the size of the frame
        frame.setLayout(new BorderLayout()); // Uses BorderLayout for the frame

        // Board Panel setup
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(10, 10)); // Sets a 10x10 grid layout for the board
        boardPanel.setPreferredSize(new Dimension(500, 500)); // Sets preferred size for the board panel
        boardLabels = new JLabel[10][10]; // Creates a 2D array for the labels on the board

        // Loop to set up each label on the board in a snaking pattern
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                int num;
                if (row % 2 == 0) {
                    num = (9 - row) * 10 + col + 1; // For even rows, numbers increase left to right
                } else {
                    num = (9 - row) * 10 + (9 - col) + 1; // For odd rows, numbers decrease right to left
                }
                boardLabels[row][col] = new JLabel(String.valueOf(num), SwingConstants.CENTER); // Creates a label with the number
                boardLabels[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Adds a border to the label
                boardPanel.add(boardLabels[row][col]); // Adds the label to the board panel
            }
        }

        frame.add(boardPanel, BorderLayout.CENTER); // Adds the board panel to the center of the frame

        // Control Panel setup
        player1Label = new JLabel(player1.getUserName() + ": " + player1.getPosition()); // Label to show player 1's name and position
        player2Label = new JLabel(player2.getUserName() + ": " + player2.getPosition()); // Label to show player 2's name and position

        rollDiceButton = new JButton("Roll Dice"); // Button to roll the dice
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice(); // Calls the rollDice method when the button is clicked
            }
        });

        // Arranging control panel components
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS)); // Sets a vertical box layout
        controlPanel.add(player1Label); // Adds player 1's label to the control panel
        controlPanel.add(player2Label); // Adds player 2's label to the control panel
        controlPanel.add(rollDiceButton); // Adds the roll dice button to the control panel
        frame.add(controlPanel, BorderLayout.EAST); // Adds the control panel to the right side of the frame

        // Message Area setup
        messageArea = new JTextArea();
        messageArea.setEditable(false); // Makes the text area non-editable
        JScrollPane scrollPane = new JScrollPane(messageArea); // Adds a scroll pane to the text area
        scrollPane.setPreferredSize(new Dimension(800, 100)); // Sets preferred size for the scroll pane
        frame.add(scrollPane, BorderLayout.SOUTH); // Adds the scroll pane to the bottom of the frame

        frame.setVisible(true); // Makes the frame visible

        // Initial Player Positions
        updatePlayerPositions(); // Updates the initial positions of the players on the board
    }

    // Method to handle rolling the dice
    private void rollDice() {
        int turn = game.getTurn(); // Gets the current player's turn
        game.setStatus(1); // Sets the game status to indicate an ongoing game
        game.setPlayerInTurn(turn); // Sets the player who is currently in turn

        Player currentPlayer = game.getPlayers().get(turn); // Gets the current player
        messageArea.append("Player in turn is " + currentPlayer.getUserName() + "\n"); // Updates the message area with the current player's turn

        int diceRoll = currentPlayer.rollDice(); // Rolls the dice for the current player
        messageArea.append(currentPlayer.getUserName() + " rolled a " + diceRoll + "\n"); // Updates the message area with the dice roll

        game.movePlayer(currentPlayer, diceRoll); // Moves the player based on the dice roll
        updatePlayerPositions(); // Updates the player positions on the board

        if (game.getStatus() == 2) { // Checks if the game has ended
            rollDiceButton.setEnabled(false); // Disables the roll dice button
            messageArea.append("The winner is: " + currentPlayer.getUserName() + "\n"); // Updates the message area with the winner
        }
    }

    // Method to update player positions on the board
    private void updatePlayerPositions() {
        // Reset all labels to their number
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                int num;
                if (row % 2 == 0) {
                    num = (9 - row) * 10 + col + 1; // Numbers for even rows
                } else {
                    num = (9 - row) * 10 + (9 - col) + 1; // Numbers for odd rows
                }
                boardLabels[row][col].setText(String.valueOf(num)); // Resets each label to its original number
            }
        }

        // Update player positions on the board
        setPlayerPosition(player1, "A"); // Sets player 1's position
        setPlayerPosition(player2, "B"); // Sets player 2's position

        player1Label.setText(player1.getUserName() + ": " + player1.getPosition()); // Updates player 1's label with the current position
        player2Label.setText(player2.getUserName() + ": " + player2.getPosition()); // Updates player 2's label with the current position
    }

    // Method to set a player's position on the board
    private void setPlayerPosition(Player player, String label) {
        int pos = player.getPosition();
        int row = 9 - (pos - 1) / 10;
        int col;
        if (row % 2 == 0) {
            col = (pos - 1) % 10;
        } else {
            col = 9 - (pos - 1) % 10;
        }
        if (!boardLabels[row][col].getText().equals("A") && !boardLabels[row][col].getText().equals("B")) {
            boardLabels[row][col].setText(label); // Sets the label to "A" or "B" if it's not already set
        } else {
            boardLabels[row][col].setText(boardLabels[row][col].getText() + label); // Appends the label if there is already another player there ensures both players'
            // positions are represented on the board without overwriting the existing player's identifier the label's text will be updated to "AB"
        }
    }
}
