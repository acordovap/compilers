package grammar;

import java.util.ArrayList;

public class Grammar {

	public static final String EPSILON = "e";
	public static final String EOF = "eof";	
	
	private ArrayList<ProductionRule> prules;
	private ArrayList<String> terminals;
	private ArrayList<String> nonterminals;
	
	public Grammar(ArrayList<ProductionRule> prules) {
		super();
		this.terminals = new ArrayList<>();
		this.nonterminals = new ArrayList<>();
		this.prules = prules;
		inferTandNT();
	}

	private void inferTandNT() {
		for(ProductionRule pr: prules) {
			if (!nonterminals.contains(pr.getL()))
				nonterminals.add(pr.getL());
		}
		for(ProductionRule pr: prules) {
			for(String s: pr.getR()) {
				if (!nonterminals.contains(s)) {
					if (!terminals.contains(s))
						terminals.add(s);
				}
			}
		}
	}
	
	public ArrayList<ProductionRule> getPrules() {
		return prules;
	}

	public ArrayList<String> getTerminals() {
		return terminals;
	}
	
	public ArrayList<String> getNonterminals() {
		return nonterminals;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("==Production Rules==\n");
		for(ProductionRule pr: prules) {
			sb.append(pr.toString()+"\n");
		}
		sb.append("\n==Terminals:==\n");
		for(String s: terminals) {
			sb.append(s+"\n");
		}
		sb.append("\n==Non Terminals==\n");
		for(String s: nonterminals) {
			sb.append(s+"\n");
		}
		return sb.toString();
	}
	
}