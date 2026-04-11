import java.util.*;
public class Sudoku {
    public static Scanner sc = new Scanner(System.in);
    private Board finalBoard;
    private Board gameBoard;
    private Board playerBoard;
    public Sudoku() {
        
        finalBoard = new Board();
        gameBoard = new Board();
        playerBoard = new Board();
        
        floodDiagonal();
        fillBoard(0, 3);
        
        gameBoard.copy(finalBoard);
    }
    
    public void play(int diff) {
        generateGame(diff);
        playerBoard.copy(gameBoard);
        printGame();
        int row, col, guess;
        do {
            System.out.print("Row: ");
            row = sc.nextInt()-1;
            if (row == -1) {
                System.out.println("You gave up, but that's okay!");
                printFinal();
                return;
            }
            System.out.print("Col: ");
            col = sc.nextInt()-1;
            System.out.print("Guess: ");
            guess = sc.nextInt();
            if (goodGuess(row, col, guess))
                printPlayer();
        } while (!isSolved());
        System.out.println("You did it!");
    }
    
    public void generateGame(int diff) {
        int numSpaces = 40;
        switch(diff) {
            case 1:
                numSpaces = (int)(Math.random()*6+40);
                break;
            case 2:
                numSpaces = (int)(Math.random()*5+46);
                break;
            case 3:
                numSpaces = (int)(Math.random()*6+50);
                break;
        }
        empty(gameBoard, numSpaces);
    }
    
    public boolean empty(Board gameBoard, int numSpaces) {
        if (numSpaces == 0)
            return true;
        int row, col;
        do {
            row = (int)(Math.random()*9);
            col = (int)(Math.random()*9);
        } while (gameBoard.getCell(row, col) == 0);
        int cell = gameBoard.getCell(row, col);
        gameBoard.setCell(row, col, 0);
        if (empty(gameBoard, numSpaces-1))
            return true;
        gameBoard.setCell(row, col, cell);
        return false;
    }
    
    public boolean goodGuess(int row, int col, int guess) {
        if (gameBoard.getCell(row, col) != 0) {
            System.out.println("You can't fill in a pre-filled cell!");
            System.out.println();
            return false;
        }
        playerBoard.setCell(row, col, guess);
        return true;
    }
    
    public boolean isSolved() {
        return playerBoard.equalTo(finalBoard);
    }
    public boolean fillBoard(int row, int col) {
        if (row == 9)
            return true;
        if (col == 9)
            return fillBoard(row + 1, 0);
        if (finalBoard.getCell(row, col) != 0)
            return fillBoard(row, col+1);
        for (int i = 1; i <= 9; i ++) {
            if (isSafe(finalBoard, row, col, i)) {
                finalBoard.setCell(row, col, i);
                if (fillBoard(row, col+1))
                    return true;
                finalBoard.setCell(row, col, 0);
            }
        }
        return false;
    }
    
    public void floodDiagonal() {
        for (int num = 0; num < 9; num += 3) {
            for (int row = num; row < num + 3; row ++) {
                for (int col = num; col < num + 3; col ++) {
                    int ran;
                    do {
                        ran = (int) (Math.random()*9+1);
                    } while (!isSafe(finalBoard, row, col, ran));
                    finalBoard.setCell(row, col, ran);
                }
            }
        }
    }
    
    public boolean isSafe(Board finalBoard, int row, int col, int ran) {
        return !finalBoard.inRow(row, ran) && 
               !finalBoard.inCol(col, ran) && 
               !finalBoard.inSquare(row, col, ran);
    }
    
    public void printFinal() {
        finalBoard.print();
    }
    
    public void printGame() {
        gameBoard.print();
    }
    
    public void printPlayer() {
        playerBoard.print();
    }
}