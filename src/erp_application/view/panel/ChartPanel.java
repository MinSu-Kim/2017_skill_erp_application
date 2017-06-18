package erp_application.view.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ChartPanel extends JPanel {
	private String[] titles;
	private int [] data;
	private int [] arcAngle;
	private Color[] pieColor;
	private int totalCnt;
	
	
/*	public ChartPanel(){
		titles = new String[]{"마케팅","개발", "경영"};
		data = new int[]{18,8,7};
		totalCnt = 33;
		pieColor = new Color[data.length];
		arcAngle = new int[data.length];
		initChart();
	}*/
	
	public ChartPanel(String[] titles, int[] data, int totalCnt) {
		this.titles = titles;
		this.data = data;
		this.pieColor = new Color[data.length];
		this.arcAngle = new int[data.length];
		this.totalCnt = totalCnt;
		initChart();
	}

	
	public void setChartData(String[] titles, int[] data, int totalCnt){
		this.titles = titles;
		this.data = data;
		this.pieColor = new Color[data.length];
		this.arcAngle = new int[data.length];
		this.totalCnt = totalCnt;
		initChart();
		repaint();
	}
	
	
	public void initChart(){
		if (data!=null){
			for(int i=0; i<data.length; i++) {
				arcAngle[i]=(int)Math.round((double)data[i]/(double)totalCnt*360);
				pieColor[i] = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
			}	
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (data!=null){
			int startAngle = 0;
			for(int i=0; i<data.length; i++) {
				g.setColor(pieColor[i]);
				g.drawString(titles[i]+" "+Math.round(arcAngle[i]*100./360.)+"%", 50+i*100, 20);
			}
			for(int i=0; i<data.length; i++) {
				g.setColor(pieColor[i]);
				g.fillArc(150,50,200,200,startAngle, arcAngle[i]);
				startAngle = startAngle + arcAngle[i];
			}
		}
	}

}
