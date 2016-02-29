package test;

import com.google.common.base.Stopwatch;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.concurrent.TimeUnit;

public class InsertTest {
	public static final String OneId = "theOneAndOnly";
	static Stopwatch sw = Stopwatch.createUnstarted();

	public static void main(String[] args) {
		while(true) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("test-pu");
			EntityManager em = emf.createEntityManager();

			for (int i = 0; i < 100; i++) {
				sw.reset();
				sw.start();
				persistMVs(emf, em);
				System.err.println("Elapsed: " + sw.elapsed(TimeUnit.MILLISECONDS) + " ms");
			}

			em.close();
			emf.close();
		}

	}

	private static void persistMVs(EntityManagerFactory emf, EntityManager em) {
		em.getTransaction().begin();
		One one = getOrCreateOne(em);

		for (int i = 0; i < 200; i++) {
			Many many = new Many();
			many.setOne(one);
			em.persist(many);
		}
		em.getTransaction().commit();
	}

	private static One getOrCreateOne(EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<One> q = cb.createQuery(One.class);
		Root<One> c = q.from(One.class);
		q.select(c).where(cb.equal(c.get("name"), OneId));

		TypedQuery<One> query = em.createQuery(q);
		One m = null;

		try {
			m = query.getSingleResult();
		} catch (Exception e){}

		if (m == null) {
			One entity = new One();
			entity.setName(OneId);
			return em.merge(entity);
		} else {
			return m;
		}
	}
}
