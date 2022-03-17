import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Application qui propose un ensemble de formation à acheter
 * 
 * @author Delmerie JOHN ROSE
 *
 */
public class Formation {

	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Bonjour et bienvenue dans l'application FullTraining");
		HashMap<Integer, ArrayList<String>> training = List.training(); // récup la liste des formations
		HashMap<Integer, Integer> cart = new HashMap<Integer, Integer>(); // panier de l'utilisateur (ID DE LA FORMATION
																			// / QUANTITY)

		boolean displayChoice = false;
		int choiceMenu;

		do {
			displayMenu(); // affiche le menu principal

			while (scanner.hasNextInt() == false) // ignore la saisie tant qu'il ne s'agit pas d'un entier
				scanner.next();
			choiceMenu = scanner.nextInt(); // récup le choix du menu

			switch (choiceMenu) {
			case 1: // afficher la liste des formations
				displayTraining(training);
				displayChoice = true;
				break;
			case 2: // ajouter des formations au panier
				add(training, cart);
				displayChoice = true;
				break;
			case 3: // afficher le panier
				displayCart(training, cart);
				displayChoice = true;
				break;
			case 4: // supprimer des trucs du panier
//				delete(training, cart);
				System.out.println("Supprimer une formation du panier");
				displayChoice = true;
				break;
			case 5: // sortir de l'application
				System.out.println("Vous avez quitté l'application, à bientôt !");
				displayChoice = false;
				break;
			default:
				System.out.println("Mauvaise saisie, recommencez");
				displayChoice = true;
			}
		} while (displayChoice);
		scanner.close();
	}

	/**
	 * Cette méthode permet d'afficher la liste des formations
	 * 
	 * @param training - Liste des formations proposées par 
	 *                 l'application
	 */
	public static void displayTraining(HashMap<Integer, ArrayList<String>> training) {
		String format = "%-2s | %-15s | %-8s | %-35s |%-10s";
		String hr = "-----------------------------------------------------------------------------";
		System.out.println(hr);
		System.out.format(format, "ID", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX");System.out.println();
		System.out.println(hr);

		for (HashMap.Entry<Integer, ArrayList<String>> tr : training.entrySet()) {
			System.out.format(format, tr.getKey(), tr.getValue().get(0), tr.getValue().get(1),
					tr.getValue().get(2), tr.getValue().get(3));System.out.println();
		}
		System.out.println(hr);
	}

	/**
	 * Cette méthode permet d'afficher le menu à l'utilisateur
	 */
	public static void displayMenu() {
		System.out.println();
		System.out.println("Que souhaitez-vous faire ? [Saisir le chiffre correspondant]");
		System.out.println("-------------------------------------------------------------");
		System.out.println("1 - Afficher la liste des formations");
		System.out.println("2 - Ajouter une formation au panier");
		System.out.println("3 - Afficher votre panier");
		System.out.println("4 - Supprimer une formation du panier");
		System.out.println("5 - Quitter l'application");
	}

	/**
	 * Permet d'ajouter une formation au panier de l'utilisateur
	 * 
	 * @param training - Liste des formations proposées
	 * @param cart     - Panier à remplir (ID de la formation / Quantité)
	 */
	public static void add(HashMap<Integer, ArrayList<String>> training, HashMap<Integer, Integer> cart) {
		displayTraining(training);
		System.out.println("Souhaitez-vous ajouter une formation à votre panier ? [Oui/Non]");
		String str = scanner.next();
		System.out.println(cart);
		while (str.equalsIgnoreCase("oui") || str.equalsIgnoreCase("o")) {
			System.out.println("Quelle formation souhaitez-vous acheter ? [Saisir l'ID de la formation]");
			while (scanner.hasNextInt() == false) // ignore la saisie tant qu'il ne s'agit pas d'un entier
				scanner.next();
			int id = scanner.nextInt();// récup le choix de l'user
			if (training.get(id) != null) { // si formation existe
				int qty = 0; // qté de base
				if (cart.containsKey(id)) { // si formation existe dans le panier +1
					qty = cart.get(id) + 1;
				} else {
					qty += 1;
				}
				cart.put(id, qty);
				System.out.println("La formation " + training.get(id).get(0) + " a bien été ajouté au panier.");
			} else {
				System.out.println("Cette formation n'exite pas dans la liste");
			}
			System.out.println("Souhaitez-vous ajouter une autre formation ? [Oui/Non]");
			str = scanner.next();
		}
	}

	public static void delete(HashMap<Integer, ArrayList<String>> training, HashMap<Integer, ArrayList<String>> cart) {
		System.out.println("Quelle formation souhaitez-vous supprimer du panier ? [Saisir l'ID de la formation]");
		int id = scanner.nextInt();// récup le choix de l'user
		if (training.get(id) != null) { // si formation existe
			cart.remove(id); // supp du panier
		}
	}

	/**
	 * Affiche le contenu du panier 
	 * @param training
	 * @param cart
	 */
	public static void displayCart(HashMap<Integer, ArrayList<String>> training, HashMap<Integer, Integer> cart) {

		if (!cart.isEmpty()) { //si le panier contient des formations
			String format = "%-2s | %-15s | %-8s | %-35s |%-8s |%-8s";
			String hr = "-------------------------------------------------------------------------------------------";
			System.out.println("Contenu actuel de votre panier");System.out.println();
			System.out.println(hr);System.out.println();
			System.out.format(format, "ID", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX", "QUANTITE");
			System.out.println(hr);
			for (HashMap.Entry<Integer, ArrayList<String>> tr : training.entrySet()) {
				for (HashMap.Entry<Integer, Integer> c : cart.entrySet()) {
					if (tr.getKey() == c.getKey()) {
						System.out.format(format, tr.getKey(), tr.getValue().get(0), tr.getValue().get(1),
								tr.getValue().get(2), tr.getValue().get(3), c.getValue());System.out.println();
					}
				}
			}
			System.out.println(hr);
		} else {
			System.out.println("Votre panier est vide pour l'instant.");
		}
	}

}