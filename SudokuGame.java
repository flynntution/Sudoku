import java.util.*;
public class SudokuGame {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int again = 1;
        do {
            Sudoku sudoku = new Sudoku();
            System.out.print("Select (1/2) \n" +
                             "1. See Sudoku (Final) \n" +
                             "2. Play Sudoku \n" +
                             "Seletion: ");
            int choice = sc.nextInt();
            System.out.println();
        
            switch(choice) {
                case 1:
                    sudoku.printFinal();
                    break;
                case 2:
                    System.out.print("Difficulty (1/2/3) \n" +
                                       "1. Easy \n" +
                                       "2. Medium \n" +
                                       "3. Hard \n" +
                                       "Selection: ");
                    int diff = sc.nextInt();
                    System.out.println();
                    sudoku.play(diff);
                    break;
            }
            
            System.out.print("Again? (0/1): ");
            again = sc.nextInt();
            System.out.println();
        } while (again == 1);
    }
}