package automaton;

import java.util.ArrayList;

public class Automaton {
	
	public static final String ERRORSTATE = "-";
	
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
	
	
	
}
