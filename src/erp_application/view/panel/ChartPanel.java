package erp_application.view.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ChartPanel extends JLabel {
	
	private String[] names;	//부서명
	private int [] empCnts;		//부서별 인원수
	private int [] arcAngle;		
	private Color[] pieColor;
	private int totalCnt;			//총인원수
	private Color textColor = new Color(0,0,0);
	
	public ChartPanel(String[] names, int[] empCnts, int totalCnt) {
		this.names = names;
		this.empCnts = empCnts;
		this.pieColor = new Color[names.length];
		this.arcAngle = new int[names.length];
		this.totalCnt = totalCnt;
		initChart();
	}

	public void setChartData(String[] names, int[] empCnts, int totalCnt){
		this.names = names;
		this.empCnts = empCnts;
		this.pieColor = new Color[names.length];
		this.arcAngle = new int[names.length];
		this.totalCnt = totalCnt;
		initChart();
		repaint();
	}
	
	private void initChart(){
		if (empCnts!=null){
			for(int i=0; i<empCnts.length; i++) {
				arcAngle[i]=(int)Math.round((double)empCnts[i]/(double)totalCnt*360);
				pieColor[i] = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
			}	
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (empCnts!=null){
			int startAngle = 0;
			for(int i=0; i<empCnts.length; i++) {
				g.setColor(pieColor[i]);
				g.fillRect(380, 30+i*20, 10, 10);
				g.setColor(textColor);
				g.drawString(names[i] + " " + empCnts[i]+" 명", 400, 40+i*20);
			}
			for(int i=0; i<empCnts.length; i++) {
				g.setColor(pieColor[i]);
				g.fillArc(140,20,200,200,startAngle, arcAngle[i]);
				startAngle = startAngle + arcAngle[i];
			}
		}
	}

}
