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
		HashMap<Integer, ArrayList<String>> cart = new HashMap<Integer, ArrayList<String>>(); // panier de l'utilisateur

		boolean displayChoice = false;
		int choiceMenu;

		do {
			displayMenu(); // affiche le menu principal

			choiceMenu = scanner.nextInt(); // récup le choix du menu

			switch (choiceMenu) {
			case 1:
				displayTraining(training);
				displayChoice = true;
				break;
			case 2:
				commande(training, cart);
				System.out.println(cart);
				displayChoice = true;
				break;
			case 3:
				System.out.println("3 - Afficher votre panier");
				displayCart(cart);
				displayChoice = true;
				break;
			case 4:
				System.out.println("4 - Quitter l'application");
				displayChoice = true;
				break;
//			case 5:
//				System.out.println("5 - Supprimer pour tester");
//				int id = scanner.nextInt();
//				if (training.get(id) != null) { // si formation existe
//					cart.remove(id); // ajout au panier
//				}
//				displayChoice = true;
//				break;
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
		System.out.println("4 - Quitter l'application");
		System.out.println("5 - Supprimer pour tester");
	}

	/**
	 * Permet d'ajouter une formation au panier de l'utilisateur
	 * 
	 * @param training - Liste des formations proposées
	 * @param cart     - Panier à remplir
	 */
	public static void commande(HashMap<Integer, ArrayList<String>> training,
			HashMap<Integer, ArrayList<String>> cart) {
		displayTraining(training);
		System.out.println("Souhaitez-vous ajouter une formation à votre panier ? [Oui/Non]");
		String str = scanner.next();
//		int key = 1;
		while(str.equalsIgnoreCase("oui") || str.equalsIgnoreCase("o")) {
			System.out.println("Quelle formation souhaitez-vous acheter ? [Saisir l'ID de la formation]");
			int id = scanner.nextInt();// récup le choix de l'user
			int key = cart.size() + 1; // permet de créer un ID unique pour chaque commande
			if (training.get(id) != null) { // si formation existe
				cart.put(key, training.get(id)); // ajout au panier
			}
			System.out.println("Souhaitez-vous ajouter une autre formation ? [Oui/Non]");
			str = scanner.next();
		}
	}

	public static void displayCart(HashMap<Integer, ArrayList<String>> cart) {
		String format = "%-2s | %-15s | %-8s | %-35s |%-10s";
		System.out.println("Contenu actuel de votre panier");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");
		System.out.format(format, "ID", "COURS", "NB/JOURS", "DESCRIPTION", "PRIX");
		System.out.println();
		System.out.println("----------------------------------------------------------------------------");

		for (int i = 1; i < cart.size() + 1; i++) {
			System.out.format(format, i, cart.get(i).get(0), cart.get(i).get(1), cart.get(i).get(2),
					cart.get(i).get(3));
			System.out.println();
		}
		System.out.println("----------------------------------------------------------------------------");
	}

}