import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        JFrame frame = new JFrame("Sudoku Solver");
        frame.setContentPane(new MainForm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        int[][] inputArray = { {3, 0, 6, 5, 0, 8, 4, 0, 0},
                               {5, 2, 0, 0, 0, 0, 0, 0, 0},
                               {0, 8, 7, 0, 0, 0, 0, 3, 1},
                               {0, 0, 3, 0, 1, 0, 0, 8, 0},
                               {9, 0, 0, 8, 6, 3, 0, 0, 5},
                               {0, 5, 0, 0, 9, 0, 6, 0, 0},
                               {1, 3, 0, 0, 0, 0, 2, 5, 0},
                               {0, 0, 0, 0, 0, 0, 0, 7, 4},
                               {0, 0, 5, 2, 0, 6, 3, 0, 0} };

        /*SudokuReader reader = new SudokuReader();
        inputArray = reader.getReadSudoku("sample.txt");
        Sudoku sudoku = new Sudoku(inputArray);
        sudoku.print();*/
        ExcelReader reader = new ExcelReader();
        inputArray = reader.read("sample.xlsx");
        Sudoku sudoku = new Sudoku(inputArray);
        sudoku.print();
    }
}
