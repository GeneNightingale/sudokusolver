import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.*;

public class MainForm {
    private JTable inputTable;
    protected JPanel panel;
    private JTable outputTable;
    private JButton solveButton;
    private JButton getPossibleNumsButton;
    private JComboBox methodsBox;

    public int LastEditedRow;
    public int LastEditedColumn;
    public String NewValue;

    SudokuUtils utils = new SudokuUtils();
    Sudoku sudoku = new Sudoku();
    SolverContext context = new SolverContext();

    List<Integer> validNumbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

    public MainForm() {

        setInputTable();
        setOutputTable();
        populateMethodBox();

        solveButton.addActionListener(e -> solveUsingMethod(context));

        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                cellEdited(e);
            }
        };
        TableCellListener tcl = new TableCellListener(inputTable, action);

        getPossibleNumsButton.addActionListener(e -> getPossibleNumbersForCell());
    }

    private void cellEdited(ActionEvent e) {
        TableCellListener tcl = (TableCellListener) e.getSource();
        LastEditedRow = tcl.getRow();
        LastEditedColumn = tcl.getColumn();
        NewValue = (tcl.getNewValue()).toString();
        try{
            int value = Integer.parseInt(NewValue);
            if (!validNumbers.contains(Integer.parseInt(NewValue)))
                inputTable.getModel().setValueAt(null, LastEditedRow, LastEditedColumn);
        } catch (NumberFormatException ex) {
            inputTable.getModel().setValueAt(null, LastEditedRow, LastEditedColumn);
        }
    }

    private void solveUsingMethod(SolverContext context) {
        ( (DefaultTableModel) outputTable.getModel()).setRowCount(0);
        int[][] values = (getTableData(inputTable));

        sudoku = new Sudoku(values);
        String strat = (String) methodsBox.getSelectedItem();
        setContextByChosenMethod(context, strat);
        sudoku = context.solveSudoku(sudoku);

        fillOutputModel(sudoku.getGrid(), (DefaultTableModel) outputTable.getModel());
    }

    private void setContextByChosenMethod(SolverContext context, String strat) {
        switch (strat){
            case "Рекурсия":
                context.setStrategy(new Recursion());
                break;
            case "С возвратом":
                context.setStrategy(new Backtracking());
        }
    }

    private void getPossibleNumbersForCell() {
        int[][] values = (getTableData(inputTable));
        sudoku = new Sudoku(values);
        int row = inputTable.getSelectedRow();
        int col = inputTable.getSelectedColumn();
        JOptionPane.showMessageDialog(panel, "Возможные значения для клетки (" + (row + 1) + ")(" + (col + 1) + "): " +
                utils.getPossibleValues(sudoku, row, col) + ".");
    }

    private void setInputTable() {
        DefaultTableModel inputModel = new DefaultTableModel(){};
        for (int i = 0; i < Sudoku.N; i++){
            inputModel.addRow(new Object[]{});
            inputModel.addColumn(i);
        }
        inputTable.setModel(inputModel);
        inputTable.getTableHeader().setReorderingAllowed(false);
    }

    private void setOutputTable() {
        DefaultTableModel outputModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i < Sudoku.N; i++){
            outputModel.addRow(new Object[]{});
            outputModel.addColumn(i);
        }
        outputTable.setModel(outputModel);
    }

    private void fillOutputModel(int[][] values, DefaultTableModel outputModel) {
        for (int[] rowData : values) {
            Vector<Object> row = new Vector<Object>(Sudoku.N);

            for (int rowDatum : rowData) {
                if (rowDatum != 0)
                    row.addElement(rowDatum);
                else
                    row.addElement(null);
            }
            outputModel.addRow(row);
        }
    }

    public int[][] getTableData (JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        int[][] tableData = new int[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++)
            for (int j = 0 ; j < nCol ; j++) {
                System.out.println("Attempting to get from [" + i + "][" + j + "]");
                if ((dtm.getValueAt(i, j) != null) && (dtm.getValueAt(i, j) != ""))
                    tableData[i][j] = Integer.parseInt((String) dtm.getValueAt(i, j));
                else
                    tableData[i][j] = 0;
            }
        return tableData;
    }

    private void populateMethodBox(){
        this.methodsBox.addItem("Рекурсия");
        this.methodsBox.addItem("С возвратом");
    }
}
