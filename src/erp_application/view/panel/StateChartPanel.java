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
		
		
		deptChartPanel = new ChartPanel("부서별 인원");
		titleChartPanel = new ChartPanel("직책별 인원");
		
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
