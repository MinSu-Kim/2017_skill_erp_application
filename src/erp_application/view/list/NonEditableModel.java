package erp_application.view.list;

import javax.swing.table.DefaultTableModel;


public class NonEditableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	NonEditableModel(Object[][] data, String[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}