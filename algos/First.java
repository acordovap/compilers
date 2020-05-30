package algos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import grammar.Grammar;
import grammar.GrammarReader;
import grammar.ProductionRule;

public class First {

	private Grammar g;
	private Map<String, Set<String>> first;
	private Map<ProductionRule, Set<String>> first_p;
	private static Logger logger = Logger.getLogger(First.class.getName());
	private static Level LVL = Level.SEVERE;

	public First(Grammar g) {
		super();
		this.g = g;
		this.first = new HashMap<>();
		this.first_p = new HashMap<>();
		logger.setLevel(LVL);
		computeFirst();
	}

	public void computeFirst() {
		for(String s: g.getTerminals()) {
			first.put(s, new HashSet<>());
			first.get(s).add(s);
		}
		first.put(Grammar.EPSILON, new HashSet<>()); 
		first.get(Grammar.EPSILON).add(Grammar.EPSILON);
		first.put(Grammar.EOF, new HashSet<>()); 
		first.get(Grammar.EOF).add(Grammar.EOF);
		for(String s: g.getNonterminals()) {
			first.put(s, new HashSet<>());
		}
		
		ArrayList<ProductionRule> prs = new ArrayList<>();
		//ArrayList<ProductionRule> prs_p = new ArrayList<>();
		for(ProductionRule pr: g.getPrules()) { //Obtaining production rules for NonTerminals
			prs.add(pr);
		}
		int j=0;
		int check = first.hashCode();
		while(true) {
			for(ProductionRule pr: prs) {
				ArrayList<String> b = pr.getR();
				Set<String> rhs = new HashSet<>(firstMinusEpsilon(b.get(0)));
				int i = 0;
				while(first.get(b.get(i)).contains(Grammar.EPSILON) && i < b.size()-1) {
					rhs.addAll(new HashSet<>(firstMinusEpsilon(b.get(i+1))));
					i++;
				}
				if( (i==b.size()-1) && (first.get(b.get(i)).contains(Grammar.EPSILON)) ) {
					rhs.add(Grammar.EPSILON);
				}
				//first_p.put(pr, new );
				first_p.put(pr, rhs);
				first.get(pr.getL()).addAll(rhs);
				//logger.info(pr.toString() + "\t->\t" + first.get(pr.getL()));
			}
			logger.info("=="+j+"==\n"+toString());
			if(check == first.hashCode()) {
				break;
			}
			else {
				check = first.hashCode();
			}
			j++;
		}
	}
	
	public Set<String> firstMinusEpsilon(String b){
		Set<String> firstB =new HashSet<>(first.get(b));
		firstB.remove(Grammar.EPSILON);
		return firstB;
	}

	public Map<String, Set<String>> getFirst() {
		return first;
	}

	public Map<ProductionRule, Set<String>> getFirst_P() {
		return first_p;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("==FIRST==\n");
		for(Map.Entry<String, Set<String>> e: first.entrySet()) {
			sb.append(e.getKey() + "\t->\t" + e.getValue().toString() + "\n");
		}
		sb.append("==first==\n");
		for(Map.Entry<ProductionRule, Set<String>> e: first_p.entrySet()) {
			sb.append(e.getKey() + "\t:\t" + e.getValue().toString() + "\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		Grammar g = GrammarReader.readGrammarFromFile("C:\\Users\\Alan\\Desktop\\grammar0.txt");
		First f = new First(g);
		System.out.println(f.toString());
	}

}
