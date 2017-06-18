package erp_application.dto;

public class Employee {
	private int empNo;
	private String empName;
	private Title title;
	private Employee manager;
	private int salary;
	private Department dept;
	private EmployeeDetail detail;
	
	public Employee() {}
	
	public Employee(int empNo) {
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
	}

	public Employee(int empNo, String empName, Title title, Employee manager, int salary, Department dept,
			EmployeeDetail detail) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
		this.detail = detail;
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

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
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

	
	public EmployeeDetail getDetail() {
		return detail;
	}

	public void setDetail(EmployeeDetail detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return String.format("%s(%s) - %s", empName, empNo, title.getTitle());
	}
	
	public String[] toArray(){
		return new String[]{empNo+"", empName, title.getTitle(), manager.getEmpNo()+"", String.format("%,d",salary), dept.getDeptName()};
	}
	
}
