package kr.co.bluezine.bookstore.book;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.bluezine.bookstore.sql.PageEntity;

/**
 * Book Controller
 * 
 * @author Kisig Ian Seo
 *
 */
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Get Book List
     * 
     * @param page
     * @param count
     * @param search
     * @return
     * @throws NumberFormatException
     * @throws SQLException
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public PageEntity list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int count,
	    @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "id") String sort)
	    throws NumberFormatException, SQLException {
	return bookService.list(page, count, search, sort);
    }

    /**
     * Read Book
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "read/{id}", method = RequestMethod.GET)
    public Book read(@PathVariable Long id) {
	return bookService.load(id);
    }

    /**
     * Insert Book
     * 
     * @param item
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Book insert(@RequestBody Book item) {
	return bookService.save(item);
    }

    /**
     * Update Book
     * 
     * @param item
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Book update(@RequestBody Book item) {
	return bookService.save(item);
    }
}
