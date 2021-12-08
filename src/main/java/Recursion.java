/*
Реализация интерфейса стратегии,
стратегия решения методом "Рекурсии"
 */
public class Recursion implements Strategy{

    @Override
    public Sudoku solveSudoku(Sudoku sudoku){
        int[][] solved = solve(sudoku.getGrid(), 0, 0);
        return new Sudoku(solved);
    }

    private int[][] solve(int[][] grid, int row, int col) {
        if (row == N - 1 && col == N)
            return grid;
        if (col == N) {
            row++;
            col = 0;
        }
        if (grid[row][col] != 0)
            return solve(grid, row, col + 1);

        for (int num = 1; num < 10; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (solve(grid, row, col + 1) !=null)
                    return grid;
            }
            grid[row][col] = 0;
        }
        return null;
    }

    private boolean isSafe(int[][] grid, int row, int col, int num) {
        for (int x = 0; x <= 8; x++)
            if (grid[row][x] == num)
                return false;
        for (int x = 0; x <= 8; x++)
            if (grid[x][col] == num)
                return false;
        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (grid[i + startRow][j + startCol] == num)
                    return false;
        return true;
    }
}
