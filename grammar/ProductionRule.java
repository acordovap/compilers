package grammar;

import java.util.ArrayList;

public class ProductionRule {
	
	private String l;
	private ArrayList<String> r;
		
	public ProductionRule() {
		super();
		this.l = null;
		this.r = new ArrayList<>();
	}
	
	public ProductionRule(String l) {
		super();
		this.l = l;
		this.r = new ArrayList<>();
	}

	public ProductionRule(String l, ArrayList<String> r) {
		super();
		this.l = l;
		this.r = r;
	}
	
	public String getL() {
		return l;
	}
	
	public void setL(String l) {
		this.l = l;
	}
	
	public ArrayList<String> getR() {
		return r;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(l + "\t->\t");
		for(String s: r) {
			sb.append(s + " ");
		}
		return sb.toString();
	}

}
