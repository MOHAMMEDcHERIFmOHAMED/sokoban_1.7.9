import java.util.Scanner;

public class Game {
    private Board board;
    private Display display;

    public Game(Board board, Display display) {
        this.board = board;
        this.display = display;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (!board.isLevelComplete()) {
            display.render(board);
            System.out.println("Move (W/A/S/D): ");
            String input = scanner.nextLine().toUpperCase();
            int dx = 0, dy = 0;

            switch (input) {
                case "W": dy = -1; break;
                case "A": dx = -1; break;
                case "S": dy = 1; break;
                case "D": dx = 1; break;
                default: System.out.println("Invalid input"); continue;
            }

            board.movePlayer(dx, dy);
        }
        System.out.println("Level Complete!");
        scanner.close();
    }
}
