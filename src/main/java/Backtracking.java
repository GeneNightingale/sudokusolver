/*
Реализация интерфейса стратегии,
стратегия решения методом "С возвратом"
 */
class Backtracking implements Strategy{

    @Override
    public Sudoku solveSudoku(Sudoku sudoku){
        int[][] solved = solve(sudoku.getGrid());
        return new Sudoku(solved);
    }

    private int[][] solve(int[][] board) {
        int n = N;
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] == 0)
                {
                    row = i;
                    col = j;

                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        if (isEmpty)
        {
            return board;
        }

        for (int num = 1; num <= n; num++)
        {
            if (isSafe(board, row, col, num))
            {
                board[row][col] = num;
                if (solve(board) != null)
                {
                    return board;
                }
                else
                {
                    board[row][col] = 0;
                }
            }
        }
        return null;
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {
        for (int d = 0; d < board.length; d++)
        {
            if (board[row][d] == num) {
                return false;
            }
        }

        for (int[] ints : board) {
            if (ints[col] == num) {
                return false;
            }
        }

        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++)
        {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++)
            {
                if (board[r][d] == num)
                {
                    return false;
                }
            }
        }
        return true;
    }

}
