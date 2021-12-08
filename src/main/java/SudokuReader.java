import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Scanner;

public class SudokuReader {

    public int[][] getReadSudoku(String filename) {
        int rows = Sudoku.N;
        int columns = Sudoku.N;
        int [][] myArray = new int[rows][columns];
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
            while(sc.hasNextLine()) {
                for (int i=0; i<myArray.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j=0; j<line.length; j++) {
                        myArray[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("File " + filename + " not found!");
        }
        return myArray;
    }
}