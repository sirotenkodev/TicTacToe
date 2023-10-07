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

    //Проверка по у
    public boolean checkY(GUESS player) {

        for (int i = 0; i < map.length; i++) {
            if(map[i][0] == player && map[i][1] == player && map[i][2] == player) {
                gameOver(player);
                return true;
            }
        }
        return false;
    }

    //Проверка по х
    public boolean checkX(GUESS player) {

        for (int i = 0; i < map.length; i++) {
            if(map[0][i] == player && map[1][i] == player && map[2][i] == player) {
                gameOver(player);
                return true;
            }
        }
        return false;
    }

    //Диагональ
    public boolean checkD(GUESS player) {
        for (int i = 0; i < map.length; i++) {
            if( map[0][0] == player && map[1][1] == player && map[2][2] == player ||
                    map[2][0] == player && map[1][1] == player && map[0][2] == player ) {
                gameOver(player);
                return true;
            }
        }

        return false;
    }

    public void gameOver(GUESS player) {
        System.out.println("Игра окончена");
        System.out.println("Выиграл игрок " + player);
    }

    public void startGame() {
        while(!isGameOver) {
            printField();
            playerGuess();
            isGameOver = checkD(GUESS.X) || checkX(GUESS.X) || checkY(GUESS.X);
            if(isGameOver) {
                break;
            }
            aiGuess();
            isGameOver = checkD(GUESS.Y) || checkX(GUESS.Y) || checkY(GUESS.Y);
            if(isGameOver) {
                break;
            }
        }
    }
}
