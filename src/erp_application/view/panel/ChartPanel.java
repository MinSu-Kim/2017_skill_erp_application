package erp_application.view.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class ChartPanel extends JLabel {
	
	private String[] names;	//부서명
	private int [] empCnts;		//부서별 인원수
	private int [] arcAngle;		
	private Color[] pieColor;
	private int totalCnt;			//총인원수
	private Color textColor = new Color(0,0,0);
	
	public ChartPanel(String title) {
		setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	}

	public void setChartData(String[] names, int[] empCnts, int totalCnt){
		this.names = names;
		this.empCnts = empCnts;
		this.pieColor = new Color[names.length];
		this.arcAngle = new int[names.length];
		this.totalCnt = totalCnt;
		initChart();
		initColor();
		repaint();
	}
	
	private void initColor(){
		for(int i=0; i<names.length; i++) {
			pieColor[i] = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
		}	
	}
	
	private void initChart(){
		if (empCnts!=null){
			for(int i=0; i<names.length; i++) {
				arcAngle[i]=(int)Math.round((double)empCnts[i]/(double)totalCnt*360);
			}	
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (empCnts!=null){
			int startAngle = 0;
			for(int i=0; i<names.length; i++) {
				g.setColor(pieColor[i]);
				g.fillRect(180, 30+i*20, 10, 10);
				g.setColor(textColor);
				g.drawString(names[i] + " " + empCnts[i]+" 명", 200, 40+i*20);
			}
			
			for(int i=0; i<names.length; i++) {
				g.setColor(pieColor[i]);
				g.fillArc(20,20,150,150,startAngle, arcAngle[i]);
				startAngle = startAngle + arcAngle[i];
			}
		}
	}

}
