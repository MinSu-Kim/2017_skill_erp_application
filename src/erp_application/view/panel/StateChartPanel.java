package erp_application.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class StateChartPanel extends JPanel {

	private ChartPanel deptChartPanel;
	private ChartPanel titleChartPanel;


	public StateChartPanel(String title) {
		setLayout(new BorderLayout(0, 0));
		setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.CENTER);
		add(tabbedPane, BorderLayout.CENTER);
		
		deptChartPanel = new ChartPanel(new String[]{"마케팅","개발", "경영"}, new int[]{18,8,7}, 33);
		tabbedPane.addTab("부서별 사원 인원", null, deptChartPanel, null);
		
		titleChartPanel = new ChartPanel(new String[]{"사장","부장", "과장", "대리", "사원"}, new int[]{1,2,3,4,10}, 20);
		tabbedPane.addTab("직책별 사원 인원", null, titleChartPanel, null);
	}

	
	public void setDeptChart(boolean isDept, String[] names, int[] empCnts, int totalCnt){
		if (isDept){
			deptChartPanel.setChartData(names, empCnts, totalCnt);
		}else{
			titleChartPanel.setChartData(names, empCnts, totalCnt);
		}
	}

}
