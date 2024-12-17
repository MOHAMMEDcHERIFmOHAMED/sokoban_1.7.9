import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SokobanGame {
    private final int rows = 5;
    private final int cols = 5;
    private final JButton[][] cells = new JButton[rows][cols];
    private final char[][] grid = {
            {'#', '#', '#', '#','#','#','#','#', '#'},
            {'#', '.', '$', '@',' ',' ',' ',' ', '#'},
            {'#', ' ', ' ', ' ',' ',' ',' ',' ', '#'},
            {'#', ' ', '$', ' ',' ',' ',' ',' ', '#'}, 
            {'#', ' ', ' ', ' ',' ',' ',' ',' ', '#'}, 
            {'#', ' ', ' ', ' ',' ',' ',' ',' ', '#'},
            {'#', ' ', ' ', ' ',' ',' ',' ',' ', '#'},
            {'#', ' ', ' ', ' ',' ',' ',' ',' ', '#'},
            {'#', ' ', ' ', ' ',' ',' ',' ',' ', '#'},
            {'#', '#', '#', '#','#','#','#','#', '#'}
    };

    private int playerRow = 1;
    private int playerCol = 3;

    private final ImageIcon wallIcon = new ImageIcon("imgs/wall.png");
    private final ImageIcon storageIcon = new ImageIcon("imgs/point.jpg");
    private final ImageIcon playerIcon = new ImageIcon("imgs/char.png");
    private final ImageIcon crateIcon = new ImageIcon("imgs/crate.png");
    private final ImageIcon emptyIcon = new ImageIcon("imgs/empty.png");

    public SokobanGame() {
        JFrame frame = new JFrame("Sokoban Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(rows, cols));

        // Initialize the grid with buttons
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                cells[row][col] = new JButton();
                updateCell(row, col);
                cells[row][col].setFocusable(false);
                frame.add(cells[row][col]);
            }
        }

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:  // Move up
                        movePlayer(-1, 0);
                        break;
                    case KeyEvent.VK_DOWN:  // Move down
                        movePlayer(1, 0);
                        break;
                    case KeyEvent.VK_LEFT:  // Move left
                        movePlayer(0, -1);
                        break;
                    case KeyEvent.VK_RIGHT:  // Move right
                        movePlayer(0, 1);
                        break;
                    default:
                        // Optional: Handle any other keys if needed
                        break;
                }
                
            }
        });

        frame.setVisible(true);
    }

    private void updateCell(int row, int col) {
        char cell = grid[row][col];  // Get the character from the grid that represents this cell
        switch (cell) {
            case '#':  // Wall
                cells[row][col].setIcon(wallIcon);
                cells[row][col].setBackground(Color.DARK_GRAY);
                break;
            case '.':  // Storage
                cells[row][col].setIcon(storageIcon);
                cells[row][col].setBackground(Color.YELLOW);
                break;
            case '@':  // Player
                cells[row][col].setIcon(playerIcon);
                cells[row][col].setBackground(Color.BLUE);
                break;
            case '$':  // Crate
                cells[row][col].setIcon(crateIcon);
                cells[row][col].setBackground(Color.GREEN);
                break;
            case ' ':  // Empty
                cells[row][col].setIcon(emptyIcon);
                cells[row][col].setBackground(Color.WHITE);
                break;
            default:
                // Optional: Handle any unexpected characters (if needed)
                break;
        }
    }
    

    private void movePlayer(int dRow, int dCol) {
        int newRow = playerRow + dRow;
        int newCol = playerCol + dCol;

        // Check if the player moves into a valid position
        if (grid[newRow][newCol] == ' ' || grid[newRow][newCol] == '.') {
            grid[playerRow][playerCol] = ' ';
            playerRow = newRow;
            playerCol = newCol;
            grid[playerRow][playerCol] = '@';
            updateCell(playerRow, playerCol);
        } else if (grid[newRow][newCol] == '$') {
            // If the player moves into a crate, check if the space next to it is empty
            int nextRow = newRow + dRow;
            int nextCol = newCol + dCol;
            if (grid[nextRow][nextCol] == ' ') {
                grid[newRow][newCol] = ' ';
                grid[nextRow][nextCol] = '$';
                grid[playerRow][playerCol] = ' ';
                playerRow = newRow;
                playerCol = newCol;
                grid[playerRow][playerCol] = '@';
                updateCell(playerRow, playerCol);
            }
        }

        // Update the grid after moving the player
        updateGrid();
    }

    private void updateGrid() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                updateCell(row, col);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SokobanGame::new);
    }
}
