package erp_application.view.panel;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractMainPanel<T> extends JPanel {	
	public abstract void clearObject();

	public abstract T getObject() throws Exception;

	public abstract String nextNo();

	public abstract void setSelectedTitle();

	public abstract void setObject(T item);
	
}
