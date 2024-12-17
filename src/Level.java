import java.util.ArrayList;
import java.util.List;

public class Level {
    private Grid grid;
    private Player player;
    private List<Crate> crates;
    private List<Position> storageLocations;

    public Level(char[][] initialGrid, Position playerStart, List<Position> storage) {
        this.grid = new Grid(initialGrid);
        this.player = new Player(playerStart);
        this.crates = new ArrayList<>();
        this.storageLocations = storage;

        // Initialize crates based on grid
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                if (grid.getCell(row, col) == '$') {
                    crates.add(new Crate(new Position(col, row)));
                }
            }
        }
    }

    public Board createBoard() {
        return new Board(grid, player, crates, storageLocations);
    }
}
