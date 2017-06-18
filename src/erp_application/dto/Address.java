package erp_application.dto;


public class Address{
	private String zipCode;
	private String sido;
	private String sigungu;
	private String doro;
	private String build1;
	private String build2;
	
	public Address(String zipCode, String sido, String sigungu, String doro, String build1,
			String build2) {
		this.zipCode = zipCode;
		this.sido = sido;
		this.sigungu = sigungu;
		this.doro = doro;
		this.build1 = build1;
		this.build2 = build2;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getSido() {
		return sido;
	}

	public String getSigungu() {
		return sigungu;
	}

	public String getDoro() {
		return doro;
	}

	public String getBuild1() {
		return build1;
	}

	public String getBuild2() {
		return build2;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s %s", sido, sigungu, doro, build1, (build2.equals("0")?"":" - " +build2+""));
	}
	
}