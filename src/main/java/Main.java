import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import static java.lang.System.out;

import java.text.ParseException;
import java.time.LocalDate;

public class Main {
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
	
		Dao dao = new Dao();
		DaoVol daoVol = new DaoVol();
		DaoResa daoResa = new DaoResa();
		
		System.out.println("1) Gestion des vols");
		System.out.println("2) Gestion des r�servations");
		System.out.println("3) Quitter");
		System.out.println("Entrez votre choix :");
		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();
		
		if(choix==1) {
			System.out.println("1) Cr�ation d'un vol");
			System.out.println("2) Liste des vols");
			System.out.println("3) Rechercher un vol par num�ro");
			System.out.println("4) Rechercher un vol par ville de d�part et d'arriv�e");
			System.out.println("Entrez votre choix :");
			int choix1 = sc.nextInt();
			switch(choix1){
				case 1 :
					{
						System.out.println("Entrez le type d'avion:");
						sc = new Scanner(System.in);
						String type = sc.nextLine();
						TypeAvion typeAvion = TypeAvion.valueOf(TypeAvion.class, type);
						System.out.println("Entrez le nombre de places");
						sc = new Scanner(System.in);
						int nbPlaces = sc.nextInt();
						System.out.println("Entrez la ville de d�part");
						sc = new Scanner(System.in);
						String villeDepart = sc.nextLine();
						System.out.println("Entrez la ville d'arriv�e");
						sc = new Scanner(System.in);
						String villeArrivee = sc.nextLine();
						System.out.println("Entrez la date de d�part");
						LocalDate dateDepart = Vol.stringToDate(sc.nextLine());
						Vol v = daoVol.insert(new Vol(typeAvion, nbPlaces, villeDepart, villeArrivee, dateDepart));
						v.setNumVol(Vol.completer(v.getId().toString()));
						daoVol.update(v);
					}
					break;
				case 2 : 
					{
						System.out.println("Liste des vols de la base :");
						Vol.affiche(daoVol.findAll());
					}
					break;
				case 3 : 
					{
						System.out.println("Entrez un num�ro de vol :");
						sc = new Scanner(System.in);
						String numVol = sc.nextLine();
						System.out.println("Caract�ristiques du vol n� "+numVol +" :" );
						System.out.println("Num�ro \t | \t Type | Place | D�part | Arriv�e | Date");
						daoVol.findNum(numVol).affiche();
					}
					break;
				case 4 : 
					{
						System.out.println("Entrez une ville de d�part :");
						sc = new Scanner(System.in);
						String villeDepart = sc.nextLine();
						System.out.println("Entrez une ville d'arriv�e :");
						sc = new Scanner(System.in);
						String villeArrivee = sc.nextLine();
						System.out.println("Liste des vol de "+villeDepart + " � " + villeArrivee +" :");
						System.out.println("Num�ro \t | \t Type | Place | D�part | Arriv�e | Date");
						for(Vol v : daoVol.findVilles(villeDepart,villeArrivee)) {
							v.affiche();
						}
					}
					break;
				default : break;
			}
		}
		
		else if(choix==2) {
			System.out.println("1) Cr�er une r�servation");
			System.out.println("2) Voir les r�servations d'un vol");
			System.out.println("3) Annuler une r�servation");
			System.out.println("4) Voir les r�servations d'une personne");
			System.out.println("Entrez votre choix :");
			sc = new Scanner(System.in);
			int choix2 = sc.nextInt();
			switch(choix2){
				case 1 : 
					{
						System.out.println("Entrez votre nom");
						sc = new Scanner(System.in);
						String nom = sc.nextLine();
						System.out.println("Entrez votre pr�nom");
						sc = new Scanner(System.in);
						String prenom = sc.nextLine();
						System.out.println("Entrez votre age");
						sc = new Scanner(System.in);
						LocalDate age = Vol.stringToDate(sc.nextLine());
						System.out.println("Voici la liste des vols disponibles : ");
						Vol.affiche(daoVol.findAll());
						System.out.println("Entrez le num�ro de vol pour le quel vous souhaitez r�server votre billet :");
						sc = new Scanner(System.in);
						String numVol = sc.nextLine();
						Vol v = daoVol.findNum(numVol);
						Reservation r = (Reservation) daoResa.insert(new Reservation(v,nom,prenom,age));
						r.setNumReservation(v.getNumVol()+"-"+r.getId());
						daoResa.update(r);
					}
					break;
				case 2 : 
					{
						System.out.println("Entrez un num�ro de vol pour le  quel vous souhaitez voir les r�servations :");
						sc = new Scanner(System.in);
						String numVol = sc.nextLine();
						System.out.println("Voici la liste des R�servations correspondants au vol n� "+numVol+" :");
						for(Reservation r : daoResa.findNumVol(numVol)) {
							System.out.println(r.toString());
						}
					}
					break;
				case 3 : 
					{
						System.out.println("Vous souhaitez annuler une r�servation : Entrez tout d'abord le num�ro de r�servation � annuler : ");
						sc = new Scanner(System.in);
						String numResa = sc.nextLine();
						daoResa.delete(daoResa.findNumResa(numResa));
					}
					break;
				case 4 :
					{
						
						
					}
				break;
				default : break;
			}
		}
		
		else {System.out.println("Fin du programme");}
	}
}
