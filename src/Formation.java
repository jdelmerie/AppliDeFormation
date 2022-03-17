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
		HashMap<Integer, Integer> cart = new HashMap<Integer, Integer>();  // panier de l'utilisateur (ID DE LA FORMATION / QUANTITY)

		boolean displayChoice = false;
		int choiceMenu;

		do {
			displayMenu(); // affiche le menu principal

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
				System.out.println("Afficher le panier");
				displayChoice = true;
				break;
			case 4: // supprimer des trucs du panier
//				delete(training, cart);
				System.out.println("Supprimer une formation du panier");
				displayChoice = true;
				break;
			case 5: // sortir de l'application
				System.out.println("5 - Quitter l'application");
				break;
			}
		} while (displayChoice);
		scanner.close();
	}

	/**
	 * Cette méthode permet d'afficher la liste des formations
	 * 
	 * @param training - Hashmap contenant la liste des formations proposées par
	 *                 l'application
	 */
	public static void displayTraining(HashMap<Integer, ArrayList<String>> training) {
		String format = "%-2s | %-15s | %-8s | %-35s |%-10s";
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponible");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
		System.out.format(format, "ID", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");

		for (int i = 1; i < training.size() + 1; i++) {
			System.out.format(format, i, training.get(i).get(0), training.get(i).get(1), training.get(i).get(2),
					training.get(i).get(3));
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------");
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
	 * @param cart     - Panier à remplir
	 */
	public static void add(HashMap<Integer, ArrayList<String>> training, HashMap<Integer, Integer> cart) {
		displayTraining(training);
		System.out.println("Souhaitez-vous ajouter une formation à votre panier ? [Oui/Non]");
		String str = scanner.next();
		System.out.println(cart);
		while (str.equalsIgnoreCase("oui") || str.equalsIgnoreCase("o")) {
			System.out.println("Quelle formation souhaitez-vous acheter ? [Saisir l'ID de la formation]");
			int id = scanner.nextInt();// récup le choix de l'user
			if (training.get(id) != null) { // si formation existe
				int qty = 0;
				if (cart.containsKey(id)) {
					qty = cart.get(id) + 1;
				} else {
					qty += 1;
				}
				cart.put(id, qty); 
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

	public static void displayCart(HashMap<Integer, ArrayList<String>> training, HashMap<Integer, Integer> cart) {

		
		if (!cart.isEmpty()) {
			System.out.println("Contenu actuel de votre panier");
			for (HashMap.Entry<Integer, ArrayList<String>> tr : training.entrySet()) {
				for (HashMap.Entry<Integer, Integer> c : cart.entrySet()) {
					if(tr.getKey() == c.getKey()) {
						System.out.println("ID : " + tr.getKey() + " | " + String.join(" | ", tr.getValue()) + " | Quantité : " + c.getValue());
					}
				}
			}
			
//			String format = "%-2s | %-15s | %-8s | %-35s |%-10s |%-10s";
//			System.out.println("Contenu actuel de votre panier");
//			System.out.println();
//			System.out.println("----------------------------------------------------------------------------");
//			System.out.format(format, "ID", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX", "QUANTITE");
//			System.out.println();
//			System.out.println("----------------------------------------------------------------------------");
//
//			for (int i = 1; i < cart.size() + 1; i++) {
//				System.out.format(format, i, cart.get(i).get(0), cart.get(i).get(1), cart.get(i).get(2),
//						cart.get(i).get(3));
//				System.out.println();
//			}
//			System.out.println("----------------------------------------------------------------------------");
		} else {
			System.out.println("Votre panier est vide pour l'instant.");
		}

	}

}