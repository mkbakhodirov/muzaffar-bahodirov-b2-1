package uz.pdp.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.User;

import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository implements BaseRepository<User> {

    @Override
    public boolean add(User user) {
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false; // username is not unique
        }
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public List<User> getList() {
        return null;
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void deleteById(Integer id) {
    }

    public User login(User user) {
        Session session = getSession();
//                "password = " + user.getPassword());
        Query<User> query = session.createQuery("select u from User u where username = :username and password = :password", User.class);
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());
        List<User> list = query.list();
        if (list == null)
            return null;
        return list.get(0);
    }
}
