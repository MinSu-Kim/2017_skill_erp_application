package erp_application.dto;

public class EmployeeDetail {
	private int empNo;
	private String addr;
	private String addr_etc;
	private boolean isMarried;
	private boolean isDependent;
	private String post;
	private byte[] pic;
	
	public EmployeeDetail(int empNO) {
		this.empNo = empNO;
	}

	public EmployeeDetail(int empNo, String addr, String addr_etc, boolean isMarried, boolean isDependent, String post,
			byte[] pic) {
		this.empNo = empNo;
		this.addr = addr;
		this.addr_etc = addr_etc;
		this.isMarried = isMarried;
		this.isDependent = isDependent;
		this.post = post;
		this.pic = pic;
	}

/*	public EmployeeDetail(int empNO, String addr, String addr_etc, boolean isMarried, boolean isDependent,
			String post) {
		this.empNo = empNO;
		this.addr = addr;
		this.addr_etc = addr_etc;
		this.isMarried = isMarried;
		this.isDependent = isDependent;
		this.post = post;
	}*/
	
	public int getEmpNO() {
		return empNo;
	}

	public void setEmpNO(int empNO) {
		this.empNo = empNO;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr_etc() {
		return addr_etc;
	}

	public void setAddr_etc(String addr_etc) {
		this.addr_etc = addr_etc;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}

	public boolean isDependent() {
		return isDependent;
	}

	public void setDependent(boolean isDependent) {
		this.isDependent = isDependent;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "EmployeeDetail [empNO=" + empNo + ", addr=" + addr + ", addr_etc=" + addr_etc + ", isMarried="
				+ isMarried + ", isDependent=" + isDependent + ", post=" + post + "]";
	}
	
	public String[] toArray(){
		return new String[]{empNo+"", addr, addr_etc, isMarried+"", isDependent+"", post};
	}
}
