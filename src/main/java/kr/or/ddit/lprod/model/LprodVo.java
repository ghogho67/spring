package kr.or.ddit.lprod.model;


public class LprodVo {
	
	private int lprod_id;
	private String lprod_gu;
	private String lprod_nm;
	private int rn;
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getLprod_id() {
		return lprod_id;
	}
	public void setLprod_id(int lprod_id) {
		this.lprod_id = lprod_id;
	}
	public String getLprod_gu() {
		return lprod_gu;
	}
	public void setLprod_gu(String lprod_gu) {
		this.lprod_gu = lprod_gu;
	}
	public String getLprod_nm() {
		return lprod_nm;
	}
	public void setLprod_nm(String lprod_nm) {
		this.lprod_nm = lprod_nm;
	}
	public LprodVo() {
	}
	public LprodVo(int lprod_id, String lprod_gu, String lprod_nm, int rn) {
		super();
		this.lprod_id = lprod_id;
		this.lprod_gu = lprod_gu;
		this.lprod_nm = lprod_nm;
		this.rn = rn;
	}
	
	
}
