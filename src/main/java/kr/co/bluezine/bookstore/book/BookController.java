package kr.co.bluezine.bookstore.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Book> list() {
	return jdbcTemplate.query("select id, title from book", new BeanPropertyRowMapper<Book>(Book.class));
    }

    @RequestMapping(value = "read/{id}", method = RequestMethod.GET)
    public Book read(@PathVariable Long id) {
	return bookService.load(id);
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Book insert(@RequestBody Book item) {
	return bookService.save(item);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Book update(@RequestBody Book item) {
	return bookService.save(item);
    }
}
