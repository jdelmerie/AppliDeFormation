import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Application qui propose un ensemble de formation à acheter L'utilisation
 * dispose d'un menu lui permettant de naviguer au sein de l'application
 * 
 * @author Delmerie JOHN ROSE
 *
 */
public class Formation {

	//
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Bonjour et bienvenue dans l'application FullTraining");
		HashMap<Integer, ArrayList<String>> training = List.training(); // récup la liste des formations
		HashMap<Integer, Integer> cart = new HashMap<Integer, Integer>(); // panier de l'utilisateur (ID DE LA FORMATION
		// / QUANTITY)

		boolean showMenu = false;
		int choice;

		do {
			menu(); 
			while (scanner.hasNextInt() == false) 
				scanner.next();
			choice = scanner.nextInt(); 

			switch (choice) {
			case 1: // afficher la liste des formations
				displayTraining(training);
				showMenu = true;
				break;
			case 2: // ajouter des formations au panier
				add(training, cart);
				showMenu = true;
				break;
			case 3: // afficher le panier
				displayCart(training, cart);
				showMenu = true;
				break;
			case 4: // supprimer des trucs du panier
				delete(training, cart);
				showMenu = true;
				break;
			case 5: // passer commande
				if (!cart.isEmpty()) {
					// affiche "l'écran secondaire" avec le panier et le total
					commande(training, cart); 
					showMenu = false;
					// demande confirmation à l'user pour validation du panier ou non
					validation(cart); 
					showMenu = true;
				} else {
					System.out.println("Votre panier est vide pour l'instant.");
					showMenu = true;
				}
				break;
			case 6: // sortir de l'application
				System.out.println("Vous avez déconnecté, à bientôt !");
				showMenu = false;
				break;
			default:
				System.out.println("Mauvaise saisie, recommencez !");
				showMenu = true;
			}
		} while (showMenu);
		scanner.close();
	}

	/**
	 * Cette méthode permet d'afficher le menu à l'utilisateur
	 */
	public static void menu() {
		System.out.println();
		System.out.println("Que souhaitez-vous faire ? [Saisir le chiffre correspondant]");
		System.out.println("------------------------------------------------------------");
		System.out.println("1 - Afficher la liste des formations");
		System.out.println("2 - Ajouter une formation au panier");
		System.out.println("3 - Afficher votre panier");
		System.out.println("4 - Supprimer une formation du panier");
		System.out.println("5 - Passer commande");
		System.out.println("6 - Quitter l'application");
	}

	/**
	 * Cette méthode permet d'afficher la liste des formations
	 * 
	 * @param training - Liste des formations proposées par l'application
	 */
	public static void displayTraining(HashMap<Integer, ArrayList<String>> training) {
		String format = "%-2s | %-15s | %-8s | %-35s |%-10s |%-10s";
		System.out.println("***LISTE DE NOS FORMATIONS***");
		String hr = "------------------------------------------------------------------------------------------------";
		System.out.println(hr);
		System.out.format(format, "ID", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX", "EN STOCK");
		System.out.println();
		System.out.println(hr);

		for (HashMap.Entry<Integer, ArrayList<String>> tr : training.entrySet()) {
			System.out.format(format, tr.getKey(), tr.getValue().get(0), tr.getValue().get(1), tr.getValue().get(2),
					tr.getValue().get(3), tr.getValue().get(4));
			System.out.println();
		}
		System.out.println(hr);
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
		while (str.equalsIgnoreCase("oui") || str.equalsIgnoreCase("o")) {
			System.out.println("Quelle formation souhaitez-vous acheter ? [Saisir l'ID de la formation]");
			while (scanner.hasNextInt() == false) // ignore la saisie tant qu'il ne s'agit pas d'un entier
				scanner.next();
			int id = scanner.nextInt();// récup le choix de l'user
			if (training.get(id) != null && training.get(id).get(4).equalsIgnoreCase("disponible")) {
				// si formation existe et qu'elle est dispo : ajout au panier
				int qty = 0; // qté de base
				if (cart.containsKey(id)) {
					qty = cart.get(id) + 1;
				} else {
					qty += 1;
				}
				cart.put(id, qty);
				System.out.println("La formation " + training.get(id).get(0) + " a bien été ajouté au panier.");
			} else {
				System.out.println("Cette formation n'existe pas dans la liste et/ou sera disponible prochainement.");
			}
			System.out.println("Souhaitez-vous ajouter une autre formation ? [Oui/Non]");
			str = scanner.next();
		}
		System.out.println();
	}

	/**
	 * Permet de supprimer une formation du panier
	 * 
	 * @param training - Liste des formations proposées
	 * @param cart     - Panier à remplir (ID de la formation / Quantité)
	 */
	public static void delete(HashMap<Integer, ArrayList<String>> training, HashMap<Integer, Integer> cart) {
		if (!cart.isEmpty()) {
			System.out.println("Quelle formation souhaitez-vous supprimer du panier ? [Saisir l'ID de la formation]");
			displayCart(training, cart);
			while (scanner.hasNextInt() == false) // ignore la saisie tant qu'il ne s'agit pas d'un entier
				scanner.next();
			int id = scanner.nextInt();// récup le choix de l'user
			if (cart.get(id) != null) { // si formation existe
				cart.put(id, cart.get(id) - 1); // supp une formation
				if (cart.get(id) == 0) { // si qté est à 0, supp du panier
					cart.remove(id);
				}
				System.out.println("La formation a bien été supprimée.");
			} else {
				System.out.println("Cette formation ne figure pas dans votre panier.");
			}
		} else {
			System.out.println("Votre panier est vide.");
		}
	}

	/**
	 * Affiche le contenu du panier
	 * 
	 * @param training - Liste des formations proposées
	 * @param cart     - Panier à remplir (ID de la formation / Quantité)
	 */
	public static void displayCart(HashMap<Integer, ArrayList<String>> training, HashMap<Integer, Integer> cart) {

		if (!cart.isEmpty()) {
			String format = "%-2s | %-15s | %-8s | %-35s |%-8s |%-8s";
			String hr = "-------------------------------------------------------------------------------------------";
			System.out.println("***VOTRE PANIER***");
			System.out.println(hr);
			System.out.format(format, "ID", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX", "QUANTITE");
			System.out.println();
			System.out.println(hr);
			for (HashMap.Entry<Integer, ArrayList<String>> tr : training.entrySet()) {
				for (HashMap.Entry<Integer, Integer> c : cart.entrySet()) {
					if (tr.getKey() == c.getKey()) {
						System.out.format(format, tr.getKey(), tr.getValue().get(0), tr.getValue().get(1),
								tr.getValue().get(2), tr.getValue().get(3), c.getValue());
						System.out.println();
					}
				}
			}
			System.out.println(hr);
		} else {
			System.out.println("Votre panier est vide pour l'instant.");
		}
	}

	/**
	 * Affiche le contenu du panier ainsi que son prix total au moment de passer
	 * commande
	 * 
	 * @param training - Liste des formations proposées
	 * @param cart     - Panier à remplir (ID de la formation / Quantité)
	 */
	public static void commande(HashMap<Integer, ArrayList<String>> training, HashMap<Integer, Integer> cart) {
		System.out.println();
		System.out.println("****************VOTRE COMMANADE****************");
		String format = "%-2s | %-15s | %-8s | %-35s |%-8s |%-8s";
		String hr = "-------------------------------------------------------------------------------------------";
		System.out.println(hr);
		System.out.format(format, "ID", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX", "QUANTITE");
		System.out.println();
		System.out.println(hr);
		int total = 0;
		for (HashMap.Entry<Integer, ArrayList<String>> tr : training.entrySet()) {
			for (HashMap.Entry<Integer, Integer> c : cart.entrySet()) {
				if (tr.getKey() == c.getKey()) {
					System.out.format(format, tr.getKey(), tr.getValue().get(0), tr.getValue().get(1),
							tr.getValue().get(2), tr.getValue().get(3), c.getValue());
					System.out.println();
					int price = (Integer.parseInt(tr.getValue().get(3)));
					int qty = c.getValue();
					total += price * qty;
				}
			}
		}
		System.out.println(hr);
		System.out.format("TOTAL DU PANIER DE VOTRE PANIER : " + total + " € \n");
	}

	/**
	 * Demande à l'utilisateur s'il souhaite valider son panier ou non
	 * @param cart     - Panier à remplir (ID de la formation / Quantité)
	 */
	public static void validation(HashMap<Integer, Integer> cart) {
		String str;

		do {
			System.out.println("Souhaitez-vous valider votre panier ? [Oui/Non]");
			str = scanner.next();
		} while (!str.equalsIgnoreCase("oui") && !str.equalsIgnoreCase("non"));
		if (str.equalsIgnoreCase("oui") || str.equalsIgnoreCase("o")) {
			System.out.println("Votre commande a bien été validé.");
			cart.clear();
		} else {
			System.out.println("Opération annulée.");
		}
	}

}