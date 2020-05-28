package layer_data_access.repo;

import config.HibernateConfig;
import javafx.collections.FXCollections;
import model.Admin;
import model.TennisMatch;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;


public class AdminRepo {
    private final static Session session = HibernateConfig.Instance();

    public static Admin findAdminByMail(String mail) {

        Transaction transaction = session.beginTransaction();
        Query<Admin> query = session.createQuery("from Admin a where a.mail=:mail", Admin.class);
        query.setParameter("mail", mail);
        Admin admin = query.uniqueResult();
        transaction.commit();

        return admin;
    }

    public static List<User> readUsers() {
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("from User", User.class);
        List<User> users = query.list();
        transaction.commit();

        return users;
    }

    public static void createNewUser(User newUser) {
            GenericRepo.save(newUser);
    }

    public static void updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createSQLQuery("UPDATE `warehouse`.`user` SET `name` = '" +
                user.getName() + "', `password` = '" + user.getPassword() + "', `mail` = '" + user.getMail() +
                "' WHERE (`id` = '" + user.getId() + "')");
        query.executeUpdate();
        transaction.commit();
    }

    public static void deleteUser(int id){
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DELETE FROM `warehouse`.`user` WHERE (`id` = '" + id + "')");
        query.executeUpdate();
        transaction.commit();
    }

    public static List<TennisMatch> readMatches() {

        Transaction transaction = session.beginTransaction();
        Query<TennisMatch> query = session.createQuery("from TennisMatch", TennisMatch.class);
        List<TennisMatch> matches = query.list();
        transaction.commit();

        return matches;
    }

    public static void createTennisMatch(TennisMatch match) {
        GenericRepo.save(match);
    }

    public static void updateTennisMatch(TennisMatch match){
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createSQLQuery("UPDATE `warehouse`.`tennis_match` SET `player1_id` = '" +
                match.getPlayer1().getId() + "', `player2_id` = '" + match.getPlayer2().getId() +
                "' WHERE (`id` = '" + match.getId() + "')");
        query.executeUpdate();
        transaction.commit();
    }

    public static  void deleteTennisMatch(int id){
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DELETE FROM `warehouse`.`tennis_match` WHERE (`id` = '" + id + "')");
        query.executeUpdate();
        transaction.commit();
    }
}
