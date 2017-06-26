package erp_application.view.panel;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class AbstractMainPanel<T> extends JPanel {	
	public abstract void clearObject();

	public abstract T getObject() throws Exception;

	public abstract String nextNo();

	public abstract void setSelectedTitle();

	public abstract void setObject(T item);
	
	public void isEmptyCheck(Container mainPanel) throws Exception {
		
		for(Component comp : mainPanel.getComponents()){
			if (comp instanceof JTextField){
				if ( ((JTextField) comp).getText().equals("") ){
					comp.requestFocus();
					throw new Exception("공백 존재");
				}
			}
		}
	}
}
