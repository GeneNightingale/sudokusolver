import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuUtils {

    int N = Sudoku.N;

    public List<Integer> getPossibleValues(Sudoku sudoku, int row, int col) {
        List<Integer> invalidNumbers = new ArrayList<>();
        int[][] grid = sudoku.getGrid();
        for (int i = 0; i < N; i ++) {
            if (!invalidNumbers.contains(grid[i][col])){
                invalidNumbers.add(grid[i][col]);
                System.out.println("Added to invalid: " + grid[i][col] + " from [" + i + "][" + col + "]");
            }
        }
        for (int i = 0; i < N; i ++) {
            if (!invalidNumbers.contains(grid[row][i])){
                invalidNumbers.add(grid[row][i]);
                System.out.println("Added to invalid: " + grid[row][i] + " from [" + row + "][" + i + "]");
            }
        }
        List<Integer> validNumbers = new ArrayList<>();
        for (int i = 1; i <= N; i ++){
            if (!invalidNumbers.contains(i))
                validNumbers.add(i);
        }
        if (!validNumbers.contains(grid[row][col]))
            validNumbers.add(grid[row][col]);
        Collections.sort(validNumbers);
        return validNumbers;
    }
}
