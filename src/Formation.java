import java.util.ArrayList;
import java.util.HashMap;

/**
 * Application qui propose un ensemble de formation à acheter
 * 
 * @author Delmerie JOHN ROSE
 *
 */
public class Formation {

	public static void main(String[] args) {

		System.out.println("Bonjour et bienvenue dans l'application FullTraining");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponible");
		System.out.println();

		HashMap<Integer, ArrayList<String>> training = List.training();

		String format = "%-15s | %-10s | %-35s |%-10s";

		System.out.println("-------------------------------------------------------------------------");
		System.out.format(format, "COURS", "NB/JOURS", "DESCRIPTION", "PRIX", "");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------");

		for (int i = 1; i < training.size() + 1; i++) {
			System.out.format(format, training.get(i).get(0), training.get(i).get(1), training.get(i).get(2),
					training.get(i).get(3));
			System.out.println();
		}
		System.out.println("-------------------------------------------------------------------------");
	}

}