public class Grid {
    private char[][] grid;

    public Grid(char[][] initialGrid) {
        this.grid = initialGrid;
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }

    public void setCell(int row, int col, char value) {
        grid[row][col] = value;
    }

    public int getRows() {
        return grid.length;
    }

    public int getCols() {
        return grid[0].length;
    }

    public char[][] getGrid() {
        return grid;
    }

    public boolean isWithinBounds(int row, int col) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length;
    }
    

}
