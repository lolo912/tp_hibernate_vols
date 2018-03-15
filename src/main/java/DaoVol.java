import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DaoVol extends Dao<Vol>{
	
	public ArrayList<Vol> findAll() {
		EntityManager em = DatabaseHelper.createEntityManager();
		Query q = em.createQuery("select v from Vol v");
	    ArrayList<Vol> vols =  new ArrayList();
	    vols.addAll(q.getResultList());
	    return vols;
	}
	
	public Vol findNum(String numVol) {
		EntityManager em = DatabaseHelper.createEntityManager();
		Query q = em.createQuery("select v from Vol v where numvol = :numVol");
		q.setParameter("numVol", numVol);
		Vol v = (Vol)q.getSingleResult();
	    return v;
	}
	
	public List<Vol> findVilles(String villeDepart, String villeArrivee) {
		EntityManager em = DatabaseHelper.createEntityManager();
		Query q = em.createQuery("select v from Vol v where villearrivee = :villeArrivee and villedepart= :villeDepart");
		q.setParameter("villeArrivee", villeArrivee);
		q.setParameter("villeDepart", villeDepart);
	    return q.getResultList();
	}
}
