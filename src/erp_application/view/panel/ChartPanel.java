package erp_application.view.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ChartPanel extends JLabel {
	
	private String[] deptTitles;	//부서명
	private int [] deptEmpCnt;		//부서별 인원수
	private int [] arcAngle;		
	private Color[] pieColor;
	private int totalCnt;			//총인원수
	private Color textColor = new Color(0,0,0);
	
	public ChartPanel(String[] deptTitles, int[] deptEmpCnt, int totalCnt) {
		this.deptTitles = deptTitles;
		this.deptEmpCnt = deptEmpCnt;
		this.pieColor = new Color[deptEmpCnt.length];
		this.arcAngle = new int[deptEmpCnt.length];
		this.totalCnt = totalCnt;
		initChart();
	}

	public void setChartData(String[] deptTitles, int[] deptEmpCnt, int totalCnt){
		this.deptTitles = deptTitles;
		this.deptEmpCnt = deptEmpCnt;
		this.pieColor = new Color[deptEmpCnt.length];
		this.arcAngle = new int[deptEmpCnt.length];
		this.totalCnt = totalCnt;
		initChart();
		repaint();
	}
	
	private void initChart(){
		if (deptEmpCnt!=null){
			for(int i=0; i<deptEmpCnt.length; i++) {
				arcAngle[i]=(int)Math.round((double)deptEmpCnt[i]/(double)totalCnt*360);
				pieColor[i] = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
			}	
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (deptEmpCnt!=null){
			int startAngle = 0;
			for(int i=0; i<deptEmpCnt.length; i++) {
				g.setColor(pieColor[i]);
				g.fillRect(280, 30+i*20, 10, 10);
				g.setColor(textColor);
				g.drawString(deptTitles[i] + " " + deptEmpCnt[i]+" 명", 300, 40+i*20);
			}
			for(int i=0; i<deptEmpCnt.length; i++) {
				g.setColor(pieColor[i]);
				g.fillArc(50,20,200,200,startAngle, arcAngle[i]);
				startAngle = startAngle + arcAngle[i];
			}
		}
	}

}
