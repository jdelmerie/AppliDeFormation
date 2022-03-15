import java.util.ArrayList;
import java.util.HashMap;

public class List {
	public static HashMap<Integer, ArrayList<String>> training(){
		HashMap<Integer, ArrayList<String>> training = new HashMap<Integer, ArrayList<String>>();
		training.put(1, new ArrayList<String>());
		training.get(1).add("Java");
		training.get(1).add("20");
		training.get(1).add("Java SE 8 : Syntax & POO");
		training.get(1).add("3000");
		training.put(2, new ArrayList<String>());
		training.get(2).add("Java avancé");
		training.get(2).add("20");
		training.get(2).add("Exceptions, fichiers, JDBC, Thread");
		training.get(2).add("5000");
		training.put(3, new ArrayList<String>());
		training.get(3).add("Spring");
		training.get(3).add("20");
		training.get(3).add("Spring Core/MVC/Security");
		training.get(3).add("5000");
		training.put(4, new ArrayList<String>());
		training.get(4).add("PHP Frameworks");
		training.get(4).add("15");
		training.get(4).add("Symphony");
		training.get(4).add("2500");
		training.put(5, new ArrayList<String>());
		training.get(5).add("C#");
		training.get(5).add("20");
		training.get(5).add("DotNet Core");
		training.get(5).add("5000");
		return training;
	}
}
