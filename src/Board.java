import java.util.List;

public class Board {
    private Grid grid; // Represents the map
    private Player player;
    private List<Crate> crates;
    private List<Position> storageLocations;

    public Board(Grid grid, Player player, List<Crate> crates, List<Position> storageLocations) {
        this.grid = grid;
        this.player = player;
        this.crates = crates;
        this.storageLocations = storageLocations;
    }

    public boolean isWall(Position pos) {
        return grid.getCell(pos.getY(), pos.getX()) == '#';
    }

    public boolean isCrate(Position pos) {
        return crates.stream().anyMatch(crate -> crate.getPosition().equals(pos));
    }

    public boolean isStorage(Position pos) {
        return storageLocations.contains(pos);
    }

    public Crate getCrateAt(Position pos) {
        return crates.stream().filter(crate -> crate.getPosition().equals(pos)).findFirst().orElse(null);
    }

    public boolean movePlayer(int dx, int dy) {
        Position currentPos = player.getPosition();
        Position newPos = currentPos.move(dx, dy);

        if (!grid.isWithinBounds(newPos.getY(), newPos.getX())) return false; // Out of bounds
        if (isWall(newPos)) return false; // Can't move into a wall

        if (isCrate(newPos)) {
            // Attempt to push the crate
            Position crateNewPos = newPos.move(dx, dy);
            if (!grid.isWithinBounds(crateNewPos.getY(), crateNewPos.getX()) ||
                isWall(crateNewPos) || isCrate(crateNewPos)) {
                return false; // Crate is blocked
            }
            Crate crate = getCrateAt(newPos);
            crate.setPosition(crateNewPos);
            grid.setCell(crateNewPos.getY(), crateNewPos.getX(), '$');
            grid.setCell(newPos.getY(), newPos.getX(), ' ');
        }

        // Move the player
        grid.setCell(currentPos.getY(), currentPos.getX(), ' ');
        player.setPosition(newPos);
        grid.setCell(newPos.getY(), newPos.getX(), '@');
        return true;
    }

    public boolean isLevelComplete() {
        for (Crate crate : crates) {
            if (!isStorage(crate.getPosition())) {
                return false;
            }
        }
        return true;
    }

    public Grid getGrid() {
        return grid;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Crate> getCrates() {
        return crates;
    }

    public List<Position> getStorageLocations() {
        return storageLocations;
    }
    
}
