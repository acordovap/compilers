package automaton;

import java.util.ArrayList;

public class Automaton {
	
	public static final String ERRORSTATE = "-";
	public static final String EPSILON = "e";
	
	private ArrayList<String> alphabet;
	private ArrayList<String> states;
	private ArrayList<ArrayList<String>> automaton;
	
	public Automaton() {
		super();
		this.alphabet = new ArrayList<>();
		this.states =  new ArrayList<>();
		this.automaton =  new ArrayList<>();
	}

	public Automaton(ArrayList<String> alphabet, ArrayList<String> states, ArrayList<ArrayList<String>> automaton) {
		super();
		this.alphabet = alphabet;
		this.states = states;
		this.automaton = automaton;
	}

	public ArrayList<ArrayList<String>> getAutomaton() {
		return automaton;
	}

	public ArrayList<String> getAlphabet() {
		return alphabet;
	}
	
	public ArrayList<String> getStates() {
		return states;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("==Automaton==\n");
		for(String s: alphabet) {
			sb.append(s+" ");
		}
		sb.append("\n==States:==\n");
		for(String s: states) {
			sb.append(s+" ");
		}sb.append("\n==Table:==\n");
		sb.append("\t\t");
		for(String s: alphabet) {
			sb.append(s+"\t");
		}
		sb.append("\n");
		for(int i = 0; i < automaton.size(); i++) {
			sb.append(states.get(i) + "\t->\t");
			for(String s: automaton.get(i)) {
				sb.append(s +"\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
