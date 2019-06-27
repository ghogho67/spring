package kr.or.ddit.main.model;

public class AddrVo {
	
	private String add1;
	private String add2;
	
	
	public String getAdd1() {
		return add1;
	}


	public void setAdd1(String add1) {
		this.add1 = add1;
	}


	public String getAdd2() {
		return add2;
	}


	public void setAdd2(String add2) {
		this.add2 = add2;
	}


	@Override
	public String toString() {
		return "AddrVo [add1=" + add1 + ", add2=" + add2 + "]";
	}


	public AddrVo(String add1, String add2) {
		super();
		this.add1 = add1;
		this.add2 = add2;
	}


	public AddrVo() {
	}
	
	
	
}
