/*
Интерфейс стратегии
*/
public interface Strategy {

    static int N = Sudoku.N;

    public Sudoku solveSudoku(Sudoku sudoku);

}
