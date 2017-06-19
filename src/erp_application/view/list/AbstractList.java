package erp_application.view.list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import erp_application.Erp_Application;

@SuppressWarnings("serial")
public abstract class AbstractList extends JPanel{
	protected JTable table;
	protected JScrollPane scrollPane;
	private JPopupMenu popupMenu;
	protected int selectedIdx;
	protected String[][] arDatas;
	protected Erp_Application main;
	
	public AbstractList(String title, Erp_Application main) {
		this.main = main;
		setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		reload();
		
		popupMenu = createPopupMenu();
		table.addMouseListener(mouseAdapter);
	}
	
	private JPopupMenu createPopupMenu() {
		JPopupMenu menu = new JPopupMenu();
		
		JMenuItem add = new JMenuItem("추가");
		add.addActionListener(menuListener);
		
		JMenuItem update = new JMenuItem("수정");
		update.addActionListener(menuListener);
		
		JMenuItem del = new JMenuItem("삭제");
		del.addActionListener(menuListener);
		
		menu.add(add);
		menu.add(update);
		menu.add(del);

		return menu;
	}

	ActionListener menuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("추가")){
				insertItem();
				
			}
			
			if (e.getActionCommand().equals("수정")){
				updateItem();
			}
			if (e.getActionCommand().equals("삭제")){
				deleteItem();
			}	
		}
	};
	
	MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			selectedIdx = table.getSelectedRow();
			if (e.getButton()== MouseEvent.BUTTON3){
				popupMenu.show(table, e.getX(), e.getY());
			}
		}
	};
	
	public void reload() {
		table.setModel(new DefaultTableModel(getRows(), getColumns()));
		table.revalidate();
		reAlign();
		reSize();
	}


	protected void tableCellAlignment(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

	protected void tableSetWidth(int... width) {
		TableColumnModel cModel = table.getColumnModel();

		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	protected abstract String[] getColumns();

	protected abstract String[][] getRows();
	
	protected abstract void reAlign();
	
	protected abstract void reSize();
	
	public abstract Object getSelectedData();
	
	protected abstract void updateItem();

	protected abstract void deleteItem();

	protected abstract void insertItem();
}
