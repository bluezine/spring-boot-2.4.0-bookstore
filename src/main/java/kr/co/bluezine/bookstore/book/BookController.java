package kr.co.bluezine.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Book Controller
 * 
 * @author Kisig Ian Seo
 *
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public void list() {

    }

    @RequestMapping(value = "read/{id}", method = RequestMethod.GET)
    public Book read(@PathVariable Long id) {
	return bookService.load(id);
    }
}
