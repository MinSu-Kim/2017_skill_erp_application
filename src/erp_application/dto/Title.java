package erp_application.dto;

public class Title {
	private int no;
	private String title;
	
	
	public Title(int no) {
		this.no = no;
	}

	public Title(int no, String title) {
		this.no = no;
		this.title = title;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}

	@Override
	public boolean equals(Object obj) {
		Title tmp = (Title) obj;
		if (no == tmp.no && title.equals(tmp.title)){
			return true;
		}else{
			return false;
		}
	}
	
	public String[] toArray(){
		return new String[]{String.format("T%03d", no), title};
	}
	
}
