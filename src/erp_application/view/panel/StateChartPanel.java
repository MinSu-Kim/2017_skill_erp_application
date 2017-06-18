package erp_application.view.panel;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;

public class StateChartPanel extends JPanel {

	public StateChartPanel(String title) {
		setLayout(new BorderLayout(0, 0));
		setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		ChartPanel deptChartPanel = new ChartPanel(new String[]{"마케팅","개발", "경영"}, new int[]{18,8,7}, 33);
		tabbedPane.addTab("부서별 사원 인원", null, deptChartPanel, null);
		
		ChartPanel titleChartPanel = new ChartPanel(new String[]{"사장","부장", "과장", "대리", "사원"}, new int[]{1,2,3,4,10}, 20);
		tabbedPane.addTab("직책별 사원 인원", null, titleChartPanel, null);
	}

}
