package automaton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AutomatonReader {

	public static Automaton readAutomatonFromFile(String fileName) throws IOException {
		
		ArrayList<String> alphabet = new ArrayList<>();
		ArrayList<String> states = new ArrayList<>();
		ArrayList<ArrayList<String>> automaton = new ArrayList<>();
		
		List<String> lines = Collections.emptyList(); 
		lines = Files.readAllLines(Paths.get(fileName));
		
		Iterator<String> it = lines.iterator();
		if(it.hasNext()) { // first line contains alphabet
			alphabet = new ArrayList<String>(Arrays.asList(it.next().trim().split("\\s+")));
		}
		while(it.hasNext()) {
			String[] s = it.next().trim().split("\\s+");
			states.add(s[0]);
			ArrayList<String> al = new ArrayList<>();
			for(int i = 1; i < s.length; i++) {
				al.add(s[i]);
			}
			automaton.add(al);
		}
		return new Automaton(alphabet, states, automaton);
	}
	
	public static void main(String[] args) throws IOException {
		Automaton a = readAutomatonFromFile("C:\\Users\\Alan\\Desktop\\aut0.txt");
		System.out.println(a.toString());
	}
}
