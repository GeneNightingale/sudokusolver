/*
Класс, задающий контекст
 */

public class SolverContext {

    Strategy actionStrategy;

    public SolverContext() {
    }

    public SolverContext(Strategy actionStrategy) {
        this.actionStrategy = actionStrategy;
    }

    public void setStrategy(Strategy strategy){
        this.actionStrategy = strategy;
    }

    public Sudoku solveSudoku(Sudoku sudoku){
        return actionStrategy.solveSudoku(sudoku);
    }
}
