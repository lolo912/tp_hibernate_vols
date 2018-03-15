import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

public class DaoResa extends Dao{
	
	public ArrayList<Reservation> findAll() {
		EntityManager em = DatabaseHelper.createEntityManager();
		Query q = em.createQuery("select r from Reservation r");
	    ArrayList<Reservation> resas =  new ArrayList();
	    resas.addAll(q.getResultList());
	    return resas;
	}
	
	public Reservation findNumResa(String numResa) {
		EntityManager em = DatabaseHelper.createEntityManager();
		Query q = em.createQuery("select r from Reservation r where numreservation = :numResa");
		q.setParameter("numResa", numResa);
		Reservation r = (Reservation) q.getSingleResult();;
	    return r;
	}
	
	public ArrayList<Reservation> findNumVol(String numVol) {
		EntityManager em = DatabaseHelper.createEntityManager();
		Query q = em.createQuery("select r from Reservation r left join r.vol v where numvol = :numVol");
		q.setParameter("numVol", numVol);
		ArrayList<Reservation> r = new ArrayList<Reservation>();
		r.addAll(q.getResultList());
	    return r;
	}
	
	public void delete(Reservation r) {
		EntityManager em = DatabaseHelper.createEntityManager();
		em.remove(r);
	}
}
