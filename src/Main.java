import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Define the grid
        char[][] grid = {
            {'#', '#', '#', '#','#','#','#', '#', '#'},
            {'#', '.', ' ', ' ',' ',' ',' ', '$', '#'},
            {'#', ' ', ' ', '.',' ',' ',' ', ' ', '#'},
            {'#', ' ', ' ', '.',' ',' ',' ', ' ', '#'},
            {'#', ' ', ' ', '.',' ',' ',' ', ' ', '#'},
            {'#', ' ', ' ', '.',' ',' ',' ', ' ', '#'},
            {'#', ' ', ' ', '.',' ',' ',' ', ' ', '#'},
            {'#', ' ', '@', ' ',' ',' ',' ', ' ', '#'},
            {'#', ' ', ' ', ' ',' ',' ',' ', '.', '#'},
            {'#', '#', '#', '#','#','#','#', '#', '#'}
        };

        // Define player starting position
        Position playerStart = new Position(3, 3);

        // Define storage locations
        List<Position> storage = Arrays.asList(
            new Position(1, 1) // Example storage position
        );

        // Create a level and board
        Level level = new Level(grid, playerStart, storage);
        Board board = level.createBoard();

        // Start the GUI
        new SokobanGame();
    }
}
