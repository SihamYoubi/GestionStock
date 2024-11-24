import java.util.Scanner;

public class GestionStock {
    static int[] codesProduits = new int[100];
    static String[] nomsProduits = new String[100];
    static int[] quantites = new int[100];
    static double[] prix = new double[100];
    static int nombreProduits = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    ajouterProduit(scanner);
                    break;
                case 2:
                    modifierProduit(scanner);
                    break;
                case 3:
                    supprimerProduit(scanner);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    rechercherProduit(scanner);
                    break;
                case 6:
                    calculerValeurStock();
                    break;
                case 0:
                    System.out.println("Fin du programme.");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);

        scanner.close();
    }

    public static void printMenu() {
        System.out.println("\nMenu de Gestion de Stock");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit par son nom");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    public static void ajouterProduit(Scanner scanner) {
        if (nombreProduits >= 100) {
            System.out.println("Le stock est plein !");
            return;
        }
        System.out.print("Entrer le code du produit : ");
        int code = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Entrer le nom du produit : ");
        String nom = scanner.nextLine();
        System.out.print("Entrer la quantité : ");
        int quantite = scanner.nextInt();
        System.out.print("Entrer le prix du produit : ");
        double prix_produit = scanner.nextDouble();

        codesProduits[nombreProduits] = code;
        nomsProduits[nombreProduits] = nom;
        quantites[nombreProduits] = quantite;
        prix[nombreProduits] = prix_produit;
        nombreProduits++;

        System.out.println("Produit ajouté avec succès !");
    }

    public static void modifierProduit(Scanner scanner) {
        System.out.print("Entrer le code du produit à modifier : ");
        int code_produit = scanner.nextInt();
        scanner.nextLine();

        int index = IndexProduit(code_produit);
        if (index == -1) {
            System.out.println("Produit non trouvé !");
            return;
        }

        System.out.print("Modifier le nom : ");
        String nouveauNom = scanner.nextLine();
        System.out.print("Modifier la quantité : ");
        int nouvelleQuantite = scanner.nextInt();
        System.out.print("Modifier le prix : ");
        double nouveauPrix = scanner.nextDouble();

        nomsProduits[index] = nouveauNom;
        quantites[index] = nouvelleQuantite;
        prix[index] = nouveauPrix;

        System.out.println("Produit modifié avec succès !");
    }

    public static void supprimerProduit(Scanner scanner) {
        System.out.print("Enter le code du produit à supprimer : ");
        int code_produit = scanner.nextInt();

        int index = IndexProduit(code_produit);
        if (index == -1) {
            System.out.println("Produit non trouvé !");
            return;
        }

        for (int i = index; i < nombreProduits - 1; i++) {
            codesProduits[i] = codesProduits[i + 1];
            nomsProduits[i] = nomsProduits[i + 1];
            quantites[i] = quantites[i + 1];
            prix[i] = prix[i + 1];
        }
        nombreProduits--;
        System.out.println("Produit supprimé avec succès !");
    }

    public static void afficherProduits() {
        if (nombreProduits == 0) {
            System.out.println("Aucun produit en stock !");
            return;
        }

        System.out.println("\n--- Liste des Produits ---");
        for (int i = 0; i < nombreProduits; i++) {
            System.out.println("Code : " + codesProduits[i] +
                    ", Nom : " + nomsProduits[i] +
                    ", Quantité : " + quantites[i] +
                    ", Prix : " + prix[i]);
        }
    }

    public static void rechercherProduit(Scanner scanner) {
        System.out.print("Nom du produit à rechercher : ");
        String nom = scanner.nextLine();

        boolean trouve = false;
        for (int i = 0; i < nombreProduits; i++) {
            if (nomsProduits[i].equalsIgnoreCase(nom)) {
                System.out.println("Code : " + codesProduits[i] +
                        ", Nom : " + nomsProduits[i] +
                        ", Quantité : " + quantites[i] +
                        ", Prix : " + prix[i]);
                trouve = true;
                break;
            }
        }
        if (!trouve) {
            System.out.println("Produit non trouvé !");
        }
    }

    public static void calculerValeurStock() {
        double valeurTotale = 0;
        for (int i = 0; i < nombreProduits; i++) {
            valeurTotale += quantites[i] * prix[i];
        }
        System.out.println("Valeur totale du stock : " + valeurTotale);
    }


    public static int IndexProduit(int code_produit) {
        for (int i = 0; i < nombreProduits; i++) {
            if (codesProduits[i] == code_produit) {
                return i;
            }
        }
        return -1;
    }
}
