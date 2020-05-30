package algos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import grammar.Grammar;
import grammar.GrammarReader;
import grammar.ProductionRule;

public class FirstPlus {
	
	private Grammar g;
	private Map<ProductionRule, Set<String>> firstplus_p;
	private static Logger logger = Logger.getLogger(First.class.getName());
	private static Level LVL = Level.SEVERE;
	
	public FirstPlus(Grammar g) {
		super();
		this.g = g;
		this.firstplus_p = new HashMap<>();
		logger.setLevel(LVL);
		computeFirstPlus();
	}
	
	public void computeFirstPlus() {
		First fi = new First(this.g);
		Follow fo = new Follow(this.g);
		
		for(ProductionRule p: g.getPrules()) {
			if(!fi.getFirst_P().get(p).contains(Grammar.EPSILON)) {
				firstplus_p.put(p, fi.getFirst_P().get(p));
			}
			else {
				firstplus_p.put(p, fi.getFirst_P().get(p));
				firstplus_p.get(p).addAll(fo.getFollow().get(p.getL()));
			}
		}
		
	}
	
	public Map<ProductionRule, Set<String>> getFirstPlus_P() {
		return firstplus_p;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("==FIRST PLUS==\n");
		for(Map.Entry<ProductionRule, Set<String>> e: firstplus_p.entrySet()) {
			sb.append(e.getKey() + "\t:\t" + e.getValue().toString() + "\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		Grammar g = GrammarReader.readGrammarFromFile("C:\\Users\\Alan\\Desktop\\grammar0.txt");
		FirstPlus f = new FirstPlus(g);
		System.out.println(f.toString());
	}
	
}
