package automaton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutomatonReader {

	public static Automaton readAutomatonFromFile(String fileName) throws IOException {
		
		ArrayList<String> alphabet = new ArrayList<>();
		ArrayList<String> states = new ArrayList<>();
		ArrayList<ArrayList<String>> automaton = new ArrayList<>();
		
		List<String> lines = Collections.emptyList(); 
		lines = Files.readAllLines(Paths.get(fileName));
		String[] l = null, r;
		for(String s: lines) {
			s.trim().split("\\s+");
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		readAutomatonFromFile("C:\\Users\\Alan\\Desktop\\aut0.txt");
	}
}
