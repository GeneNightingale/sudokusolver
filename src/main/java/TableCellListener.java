import java.awt.event.*;
import javax.swing.*;
import java.beans.*;

public class TableCellListener implements PropertyChangeListener, Runnable
{
    private JTable table;
    private Action action;

    private int row;
    private int column;
    private Object oldValue;
    private Object newValue;

    /**
     *  Создаем TableCellListener.
     *  @param table   Таблица, в которой ожидаем изменения
     *  @param action  Действие, которое выполняем при редактировании */
    public TableCellListener(JTable table, Action action)
    {
        this.table = table;
        this.action = action;
        this.table.addPropertyChangeListener( this );
    }

    /**
     *  Создаем TableCellListener с копией всех нужных данных
     *  @param row  строка
     *  @param column  столбец
     *  @param oldValue  старое значение
     *  @param newValue  новое значение*/
    private TableCellListener(JTable table, int row, int column, Object oldValue, Object newValue)
    {
        this.table = table;
        this.row = row;
        this.column = column;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    //Какрй столбик редактировали
    public int getColumn()
    {
        return column;
    }

    //Новое значение ячейки
    public Object getNewValue()
    {
        return newValue;
    }

    //Старое значение ячейки
    public Object getOldValue()
    {
        return oldValue;
    }

    //Какую строку редактировали
    public int getRow()
    {
        return row;
    }

    //В какой таблице редактировали ячейку
    public JTable getTable()
    {
        return table;
    }

    //  Интерфейс PropertyChangeListener
    @Override
    public void propertyChange(PropertyChangeEvent e)
    {
        //  Начали/закончили редактировать ячейку
        if ("tableCellEditor".equals(e.getPropertyName()))
        {
            if (table.isEditing())
                processEditingStarted();
            else
                processEditingStopped();
        }
    }

    //Сохраняем информацию до редактирования
    private void processEditingStarted()
    {
        SwingUtilities.invokeLater( this );
    }
    @Override
    public void run()
    {
        row = table.convertRowIndexToModel( table.getEditingRow() );
        column = table.convertColumnIndexToModel( table.getEditingColumn() );
        oldValue = table.getModel().getValueAt(row, column);
        newValue = null;
    }

    private void processEditingStopped()
    {
        newValue = table.getModel().getValueAt(row, column);
        //  Данные были изменены, выполняем действие
        if (! newValue.equals(oldValue))
        {
            //  На всякий случай делаем копию данніх
            TableCellListener tcl = new TableCellListener(
                    getTable(), getRow(), getColumn(), getOldValue(), getNewValue());
            ActionEvent event = new ActionEvent(
                    tcl,
                    ActionEvent.ACTION_PERFORMED,
                    "");
            action.actionPerformed(event);
        }
    }
}
