package uz.pdp.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Book;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements BaseRepository<Book> {
    @Override
    public boolean add(Book book) {
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false; // name is not unique
        }
    }

    @Override
    public Book getById(Integer id) {
        Session session = getSession();
        Query<Book> query = session.createQuery("select b from Book b where id = :id", Book.class);
        query.setParameter("id", id);
        List<Book> list = query.list();
        if (list == null)
            return null;
        return list.get(0);
    }


    @Override
    public void update(Book book) {
        Session session = getSession();
        Query query = session.createQuery("update Book set name = :name, author = :author, " +
                "price = :price, update_time = :time where id = :id";
        query.setParameter("name", book.getName());
        query.setParameter("author", book.getAuthor());
        query.setParameter("price", book.getPrice());
        query.setParameter("time", LocalDateTime.now());
        query.setParameter("id", book.getId());
        query.executeUpdate();
        session.close();
    }

    @Override
    public void deleteById(Integer id) {
        Session session = getSession();
        Query query = session.createQuery("update Book set is_active = false where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Book> getList() {
        List<Book> books = new ArrayList<>();
        Session session = getSession();
        Query<Book> query = session.createQuery("select b from Book b where is_active = true");
        return query.list();
    }
}
