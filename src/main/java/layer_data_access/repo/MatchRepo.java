package layer_data_access.repo;

import config.HibernateConfig;
import model.TennisMatch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MatchRepo {
    private final static Session session = HibernateConfig.Instance();

    public static TennisMatch findMatchByID(int id) {
        Transaction transaction = session.beginTransaction();
        Query<TennisMatch> query = session.createQuery("from TennisMatch u where u.id=:id", TennisMatch.class);
        query.setParameter("id", id);
        TennisMatch match = query.uniqueResult();
        transaction.commit();

        return match;
    }
}
