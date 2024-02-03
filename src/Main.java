
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final int WINNING_POSITION = 100;
    private static final int DIE_SIDES = 6;

    public static void main(String[] args) {
        Main m1 = new Main();

        Player player1 = new Player(1);
        Player player2 = new Player(2);
        GameBoard g = new GameBoard();

        System.out.println("Press Enter to roll the die and start the game...");
        sc.nextLine();

        // Ensure both players roll a 6 to start the game
        while (m1.dieRoll() != 6 || m1.dieRoll() != 6) {
            System.out.println("Both players need to roll a 6 to start the game. Press Enter to roll again...");
            sc.nextLine();
        }

        System.out.println("Game started!");
        player1.currentPosition = 1;
        player2.currentPosition = 1;

        int diceRollCountPlayer1 = 0;
        int diceRollCountPlayer2 = 0;

        while (player1.currentPosition < WINNING_POSITION && player2.currentPosition < WINNING_POSITION) {
            diceRollCountPlayer1 += m1.playTurn(player1, g);
            diceRollCountPlayer2 += m1.playTurn(player2, g);
        }

        System.out.println("Game Over!");
        System.out.println("Number of times Player 1 rolled the dice: " + diceRollCountPlayer1);
        System.out.println("Number of times Player 2 rolled the dice: " + diceRollCountPlayer2);

        if (player1.currentPosition == WINNING_POSITION) {
            System.out.println("Player 1 won!");
        } else {
            System.out.println("Player 2 won!");
        }
    }

    private int playTurn(Player player, GameBoard gameBoard) {
        System.out.println("\nPlayer " + player.playerNumber + ", press Enter to roll the die...");
        sc.nextLine();

        int rollResult = dieRoll();
        int diceRollCount = 1; // Count the first roll

        while (true) {
            int updatePosition = player.currentPosition + rollResult;

            if (gameBoard.ladders.contains(updatePosition)) {
                // If the player lands on a ladder, they get another turn
                System.out.println("Player " + player.playerNumber + " got a ladder! They get another turn.");
                System.out.println("Player " + player.playerNumber + " - Position after dice roll: " + player.currentPosition + " DiceValue: " + player.diceValue);
                System.out.println("Press Enter to roll the die again...");
                sc.nextLine();
                rollResult = dieRoll();
                diceRollCount++;
            } else {
                if (gameBoard.snakes.contains(updatePosition)) {
                    int index = gameBoard.snakes.indexOf(updatePosition);
                    player.currentPosition = gameBoard.snakesBites.get(index);
                } else {
                    // Ensure the player gets to the exact winning position (100)
                    if (updatePosition <= WINNING_POSITION) {
                        player.currentPosition = updatePosition;
                    }
                    // If the player's position goes above 100, stay in the previous position
                    // until the player gets the exact number that adds to 100
                }

                System.out.println("Player " + player.playerNumber + " - Position after dice roll: " + player.currentPosition + " DiceValue: " + player.diceValue);
                break;
            }
        }

        return diceRollCount;
    }

    private int dieRoll() {
        return (int) (Math.random() * DIE_SIDES) + 1;
    }
}
