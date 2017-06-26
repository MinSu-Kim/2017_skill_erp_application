package erp_application.view.panel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import erp_application.dao.ChartDao;
import erp_application.dto.Chart;

@SuppressWarnings("serial")
public class StateChartPanel extends JPanel {

	private ChartPanel deptChartPanel;
	private ChartPanel titleChartPanel;


	public StateChartPanel(String title) {
		setLayout(new GridLayout(0, 2));
		setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		
/*		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.CENTER);
		add(tabbedPane, BorderLayout.CENTER);*/
		
		deptChartPanel = new ChartPanel(new String[]{"마케팅","개발", "경영"}, new int[]{18,8,7}, 33, "부서별 인원");
		titleChartPanel = new ChartPanel(new String[]{"사장","부장", "과장", "대리", "사원"}, new int[]{1,2,3,4,10}, 20, "직책별 인원");
		
/*		tabbedPane.addTab("부서별 사원 인원", null, deptChartPanel, null);
		tabbedPane.addTab("직책별 사원 인원", null, titleChartPanel, null);*/
		add(deptChartPanel);
		add(titleChartPanel);
		setDeptChart(true);
		setDeptChart(false);
	}

	
	public void setDeptChart(boolean isDept){
		
		if (isDept){
			Chart chart = ChartDao.getInstance().selectByChartDatas(isDept);
			deptChartPanel.setChartData(chart.getNames(), chart.getEmpCnts(), chart.getTotalCnt());
		}else{
			Chart chart = ChartDao.getInstance().selectByChartDatas(isDept);
			titleChartPanel.setChartData(chart.getNames(), chart.getEmpCnts(), chart.getTotalCnt());
		}
	}

}
