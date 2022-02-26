package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Book;
import uz.pdp.repository.BookRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Book book, Model model) {
        boolean res = bookRepository.add(book);
        if (!res)
            model.addAttribute("res", book.getName() + " already exists");
        List<Book> books = bookRepository.getList();
        model.addAttribute("books", books);
        return "admin-cabinet";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model) {
        List<Book> books = bookRepository.getList();
        model.addAttribute("books", books);
        return "admin-cabinet";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get(HttpServletRequest request, Model model) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Book book = bookRepository.getById(id);
        model.addAttribute("book", book);
        return "admin-cabinet";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, Model model) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        bookRepository.deleteById(id);
        List<Book> books = bookRepository.getList();
        model.addAttribute("books", books);
        return "admin-cabinet";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@ModelAttribute Book book, Model model) {
        bookRepository.update(book);
        List<Book> books = bookRepository.getList();
        model.addAttribute("books", books);
        return "admin-cabinet";
    }

}
