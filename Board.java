import java.util.*;
public class Board {
    private int[][] board;
    public Board() {
        board = new int[9][9];
    }
    
    public int getLength() {
        return board.length;
    }
    
    public int getRowLength() {
        return board[0].length;
    }
    
    public int getCell(int row, int col) {
        return board[row][col];
    }
    
    public void setCell(int row, int col, int num) {
        board[row][col] = num;
    }
    
    public void print() {
        System.out.println("    1 2 3   4 5 6   7 8 9");
        System.out.println("  +-------+-------+-------+");
        for (int i = 0; i < board.length; i ++) {
            System.out.print((i+1) + " | ");
            for (int j = 0; j < board[i].length; j ++) {
                if (board[i][j] == 0)
                    System.out.print("- ");
                else
                    System.out.print(board[i][j] + " ");
                if ((j + 1) % 3 == 0)
                    System.out.print("| ");
            }
            System.out.println();
            if ((i + 1) % 3 == 0)
                System.out.println("  +-------+-------+-------+");
        }
        System.out.println();
    }
    
    public boolean inRow(int row, int num) {
        for (int cell : board[row]) {
            if (cell == num)
                return true;
        }
        return false;
    }
    
    public boolean inCol(int col, int num) {
        for (int i = 0; i < board.length; i ++) {
            if (board[i][col] == num)
                return true;
        }
        return false;
    }
    
    public boolean inSquare(int row, int col, int num) {
        int rowStart = row/3*3;
        int colStart = col/3*3;
        for (int i = rowStart; i < rowStart + 3; i ++) {
            for (int j = colStart; j < colStart + 3; j ++) {
                if (board[i][j] == num)
                    return true;
            }
        }
        return false;
    }
    
    public void copy(Board other) {
        for (int i = 0; i < other.getLength(); i ++) {
            for (int j = 0; j < other.getRowLength(); j ++) {
                int num = other.getCell(i, j);
                board[i][j] = num;
            }
        }
    }
    
    public boolean equalTo(Board other) {
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[i].length; j ++) {
                if (board[i][j] != other.getCell(i, j))
                    return false;
            }
        }
        return true;
    }
}
