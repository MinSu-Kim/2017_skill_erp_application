package erp_application.dto;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private int salary;
	private Department dept;
	private String post;
	private String addr;
	
	public Employee() {}
	
	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, Title title, int salary, Department dept, String post, String addr) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.salary = salary;
		this.dept = dept;
		this.addr = addr;
		this.post = post;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return String.format("%s(%s) - %s", empName, empNo, title.getTitle());
	}
	
	public String[] toArray(){
		//"사원번호", "사원명", "직책", "급여", "부서", "주소", "세부 주소"
	
		return new String[]{String.format("E%04d", empNo), empName, title.getTitle(), String.format("%,d",salary), dept.getDeptName(), addr};
	}
	
}
