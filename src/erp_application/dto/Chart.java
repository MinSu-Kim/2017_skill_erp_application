package erp_application.dto;

public class Chart {
	private String[] names;	//부서명, 직책명
	private int [] empCnts;	//부서별 인원수, 직책별 인원수
	private int totalCnt;	//총인원수
	
	public String[] getNames() {
		return names;
	}

	public int[] getEmpCnts() {
		return empCnts;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public void setEmpCnts(int[] empCnts) {
		this.empCnts = empCnts;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}	
	
	
}
