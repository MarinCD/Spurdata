import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonEditableTableModel(String[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}