package layer_data_access.repo;

import config.HibernateConfig;
import javafx.css.Match;
import model.TennisMatch;
import model.TennisSet;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepo {

    private final static Session session = HibernateConfig.Instance();

    public static User findUserByMail(String mail) {

        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.mail=:mail", User.class);
        query.setParameter("mail", mail);
        User user = query.uniqueResult();
        transaction.commit();

        return user;
    }

    public static User findUserByID(int id) {
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User u where u.id=:id", User.class);
        query.setParameter("id", id);
        User user = query.uniqueResult();
        transaction.commit();

        return user;
    }

    public static List<TennisMatch> readMatches() {
        Transaction transaction = session.beginTransaction();
        Query<TennisMatch> matches = session.createQuery("from TennisMatch", TennisMatch.class);
        transaction.commit();

        return matches.list();
    }

    public static List<TennisSet> readSets(int id) {
        Transaction transaction = session.beginTransaction();
        Query<TennisSet> sets = session.createQuery("from TennisSet s where s.tennisMatch.id = :id", TennisSet.class);
        sets.setParameter("id", id);
        transaction.commit();

        return sets.list();
    }
}
