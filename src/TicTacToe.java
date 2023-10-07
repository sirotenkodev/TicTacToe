import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private boolean isGameOver = false;
    private enum GUESS {EMPTY, X, Y};
    private GUESS map[][] = {
            {GUESS.EMPTY, GUESS.EMPTY, GUESS.EMPTY},
            {GUESS.EMPTY, GUESS.EMPTY, GUESS.EMPTY},
            {GUESS.EMPTY, GUESS.EMPTY, GUESS.EMPTY}
    };

    public TicTacToe() {
    }

    private char getFieldChar(GUESS field) {
        switch (field) {
            case EMPTY :
                return ' ';
            case X:
                return 'X';
            case Y:
                return 'Y';
        }

        return ' ';
    }
    private void printField() {
        for(int y = 0 ; y < 3; ++y) {
            for(int x = 0 ; x < 3; ++x) {
                if(x != 2) {
                    System.out.print(getFieldChar(map[y][x]) + "|");
                } else {
                    System.out.print(getFieldChar(map[y][x]));
                }
            }

            if(y != 2) {
                System.out.println();
                System.out.println("-----");
            } else {
                System.out.println();
            }

        }
    }

    private boolean checkGuess(GUESS guess) {
        if(guess == GUESS.X|| guess == GUESS.Y) {
            return false;
        }

        return true;
    }

    private void playerGuess() {
        System.out.println("\nВведите клетку для игрока О: ");
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите х 0-2: ");
        int x = sc.nextInt();
        System.out.println("Введите y 0-2: ");
        int y = sc.nextInt();

        if(checkGuess(map[y][x])) {
            map[y][x] = GUESS.X;
        } else {
            playerGuess();
        }
    }

    private void aiGuess() {
        Random rand = new Random();
        int x = rand.nextInt(3);
        int y = rand.nextInt(3);

        if(checkGuess(map[y][x])) {
            map[y][x] = GUESS.Y;
        } else {
            aiGuess();
        }
    }

    public void startGame() {
        while(!isGameOver) {
            printField();
            playerGuess();
            aiGuess();
        }
    }
}
