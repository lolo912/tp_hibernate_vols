import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@SequenceGenerator(name = "seq_vol", sequenceName = "seq_vol", initialValue = 1, allocationSize = 1)
@Table
public class Vol {
	
	@Id
	@GeneratedValue(generator = "seq_vol")
	private Integer id;
	
	@Column
	private String numVol;

	@Column
	@Enumerated(EnumType.STRING)
	private TypeAvion typeAvion;
	
	@Column
	private Integer nbPlaces;
	
	@Column
	private String villeArrivee;
	
	@Column
	private String villeDepart;
	
	@Column
	private LocalDate dateDepart;
	
	@OneToMany
	@JoinColumn(name = "id")
	private List<Reservation> reservations;
	
	public Vol() {}
	public Vol(TypeAvion typeAvion, int nbPlaces, String villeDepart, String villeArrivee, LocalDate dateDepart) {
		this.typeAvion = typeAvion;
		this.nbPlaces = nbPlaces;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.dateDepart = dateDepart;
	}
	
	public static void listeVols() {
		ArrayList<Vol> vols = new DaoVol().findAll();
	    System.out.println("Numéro \t | \t Type | Place | Départ | Arrivée | Date");
		for(Vol v : vols) {
			System.out.println(v.getNumVol()+" "+v.getTypeAvion() + " " + v.getNbPlaces() + " " + v.getVilleDepart() + " " + v.getVilleArrivee() + " " + v.getDateDepart());
		}
	}
	
	public void affiche() {
		System.out.println(formatage(this));
	}
	
	public static void affiche(ArrayList<Vol> vols) {
	    System.out.println("Numéro \t | \t Type | Place | Départ | Arrivée | Date");
		for(Vol v : vols) {
			System.out.println(formatage(v));
		}
	}
	
	public static String formatage(Vol v) {
		int nbResa = v.getReservations().size();
		String chaine = v.getNumVol()+" | "+v.getTypeAvion() + " | " + nbResa+"/"+v.getNbPlaces() + " | " + v.getVilleDepart() + " | " + v.getVilleArrivee() + " | " + v.getDateDepart();
		return chaine;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumVol() {
		return numVol;
	}

	public void setNumVol(String numVol) {
		this.numVol = numVol;
	}

	public TypeAvion getTypeAvion() {
		return typeAvion;
	}

	public void setTypeAvion(TypeAvion typeAvion) {
		this.typeAvion = typeAvion;
	}

	public Integer getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(Integer nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public LocalDate getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = dateDepart;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	public static LocalDate stringToDate(String date){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dtf = dtf.withLocale(Locale.ENGLISH);
		LocalDate dateLocal = LocalDate.parse(date,dtf);		
		return dateLocal;
	}
	
	public static String completer(String n){
	   StringBuilder sb = new StringBuilder();
	   for(int i=0; i<7-n.length(); i++){
	      sb.append("0");
	   }
	   sb.append(n);
	   return sb.toString();
	}
	@Override
	public String toString() {
		return "Vol [id=" + id + ", numVol=" + numVol + ", typeAvion=" + typeAvion + ", nbPlaces=" + nbPlaces
				+ ", villeArrivee=" + villeArrivee + ", villeDepart=" + villeDepart + ", dateDepart=" + dateDepart
				+ "]";
	}

}