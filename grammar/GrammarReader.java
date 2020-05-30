package grammar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GrammarReader {

	public static Grammar readGrammarFromFile(String fileName) throws IOException 
	{
		ArrayList<ProductionRule> prs = new ArrayList<>();
		List<String> lines = Collections.emptyList(); 
		lines = Files.readAllLines(Paths.get(fileName));
		
		String[] l = null, r;
		for(String s: lines) {
			
			if (!s.equals("")) {
				if(s.contains(":")) {
					l = s.trim().split(":")[0].trim().split("\\s+");
					r = s.trim().split(":")[1].trim().split("\\|");
				}
				else {
					r = s.trim().split("\\|");
				}
				for(String ri: r) {
					String rii = ri.trim();
					if(!rii.equals("")) {
						String[] srii = rii.split("\\s+");
						ProductionRule pr = new ProductionRule(l[0],  new ArrayList<String>(Arrays.asList(srii)) );
						prs.add(pr);
					}
				}
			}
		}
		return new Grammar(prs); 
	} 
	
	
	
	public static void main(String[] args) throws IOException {
		Grammar g = readGrammarFromFile("C:\\Users\\Alan\\Desktop\\grammar0.txt");
		System.out.println(g.toString());
	}

}
