import javax.persistence.EntityManager;

public class Dao<T> {
	
	public T insert(T o) {
		EntityManager em = DatabaseHelper.createEntityManager();
		em.getTransaction().begin();
		em.persist(o);
		DatabaseHelper.commitTxAndClose(em);
		return o;
	}
	
	public void update(T o) {
		EntityManager em = DatabaseHelper.createEntityManager();
		em.getTransaction().begin();
		em.merge(o);
		DatabaseHelper.commitTxAndClose(em);
	}

}
