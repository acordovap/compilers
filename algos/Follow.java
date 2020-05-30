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

public class Follow {

	private Grammar g;
	private Map<String, Set<String>> follow;
	private Map<ProductionRule, Set<String>> follow_p;
	private static Logger logger = Logger.getLogger(First.class.getName());
	private static Level LVL = Level.SEVERE;
	
	public Follow(Grammar g) {
		super();
		this.g = g;
		this.follow = new HashMap<>();
		this.follow_p = new HashMap<>();
		logger.setLevel(LVL);
		computeFollow();
	}
	
	public void computeFollow() {
		First f = new First(this.g);
		
		for(String s: g.getNonterminals()) {
			follow.put(s, new HashSet<>());
		}
		follow.put(g.getStart(), new HashSet<>());
		follow.get(g.getStart()).add(Grammar.EOF);
		
		ArrayList<ProductionRule> prs = new ArrayList<>();
		for(ProductionRule pr: g.getPrules()) { //Obtaining production rules for NonTerminals
			if(g.getNonterminals().contains(pr.getL())) {
				prs.add(pr);
			}
		}
		int j=0;
		int check = follow.hashCode();
		while(true) {
			for(ProductionRule pr: prs) {
				ArrayList<String> b = pr.getR();
				Set<String> trailer = new HashSet<String>(follow.get(pr.getL()));
				for(int i = b.size()-1; i >= 0; i--) {
					if(g.getNonterminals().contains(b.get(i))) {
						follow_p.put(pr, trailer);
						follow.get(b.get(i)).addAll(trailer);
						if(f.getFirst().get(b.get(i)).contains(Grammar.EPSILON)) {
							trailer.addAll(f.firstMinusEpsilon(b.get(i)));
						}
						else {
							trailer = new HashSet<String>(f.getFirst().get(b.get(i)));
						}
					}
					else {
						trailer = new HashSet<String>(f.getFirst().get(b.get(i)));
					}
				}
			}
			logger.info("=="+j+"==\n"+toString());
			if(check == follow.hashCode()) {
				break;
			}
			else {
				check = follow.hashCode();
			}
			j++;
		}
	}

	public Map<String, Set<String>> getFollow() {
		return follow;
	}
	
	public Map<ProductionRule, Set<String>> getFollow_P() {
		return follow_p;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("==FOLLOW==\n");
		for(Map.Entry<String, Set<String>> e: follow.entrySet()) {
			sb.append(e.getKey() + "\t->\t" + e.getValue().toString() + "\n");
		}		
		sb.append("==follow==\n");
		for(Map.Entry<ProductionRule, Set<String>> e: follow_p.entrySet()) {
			sb.append(e.getKey() + "\t:\t" + e.getValue().toString() + "\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		Grammar g = GrammarReader.readGrammarFromFile("C:\\Users\\Alan\\Desktop\\grammar0.txt");
		Follow f = new Follow(g);
		System.out.println(f.toString());
	}
	
}
