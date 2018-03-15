import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@SequenceGenerator(name = "seq_resa", sequenceName = "seq_resa", initialValue = 1, allocationSize = 1)
@Table
public class Reservation {
	
	@Id
	@GeneratedValue(generator = "seq_resa")
	private Integer id;

	@Column
	private String numReservation;
	
	@Column
	private String nom;
	
	@Column
	private String prenom;
	
	@Column
	private LocalDate age;
	
	@ManyToOne
	@JoinColumn(name = "idVol")
	private Vol vol;
	
	public Reservation() {}
	public Reservation(Vol v,String nom, String prenom, LocalDate age) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.vol = v;
	}
	public Integer getId() {
		return id;
	}
	
	public void setNumReservation(String numReservation) {
		this.numReservation = numReservation;
	}
	
	public String getNumReservation() {
		return numReservation;
	}
}
