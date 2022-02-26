package uz.pdp.repository;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import uz.pdp.entity.Book;
import uz.pdp.entity.User;

import java.util.List;

public interface BaseRepository<T> {

    boolean add(T t);
    T getById(Integer id);
    List<T> getList();
    void update(T t);
    void deleteById(Integer id);

    default Session getSession() {
        return new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory().openSession();
    }
}
