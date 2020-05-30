package algos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import grammar.Grammar;
import grammar.GrammarReader;
import grammar.ProductionRule;

public class LlTable {

	private Grammar g;
	private HashMap<String, HashMap<String, String>> table;
	private static Logger logger = Logger.getLogger(First.class.getName());
	private static Level LVL = Level.SEVERE;
	
	
	public LlTable(Grammar g) {
		super();
		this.g = g;
		logger.setLevel(LVL);
		generateTable();
	}


	public  HashMap<String, HashMap<String, String>> getTable() {
		return table;
	}

	private void generateTable() {
		FirstPlus fip = new FirstPlus(g);
		
		table = new  HashMap<>(); 
		
		for(String nt: g.getNonterminals()) {
			table.put(nt, new HashMap<String, String>());
			for(String t: g.getTerminals()) {
				if(!t.equals(Grammar.EPSILON)) //omit epsilon
					table.get(nt).put(t, Grammar.ERROR);
			}
			table.get(nt).put(Grammar.EOF, Grammar.ERROR);
		}
		
		for(ProductionRule p: g.getPrules()) {
			for(String s: fip.getFirstPlus_P().get(p)) {
				if(g.getTerminals().contains(s)) {
					//table.get(p.getL()).put(s, p.toString()); // put the rule
					table.get(p.getL()).put(s, Integer.toString(g.getPrules().indexOf(p)) ); // put the index rule 
				}
			}
			if(fip.getFirstPlus_P().get(p).contains(Grammar.EOF)) {
				//table.get(p.getL()).put(Grammar.EOF, p.toString()); // put the rule
				table.get(p.getL()).put(Grammar.EOF, Integer.toString(g.getPrules().indexOf(p))); // put the index rule
			}
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("==LL PARSE TABLE==\n");
		for(Map.Entry<String, HashMap<String, String>> e: table.entrySet()) {
			sb.append(e.getKey()+"\t");
			for(Map.Entry<String, String> ee:  e.getValue().entrySet()) {
				sb.append("\n\t"+ee.getKey()+"\t: ");
				sb.append(ee.getValue());
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		Grammar g = GrammarReader.readGrammarFromFile("C:\\Users\\Alan\\Desktop\\grammar0.txt");
		LlTable t  = new LlTable(g);
		System.out.println(g.toString());
		System.out.println(t.toString());
	}
	
}
